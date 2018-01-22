package com.zy.many.schedule;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class UserInfoGroup extends HashMap<String, Map<String, Object>> implements Serializable {
	private static final long serialVersionUID = -7037184289298223430L;
}
