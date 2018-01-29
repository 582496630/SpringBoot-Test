package com.zy.many.server.mina;

import java.io.UnsupportedEncodingException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zy.many.utils.JsonUtils;

@Component
public class ServerHandler extends IoHandlerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);
	private static final String TimeOut_Count = "timeOutCount";

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// IoBuffer buffer = (IoBuffer) message;
		// String body = buffer.getString(decoder);
		String body = message.toString();
		if (logger.isDebugEnabled()) {
			logger.debug("接收的数据：" + body);
		}
		logger.info("接收的数据：" + body);
		TcpMsg msgReceive = JsonUtils.decode(body, TcpMsg.class);// 接收的数据转换为实体类型
		// tcpService.execute(session, msg);//把数据传入后台逻辑

		TcpMsg msg = new TcpMsg();
		msg.setPacketID(String.valueOf(System.currentTimeMillis()));
		msg.setService("testserver");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "23456");
		jsonObject.put("avatar", "2");
		msg.setData(jsonObject);
		String strToClient = JsonUtils.encode(msg);// 把TcpMsg类型转换为String
		logger.info("发送数据：" + strToClient);
		session.write(strToClient);

	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug(session.getRemoteAddress().toString() + "connect");
		}
		logger.info(session.getRemoteAddress().toString() + "connect");
		super.sessionCreated(session);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.info("会话关闭");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		if (status == IdleStatus.BOTH_IDLE) {
			logger.info("客户端超时");
			String timeOutCount = (String) session.getAttribute(TimeOut_Count);
			if (timeOutCount == null) {
				timeOutCount = "0";
				session.setAttribute(TimeOut_Count, timeOutCount);
				return;
			}
			int count = Integer.valueOf(timeOutCount);
			if (count > 3) {
				session.close();
			} else {
				count++;
				session.setAttribute(TimeOut_Count, String.valueOf(count));
				System.out.println("超时次数：" + timeOutCount);
			}
		} else {
			super.sessionIdle(session, status);
		}
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		cause.printStackTrace();
		logger.error(session.getRemoteAddress() + cause.getLocalizedMessage());
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {

	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
		super.inputClosed(session);
	}

	/**
	 * 返回信息方法
	 * 
	 * @param session
	 * @param msg
	 */
	public void ret(IoSession session, TcpMsg msg) {
		String strToClient = JsonUtils.encode(msg);
		logger.info("strToClient:" + strToClient);
		byte[] ret = new byte[0];
		try {
			ret = (strToClient + "\n").getBytes("UTF-8");
		} catch (UnsupportedEncodingException ignore) {
			logger.error("game回应游戏数据异常");
		}
		IoBuffer buf = IoBuffer.wrap(ret);
		session.write(buf);
	}

}
