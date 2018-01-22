package com.zy.many.server.netty.chat.manychat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;    
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * 多人聊天功能，服务端只起到信息转发的功能
 * 
 * @author zhouyou
 * @version
 */
public class ServerMain {

private int port;

public ServerMain(int port) {
    this.port = port;
}

public static void main(String[] args) {
	int port;
	 if (args.length > 0) {
         port = Integer.parseInt(args[0]);
     } else {
         port = 2222;
     }
     new ServerMain(port).run();
}

public void run() {
    EventLoopGroup acceptor = new NioEventLoopGroup();
    EventLoopGroup worker = new NioEventLoopGroup();
    ServerBootstrap bootstrap = new ServerBootstrap();
    bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
    bootstrap.group(acceptor, worker);//设置循环线程组，前者用于处理客户端连接事件，后者用于处理网络IO
    bootstrap.channel(NioServerSocketChannel.class);//用于构造socketchannel工厂
    bootstrap.childHandler(new ServerIniterHandler());//为处理accept客户端的channel中的pipeline添加自定义处理函数
    try {
        Channel channel = bootstrap.bind(port).sync().channel();//绑定端口（实际上是创建serversocketchannnel，并注册到eventloop上），同步等待完成，返回相应channel
        System.out.println("server strart running in port:" + port);
        channel.closeFuture().sync();//在这里阻塞，等待关闭
    } catch (InterruptedException e) {
        e.printStackTrace();
    } finally {
        //优雅退出
        acceptor.shutdownGracefully();
        worker.shutdownGracefully();
        }
    }
}

/*作者：levy兄
链接：http://www.jianshu.com/p/216881b0573d
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
