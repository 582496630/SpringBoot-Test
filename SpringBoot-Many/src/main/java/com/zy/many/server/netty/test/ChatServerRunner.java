package com.zy.many.server.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ChatServerRunner {
	private int port;

	public ChatServerRunner(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();// 用于接收进来客户端的连接
		EventLoopGroup workerGroup = new NioEventLoopGroup();// 用于处理已经被接收的连接
		try {
			ServerBootstrap b = new ServerBootstrap();// 启动NIO服务的辅助启动类
														// 为了降低服务端的开发复杂度
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new ChatServerHandler());// 以上及以下很多都是模板化的代码,主要要更改的是ServerHandler()的实现
						}
					}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
			// 绑定端口，同步等待成功
			ChannelFuture f = b.bind(port).sync();

			// 不生效，因为ServerHandler接收到数据后会先处理，才会在传值过去，这时候这个已经没有必要了
			f.channel().write(Unpooled.copiedBuffer("与服务器连接正常...".getBytes()));// 发送给客户端数据
			f.channel().flush();// 发送数据之后清楚缓存

			// 等待服务端监听端口关闭
			f.channel().closeFuture().sync();

		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port;
		// args = new String[]{"8888","8889"};

		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 11113;
		}
		System.out.println("服务正常启动");
		new ChatServerRunner(port).run();
	}
}
