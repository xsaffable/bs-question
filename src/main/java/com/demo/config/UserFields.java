package com.demo.config;

/**
 * @author affable
 * @description 用户相关的常量
 * @date 2020/7/10 13:45
 */
public interface UserFields {

    /** 男性 */
    int SEX_MEN = 0;

    /** 女性 */
    int SEX_WOMEN = 1;

    /** 0: 超级管理员 */
    int ROLE_SUPER_ADMIN = 0;

    /** 1: 管理员 */
    int ROLE_ADMIN = 1;

    /** 2: 普通用户 */
    int ROLE_USER = 2;

    /** 管理员首页 */
    String ADMIN_INDEX = "/index";
    /** 普通用户首页 */
    String VIEW_INDEX = "/view/index";

}
