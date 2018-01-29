package com.zy.many.excel;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.many.model.Learndate;
import com.springboot.many.model.Users;
import com.zy.many.service.LearnService;
import com.zy.many.service.UserServices;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Excel 相关操作
 * 
 * @author zhouyou
 * @version
 */
@Service
public class ExcelOperater {

	@Autowired
	private UserServices userServices;
	@Autowired
	private LearnService learnService;

	public void excelIn(Integer userId, String pathname) throws IOException {
		// 获取用户信息
		Users users = userServices.selectByPrimaryKey(userId);
		// 获取用户对应的学习网址信息
		List<Learndate> learndates = learnService.selectLearns(userId);

		Map<Users, List<Learndate>> map = new HashMap<Users, List<Learndate>>();
		// 需要打印的信息放入map中
		map.put(users, learndates);

		File file = new File(pathname);

		// 创建Excel对象
		WritableWorkbook bWorkbook = Workbook.createWorkbook(file);
		// 通过Excel对象创建一个选项卡对象
		WritableSheet sheet = bWorkbook.createSheet("sheet1", 0);

		try {
			/**
			 * 格式
			 */
			WritableCellFormat titleFormat = new WritableCellFormat();
			// 设置文本水平居中对齐
			titleFormat.setAlignment(Alignment.CENTRE);
			// 设置文本垂直居中对齐
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

			/**
			 * 导出用户信息
			 */
			// 把单元格（column, row）到单元格（column1, row1）进行合并。
			// mergeCells(column, row, column1, row1);
			sheet.mergeCells(0, 0, 4, 0);// 单元格合并方法
			Label labU = new Label(0, 0, "用户信息", titleFormat);// 用户信息表头
			Label lab = new Label(0, 1, "用户ID");// 用户信息字段
			Label lab1 = new Label(1, 1, "用户名");
			Label lab2 = new Label(2, 1, "密码");
			Label lab3 = new Label(3, 1, "年龄");
			Label lab4 = new Label(4, 1, "地址");
			sheet.addCell(labU);
			sheet.addCell(lab);
			sheet.addCell(lab1);
			sheet.addCell(lab2);
			sheet.addCell(lab3);
			sheet.addCell(lab4);
			/**
			 * 导出学习网址信息
			 */
			sheet.mergeCells(0, 3, 3, 3);// 单元格合并方法
			Label labL = new Label(0, 3, "用户学习网址信息", titleFormat);// 学习网站信息表头
			Label labl = new Label(0, 4, "序号");// 学习网站信息字段
			Label labl1 = new Label(1, 4, "ID");
			Label labl2 = new Label(2, 4, "网站名称");
			Label labl3 = new Label(3, 4, "网站地址");
			sheet.addCell(labL);
			sheet.addCell(labl);
			sheet.addCell(labl1);
			sheet.addCell(labl2);
			sheet.addCell(labl3);
			// 用户信息内容
			for (Entry<Users, List<Learndate>> entry : map.entrySet()) {
				Label label = new Label(0, 2, String.valueOf(users.getId()));
				Label label1 = new Label(1, 2, String.valueOf(users.getUsername()));
				Label label2 = new Label(2, 2, String.valueOf(users.getPwd()));
				Label label3 = new Label(3, 2, String.valueOf(users.getAge()));
				Label label4 = new Label(4, 2, String.valueOf(users.getAddress()));
				sheet.addCell(label);
				sheet.addCell(label1);
				sheet.addCell(label2);
				sheet.addCell(label3);
				sheet.addCell(label4);
				// 学习网站信息内容
				for (int i = 0; i < entry.getValue().size(); i++) {
					Learndate learndate = entry.getValue().get(i);
					Label label5 = new Label(0, i + 5, String.valueOf(i + 1));
					Label label6 = new Label(1, i + 5, String.valueOf(learndate.getId()));
					Label label7 = new Label(2, i + 5, String.valueOf(learndate.getWebName()));
					Label label8 = new Label(3, i + 5, String.valueOf(learndate.getWebAddress()));
					sheet.addCell(label5);
					sheet.addCell(label6);
					sheet.addCell(label7);
					sheet.addCell(label8);
				}
			}
			// 写入目标路径
			bWorkbook.write();
		} catch (WriteException e) {
			e.printStackTrace();
		} finally {
			try {
				bWorkbook.close();
			} catch (WriteException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
