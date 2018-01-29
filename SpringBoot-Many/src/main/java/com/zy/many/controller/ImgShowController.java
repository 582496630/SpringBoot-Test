package com.zy.many.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.many.model.Imgshow;
import com.zy.many.service.ImgShowService;
import com.zy.many.utils.Ret;
import com.zy.many.utils.StringUtils;

/**
 * 图片相关
 * 
 * @author zhouyou
 * @version 2017-08-02 09:18:30
 */
@RequestMapping(value = "/img")
@Controller
public class ImgShowController {

	private static String ERROR = "error";
	private static String SUCCESS = "success";

	@Autowired
	private ImgShowService imgShowService;

	/**
	 * 图片上传 上传文件到/webapp/uploads/img/
	 * 
	 * @param files
	 * @param request
	 * @return
	 */
	// requestParam要写才知道是前台的那个数组;获取input-file 中的文件
	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public Object filesUpload(@RequestParam("myImgs") MultipartFile[] files, HttpServletRequest request, Model model) {

		try {
			Integer type = null;
			// 判断上传的文件是否为空
			for (MultipartFile multipartFile : files) {
				if (StringUtils.isBlank(multipartFile.getOriginalFilename())) {
					type = 0;// 上传文件为空
				} else {
					type = 1;// 上传文件不为空
				}
			}
			// 获取登录用户的用户id
			Integer id = (Integer) request.getSession().getAttribute("loginId");
			// 判断用户是否登录
			if (id == null) {
				model.addAttribute(ERROR, "请先登录");
				return "error";
			}
			List<String> list = new ArrayList<String>();
			// 判断文件不为空，并保存文件
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					MultipartFile file = files[i];
					// 保存文件
					list = saveFile(request, file, list, id);
				}
			}
			return "redirect:/img/downloadfile?success=" + type;// 上传成功后跳转的下载（获取图片并显示到前端）
		} catch (Exception e) {
			model.addAttribute(ERROR, "上传失败");
			return "error";
		}
	}

	/**
	 * 上传时保存文件的方法
	 * 
	 * @param request
	 * @param file
	 * @param list
	 * @param id
	 * @return
	 */
	private List<String> saveFile(HttpServletRequest request, MultipartFile file, List<String> list, Integer id) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 把上传的图片/文件的相关属性设置好，并存入数据库内
				String filePathSql = "uploads/img/";
				Imgshow imgshow = new Imgshow();
				imgshow.setuId(id);
				imgshow.setImgName(file.getOriginalFilename());
				imgshow.setImgAddress(filePathSql);
				// 把图片相关信息写入数据库中
				imgShowService.addImg(imgshow);

				// 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
				// 拼接完整的上传路径与文件名；及上传到webapp中的uploads/img文件夹下
				String filePath = request.getSession().getServletContext().getRealPath("/") + filePathSql
						+ file.getOriginalFilename();
				// 把文件名放入list中
				list.add(file.getOriginalFilename());
				// 根据路径创建file
				File saveDir = new File(filePath);
				// 如果路径中的文件夹不存在，则创建文件夹
				if (!saveDir.getParentFile().exists())
					saveDir.getParentFile().mkdirs();
				// 转存文件，源文件file转存到 saveDir（规定路径下的file）
				file.transferTo(saveDir);
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 展示、下载 图片/文件
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/downloadfile")
	public String download(HttpServletRequest request, Model model) {
		String success = request.getParameter("success");
		Integer s = null;
		// 根据success的值判断 是否有上传文件
		if (!StringUtils.isBlank(success)) {
			s = Integer.valueOf(success);
			// 判断是否有上传文件，0 为空 1 为有文件
			if (s == 0) {
				model.addAttribute(ERROR, "请选择需要上传的图片");
			} else if (s == 1) {
				model.addAttribute(SUCCESS, "上传成功！");
			}
		}
		// 获取页数
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			PageHelper.startPage(1, 5);
		} else {
			PageHelper.startPage(Integer.parseInt(pageNum), 5);
		}
		// 从数据库查出所有值
		List<Imgshow> imgLists = imgShowService.selectImgs();
		/**
		 * 把imgLists 当作一个属性添加进分页当中，并放入fileNameList(数据list+分页属性)中，
		 * 前端可以直接遍历fileNameList.list，获取相关属性值
		 */
		PageInfo<Imgshow> PageInfo = new PageInfo<Imgshow>(imgLists);
		model.addAttribute("fileNameList", PageInfo);

		return "imgLook/img";
	}

	/**
	 * 从数据库中删除选中的图片（可批量）
	 * 
	 * @param ids
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteImg")
	@ResponseBody
	public Ret deleteImg(@RequestParam(value = "ids") String ids, HttpServletRequest request, Model model) {
		try {
			String loginName = (String) request.getSession().getAttribute("loginname");
			if (StringUtils.isBlank(loginName)) {
				/*
				 * map.put(ERROR, "用户未登录，请先登录"); return map;
				 */
				return Ret.error("错误信息！");
			}
			List<String> idList = Arrays.asList(ids.split(","));
			List<Integer> list = new ArrayList<Integer>();
			for (String string : idList) {
				Integer id = Integer.valueOf(string);
				list.add(id);
			}
			int count = imgShowService.deleteImgs(list);
			if (count == idList.size()) {
				/*
				 * map.put(SUCCESS, "删除成功"); map.put("img", "111"); return map;
				 */
				Ret ret = Ret.success("删除成功");
				return ret;
			} else {
				/*
				 * map.put(ERROR, "删除不完整"); return map;
				 */
				return Ret.error("删除不完整");
			}
		} catch (Exception e) {
			/*
			 * map.put(ERROR, "删除出现异常"); return map;
			 */
			return Ret.error("删除出现异常");
		}

	}

}