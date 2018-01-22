package com.zy.many.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.many.mapping.mapper.ImgshowMapper;
import com.springboot.many.model.Imgshow;

/**
 * 图片相关service
 * 
 * @author zhouyou
 * @version  2017-08-02 09:20:24
 */
@Service
public class ImgShowService {
	
	@Autowired
	private ImgshowMapper imgshowMapper;
	
	public List<Imgshow> selectImgs(){
		return imgshowMapper.selectAll();
	}
	
	public void addImg(Imgshow imgshow){
		imgshowMapper.insertSelective(imgshow);
	}
	
	public Integer deleteImgs(List<Integer> imgIds){
		
		return imgshowMapper.deleteByIds(imgIds);
		
	}

}
