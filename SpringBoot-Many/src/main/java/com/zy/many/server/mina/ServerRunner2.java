package com.zy.many.server.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
public class ServerRunner2 {
	private final static Logger logger = LoggerFactory.getLogger(ServerRunner2.class);
	public static void main(String[] args) {
		Integer port = 8888;
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
		//采用框架自带的编解码；LineDelimiter.WINDOWS.getValue()表示windows系统的分隔符
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
						LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
		acceptor.setHandler(new ServerHandler());//数据处理逻辑
		acceptor.getSessionConfig().setReadBufferSize(1024);//设置缓存区大小
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);//设置空闲时间10S
		try {
			acceptor.bind(new InetSocketAddress(port));//绑定端口
			logger.info("游戏TCP服务器正在端口   " + port + " 上监听中...");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
