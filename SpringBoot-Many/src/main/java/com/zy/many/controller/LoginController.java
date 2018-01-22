package com.zy.many.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.springboot.many.model.Users;
import com.zy.many.service.UserServices;

/**
 * 
 * 登录页面与
 * 
 * @author zhouyou
 * @version 2017-07-29 16:22:54
 */
@Controller
public class LoginController {
	
	private static String ERROR = "error";

	@Autowired
	private UserServices userServices;

	/**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/tologin")
	public String tologin() {
		return "login";
	}

	/**
	 * 用户登陆 逻辑判断
	 * 
	 * @param username
	 * @param pwd
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "pwd", required = false) String pwd, HttpServletRequest request, Model model) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Users users = new Users();
			if (StringUtils.isEmpty(username)) {
				map.put(ERROR, "请填写用户名！");
				return map;
			}
			if (StringUtils.isEmpty(pwd)) {
				map.put(ERROR, "请输入密码！！");
				return map;
			}
			// 判断用户名是否存在
			users.setUsername(username);
			users = userServices.selectOne(users);
			if (users == null) {
				map.put(ERROR, "用户名不存在！");
				return map;
			}
			if (!users.getPwd().equals(pwd)) {
				/*
				 * msg = "密码不对！"; model.addAttribute("msg", msg);
				 */
				map.put(ERROR, "密码不对！");
				return map;
			}

			// 把登录用户名放入session中，用于layout.html显示登录用户名
			request.getSession().setAttribute("loginname", users.getUsername());
			// 把登陆用户ID放入session中，后期逻辑多有涉及用户id，（判断是否登录，用户权限等）
			request.getSession().setAttribute("loginId", users.getId());

			System.out.println(users);
			return users;
		} catch (Exception e) {
			map.put(ERROR, "登录异常");
			return map;
		}
	}
	/**
	 * 退出登录
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/exitLogin")
	public String exitLogin(Model model,HttpServletRequest request){
		String username = (String) request.getSession().getAttribute("loginname");
		request.getSession().invalidate();//使session失效
		//request.getSession().removeAttribute("loginname");;//移除session中的某一个属性
		
		System.out.println("用户“"+username+"”已退出");
		return "redirect:tologin";
	}
	/**
	 * 跳转到警告页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/errorHtml")
	public String error(Model model){
		model.addAttribute(ERROR, "用户未登录，请先登录");
		return "error";
	}
}
