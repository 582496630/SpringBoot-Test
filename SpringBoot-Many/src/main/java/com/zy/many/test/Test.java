package com.zy.many.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.util.FileUtil;
import jxl.biff.StringHelper;

public class Test {

	public static void main(String[] args) {
		/*
		 * Integer n = 10; Integer m = 10250; for (int i = 1; i < 12; i++) { n =
		 * n+n; System.out.println(n+"--"+i); if(m < n){
		 * System.out.println(i-1); break; } else if (m >= 20480 && i > 10) {
		 * System.out.println(11); } } Calendar a = Calendar.getInstance();
		 * a.set(Calendar.DATE, 1);//把日期设置为当月第一天 a.roll(Calendar.DATE,
		 * -1);//日期回滚一天，也就是最后一天 int maxDate = a.get(Calendar.DATE);
		 * System.out.println(maxDate); System.out.println((new
		 * Date()).getTime());
		 */

		/*
		 * String strs = ""; String[] interactUserIds = strs.split(","); for
		 * (String string : interactUserIds) { System.out.println(string+"***");
		 * } String codes = ""; System.out.println(interactUserIds
		 * +"\n"+"length:"+interactUserIds.length); List<String> codeList =
		 * Arrays.asList(codes.split(",")); System.out.println(codeList.size());
		 */

		/*
		 * Long time = 1510309105933L; SimpleDateFormat sdf= new
		 * SimpleDateFormat("yyyy-mm-dd HH:MM:ss,SSS");
		 * 
		 * Date date = new Date(time);
		 * 
		 * System.out.println(sdf.format(date)); String time1 =
		 * transferLongToDate("yyyy-mm-dd HH:MM:ss,SSS", time);
		 * System.out.println(time1);
		 * 
		 * double x = (double)2/(double)3; float y = (float)2/(float)3;
		 * System.out.println(x); System.out.println(y);
		 * 
		 * Long time2 = System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000L;
		 * System.out.println(new Date(time2));
		 */
		/*
		 * String interactIds = "334,255"; String[] interactUserIds =
		 * interactIds.split(","); System.out.println(interactUserIds.length);
		 * String x[] = {}; if (interactUserIds != null) { for (String userIdStr
		 * : x) { if (!com.zy.many.utils.StringUtils.isBlank(userIdStr)) {
		 * Integer interact = Integer.valueOf(userIdStr);
		 * System.out.println(interact); } } }
		 */
		String path = ",乐谱与原书不一致,音符错误";
		String errorType = ",乐谱与原书不一致,音符错误";

		String mainTitle1 = path.substring(0, 1);
		String mainTitle = path.substring(1, path.length());
		if (errorType.substring(0, 1).equals(",")) {
			errorType = errorType.substring(1, errorType.length());
			System.out.println(errorType);
		}
		System.out.println(errorType);

		String string = "1513841643000";
		Date date = new Date(Long.valueOf(string));
		System.out.println(date);

		Long userId = 188L;
		String deleteId = "188";
		System.out.println(deleteId.equals(userId.toString()));

		Integer sInteger = (10 % 2);
		System.out.println(sInteger);
		String path1 = Test.class.getResource("/templates/XML/my.xml").getFile();
		String path2 = Test.class.getResource("/templates/XML/my.xml").getPath();
		URL pathUrl = Test.class.getResource("/templates/XML/my.xml");
		System.out.println(path1 + "\n" + path2 + "\n" + pathUrl);

		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "a");
		map.put(1, "b");
		for (Integer key : map.keySet()) {
			System.out.println("key: " + key + "\t" + "value:" + map.get(key));
		}

		for (Map.Entry<Integer, String> key : map.entrySet()) {
			System.out.println("key: " + key.getKey() + "\t" + "value:" + key.getValue());
		}
		System.out.println(map.get(1));

		StringBuffer sb = new StringBuffer();
		String rootName = "D:\\1.zip";

