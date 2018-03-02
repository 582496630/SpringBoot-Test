package com.springboot.many.mapping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springboot.many.mapping.MyMapper;
import com.springboot.many.model.Learndate;

public interface LearndateMapper extends MyMapper<Learndate> {
	
	public Integer deleteByIds(@Param("learnIds") List<Integer> ids);
}