package com.zy.many.schedule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 监听：web服务的启动与关闭，用来把内存中的实体类的信息存档
 * 
 * @author zhouyou
 * @version 2017-08-26 09:38:35
 */
//@Component
//@RestController
//@Service
@WebListener
public class ObjectInitializationListener2 implements ServletContextListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectInitializationListener2.class);
	
	/** 对象存储根路径 */
	//private static String OBJ_STORE_BASE_PATH = ObjectInitializationListener.class.getClassLoader().getResource("classpath:/resources/").getPath();
	private static String OBJ_STORE_BASE_PATH = "D://obj";
	
	private static String LIVE_ROOM_GROUP_PATH = OBJ_STORE_BASE_PATH + File.separator + "liveRoomGroup.json";
	
	//private static String USER_INFO_GROUP_PATH =OBJ_STORE_BASE_PATH + File.separator + "userInfoGroup.json"; 
	
	//private static String GLOABAL_CHANNEL_GROUP = OBJ_STORE_BASE_PATH +File.separator +"gloabalChannelGroup.json";
	
	
	
	//@Autowired
	private static LiveRoomGroup liveRoomGroup;
	
	private static UserInfoGroup userInfoGroup;
	@Autowired
	public ObjectInitializationListener2 (LiveRoomGroup liveRoomGroup,UserInfoGroup userInfoGroup){
		ObjectInitializationListener2.liveRoomGroup=liveRoomGroup;
		ObjectInitializationListener2.userInfoGroup=userInfoGroup;
	}
/*	@Autowired
	private  UserInfoGroup userInfoGroup;
	@Autowired
	private  LiveRoomGroup liveRoomGroup;
	*/

	/**
	 * Tomcat服务启动时，读取存储在硬盘中的实体信息到内存当中
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		 //WebApplicationContextUtils.getRequiredWebApplicationContext(servletContextEvent.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
		//LiveRoomGroup liveRoomGroup = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()).getBean(LiveRoomGroup.class);
		
		//readLiveRoomGroup(servletContextEvent);
		
		System.out.println("Initialized:"+liveRoomGroup);
		System.out.println("Initialized:"+userInfoGroup);
	}

	/**
	 * Tomcat服务关闭时，保存内存中的实体信息到本地硬盘
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		//WebApplicationContextUtils.getRequiredWebApplicationContext(servletContextEvent.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
		
		/*WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContextEvent.getServletContext());
		userInfoGroup = (UserInfoGroup) springContext.getBean("userInfoGroup");*/
		
		/*LiveRoomGroup liveRoomGroup = WebApplicationContextUtils.getWebApplicationContext
				(servletContextEvent.getServletContext()).getBean(LiveRoomGroup.class);*/
		
		File file = new File(OBJ_STORE_BASE_PATH);
		if (!file.exists()) {
			file.mkdir();
		}
		//System.out.println(liveRoomGroup.get(1));
		System.out.println("Destroyed:"+liveRoomGroup);
		System.out.println("Destroyed:"+userInfoGroup);
		storeLiveRoomGroup(servletContextEvent);
	}
	private void storeLiveRoomGroup(ServletContextEvent servletContextEvent) {
		
		ObjectOutputStream objectOutputStream = null;
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File(LIVE_ROOM_GROUP_PATH));
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(liveRoomGroup);
			LOGGER.info("把LiveRoomGroup对象保存到文件中"+liveRoomGroup);
		} catch (FileNotFoundException e) {
			LOGGER.error("error", e);
		} catch (IOException e) {
			LOGGER.error("error", e);
		} finally {
			try {
				if (objectOutputStream != null) {
					objectOutputStream.flush();
					objectOutputStream.close();
				}
			} catch (Exception e) {
				LOGGER.error("error", e);
			}
		}
	}

/*	private void readLiveRoomGroup(ServletContextEvent servletContextEvent) {
		File file = new File(LIVE_ROOM_GROUP_PATH);
		if (!file.exists()) {
			return;
		}
		ObjectInputStream objectInputStream = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(LIVE_ROOM_GROUP_PATH));
			objectInputStream = new ObjectInputStream(fileInputStream);
			liveRoomGroup = (LiveRoomGroup) objectInputStream.readObject();
			
			LOGGER.info("从文件中读取LiveRoomGroup对象"+liveRoomGroup);
			
		} catch (FileNotFoundException e) {
			LOGGER.error("error", e);
		} catch (IOException e) {
			LOGGER.error("error", e);
		} catch (ClassNotFoundException e) {
			LOGGER.error("error", e);
		} finally {
			try {
				if (objectInputStream != null) {
					objectInputStream.close();
				}
			} catch (Exception e) {
				LOGGER.error("error", e);
			}
		}
	}*/

}
