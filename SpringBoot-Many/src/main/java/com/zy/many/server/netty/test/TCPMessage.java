package com.zy.many.server.netty.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liukun on 2017/3/24.
 * 通信协议类
 */
public class TCPMessage {
	
	/**
	 * 协议类型
	 */
    private String service;
    
    /**
     * 通信数据
     */
    private TCPData data;

    /**
     * 获取通信数据
     * @return
     */
    public TCPData getData() {
        return data;
    }

    /**
     * 设置通信数据
     * @param data	通信数据
     */
    public void setData(TCPData data) {
        this.data = data;
    }

    /**
     * 获取协议类型
     * @return
     */
    public String getService() {
        return service;
    }

    /**
     * 设置协议类型
     * @param service	服务类型
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * 将响应信息封装为TCPMessage对象
     * @param service	协议类型
     * @param status	响应状态
     * @param type		响应类型
     * @param message	响应消息
     * @return
     */
    public static TCPMessage ResInfo(String service, String status, String type, String message) {
        Map<String, String> map = new HashMap<>();
        map.put("status", status);
        map.put("message", message);
        return new TCPMessage(service, new TCPData(type, map));
    }

    /**
     * 将响应信息封装为TCPMessage对象
     * @param service	协议类型
     * @param type		响应类型
     * @param info		响应消息集合
     * @return
     */
    public static TCPMessage ResInfo(String service, String type, Map<String, String> info) {
        return new TCPMessage(service, new TCPData(type, info));
    }
    
    public static TCPMessage getTCPMessage(Map<String, String> info) {
    	return ResInfo("", "", info);
    }

    /**
     * 有参构造器
     * @param service	协议类型
     * @param data		响应数据类对象
     */
    public TCPMessage(String service, TCPData data) {
        this.service = service;
        this.data = data;
    }

    /**
     * 通过响应消息的key值获取对应的value值
     * @param key	响应消息的key值
     * @return
     */
    public String info(String key) {
        return this.data.getInfo().get(key);
    }

    /**
     * 获取响应消息集合
     * @return
     */
    public Map<String, String> infoMap() {
        return this.data.getInfo();
    }

    /**
     * 获取响应类型
     * @return
     */
    public String type() {
        return this.data.getType();
    }

    /**
     * 无参构造器
     */
    public TCPMessage() {
    }
}







