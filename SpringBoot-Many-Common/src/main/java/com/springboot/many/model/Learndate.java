package com.springboot.many.model;

import javax.persistence.*;

public class Learndate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "u_id")
    private Integer uId;

    @Column(name = "web_name")
    private String webName;

    @Column(name = "web_address")
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