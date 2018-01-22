package com.zy.many.server.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;

/**
 * 编解码器生成工厂
 * 
 * @author zhouyou
 * @version  2017-10-24 17:16:15
 */
public class MsgProtocolCodecFactory implements ProtocolCodecFactory {
	private ProtocolDecoder decoder;
	private ProtocolEncoder encoder;

	public MsgProtocolCodecFactory(boolean server) {
		if (server) {
			decoder = new MsgDecoder();
			encoder = new TextLineEncoder(Charset.forName("UTF-8"));
		} else {
			decoder = new TextLineDecoder(Charset.forName("UTF-8"));
			encoder = new MsgEncoder();
		}
	}
	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

}