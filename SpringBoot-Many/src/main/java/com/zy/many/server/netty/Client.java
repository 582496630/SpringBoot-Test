package com.zy.many.server.netty;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
    public static void main(String[] args) throws Exception {

        String host = "localhost";
        int port = 8088;
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ClientHandler());//客户端接受服务端发送的数据进行的处理逻辑
                }
            });

            ChannelFuture f = b.connect(host, port).sync();
            f.channel().write(Unpooled.copiedBuffer("与客户端连接正常...".getBytes()));//发送给服务端数据
     //客户端可以先写值传值   有这段就是客户端可以先发数据给服务端，没有这段就是服务端可以先发数据给客户端    
            List<String> list = new ArrayList<String>();
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

            f.channel().write(Unpooled.copiedBuffer(stringlist.getBytes()));//发送给服务端数据
      
          
            f.channel().flush();//发送之后清空缓存
            //f.channel().writeAndFlush(Unpooled.copiedBuffer("test".getBytes()));//这行代码和上面两行意思是一样的

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}