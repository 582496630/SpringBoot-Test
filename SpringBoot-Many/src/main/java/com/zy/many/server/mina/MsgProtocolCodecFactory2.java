package com.zy.many.server.mina;

import java.nio.charset.Charset;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MsgProtocolCodecFactory2 implements ProtocolCodecFactory {
	private Charset charset; // 编码格式
	private String delimiter; // 文本分隔符

	public MsgProtocolCodecFactory2(Charset charset, String delimiter) {
		this.charset = charset;
		this.delimiter = delimiter;
	}

	public ProtocolDecoder getDecoder(IoSession session) throws Exception {

		return new MsgDecoder2(charset, delimiter);
	}

	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return new MsgEncoder2(charset, delimiter);
	}
}