package com.zy.many.server.netty.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zy.many.utils.JsonUtils;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client3 {
	public static void main(String[] args) throws Exception {

		String host = "localhost";
		int port = 11113;
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new ClientHandler());// 客户端接受服务端发送的数据进行的处理逻辑
				}
			});

			ChannelFuture f = b.connect(host, port).sync();

			Map<String, String> map3 = new HashMap<String, String>();
			map3.put("usertype", "stu");
			map3.put("username", "张三");
			String maps = null;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", "S");
			map.put("info", map3);
			maps = JSONObject.toJSONString(map);
			String maps2 = null;
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("service", "send");
			map2.put("data", map);
			maps2 = JSONObject.toJSONString(map2);

			TCPMessage message = new TCPMessage();
			message.setService("send");
			TCPData data = new TCPData();
			data.setType("S");
			data.setInfo(map3);
			message.setData(data);

			String response = JsonUtils.encode(message);

			f.channel().write(Unpooled.copiedBuffer(response.getBytes()));// 发送给服务端数据

			System.out.println("Client 发送数据：" + response);

			f.channel().flush();// 发送之后清空缓存

			f.channel().closeFuture().sync();
			System.out.println("客户端启动成功");
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
}