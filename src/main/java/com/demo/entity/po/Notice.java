package com.demo.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * 公告(Notice)实体类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public class Notice implements Serializable {
    private static final long serialVersionUID = -41642870206031034L;
    /**
    * id
    */
    private String id;
    /**
    * 所属用户id
    */
    private String uid;
    /**
     * 用户名
     */
    private String username;
    /**
    * 公告内容
    */
    private String text;
    /**
    * 是否置顶
0: 不置顶
1: 置顶
    */
    private Integer top;
    /**
    * 创建时间
    */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createtime;
    /**
    * 更新时间
    */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updatetime;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
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