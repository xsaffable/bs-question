package com.demo.entity.po;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * 用户填写的问卷问题(UserQT)实体类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public class UserQT implements Serializable {
    private static final long serialVersionUID = -51142072613785779L;
    
    private String userQId;
    
    private String qid;
    
    private String tid;
    
    private Integer tOrder;
    /**
    * 问题的 title
    */
    private String tTitle;
    /**
    * 选项的id
    */
    private String ansId;
    
    private Integer ansOrder;
    /**
    * 选项的 title
    */
    private String ansTitle;
    
    private Timestamp createtime;
    
    private Timestamp updatetime;


    public String getUserQId() {
        return userQId;
    }

    public void setUserQId(String userQId) {
        this.userQId = userQId;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Integer getTOrder() {
        return tOrder;
    }

    public void setTOrder(Integer tOrder) {
        this.tOrder = tOrder;
    }

    public String getTTitle() {
        return tTitle;
    }

    public void setTTitle(String tTitle) {
        this.tTitle = tTitle;
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