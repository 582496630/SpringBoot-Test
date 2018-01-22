package com.zy.many.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * 初次接触jxl.jar 导入导出数据到Excel（与项目无关）
 * 
 * @author zhouyou
 * @version  2017-08-10 17:38:41
 */
public class ExcelBook {
	
	// 将数据导入到Excel中
	public void excelIn() throws IOException {
		// 创建Excel对象
		WritableWorkbook bWorkbook = Workbook.createWorkbook(new File("D:/book.xls"));
		// 通过Excel对象创建一个选项卡对象
		WritableSheet sheet = bWorkbook.createSheet("sheet1", 0);
		//用LinkedHashMap，可以保证迭代输出的顺序和输入的一样
		Map<String, List<Book>> mapIn = new LinkedHashMap<String, List<Book>>();
		//第一组数据
		ArrayList<Book> arrayList = new ArrayList<Book>();
		Book bo = new Book();
		bo.setId(1);
		bo.setName("酒馆");
		bo.setType("生活");

		Book bo1 = new Book();
		bo1.setId(2);
		bo1.setName("酒馆1");
		bo1.setType("生活1");

		arrayList.add(bo);
		arrayList.add(bo1);

		String key = "Book1";
		mapIn.put(key, arrayList);

		
		//第二组数据
		ArrayList<Book> arrayList2 = new ArrayList<Book>();
		Book bo2 = new Book();
		bo2.setId(1);
		bo2.setName("街边");
		bo2.setType("生活");
		
		Book bo3 = new Book();
		bo3.setId(2);
		bo3.setName("街边1");
		bo3.setType("生活1");
		
		arrayList2.add(bo2);
		arrayList2.add(bo3);
		
		String key2 = "Book2";
		mapIn.put(key2, arrayList2);
		//第三组数据
		ArrayList<Book> arrayList3 = new ArrayList<Book>();
		Book bo4 = new Book();
		bo4.setId(1);
		bo4.setName("故事");
		bo4.setType("生活");
		
		Book bo5 = new Book();
		bo5.setId(2);
		bo5.setName("故事1");
		bo5.setType("生活1");
		
		arrayList3.add(bo4);
		arrayList3.add(bo5);
		
		String key3 = "Book3";
		mapIn.put(key3, arrayList3);
		
		
		
		try {

			// 把单元格（column, row）到单元格（column1, row1）进行合并。
			// mergeCells(column, row, column1, row1);
			sheet.mergeCells(0, 0, 2, 0);// 单元格合并方法
			sheet.mergeCells(3, 0, 5, 0);// 单元格合并方法
			sheet.mergeCells(6, 0, 8, 0);// 单元格合并方法

			// 创建WritableFont 字体对象，参数依次表示黑体、字号12、粗体、非斜体、不带下划线、亮蓝色
			WritableFont titleFont = new WritableFont(WritableFont.createFont("黑体"), 12, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.LIGHT_BLUE);
			// 创建WritableCellFormat对象，将该对象应用于单元格从而设置单元格的样式
			WritableCellFormat titleFormat = new WritableCellFormat();
			// 设置字体格式
			titleFormat.setFont(titleFont);
			// 设置文本水平居中对齐
			titleFormat.setAlignment(Alignment.CENTRE);
			// 设置文本垂直居中对齐
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 设置背景颜色
			titleFormat.setBackground(Colour.GRAY_25);
			// 设置自动换行
			titleFormat.setWrap(true);

			
			//获取写入数据的位置 列
			Integer col = 0;
			Integer n = 0;
			for (Entry<String, List<Book>> entry : mapIn.entrySet()) {
				entry.getKey();
				// 添加Label对象，参数依次表示在第一列，第一行，内容，使用的格式
				Label lab_00 = new Label(col, 0, entry.getKey(), titleFormat);
				// 写入标题
				sheet.addCell(lab_00);
				
				// 使用循环将数据写入
				for (int i = 0; i < entry.getValue().size(); i++) {
					Book book = entry.getValue().get(i);
					Label label = new Label(col, i + 1, String.valueOf(book.getId()));
					Label label1 = new Label(col+1, i + 1, String.valueOf(book.getName()));
					Label label2 = new Label(col+2, i + 1, String.valueOf(book.getType()));
					sheet.addCell(label);
					sheet.addCell(label1);
					sheet.addCell(label2);
					
				}
				n++;
				col = n*3;
			}
			// 写入目标路径
			bWorkbook.write();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bWorkbook.close();
			} catch (WriteException | IOException e) {
				e.printStackTrace();
			}
		}

	}

	// 将Excel中的数据导出 到arraylist中
	public Map<String, List<Book>> excelOut(Integer keyid) {
		ArrayList<Book> arrayList = new ArrayList<Book>();
		Map<String, List<Book>> map = new HashMap<String, List<Book>>();
		Workbook bWorkbook = null;
		try {
			Integer col = keyid*3;
			bWorkbook = Workbook.getWorkbook(new File("D:/book.xls"));
			Sheet sheet = bWorkbook.getSheet(0);
			// 获取第一行数据内容 为map的key
			String string = sheet.getCell(col, 0).getContents();
			// 遍历book属性到list中为map的value
			for (int i = 1; i < sheet.getRows(); i++) {
				Book book = new Book();
				// 获取单元格对象
				Cell cell = sheet.getCell(col, i); // (列，行)
				// 获取单元格的值
				book.setId(Integer.valueOf(cell.getContents()));
				book.setName(sheet.getCell(col+1, i).getContents());
				book.setType(sheet.getCell(col+2, i).getContents());
				arrayList.add(book);
			}
			map.put(string, arrayList);

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bWorkbook.close();
		}
		return map;
	}

	public static void main(String[] args) throws IOException {

		
		// 将数据导入到Excel中
		ExcelBook book = new ExcelBook();
		book.excelIn();
	
		
		
		// 将数据从Excel中导出 并遍历打印到控制台
		Integer keyid =3;//获取几组数据
		for (int i = 0; i < keyid; i++) {
			Map<String, List<Book>> map = book.excelOut(i);
			System.out.println(map);
			// System.out.println("通过Map.entrySet遍历key和value");
			for (Entry<String, List<Book>> entry : map.entrySet()) {
				System.out.println("key:\n " + entry.getKey() + "\nvalue:");
				for (Book book2 : entry.getValue()) {
					System.out.println(book2.getName() + book2.getType());
				}
			}
		}
	}
}
