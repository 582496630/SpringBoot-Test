package com.zy.many.server.netty;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends ChannelHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            //接收从服务端发送的数据
            ByteBuf buf = (ByteBuf)msg;
            byte[] data = new byte[buf.readableBytes()];
            buf.readBytes(data);
            String request = new String(data, "utf-8");
            System.out.println("Client get: " + request);
            
            
            
            List<String> list = new ArrayList<String>();
    		@SuppressWarnings("resource")//压制input提示的永远不会关闭的警告
			Scanner input = new Scanner(System.in);
    		System.out.println("请输入你想输入的值，以“OK”结束！");
    		for (int i = 0; i<5; i++) {
    		if (list.contains("ok")||list.contains("OK")) {
    				break;
    			} else {
    				list.add(input.next());
    			}
    		}
    		list.remove("ok");
    		list.remove("OK");
    		String stringlist = "\nClient1发来的信息：";
    		for (String string : list) {
    		//	System.out.print(string+",");
    			stringlist =stringlist+"\n"+string;
    		}

    		ctx.write(Unpooled.copiedBuffer(stringlist.getBytes()));//发送给服务端数据
            
    		ctx.flush();
    
            
            


        } finally {
            ReferenceCountUtil.release(msg);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}