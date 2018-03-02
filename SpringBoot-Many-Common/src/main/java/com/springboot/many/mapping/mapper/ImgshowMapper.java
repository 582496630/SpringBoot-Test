package com.springboot.many.mapping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springboot.many.mapping.MyMapper;
import com.springboot.many.model.Imgshow;

public interface ImgshowMapper extends MyMapper<Imgshow> {
	
	public Integer deleteByIds(@Param("imgIds") List<Integer> imgIds);
}