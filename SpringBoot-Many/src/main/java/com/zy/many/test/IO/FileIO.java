package com.zy.many.test.IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class FileIO {
	private final static Charset charset = Charset.forName("UTF-8");
	public static String writeIn(String pathName){
		
		File file = new File(pathName);
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
			String string = "你好";
			byte b[] = string.getBytes();
			outputStream.write(b);
			outputStream.flush();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
				System.out.println("close 输出流");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String success = "写入成功";
		return success;
	}
	
	public static String readOut(String pathName){
		File file = new File(pathName);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			byte b[] = new byte[10];
			//byte b[] = new byte[1024];
			String str = "";
			StringBuffer buffer = new StringBuffer();
			while (inputStream.read(b) != -1) {
				str =  new String(b,charset);
				buffer.append(str);
			}
			System.out.println("文档的内容："+ buffer.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				System.out.println("close 输入流");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String success = "读出结束";
		return success;
	}
	public static String readOutBuffer(String pathName){
		File file = new File(pathName);
		BufferedReader bReader = null;
		try {
			bReader = new BufferedReader(new FileReader(file));
			String str = "";
			StringBuffer buffer = new StringBuffer();
			while ((str = bReader.readLine()) != null) {
				buffer.append(str);
			}
			System.out.println("文档的内容："+ buffer.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bReader.close();
				System.out.println("close 输入流");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String success = "读出结束";
		return success;
	}
	
	public static String readeFile() {
		try {
			String pt = FileIO.class.getResource("/").getFile();
			pt = pt.substring(0, pt.lastIndexOf("/"));
			pt = pt.substring(0, pt.lastIndexOf("/"));
			pt = pt.substring(0, pt.lastIndexOf("/"));
			System.out.println(pt);
			
			String path = pt + "/IOtest.txt";
			
			File file = new File(path);
			if (!file.getParentFile().exists()) {//父文件夹不存在则创建
				file.getParentFile().mkdirs();
			}
			if (!file.exists()) {//文件不存在创建
					file.createNewFile();
			}
			
			FileInputStream inputStream = new FileInputStream(file);
			InputStreamReader inReader = new InputStreamReader(inputStream);
			
			char c[] = new char[8];
			StringBuffer sb = new StringBuffer();
			int len;
			String str;
			
			while((len = inReader.read(c, 0, 8)) != -1) {//此处的8 不能大于char数组缓冲区的范围
				//char 转   String
				str = new String(c, 0, len);
				sb.append(str);
			}
			System.out.println("文件内容：" + sb);
			//关闭流
			inputStream.close();
			inReader.close();
			
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "读取异常";
		}
	}
	
	public static String writeFile(){
		
		try {
			
		System.out.println("读取文件 《" + FileIO.class.getResource("/templates/XML/my.xml").getFile() + "》中的内容");
			
			InputStream inputStream = FileIO.class.getResourceAsStream("/templates/XML/my.xml");
			
			InputStreamReader reader = new InputStreamReader(inputStream);
			
			char c[] = new char[8];
			StringBuffer sb = new StringBuffer();
			int len;
			String str;
			
			while((len = reader.read(c, 0, 8)) != -1) {//此处的8 不能大于char数组缓冲区的范围
				//char 转   String
				str = new String(c, 0, len);
				sb.append(str);
			}
			
			inputStream.close();
			reader.close();
			
			String pt = FileIO.class.getResource("/").getFile();
			pt = pt.substring(0, pt.lastIndexOf("/"));
			pt = pt.substring(0, pt.lastIndexOf("/"));
			pt = pt.substring(0, pt.lastIndexOf("/"));
			
			String path = pt + "/IOtest.txt";
			System.out.println("写入：  " + path);
			File file = new File(path);
			FileOutputStream outputStream = new FileOutputStream(file);
			
			byte b[] = sb.toString().getBytes();
			
			outputStream.write(b);
			outputStream.flush();
			outputStream.close();
			
			return "写入成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "写入失败";
		}
	}
	
	
	public static void main(String[] args) {
		String pathName = "D://IOtest.txt";
		//String writeIn = writeIn(pathName);
		//System.out.println(writeIn);
//		System.out.println("*********************");
//		String readOut = readOut(pathName);
//		System.out.println(readOut);
//		System.out.println("*********************");
//		String readOutBuffer = readOutBuffer(pathName);
//		System.out.println(readOutBuffer);
		System.out.println("*********************");
		
		String content = writeFile();
		System.out.println(content);
		
		System.out.println("*********************");
		
		content = readeFile();
		System.out.println(content);
		
	}

}
