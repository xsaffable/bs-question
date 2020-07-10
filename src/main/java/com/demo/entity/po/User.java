package com.demo.entity.po;

import java.sql.Timestamp;
import java.io.Serializable;
import java.util.UUID;

/**
 * 用户表(User)实体类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public class User implements Serializable {
    private static final long serialVersionUID = -98169251382586298L;
    /**
    * id
    */
    private String id;
    /**
    * 用户名(登录名)
    */
    private String username;
    /**
    * 昵称
    */
    private String nickname;
    /**
    * 密码
    */
    private String password;
    /**
    * 性别
0: 女
1: 男
    */
    private Integer sex;
    /**
    * 手机号
    */
    private String phoneNumber;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 备注
    */
    private String remarks;
    /**
    * 角色
0: 超级管理员
1: 管理员
2: 普通用户
    */
    private Integer role;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
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