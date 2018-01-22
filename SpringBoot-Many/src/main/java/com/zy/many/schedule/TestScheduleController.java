package com.zy.many.schedule;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zy.many.utils.JsonUtils;

@RestController
@RequestMapping(value = "test")
public class TestScheduleController {
	
	@Autowired
	private LiveRoomGroup liveRoomGroup;

	@Autowired
	private UserInfoGroup userInfoGroup;
	
	@RequestMapping(value = "storeValue")
	public String storeValue(){
		
		Map<String, Object> value = new HashMap<>();
		value.put("abc", "123");
		userInfoGroup.put("abc", value);
		liveRoomGroup.put(1, "1111");
		liveRoomGroup.put(2, "2222");
		
		String userS = JsonUtils.encode(userInfoGroup);
		String liveS= JsonUtils.encode(liveRoomGroup);
		
		return liveS+"\r\n"+userS;
	}
	
	
}
