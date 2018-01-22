package com.zy.many.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.many.model.Learndate;
import com.zy.many.excel.ExcelOperater;
import com.zy.many.service.LearnService;
import com.zy.many.utils.Ret;
import com.zy.many.utils.StringUtils;

/**
 * 学习网站相关
 * 
 * @author zhouyou
 * @version  2017-08-02 09:19:06
 */
@Controller
@RequestMapping(value = "/learn")
public class LearnController {
	private static String ERROR = "error";
	private static String SUCCESS = "success";

	@Autowired
	private LearnService learnService;
	@Autowired
	private ExcelOperater excelOperater;
/**
 * 获取登录用户相关的学习网址
 * @param model
 * @param request
 * @return
 */
	@RequestMapping(value = "/learns")
	public String selectLearns(Model model, HttpServletRequest request) {
		// 获取登录用户的用户id
		Integer id = (Integer) request.getSession().getAttribute("loginId");
		//获取分页的页数
		String pageNum = request.getParameter("pageNum");
		//判断页数，为空则取默认值，不为空则取获取的值
		if (pageNum == null) {
			PageHelper.startPage(1, 5);
		} else {
			PageHelper.startPage(Integer.parseInt(pageNum), 5);
		}
		List<Learndate> learndateList = new ArrayList<Learndate>();
		//判断登录用户的id是否为空
		if (id == null) {
			model.addAttribute(ERROR, "请先登录");
			PageInfo<Learndate> PageInfo = new PageInfo<Learndate>(learndateList);
			model.addAttribute("learnList", PageInfo);
			return "learn/learndate";
		}
		//根据用户id获取此用户相关的网址信息
		learndateList = learnService.selectLearns(id);
		//判断之前有没有添加过信息
		if (learndateList.isEmpty()) {
			model.addAttribute(ERROR, "您还没有学习资料，请添加！");
			PageInfo<Learndate> PageInfo = new PageInfo<Learndate>(learndateList);
			model.addAttribute("learnList", PageInfo);
			return "learn/learndate";
		}
		//把learndateList 当作一个属性添加进分页当中，并放入learnList(数据list+分页属性)中，前端可以直接遍历learnList.list
		PageInfo<Learndate> PageInfo = new PageInfo<Learndate>(learndateList);
		model.addAttribute("learnList", PageInfo);
		return "learn/learndate";
	}
	/**
	 * 跳转到添加信息的窗口
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addWebUI")
	public String addWebUI(Model model,HttpServletRequest request) {
		Integer id = (Integer) request.getSession().getAttribute("loginId");
		if (id == null) {
			model.addAttribute(ERROR, "请先登录");
			return "error";
		}
		return "learn/addWeb";
	}
	/**
	 * 添加学习网址
	 * @param learndate
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addLearnWeb")
	@ResponseBody
	public Map<String, String> addLearnWeb(Learndate learndate,HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		// 获取登录用户的用户id
		Integer id = (Integer) request.getSession().getAttribute("loginId");
		//判断用户是否登录
		if (id == null) {
			map.put(ERROR, "用户未登录，请登录");
			return map;
		}
		//判断添加的新值是否为空
		if (learndate.getWebAddress().isEmpty() || learndate.getWebName().isEmpty()) {
			map.put(ERROR, "“网站名称”与“网站地址”不可为空！");
			return map;
		}
		//判断添加的内容是否已存在
		List<Learndate> learndateList = learnService.selectLearns(id);
		for (Learndate learndate2 : learndateList) {
			if (learndate2.getWebName().equals(learndate.getWebName())) {
				map.put(ERROR, "“网站名称”已存在，请修改");
				return map;
			}
			if (learndate2.getWebAddress().equals(learndate.getWebAddress())) {
				map.put(ERROR, "网站已添加，请查看");
				return map;
			}
		}
		//添加到数据库
		learndate.setuId(id);
		learnService.addWeb(learndate);
		map.put(SUCCESS, "添加成功");
		return map;
	}
	/**
	 * 删除选中的信息（可批量）
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteLearnWeb")
	@ResponseBody
	public Map<String, String> deleteLearnWeb(@RequestParam(value="ids") String ids,
			HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		//判断前端是否传来id集
		if (StringUtils.isBlank(ids)) {
			map.put(ERROR, "请选择需要删除的选项");
			return map;
		}
		//分割id字符串集为list
		List<String> idLists = Arrays.asList(ids.split(","));
		List<Integer> idList = new ArrayList<Integer>();
		//遍历并把String类型的list转换为Integer类型的list
		for (String idS : idLists) {
			Integer id = Integer.valueOf(idS);
			idList.add(id);
		}
		//根据ids批量删除并返回删除的个数
		int count = learnService.deleteWeb(idList);
		//判断，删除的个数是否与list的长度一致
		if (count == idList.size()) {
			map.put(SUCCESS, "删除成功");
			return map;
		} else {
			map.put(ERROR, "删除失败");
			return map;
		}
	}
	
	/**
	 * 导出学习网站信息到本地excel中
	 * @param pathName
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/outExcel")
	@ResponseBody
	public Ret outExcel(HttpServletRequest request){
		Integer userId = (Integer) request.getSession().getAttribute("loginId");
		try {
			String pathName = request.getSession().getServletContext()
					 .getRealPath("/") + "excel/learnData.xls";
			excelOperater.excelIn(userId, pathName);
		} catch (IOException e) {
			e.printStackTrace();
			return Ret.error("信息导出异常");
		}
		return Ret.success("信息导出成功");
	}
	
}
