package com.mg.csbackstage.netty.webSocketServer;


import com.mg.csbackstage.netty.webSocketServer.interfaces.IWebSocketChannelCallBack;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class WebSocketServer implements Runnable {
	private final IWebSocketChannelCallBack callBack;
	private final int port;
	private  EventLoopGroup bossGroup;
	private  EventLoopGroup workerGroup;

	public WebSocketServer(int port, IWebSocketChannelCallBack callBack) {
		this.port = port;
        this.callBack = callBack;
	}
	
	public void run(){
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new WebSocketServerInitializer(callBack))
					.option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true);
 
            Channel ch = b.bind(port).sync().channel();
            System.out.println("webSocket server started at port " + port + '.');
            ch.closeFuture().sync();
        } catch (InterruptedException e) {
        	e.printStackTrace();
        } finally {
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
        }
    }
	
	 public void stopServer() {
	    	if (bossGroup != null) {
	    		bossGroup.shutdownGracefully();
	    	}
	    	if (workerGroup != null) {
	    		workerGroup.shutdownGracefully();
	    	}
	    }
 
    public static void main(String[] args) throws Exception {
    	WebSocketServer ws = new WebSocketServer(8080,new WebSocketServerBase());
    	new Thread(ws).start();
    }

}
