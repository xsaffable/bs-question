package com.demo.entity.vo.login;

import lombok.Data;

/**
 * @author affable
 * @description 记住用户名密码的VO
 * @date 2020/7/10 14:54
 */
@Data
public class UserRememberVO {

    private UserVO userVO;

    private Integer roleId;

    private String userId;

    /** 要跳转的首页 */
    private String index;

}
