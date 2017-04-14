package com.mg.csbackstage.netty.webSocketServer;


import com.mg.csbackstage.netty.webSocketServer.interfaces.IWebSocketChannelCallBack;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @description:消息通道初始化类 Created by BennieSun on 2017/2/17.
 */
public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {

    private final IWebSocketChannelCallBack callBack;

    /**
     * 往通道中读写数据，以及通道状态发生变化时的回调函数
     *
     * @param callBack
     */
    public WebSocketServerInitializer(IWebSocketChannelCallBack callBack) {
        this.callBack = callBack;
    }


    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast("handler", new WebSocketServerHandler(callBack));

        System.out.println("imClient:" + ch.remoteAddress() + "连接上");
    }
}
