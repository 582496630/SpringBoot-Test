package com.zy.many.server.netty.chat.manychat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChatClientHandler extends SimpleChannelInboundHandler<String> {

	protected void channelRead0(ChannelHandlerContext arg0, String arg1) throws Exception {
		System.out.println(arg1);
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println(msg);

	}

}

/*
 * 作者：levy兄 链接：http://www.jianshu.com/p/216881b0573d 來源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
