package com.demo.entity.po;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * 问卷表(Questionnaire)实体类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public class Questionnaire implements Serializable {
    private static final long serialVersionUID = -46446241329675697L;
    /**
    * id
    */
    private String id;
    /**
    * 问卷类别id
    */
    private String typeId;
    /**
    * 标题
    */
    private String title;
    /**
    * 创建人id
    */
    private String uid;
    /**
    * 是否发布
0: 未发布
1: 已发布
    */
    private Integer state;
    /**
    * 创建时间
    */
    private Timestamp createtime;
    /**
    * 更新时间
    */
    private Timestamp updatetime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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