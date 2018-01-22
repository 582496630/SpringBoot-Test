package com.zy.many.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * BUG:
 * 1、控制台输入的空格键跟回车键效果类似，及空格也会自动分割list元素，但必须按回车键才会传值
 * 2、当一端一次传输的数据超过list设定的长度时，数据会丢失；
 * 3、接收到数据之后，才可以做处理，不可以连续发送数据或者连续接收数据
 * 具体过程如下叙述：
 * 因为我限制了最多一次只能传5条及list的五个元素满了按回车或者输入ok+回车,就会传值过去
 * 但一行数据以空格隔开，超过五个元素外的其他元素就不会传值过去，会丢失
 * 如果是以回车键隔开每个元素，在第5个元素+回车的时候，会自动传数据过去，如果剩下的还有数据，会在接受到对端传来的数据之后再自动传过去，
 * 但是只会累积最多10条数据，超过的都会丢失；及如果你一开始输入13数据，在第五条数据+回车键时，会传数据过去一次（内容1-5），
 * 在接收到对端传的数据的时候，会自动传数据过去（6-10），其余的数据就会丢失
 * 
 * 
 * 解决方式：基于以上BUG我会把list长度放到100，及满一百才会自动传数据
 */


//服务端与客户端可以通过控制台传值
public class Server {
    private int port;
    public Server(int port){
        this.port = port;
    }
    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();//用于接收进来客户端的连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();//用于处理已经被接收的连接   
        try {
            ServerBootstrap b = new ServerBootstrap();//启动NIO服务的辅助启动类    为了降低服务端的开发复杂度
            b.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ServerHandler());//以上及以下很多都是模板化的代码,主要要更改的是ServerHandler()的实现
                }
            })
            .option(ChannelOption.SO_BACKLOG, 128)
            .childOption(ChannelOption.SO_KEEPALIVE, true);
          //绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();
            
            //不生效，因为ServerHandler接收到数据后会先处理，才会在传值过去，这时候这个已经没有必要了
            f.channel().write(Unpooled.copiedBuffer("与服务器连接正常...".getBytes()));//发送给客户端数据
            f.channel().flush();//发送数据之后清楚缓存
            
          //等待服务端监听端口关闭
            f.channel().closeFuture().sync();
            
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception {
        int port;
        //args = new String[]{"8888","8889"};
        
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8088;
        }
        new Server(port).run();
    }
}