package com.zy.many.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test")
public class ObjectStoreService2 {
	@Autowired
	private LiveRoomGroup liveRoomGroup;
	@Autowired
	private UserInfoGroup userInfoGroup;

	@RequestMapping(value = "objectStoreService2")
	public LiveRoomGroup get() {
		System.out.println("Service2:" + liveRoomGroup);
		System.out.println("Service2:" + userInfoGroup);
		return liveRoomGroup;
	}
}
