package com.zy.many.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.many.mapping.mapper.LearndateMapper;
import com.springboot.many.model.Learndate;

/**
 * 学习网址 service
 * 
 * @author zhouyou
 * @version
 */
@Service
public class LearnService extends _BaseService<Learndate>{

	@Autowired
	private LearndateMapper learndateMapper;

	/**
	 * 根据用户ID获取学习网址信息
	 * 
	 * @param uid
	 * @return List<Learndate
	 */
	public List<Learndate> selectLearns(Integer uid) {
		Learndate learndate = new Learndate();
		learndate.setuId(uid);
		return learndateMapper.select(learndate);
	}

	/**
	 * 根据实体属性 添加
	 * 
	 * @param learndate
	 */
	public void addWeb(Learndate learndate) {
		learndateMapper.insertSelective(learndate);
	}

	/**
	 * 根据id list集 实现批量删除
	 * 
	 * @param ids
	 * @return Integer
	 */
	public Integer deleteWeb(List<Integer> ids) {
		return learndateMapper.deleteByIds(ids);
	}

}
