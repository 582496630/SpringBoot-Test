package com.springboot.many.model;

import javax.persistence.*;

public class Midiinfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer localtion;

    @Column(name = "difficulty_level")
    private Integer difficultyLevel;

    private Integer solt;

    @Column(name = "opern_num")
    private String opernNum;

    private String title;

    private String composer;

    @Column(name = "web_path")
    private String webPath;

    @Column(name = "local_path")
    private String localPath;

    @Column(name = "parent_subject")
    private String parentSubject;

    private String category;

    private String size;

    private String data;

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
     * @return localtion
     */
    public Integer getLocaltion() {
        return localtion;
    }

    /**
     * @param localtion
     */
    public void setLocaltion(Integer localtion) {
        this.localtion = localtion;
    }

    /**
     * @return difficulty_level
     */
    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    /**
     * @param difficultyLevel
     */
    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    /**
     * @return solt
     */
    public Integer getSolt() {
        return solt;
    }

    /**
     * @param solt
     */
    public void setSolt(Integer solt) {
        this.solt = solt;
    }

    /**
     * @return opern_num
     */
    public String getOpernNum() {
        return opernNum;
    }

    /**
     * @param opernNum
     */
    public void setOpernNum(String opernNum) {
        this.opernNum = opernNum;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return composer
     */
    public String getComposer() {
        return composer;
    }

    /**
     * @param composer
     */
    public void setComposer(String composer) {
        this.composer = composer;
    }

    /**
     * @return web_path
     */
    public String getWebPath() {
        return webPath;
    }

    /**
     * @param webPath
     */
    public void setWebPath(String webPath) {
        this.webPath = webPath;
    }

    /**
     * @return local_path
     */
    public String getLocalPath() {
        return localPath;
    }

    /**
     * @param localPath
     */
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    /**
     * @return parent_subject
     */
    public String getParentSubject() {
        return parentSubject;
    }

    /**
     * @param parentSubject
     */
    public void setParentSubject(String parentSubject) {
        this.parentSubject = parentSubject;
    }

    /**
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }
}