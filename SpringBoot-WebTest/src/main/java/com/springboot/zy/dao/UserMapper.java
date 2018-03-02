package com.springboot.zy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.springboot.zy.domain.Users;
import com.springboot.zy.util.MyMapper;

@Mapper
public interface UserMapper extends MyMapper<Users>{
	
	//@Select(value = {"select * from users"})
	public List<Users> getUsers();
	@Select(value = {"select * from users where id = #{id}"})
	public Users getUser(@Param("id") Integer id);
}
