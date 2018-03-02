package com.zy.many.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.many.model.Users;
import com.zy.many.service.UserServices;

/**
 * 用户资料相关
 * 
 * @author zhouyou
 * @version 2017-08-02 09:19:37
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static String ERROR = "error";
	private static String SUCCESS = "success";

	@Autowired
	private UserServices userServices;

	/**
	 * 获取用户信息列表 user.html 前期用于测试 暂时不在项目中
	 * 
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/userList")
	public String selctUserList(Model model) throws Exception {
		Users users = new Users();
		List<Users> list = userServices.select(users);
		model.addAttribute("list", list);
		return "user";
	}

	/**
	 * 根据id获取登录用户个人信息
	 * 
	 * @param request
	 * @param model
	 * @return indexInformation.html
	 * @throws Exception
	 */
	@RequestMapping(value = "/indexInformation")
	public String selectUserOne(HttpServletRequest request, Model model) {
		// 获取登录用户的用户id
		Integer id = (Integer) request.getSession().getAttribute("loginId");
		if (id == null) {
			return "redirect:/errorHtml";
		}
		Users user = new Users();
		user.setId(id);
		user = userServices.selectOne(user);
		model.addAttribute("user", user);

		return "indexInformation";
	}

	/**
	 * 根据id获取登录用户个人信息
	 * 
	 * @param request
	 * @param model
	 * @return user/information.html
	 * @throws Exception
	 */
	@RequestMapping(value = "/information", method = RequestMethod.GET)
	public String selectUser(HttpServletRequest request, Model model) {

		// 获取用户对应的资源
		// List<Resource> list =
		// resourceService.findResourcesByUserId(userEntity.getId());
		// List<Resource> treeList = new
		// TreeUtil().getChildResourceEntitys(list, null);
		// request.getSession().setAttribute("list", treeList);

		// 获取登录用户的用户id
		Integer id = (Integer) request.getSession().getAttribute("loginId");
		if (id == null) {
			model.addAttribute(ERROR, "请先登录");
			return "error";
		}
		Users user = new Users();
		user.setId(id);
		user = userServices.selectOne(user);
		model.addAttribute("user", user);
		return "user/information";

	}

	/**
	 * 跳转到 修改用户信息页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editUI")
	public String editUI(Model model, HttpServletRequest request) {
		Integer id = (Integer) request.getSession().getAttribute("loginId");
		if (id == null) {
			return "redirect:/errorHtml";
		}
		Users users = new Users();
		users.setId(id);
		users = userServices.selectOne(users);
		model.addAttribute("user", users);
		return "user/updateInformation";
	}

	/**
	 * 修改登录用户的个人信息
	 * 
	 * @param users
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	@ResponseBody
	public Object editUser(Users users, Model model, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Integer id = (Integer) request.getSession().getAttribute("loginId");
			if (id == null) {
				return "redirect:/errorHtml";
			}
			// 因为更改方法是根据主键，所以要设置用户的id属性
			users.setId(id);
			userServices.updateOne(users);
			map.put(SUCCESS, "修改成功");
			return map;

		} catch (Exception e) {
			map.put(ERROR, "修改失败");
			return map;
		}
	}
}
