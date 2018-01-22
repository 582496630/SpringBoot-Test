package com.zy.many.server.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.zy.many.utils.JsonUtils;

public class ClientHandler extends IoHandlerAdapter {
	 private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);
	@Override
	 public void messageSent(IoSession session, Object message) throws Exception {
		//String str = "你好!";
		//session.write(str);
    }
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		logger.info("收到数据：" + message.toString());
		String str = "我收到你的信息了，服务端!\n";
		//session.write(str);
	}
	//在会话创建时发送给服务端信息
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		
		TcpMsg msg = new TcpMsg();
        msg.setPacketID(String.valueOf(System.currentTimeMillis()));
        msg.setService("testserver");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "12345");
        jsonObject.put("avatar", "1");
        msg.setData(jsonObject);
		String strToClient = JsonUtils.encode(msg);//把TcpMsg类型转换为String
		logger.info("发送数据："+ strToClient);
		session.write(strToClient);
	    }
	@Override
	public void sessionOpened(IoSession session) {
		
	}
}