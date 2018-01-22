package com.zy.many.server.mina;


import com.alibaba.fastjson.JSONObject;

/**
 * 包 格式类
 * 
 * @author zhouyou
 * @version
 */
public class TcpMsg {
	/**
	 * 报文类型
	 */
    private String service;
    /**
     * 通信ID,系统时间值ms
     */
    private String packetID;
    /**
     * 数据域
     */
    private JSONObject data;

    public TcpMsg() {
    }

    public TcpMsg(String service, Boolean ack, String packetID, JSONObject data) {
        this.service = service;
        this.packetID = packetID;
        this.data = data;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPacketID() {
        return packetID;
    }

    public void setPacketID(String packetID) {
        this.packetID = packetID;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "packetId:  " + packetID + "  service:  " + service;
    }
}
