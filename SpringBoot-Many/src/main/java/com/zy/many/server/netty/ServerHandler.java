package com.zy.many.server.netty;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends ChannelHandlerAdapter {
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 获得接收客户端的数据并进行处理
		ByteBuf buf = (ByteBuf) msg;
		byte[] data = new byte[buf.readableBytes()];
		buf.readBytes(data);
		String str = new String(data, "UTF-8");
		System.out.println("server get:" + str);

		// 收到信息之后立刻回信息给客户端，表示收到信息
		// ctx.write(Unpooled.copiedBuffer("我是服务器，收到了你的信息！".getBytes()));
		// 直接用String，会实时的传输信息，在一轮信息互传之后，就没办法继续了
		/*
		 * Scanner input = new Scanner(System.in); String string = input.next();
		 * ctx.write(Unpooled.copiedBuffer(string.getBytes()));
		 */

		// 把输入的信息先存到list中，然后输入完成之后以“ok”结束，结束之后再开始一次传过去
		List<String> list = new ArrayList<String>();

		@SuppressWarnings("resource") // 压制input提示的永远不会关闭的警告
		Scanner input = new Scanner(System.in);
		System.out.println("请输入你想输入的值，以“OK”结束！");
		for (int i = 0; i < 5; i++) {
			if (list.contains("ok") || list.contains("OK")) {
				break;
			} else {
				list.add(input.next());
			}
		}
		list.remove("ok");
		list.remove("OK");
		String stringlist = "\nServer发来的信息：";
		for (String string : list) {
			stringlist = stringlist + "\n" + string;
		}
		ctx.write(Unpooled.copiedBuffer(stringlist.getBytes()));

		// 回写给客户端数据
		/*
		 * ctx.write(Unpooled.copiedBuffer("我是服务器，收到了你的信息！".getBytes()))
		 * .addListener(ChannelFutureListener.CLOSE);
		 */ // 发送完数据之后关闭跟客户端的连接
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		cause.printStackTrace();
		ctx.close();
	}
}
