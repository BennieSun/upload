/**
 * 
 */
package com.mg.csbackstage.utils;

import com.mg.csbackstage.configs.HttpConfig;
import com.mg.csbackstage.configs.HttpConfig.HttpClass;
import com.mg.csbackstage.manager.WebSocketManager;
import com.mg.csbackstage.netty.webSocketServer.WebSocketServerBase;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by BennieSun on 2017/4/5.
 */
public class WebSocketUtil extends WebSocketServerBase {

	private static Logger logger = Logger.getLogger(WebSocketUtil.class);

	/**
	 * @param message
	 * @param ctx
	 * @return 初始化session
	 */
	@Override
	public void setInitSession(String message, ChannelHandlerContext ctx) throws Exception {
		WebSocketManager.setInitSession(message, ctx);
	}

	/**
	 * 移除session
	 *
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void removeSession(ChannelHandlerContext ctx) throws Exception {
		WebSocketManager.removeSession(ctx);
	}

	@Override
	public void channelData(Map<String, String> params, ChannelHandlerContext ctx) throws Exception {
		logger.info("channelData socket");
		WebSocketManager.channelData(params, ctx);
	}

	@Override
	public String response(Map<String, String> args) throws Exception {
		logger.info("response http");
		try{
			return this.parse(args);
		}catch(Exception e) {
			String str =  e.getMessage();
			if (str.length() > 0) {
				return str.replaceFirst("java.lang.RuntimeException: ", "");
			}
			return e.getCause().getMessage();
		}
	}
	
	private String parse(Map<String, String> args) throws Exception {
		if (!args.containsKey("code")) {
			throw new Exception("params is code not exist");
		}

		int code = getInt(args.get("code"));

		HttpClass hc = HttpConfig.getInstance().parseData(code);
		logger.info("收到http请求Code:" + code +":" + hc.method);
		Method method = hc.hclass.getMethod(hc.method, Map.class);
		Object data = "";

		try{
			data = method.invoke(hc.hclass.newInstance(), args);
			logger.info("收到http请求" + code +"执行成功");
		}catch(Exception e){
			e.printStackTrace();
			return e.getCause().getMessage();
		}
		if (data == null) {
			return "success";
		}
		return data.toString();
	}

	public int getInt(Object data) {
		return Integer.parseInt(data.toString());
	}

	@Override
	public void chanelClose(ChannelHandlerContext ctx) {
		logger.info("chanelClose");
		WebSocketManager.removeSession(ctx);
	}

	/**
	 * IO异常
	 * @param ctx
	 * @param cause
	 */
	@Override
	public void channelException(ChannelHandlerContext ctx, Throwable cause) {
		logger.info("channelException");
		WebSocketManager.removeSession(ctx);
		throw new RuntimeException(cause);
	}

}
