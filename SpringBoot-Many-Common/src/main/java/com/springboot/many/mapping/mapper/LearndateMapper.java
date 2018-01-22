package com.springboot.many.mapping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springboot.many.model.Learndate;
import tk.mybatis.mapper.common.Mapper;

public interface LearndateMapper extends Mapper<Learndate> {
	
	public Integer deleteByIds(@Param("learnIds") List<Integer> ids);
}