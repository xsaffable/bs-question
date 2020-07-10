package com.demo.entity.po;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * 用户填写试卷(UserQ)实体类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public class UserQ implements Serializable {
    private static final long serialVersionUID = 228961074866832553L;
    /**
    * id
    */
    private String id;
    /**
    * 用户id
    */
    private String uid;
    /**
    * 问卷id
    */
    private String qid;
    /**
    * 问卷类别id
    */
    private String qTypeId;
    /**
    * 问卷的 title
    */
    private String qTitle;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getQTypeId() {
        return qTypeId;
    }

    public void setQTypeId(String qTypeId) {
        this.qTypeId = qTypeId;
    }

    public String getQTitle() {
        return qTitle;
    }

    public void setQTitle(String qTitle) {
        this.qTitle = qTitle;
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