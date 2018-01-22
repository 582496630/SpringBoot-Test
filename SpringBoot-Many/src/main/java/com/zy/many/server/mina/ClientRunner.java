package com.zy.many.server.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
public class ClientRunner extends Thread {
	private final static Logger logger = LoggerFactory.getLogger(ClientRunner.class);
	
/*	//基础写法 在main方法中直接运行
	public static void main(String[] args) {
		IoConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
		connector.setConnectTimeoutMillis(5000);
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
						LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
//		connector.getFilterChain().addLast("codec", new
//				 ProtocolCodecFilter(new MsgProtocolCodecFactory(true)));
		connector.setHandler(new ClientHandler());
		connector.getSessionConfig().setReadBufferSize(1024);
		connector.connect(new InetSocketAddress("localhost", 8888));
		logger.info("MINA 客户端运行成功");
	}*/
	@Override
	public void run(){
		IoConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
		connector.setConnectTimeoutMillis(5000);
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
						LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
//		connector.getFilterChain().addLast("codec", new
//				 ProtocolCodecFilter(new MsgProtocolCodecFactory(true)));
		connector.setHandler(new ClientHandler());
		connector.getSessionConfig().setReadBufferSize(1024);
		connector.connect(new InetSocketAddress("localhost", 8888));
		logger.info("MINA 客户端运行成功");
	}
}