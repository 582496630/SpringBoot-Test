package com.zy.many.server.scoket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {

	public static void main(String arg[]) {

		Socket socket = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {
			System.out.println("******Client******");
			// 创建Socket实例
			socket = new Socket("localhost", 1110);
			// 获取输出流，向服务器传输数据
			outputStream = socket.getOutputStream();
			//创建scanner 实例
			/*Scanner scanner = new Scanner(System.in);
			//转型
			String b = String.valueOf(scanner);*/
			
			String b = "hello";
			outputStream.write(b.getBytes());
			
			
			// 获取输入流，获取服务器的响应
			inputStream = socket.getInputStream();
			byte[] buff = new byte[1024];
			int len = 0;
			len = inputStream.read(buff);
			// 打印服务端的内容
			System.out.println(new String(buff, 0, len,"UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				outputStream.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
