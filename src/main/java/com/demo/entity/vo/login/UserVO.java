package com.demo.entity.vo.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author affable
 * @description 用户
 * @date 2020/7/10 11:14
 */
@Data
public class UserVO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    /** 验证码 */
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;

}
