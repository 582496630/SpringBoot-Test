package com.springboot.zy.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.zy.domain.Learndate;
import com.springboot.zy.domain.Users;
import com.springboot.zy.services.IndexServices;

@Controller
public class IndexController {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(){
		logger.info("slf4j{}{}","1","2");
		
		return "content";
	}


	  @Autowired
	    private IndexServices indexser;

	    @RequestMapping("/list")
	    @ResponseBody
	    public List<Users> getUserList(){
	        return indexser.getUsers();
	    }
	    @RequestMapping("/list2")
	    @ResponseBody
	    public List<Learndate> getLearndate(){
	    	return indexser.getLearndate();
	    }
	
	
	
	
	
	
	
}
