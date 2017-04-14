package com.mg.csbackstage.netty.webSocketServer.interfaces;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

public interface IWebSocketChannelCallBack {
	/**
	 *
	 * paramsMap.put("params", params); => json
	 * paramsMap.put("sendMessageTime", GlobalHelper.currentTimestamp()+""); => int
	 *
	 * @param params 前端传给服务端的params(socket)
	 * @param ctx
	 * @return	后端返回给前端
	 */
	public void channelData(Map<String, String> params, ChannelHandlerContext ctx) throws Exception;

	/**
	 *
	 * @param params 前端传给服务端的params(http)
	 * @return	后端返回给前端
	 */
	public String response(Map<String, String> params) throws Exception;

	/**
	 * 初始化session
	 * @param message
	 * @param ctx
	 */
	public void setInitSession(String message, ChannelHandlerContext ctx) throws Exception;

	/**
	 * 移除session
	 * @param ctx
	 * @throws Exception
	 */
	public void removeSession(ChannelHandlerContext ctx) throws Exception;

	/**
	 * 通道打开时调用此方法
	 * @param ctx
	 */
	public void channelOpen(ChannelHandlerContext ctx);
	
	/**
	 * 关闭通道时调用此方法
	 * @param ctx
	 */
	public void chanelClose(ChannelHandlerContext ctx);

	/**
	 * IO异常
	 * @param ctx
	 * @param cause
	 */
	public void channelException(ChannelHandlerContext ctx, Throwable cause);

}
