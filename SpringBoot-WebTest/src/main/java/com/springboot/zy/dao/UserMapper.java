package com.springboot.zy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.springboot.zy.domain.Learndate;
import com.springboot.zy.domain.Users;

@Mapper
public interface UserMapper {
	
	@Select(value = {"select * from users"})
	public List<Users> getUsers();
}
