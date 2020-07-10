package com.demo.service;

/**
 * @author affable
 * @description 加密
 * @date 2020/7/10 11:28
 */
public interface EncService {

    /**
     * 加密密码
     * @param pwd 密码
     * @return 加密后的密码
     */
    String encPwd(String pwd);

}
