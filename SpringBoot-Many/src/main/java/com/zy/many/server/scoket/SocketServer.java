package com.zy.many.server.scoket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) {
		Socket socket = null;
		ServerSocket serverSocket = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {
			System.out.println("******Server******");
			// 服务端接收消息的类
			serverSocket = new ServerSocket(1110);
			// 获取socket，这个方法是阻塞的,一旦有堵塞, 则表示服务器与客户端获得了连接
			socket = serverSocket.accept();
			// 获得输入流，获取客户端发来的消息
			inputStream = socket.getInputStream();
			byte[] buff = new byte[1024];
			int len = 0;
			len = inputStream.read(buff);
			// 打印
			System.out.println(new String(buff, 0, len, "UTF-8"));

			// 获取输出流，给客户端发消息
			outputStream = socket.getOutputStream();
			/*
			 * Scanner scanner = new Scanner(System.in); String b =
			 * String.valueOf(scanner)+"S";
			 */

			String b = "收到";
			outputStream.write(b.getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				outputStream.close();
				socket.close();
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