		String[] list = rootName.split("\\.");
		for (String string2 : list) {

			System.out.println("*********" + string2);
		}
		System.out.println("*********" + list[list.length - 1]);

		ZipFile slf_zipFile = null;
		try {
			slf_zipFile = new ZipFile(rootName);
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		Enumeration e = slf_zipFile.getEntries();
		while (e.hasMoreElements()) {
			ZipEntry slf_zipEntry = (ZipEntry) e.nextElement();
			if (!slf_zipEntry.isDirectory()) {
				String filename = slf_zipEntry.getName().substring(slf_zipEntry.getName().lastIndexOf("/") + 1,
						slf_zipEntry.getName().length());
				sb.append(filename + "\n");
			}
		}
		System.out.println(sb);

		try {
			// readByApacheZipFile("D:\\1.zip","D:");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// 保存路径
		String savefile = "/static/course/" + "12313454331313";
		// 上传路径
		String filePath = "klsw-piano.oss-cn-hangzhou.aliyuncs.com" + savefile;
		String string2 = filePath.substring(filePath.lastIndexOf("static"));

		System.out.println(string2);

		File file = new File("D:\\cache");
		FileInputStream input;
		try {
			input = new FileInputStream(file);
			MultipartFile uploadFile = new MockMultipartFile(file.getName(), input);
			System.out.println(uploadFile);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	/*
	 * private static String transferLongToDate(String dateFormat,Long millSec){
	 * SimpleDateFormat sdf = new SimpleDateFormat(dateFormat); Date date= new
	 * Date(millSec); return sdf.format(date); }
	 */

	public static void readByApacheZipFile(String archive, String decompressDir) throws Exception {
		BufferedInputStream bi;

		ZipFile zf = new ZipFile(archive, "GBK");// 支持中文

		Enumeration e = zf.getEntries();
		while (e.hasMoreElements()) {
			ZipEntry ze2 = (ZipEntry) e.nextElement();

			InputStream inputStream = zf.getInputStream(ze2);
			/*
			 * byte b[] = new byte[10]; //byte b[] = new byte[1024]; String str
			 * = ""; StringBuffer buffer = new StringBuffer(); while
			 * (inputStream.read(b) != -1) { str = new String(b,"utf-8");
			 * buffer.append(str); } System.out.println("文档的内容："+
			 * buffer.toString());
			 */

			String entryName = ze2.getName();
			String path = decompressDir + "/" + entryName;
			if (ze2.isDirectory()) {
				System.out.println("正在创建解压目录 - " + entryName);
				File decompressDirFile = new File(path);
				if (!decompressDirFile.exists()) {
					decompressDirFile.mkdirs();
				}
			} else {
				System.out.println("正在创建解压文件 - " + entryName);
				String fileDir = path.substring(0, path.lastIndexOf("/"));
				File fileDirFile = new File(fileDir);
				if (!fileDirFile.exists()) {
					fileDirFile.mkdirs();
				}
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(decompressDir + "/" + entryName));

				bi = new BufferedInputStream(zf.getInputStream(ze2));
				byte[] readContent = new byte[1024];
				int readCount = bi.read(readContent);
				while (readCount != -1) {
					bos.write(readContent, 0, readCount);
					readCount = bi.read(readContent);
				}
				bos.close();

				File file = new File("D:\\2");
				inputstreamtofile(inputStream, file);
				inputStream = new FileInputStream(file);
				byte b[] = new byte[10];
				// byte b[] = new byte[1024];
				String str = "";
				StringBuffer buffer = new StringBuffer();
				while (inputStream.read(b) != -1) {
					str = new String(b, "utf-8");
					buffer.append(str);
				}
				System.out.println("文档的内容：" + buffer.toString());
			}
		}
		zf.close();
	}

	public static void inputstreamtofile(InputStream ins, File file) throws Exception {
		OutputStream os = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		ins.close();
	}

}
