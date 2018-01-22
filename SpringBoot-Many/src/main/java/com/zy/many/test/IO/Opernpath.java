package com.zy.many.test.IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Opernpath {
	private final static Charset charset = Charset.forName("UTF-8");
	public static void main(String[] args) {
		File file = new File("D:\\test.txt");
		FileInputStream inputStream = null;
		BufferedReader bReader = null;
			try {
				bReader = new BufferedReader(new FileReader(file));
				String str = "";
				StringBuffer buffer = new StringBuffer();
				List<String> list = new ArrayList<String>();
				while ((str = bReader.readLine()) != null) {
					list.add(str);
					buffer.append(str);
				}
				System.out.println("文档的内容："+ buffer.toString());
				System.out.println(list);
				
				for (String string : list) {
					List<String> strList = Arrays.asList(string.split("/"));
					Integer len = strList.size();
					System.out.println(strList.get(5));
					System.out.println(strList.get(len-2).substring(strList.get(6).indexOf(".") +1));
					System.out.println(strList.get(len-1).substring(strList.get(7).indexOf(".") +1));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("********************");
			String title = "01NO.一级";
			String title2 = "001.白月光（节选）.tmf";
			String title3 = "07c2db00068c33487e119a4be97f2967	/usr/share/tasco/midList/考级教程/03NO.英国皇家音乐学院联合委员会钢琴考级作品/02NO.二　　　级/009.C3福音乐风-《迷你爵士乐》第1册，第43首.tmf";
			title3 =title3.substring(title3.indexOf("midList/") +8);
			title = title.substring(title.indexOf(".") +1);
			title2 = title2.substring(title2.indexOf(".") +1,title2.lastIndexOf("."));
			System.out.println(title);
			System.out.println(title2);
			System.out.println(title3);
	}

}
