package com.zy.many.server.netty.test;

import java.net.SocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zy.many.utils.JsonUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class ChatServerHandler extends ChannelHandlerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(ChatServerHandler.class);

	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		try {
			// 获得接收客户端的数据并进行处理
			ByteBuf buf = (ByteBuf) msg;
			byte[] data = new byte[buf.readableBytes()];
			buf.readBytes(data);
			String str = new String(data, "UTF-8");
			System.out.println("server get:" + str);

			// String body = (String) msg;
			// logger.info(body);

			// TCPMessage message = JSONObject.parseObject(body,
			// TCPMessage.class);
			TCPMessage message = JsonUtils.decode(str, TCPMessage.class);
			login(ctx, message);
		} catch (Exception e) {
			System.out.println("eeeeeeee");
		}
	}

	public void login(ChannelHandlerContext ctx, TCPMessage message) {
		String userType = message.info("usertype");
		// 用户名
		String userName = message.info("username");
		System.out.println("login 逻辑：" + userName + userType);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();// 将消息发送队列中的消息写入到SocketChannel中发送给对方
	}

	@Override
	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress,
			ChannelPromise promise) throws Exception {
		ctx.connect(remoteAddress, localAddress, promise);
		// System.out.println("发现有客户端连上");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.fireChannelActive();
		System.out.println("发现有客户端连上");
	}
}
