package com.zy.many.server.netty.test;

import java.util.Map;

/**
 * 响应数据类
 * TCPData
 */
public class TCPData {
	/**
	 * 响应类型
	 */
    private String type;
    
    /**
     * 响应消息集合
     */
    private Map<String, String> info;

    /**
     * 获取响应类型
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * 设置响应类型
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取响应消息集合
     * @return
     */
    public Map<String, String> getInfo() {
        return info;
    }

    /**
     * 设置响应消息集合
     * @param info
     */
    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

    /**
     * 有参构造器
     * @param type	响应类型
     * @param info	消息消息集合
     */
    public TCPData(String type, Map<String, String> info) {
        this.type = type;
        this.info = info;
    }

    /**
     * 无参构造器
     */
    public TCPData() {
    }
}
