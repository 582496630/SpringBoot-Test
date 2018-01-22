package com.springboot.many.mapping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springboot.many.model.Imgshow;
import tk.mybatis.mapper.common.Mapper;

public interface ImgshowMapper extends Mapper<Imgshow> {
	
	public Integer deleteByIds(@Param("imgIds") List<Integer> imgIds);
}