package com.mg.csbackstage.netty.webSocketServer;

import com.mg.csbackstage.netty.webSocketServer.interfaces.IWebSocketChannelCallBack;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;

import java.util.Map;


public class WebSocketServerBase implements IWebSocketChannelCallBack {

	private static final Logger logger = Logger.getLogger(WebSocketServerBase.class);


	/**
	 * paramsMap.put("params", params); => json
	 * paramsMap.put("sendMessageTime", GlobalHelper.currentTimestamp()+""); => int
	 *
	 * @param params 前端传给服务端的params(socket)
	 * @param ctx
	 * @return 后端返回给前端
	 */
	@Override
	public void channelData(Map<String, String> params, ChannelHandlerContext ctx) throws Exception {
		logger.info("channelData socket");
	}

	/**
	 * @param params 前端传给服务端的params(http)
	 * @return 后端返回给前端
	 */
	@Override
	public String response(Map<String, String> params) throws Exception {
		logger.info("response http");
		return null;
	}

	/**
	 * @param message
	 * @param ctx
	 * @return 初始化session
	 */
	@Override
	public void setInitSession(String message, ChannelHandlerContext ctx) throws Exception {
		logger.info("setInitSession");
	}

	/**
	 * 移除session
	 *
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void removeSession(ChannelHandlerContext ctx) throws Exception {
		logger.info("removeSession");
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx) {
		logger.info("channelOpen");
	}

	@Override
	public void chanelClose(ChannelHandlerContext ctx) {
		logger.info("chanelClose");
	}

	/**
	 * IO异常
	 * @param ctx
	 * @param cause
	 */
	@Override
	public void channelException(ChannelHandlerContext ctx, Throwable cause) {
		logger.info("channelException");
		throw new RuntimeException(cause);
	}

}
