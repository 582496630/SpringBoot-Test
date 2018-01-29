package com.zy.many.server.netty.chat.manychat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

	public static final ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	protected void channelRead0(ChannelHandlerContext arg0, String arg1) throws Exception {
		Channel channel = arg0.channel();
		for (Channel ch : group) {
			if (ch == channel) {
				ch.writeAndFlush("[you]：" + arg1 + "\n");
			} else {
				ch.writeAndFlush("[" + channel.remoteAddress() + "]: " + arg1 + "\n");
			}
		}

	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
		Channel channel = ctx.channel();
		for (Channel ch : group) {
			if (ch == channel) {
				ch.writeAndFlush("[you]：" + msg + "\n");
			} else {
				ch.writeAndFlush("[" + channel.remoteAddress() + "]: " + msg + "\n");
			}
		}
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		for (Channel ch : group) {
			ch.writeAndFlush("[" + channel.remoteAddress() + "] " + "is comming");
		}
		group.add(channel);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		for (Channel ch : group) {
			ch.writeAndFlush("[" + channel.remoteAddress() + "] " + "is comming");
		}
		group.remove(channel);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println("[" + channel.remoteAddress() + "] " + "online");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println("[" + channel.remoteAddress() + "] " + "offline");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("[" + ctx.channel().remoteAddress() + "]" + "exit the room");
		ctx.close().sync();
	}

}

/*
 * 作者：levy兄 链接：http://www.jianshu.com/p/216881b0573d 來源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
