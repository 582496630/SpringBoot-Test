package com.zy.many.schedule;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;

@Component
public class LiveRoomGroup extends HashMap<Integer, String>  implements Serializable {
	private static final long serialVersionUID = 2428336596510057535L;
}
