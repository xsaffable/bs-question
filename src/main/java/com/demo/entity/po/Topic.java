package com.demo.entity.po;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * 问题(Topic)实体类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public class Topic implements Serializable {
    private static final long serialVersionUID = 798517235943199919L;
    /**
    * 问题id
    */
    private String id;
    /**
    * 所属问卷的id
    */
    private String qId;
    /**
    * 第几题
    */
    private Integer order;
    /**
    * 题目名
    */
    private String title;
    /**
    * 问题类型
0: 单选题
1: 多选题
    */
    private Integer type;
    /**
    * 选项id
    */
    private String ansId;
    /**
    * 选项的顺序
    */
    private Integer ansOrder;
    /**
    * 选项的标题
    */
    private String ansTitle;
    /**
    * 选项的内容
    */
    private String ansText;
    
    private Timestamp createtime;
    
    private Timestamp updatetime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQId() {
        return qId;
    }

    public void setQId(String qId) {
        this.qId = qId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAnsId() {
        return ansId;
    }

    public void setAnsId(String ansId) {
        this.ansId = ansId;
    }

    public Integer getAnsOrder() {
        return ansOrder;
    }

    public void setAnsOrder(Integer ansOrder) {
        this.ansOrder = ansOrder;
    }

    public String getAnsTitle() {
        return ansTitle;
    }

    public void setAnsTitle(String ansTitle) {
        this.ansTitle = ansTitle;
    }

    public String getAnsText() {
        return ansText;
    }

    public void setAnsText(String ansText) {
        this.ansText = ansText;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

}