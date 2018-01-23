package com.springboot.zy.domain;

public class Learndate {
    private Integer id;
    
    private Integer uId;

    private String webName;

    private String webAddress;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return u_id
     */
    public Integer getuId() {
        return uId;
    }

    /**
     * @param uId
     */
    public void setuId(Integer uId) {
        this.uId = uId;
    }

    /**
     * @return web_name
     */
    public String getWebName() {
        return webName;
    }

    /**
     * @param webName
     */
    public void setWebName(String webName) {
        this.webName = webName;
    }

    /**
     * @return web_address
     */
    public String getWebAddress() {
        return webAddress;
    }

    /**
     * @param webAddress
     */
    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }
}