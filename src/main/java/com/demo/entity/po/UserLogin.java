package com.demo.entity.po;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * 用户登录历史(UserLogin)实体类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public class UserLogin implements Serializable {
    private static final long serialVersionUID = -96098006389094906L;
    /**
    * id
    */
    private String id;
    /**
    * 用户id
    */
    private String uid;
    /**
    * 创建时间
    */
    private Timestamp createtime;


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

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

}