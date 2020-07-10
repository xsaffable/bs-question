package com.demo.entity.vo.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author affable
 * @description 用户注册 VO
 * @date 2020/7/10 13:42
 */
@Data
public class UserRegVO {

    /** 用户名 */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /** 密码 */
    @NotBlank(message = "密码不能为空")
    private String password;

}
