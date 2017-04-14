package com.mg.csbackstage.service;

import com.mg.csbackstage.netty.webSocketServer.WebSocketServer;
import com.mg.csbackstage.utils.WebSocketUtil;

/**
 * 
 * @description: 聊天消息服务启动类
 * @fileName:IMServer.java
 * @createTime:2015年7月23日 下午7:33:54
 * @author:肖震
 * @version 1.0.0
 * 
 */
public class NettyIMServer
{
	
	// 端口
	private int port;
	
	// 构造方法
	public NettyIMServer(int port)
	{
		this.port = port;
	}
	
	// 服务运行方法
	public void run() throws Exception
	{
		WebSocketServer ws = new WebSocketServer(port,new WebSocketUtil());
		new Thread(ws).start();
	}
	
	// web方式运行启动
	public static void main(String[] args)
		throws Exception
	{
		int port;
		if (args.length > 0)
		{
			port = Integer.parseInt(args[0]);
		}
		else
		{
			port = 8080;
		}
		new NettyIMServer(port).run();
		
	}
}
