package com.springboot.zy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.springboot.zy.domain.Learndate;
import com.springboot.zy.domain.Users;

@Mapper
public interface LearndateMapper {
	
	@Select(value = {"select * from learndate"})
    @Results({
        @Result(property = "uId",  column = "u_id"),
        @Result(property = "webAddress",  column = "web_address"),
        @Result(property = "webName", column = "web_name")
    })
	public List<Learndate> getLearndate();
}
