package com.zy.many.server.netty.chat.manychat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.zy.many.server.netty.Server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientMain2 {
	private String host;
	private int port;
	private boolean stop = false;

	public ClientMain2(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public static void main(String[] args) throws IOException {

		new ClientMain2("localhost", 2222).run();
	}

	public void run() throws IOException {
		EventLoopGroup worker = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(worker);
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.handler(new ClientIniter());

		try {
			Channel channel = bootstrap.connect(host, port).sync().channel();
			while (true) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String input = reader.readLine();
				if (input != null) {
					if ("quit".equals(input)) {
						System.exit(1);
					}
					channel.writeAndFlush(input);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

}
/*
 * 作者：levy兄 链接：http://www.jianshu.com/p/216881b0573d 來源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
