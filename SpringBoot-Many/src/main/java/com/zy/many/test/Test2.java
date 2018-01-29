package com.zy.many.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Test2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(Test2.class.getResource("/").getFile());
		System.out.println(Test2.class.getProtectionDomain().getCodeSource().getLocation().getPath());

		Set<String> set = new LinkedHashSet<String>();
		String[] array = set.toString().split(",");
		System.out.println(array.length);

		System.out.println("---------分割线----------");
		// map迭代器遍历
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "a");
		map.put("2", "b");
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			System.out.println(key);
			System.out.println("key: " + key + "--" + "value: " + map.get(key));
		}
		System.out.println("---------分割线----------");
		for (String key : map.keySet()) {
			System.out.println("key: " + key + "--" + "value: " + map.get(key));
		}
		System.out.println("---------分割线----------");
		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry);
			String string = entry.toString().replaceAll("=", " ====》 ");
			System.out.println(string);
		}

		System.out.println(3 / 4);
		double d = (double) 3 / (double) 4;
		System.out.println(d);

		System.out.println("---------分割线----------");
		Iterator<String> iterator2 = map.keySet().iterator();
		String key;
		String value;
		while (iterator2.hasNext()) {
			key = iterator2.next();
			System.out.println(key + "===>" + map.get(key));
		}
		for (String key1 : map.keySet()) {
			System.out.println(key1 + "===>" + map.get(key1));
		}
		for (Entry<String, String> entry : map.entrySet()) {

			String mapStr = entry.toString();
			System.out.println(mapStr);
			key = mapStr.substring(0, mapStr.lastIndexOf("="));
			System.out.println("key: " + key);
			value = mapStr.substring(mapStr.lastIndexOf("=") + 1, mapStr.length());
			System.out.println("value: " + value);
			System.out.println("key: " + entry.getKey());
			System.out.println("value: " + entry.getValue());
		}

		String pt = Test2.class.getResource("/").getFile();
		System.out.println(pt);
		pt = pt.substring(0, pt.lastIndexOf("/"));
		System.out.println(pt);
		pt = pt.substring(0, pt.lastIndexOf("/"));
		System.out.println(pt);
		pt = pt.substring(0, pt.lastIndexOf("/"));
		System.out.println(pt);
		String path = pt + "/testIO.txt";

		File fil = new File(path);

		// FileOutputStream outputStream = new FileOutputStream(fil);
		FileInputStream inputStream = new FileInputStream(fil);
		String str = "你好，这是测试数据";
		byte[] bs = str.getBytes();
		System.out.println(bs.length);
		// outputStream.write(bs);
		// outputStream.flush();
		// outputStream.close();

		byte inbt[] = new byte[1024];
		// inputStream.read(inbt, 0, 15);

		// byte 转 String
		String instr = new String(inbt, "utf-8");
		System.out.println(instr);
		/**
		 * 注：国家标准GB2312： 一个汉字＝2个字节，UTF－8：一个汉字＝3个字节
		 */
		StringBuffer sBuffer = new StringBuffer();
		while (inputStream.read(inbt, 0, 1024) != -1) {
			instr = new String(inbt, "utf-8");
			sBuffer.append(instr);
		}
		System.out.println("FileInput: " + sBuffer);

		String copePath = pt + "/cope.txt";

		InputStreamReader inReader = new InputStreamReader(inputStream);
		int length;
		char c[] = new char[3];
		while ((length = inReader.read(c, 0, 3)) != -1) {
			// char 转 String
			instr = new String(c, 0, length);
			sBuffer.append(instr);
		}
		System.out.println("InputStreamReader : " + sBuffer);
		inReader.close();
		inputStream.close();

		File file = new File(copePath);
		FileOutputStream outStream = new FileOutputStream(file);
		outStream.write(sBuffer.toString().getBytes());
		outStream.flush();
		outStream.close();

	}

}
