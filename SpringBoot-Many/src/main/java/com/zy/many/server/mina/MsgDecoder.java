package com.zy.many.server.mina;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * mina 解码
 * 
 * @author zhouyou
 * @version  2017-10-24 17:14:42
 */
public class MsgDecoder implements ProtocolDecoder {

	private final static Charset charset = Charset.forName("UTF-8");
	private final static AttributeKey leftBuff = new AttributeKey(CharsetDecoder.class, "leftByteBuff");// Creates a Key from a class name and an attribute name. The resulting Key will be stored in the session Map.
	private final static AttributeKey rightBuff = new AttributeKey(CharsetDecoder.class, "rightByteBuff");// Creates a Key from a class name and an attribute name. The resulting Key will be stored in the session Map.
	private IoBuffer buff = IoBuffer.allocate(0).setAutoExpand(true);// 生成一个自动扩展的buffer

	@Override
	public synchronized void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		try {
			/*//直接输出in中的内容，这里输出会导致下面程序无法输出内容
			byte[] bytes1 = new byte[in.limit()];
			in.get(bytes1);
			String messagebuff1 = new String(bytes1, charset);
			System.out.println(messagebuff1);
			*/
			int i = 0;//记录    完整包+断包前部分    前面完整的包输出完，把后面的内容记录为断包
			String sessionleftBuff = (String) session.getAttribute(leftBuff);
			String sessionrightBuff = (String) session.getAttribute(rightBuff);
			//TEST
			byte firstIoBuffer = in.get(0);
			if (firstIoBuffer != 123 && sessionleftBuff == null) {
				System.out.println(firstIoBuffer);
			}//TEST END
			
			while (in.hasRemaining()) {
				byte b = in.get();
				if (b == '\n') {
					buff.flip();
					byte[] bytes = new byte[buff.remaining()];
					buff.get(bytes);
					String messagebuff = new String(bytes, charset);
					firstIoBuffer = buff.get(0);
						out.write(messagebuff);
					buff.clear();
					i = 1;
					// log.info("message: " + message);
				} else {
					if (i == 1) {
						i = 2;
					}
					buff.put(b);// 把当前b!=10数据加入全局buff中
				}
			}
			if (i == 2) {
				buff.flip();
				byte[] bytes = new byte[buff.remaining()];
				buff.get(bytes);
				String messagebuff = new String(bytes, charset);
				if (sessionrightBuff == null) {
					session.setAttribute(leftBuff, messagebuff);
					System.out.println("出现断包（前）：" + messagebuff);
				} else {
					session.removeAttribute(rightBuff);
					String message = messagebuff + sessionrightBuff;
					out.write(message);
					System.out.println("组合包前：" + messagebuff);
				}
				buff.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1) throws Exception {
		// TODO Auto-generated method stub
	}
}

/*public class MsgDecoder extends CumulativeProtocolDecoder {
	private String parseString(IoBuffer in) throws CharacterCodingException {
		// Convert the bytes in the specified buffer to a
		// Command object.
		CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
		int end = in.remaining() - 1;
		String res = in.getString(end, decoder);
		return res;
	}

	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {

		CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
		System.out.println("缓存区内容：" + in.getString(in.limit() - 1, decoder));
		System.out.println("*****************");
		// Remember the initial position.
		// 记下初始的位置 (position,limit的关系)
		int start = in.position();

		// Now find the first CRLF in the buffer.
		// 查找第一个 回车换行结束符的位置，也即一条消息的结束符
		// byte previous = 0;
		while (in.hasRemaining()) {
			// previous = in.get(start);
			byte current = in.get();
			if (current == '\n') {
				// if (previous == '\r' && current == '\n') {
				// 找到了回车换行符
				// Remember the current position and limit.
				int position = in.position();
				int limit = in.limit();
				try {
					in.position(start);
					in.limit(position);
					// The bytes between in.position() and in.limit()
					// now contain a full CRLF terminated line.
					// 现在start和position之间的字节是一条完整的消息,把它从缓冲区
					// 中取出来解码parseCommand(in.slice()),然后发给下个处理对象
					out.write(parseString(in.slice()));
				} finally {
					// Set the position to point right after the
					// detected line and set the limit to the old
					// one.设置缓冲区准备提取下一条消息或者等待下一个数据包到
					// 来组成完整的数据包
					in.position(position);
					in.limit(limit);
				}
				// Decoded one line; CumulativeProtocolDecoder will
				// call me again until I return false. So just
				// return true until there are no more lines in the
				// buffer.
				// 成功解码一行,CumulativeProtocolDecoder解码器将会再次调用此函数
				// 直到返回false,所以如果可以从缓冲区提取出更多的完整消息则继续返回true
				return true;
			}
			// previous = current;
		}
		// Could not find CRLF in the buffer. Reset the initial
		// position to the one we recorded above.
		in.position(start);
		return false;

	}
}*/
