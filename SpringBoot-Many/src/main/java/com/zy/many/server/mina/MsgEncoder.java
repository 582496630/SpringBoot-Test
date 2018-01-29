package com.zy.many.server.mina;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * mina 编码，用不到，用的是mina框架自带的编码
 * 
 * @author zhouyou
 * @version 2017-10-24 17:15:43
 */
public class MsgEncoder extends ProtocolEncoderAdapter {

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {

		TcpMsg res = (TcpMsg) message;
		IoBuffer buff = IoBuffer.allocate(32);
		buff.setAutoExpand(true);
		buff.setAutoShrink(true);

		CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
		buff.putString(res.getService(), encoder);
		buff.putString(res.getPacketID(), encoder);
		buff.putString((CharSequence) res.getData(), encoder);
		buff.put((byte) '\n');
		buff.flip();
		out.write(buff);
		out.flush();
	}

}