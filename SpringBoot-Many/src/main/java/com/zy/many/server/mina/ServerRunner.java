package com.zy.many.server.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerRunner implements CommandLineRunner {
	private final static Logger logger = LoggerFactory.getLogger(ServerRunner.class);
	// 端口配置
	private Integer port = 888;
	@Resource
	private ServerHandler serverHandler;

	@Override
	public void run(String... args) throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(() -> {
			IoAcceptor acceptor = new NioSocketAcceptor();
			acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));

			acceptor.getFilterChain().addLast("codec",
					new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
							LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
			// acceptor.getFilterChain().addLast("codec", new
			// ProtocolCodecFilter(new MsgProtocolCodecFactory(true)));
			// acceptor.getFilterChain().addLast("codec",new
			// ProtocolCodecFilter(new
			// MsgProtocolCodecFactory2(Charset.forName("UTF-8"),"\n")));

			acceptor.setHandler(serverHandler);
			acceptor.getSessionConfig().setReadBufferSize(1024);
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
			try {
				acceptor.bind(new InetSocketAddress(port));
				logger.info("游戏TCP服务器正在端口   " + port + " 上监听中...");
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		});
	}

}
