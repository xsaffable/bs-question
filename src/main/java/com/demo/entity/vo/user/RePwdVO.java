package com.demo.entity.vo.user;

import lombok.Data;

/**
 * @author affable
 * @description 重置密码的 VO
 * @date 2020/8/17 20:21
 */
@Data
public class RePwdVO {

    private String oldPassword;

    private String password;

}
