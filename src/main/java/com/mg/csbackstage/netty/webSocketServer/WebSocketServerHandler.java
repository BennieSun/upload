package com.mg.csbackstage.netty.webSocketServer;

import com.mg.csbackstage.netty.webSocketServer.interfaces.IWebSocketChannelCallBack;
import com.mg.util.core.GlobalHelper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpMethod.GET;
import static io.netty.handler.codec.http.HttpMethod.POST;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @description:消息业务处理类 Created by BennieSun on 2017/2/17.
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> { // (1)

    private static final Logger logger = Logger.getLogger(WebSocketServerHandler.class);

    private WebSocketServerHandshaker handshaker;

    private static final String WEBSOCKET_PATH = "/websocket";

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    // 在线用户状态map,由ip：在线状态组成
    //private static final Map<String, String> onlineMap = new HashMap<String, String>();

    // 在线用户channel map,由ip：channel组成
    //private static final Map<String, Channel> onlineUserChannelMap = new HashMap<String, Channel>();

    private final IWebSocketChannelCallBack callBack;

    public WebSocketServerHandler(IWebSocketChannelCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        logger.info("handlerAdded "+incoming.id());
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress()+":"+incoming.id()+ " 加入\n");
        }
        channels.add(ctx.channel());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        logger.info("channelReadComplete "+ctx.channel().id());
        ctx.flush();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        logger.info("handlerRemoved "+ctx.channel().id());
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress()
                    + " 离开\n");
        }
        channels.remove(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("channelInactive "+ctx.channel().id());
        this.callBack.chanelClose(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.info("exceptionCaught "+ctx.channel().id());
        this.callBack.channelException(ctx, cause);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        logger.info("channelRead0 "+ctx.channel().id());
        if (msg instanceof FullHttpRequest) {// http请求
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame){// websocket请求
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    /**
     * @param req：http请求对象
     * @return String ：返回地址字符串
     * @throws
     * @function: 获得本地webscoket地址
     * @author:肖震
     * @since 1.0.0
     */
    private static String getWebSocketLocation(FullHttpRequest req) {
        String location = req.headers().get(HOST) + WEBSOCKET_PATH;

        return "ws://" + location;
    }

    /**
     * @param ctx
     * @param frame
     * @throws
     * @function: 处理websocket请求业务
     * @author:肖震
     * @since 1.0.0
     */
    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {

        // 是否关闭
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 是否ping
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 非文本
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(String.format(
                    "%s frame types not supported", frame.getClass().getName()));
        }

        // 接收到的消息
        String params = ((TextWebSocketFrame) frame).text();

        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("params", params);
        paramsMap.put("sendMessageTime", GlobalHelper.currentTimestamp()+"");
        this.callBack.channelData(paramsMap,ctx);
    }

    /**
     * @param ctx
     * @param req
     * @throws Exception
     * @throws
     * @function: http请求处理
     * @author:肖震
     * @since 1.0.0
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        logger.info("handleHttpRequest method=" + req.method()+",uri="+req.uri());

        if (req.method() == GET) {
            httpGetDeal(ctx, req);
        }

        if (req.method() == POST) {
            httpPostDeal(ctx, req);
        }
    }

    /**
     * @param ctx
     * @param req
     * @throws IOException
     * @throws
     * @function: http post方式处理
     * @author:肖震
     * @since 1.0.0
     */
    private void httpPostDeal(ChannelHandlerContext ctx, FullHttpRequest req)
            throws IOException {
        // 处理POST请求
        HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(
                new DefaultHttpDataFactory(false), req);

        InterfaceHttpData postData = decoder.getBodyHttpData("q"); // //
        // 读取从客户端传过来的参数
        String question = "";
        if (postData.getHttpDataType() == HttpDataType.Attribute) {
            Attribute attribute = (Attribute) postData;
            question = attribute.getValue();
            System.out.println("q:" + question);

        }
        if (question != null && !question.equals("")) {
            String data = question;
            FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, OK,
                    Unpooled.wrappedBuffer(data.getBytes(CharsetUtil.UTF_8)));

            res.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
            res.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            res.headers().set(CONTENT_LENGTH, res.content().readableBytes());
            sendHttpResponse(ctx, req, res);

        }
        return;
    }

    /**
     * @param ctx
     * @param req void
     * @throws
     * @function: http get方式处理
     * @author:肖震
     * @since 1.0.0
     */
    private void httpGetDeal(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception{
        // 如果get请求地址中包括websocket
        if (req.uri().startsWith("/websocket")) {
            // Handshake
            WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                    getWebSocketLocation(req), null, false);
            handshaker = wsFactory.newHandshaker(req);
            if (handshaker == null) {
                wsFactory.sendUnsupportedVersionResponse(ctx.channel());
            } else {
                handshaker.handshake(ctx.channel(), req);
                String message = URLDecoder.decode(req.uri(),"utf-8");
                message = message.replace("/websocket?","");
                this.callBack.setInitSession(message,ctx);
                System.out.println("imClient[" + ctx.channel().remoteAddress() + "]在线");
            }
        } else{// 处理get请求
            String url = req.uri();
            url = URLDecoder.decode(url,"UTF-8");
            if (url.indexOf("/") == 0) {
                url = url.substring(1);
            }
            if (url.indexOf("?") == 0) {
                url = url.substring(1);
            }
            if (url.length() == 0) {
                UrlError(ctx,req);
                return ;
            }

            Map<String, String> argsMap = GlobalHelper.RequestParameterHelper.getParamValue(url);

            if (argsMap.size() == 0) {
                UrlError(ctx, req);
                return;
            }

            String responseData = this.callBack.response(argsMap);
            if (responseData == null) {
                responseData = "";
            }
            //ByteBuf content = string2ByteBuf(responseData);

            // 返回内容
            FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1,OK,
                    Unpooled.wrappedBuffer(responseData.getBytes(CharsetUtil.UTF_8)));
            res.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
            res.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            res.headers().set(CONTENT_LENGTH, res.content().readableBytes());

            sendHttpResponse(ctx,req,res);
        }
    }

    /**
     * 把字符串类型转为ByteBuf
     * @param data
     * @return
     */
    private ByteBuf string2ByteBuf(String data) {
        return Unpooled.copiedBuffer(data,CharsetUtil.UTF_8);
    }

    /**
     * 响应请求
     * @param ctx
     * @param req
     * @param content
     */
    private void response(ChannelHandlerContext ctx,FullHttpRequest req,ByteBuf content) {
        FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, OK, content);
        res.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
        HttpUtil.setContentLength(res, content.readableBytes());
        sendHttpResponse(ctx, req, res);
    }

    /**
     * @param ctx
     * @param req
     * @param res void
     * @throws
     * @function: http请求响应处理
     * @author:肖震
     * @since 1.0.0
     */
    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        // Generate an error page if response getStatus code is not OK (200).
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            HttpUtil.setContentLength(res, res.content().readableBytes());
        }

        // Send the response and close the connection if necessary.
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {
        logger.info("channelActive "+ctx.channel().id());
        System.out.println("imClient[" + ctx.channel().remoteAddress() + "] channelActive 在线");
    }

    /**
     * URL错误
     * @param ctx
     * @param req
     */
    private void UrlError(ChannelHandlerContext ctx,FullHttpRequest req) {
        ByteBuf content = string2ByteBuf("Parameter error! Please check the url you used to access this page");
        response(ctx, req, content);
    }


    


}
