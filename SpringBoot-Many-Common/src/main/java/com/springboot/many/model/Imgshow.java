package com.springboot.many.model;

import javax.persistence.*;

public class Imgshow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "u_id")
    private Integer uId;

    /**
     * 图片名
     */
    @Column(name = "img_name")
    private String imgName;

    /**
     * 图片存储
     */
    @Column(name = "img_address")
    private String imgAddress;

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
     * 获取用户id
     *
     * @return u_id - 用户id
     */
    public Integer getuId() {
        return uId;
    }

    /**
     * 设置用户id
     *
     * @param uId 用户id
     */
    public void setuId(Integer uId) {
        this.uId = uId;
    }

    /**
     * 获取图片名
     *
     * @return img_name - 图片名
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * 设置图片名
     *
     * @param imgName 图片名
     */
    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    /**
     * 获取图片存储
     *
     * @return img_address - 图片存储
     */
    public String getImgAddress() {
        return imgAddress;
    }

    /**
     * 设置图片存储
     *
     * @param imgAddress 图片存储
     */
    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }
}