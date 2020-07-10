package com.demo.entity.po;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * 问卷类别(QuestionType)实体类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public class QuestionType implements Serializable {
    private static final long serialVersionUID = 867045990128055731L;
    /**
    * id
    */
    private String id;
    /**
    * 类别名
    */
    private String name;
    /**
    * 创建人id
    */
    private String uid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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