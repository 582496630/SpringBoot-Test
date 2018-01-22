package com.zy.many.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.many.mapping.mapper.UsersMapper;
import com.springboot.many.model.Users;

/**
 * 用户相关services
 * 
 * @author zhouyou
 * @version  2017-08-02 09:21:35
 */
@Service
public class UserServices {
	
	@Autowired
	private UsersMapper usersMapper;
	
	/**
	 * 查找所有用户信息
	 * @return
	 */
	public List<Users> selectUser(){
		return usersMapper.selectAll();
	}
	/**
	 * 根据实体中的属性查询用户信息，只有一个返回值
	 * @param users
	 * @return Users
	 */
	public Users selectOne(Users users){
		return usersMapper.selectOne(users);
	}
	/**
	 * 根据用户主键id 查询用户信息
	 * @param id
	 * @return
	 */
	public Users selectByPrimaryKey(Integer id){
		return usersMapper.selectByPrimaryKey(id);
	}
	/**
	 * 根据主键更新属性不为null的值
	 * @param users
	 */
	public void updateOne(Users users){
		usersMapper.updateByPrimaryKeySelective(users);
	}
}
