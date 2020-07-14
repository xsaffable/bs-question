package com.demo.entity.vo.usermanage;

import lombok.Data;

/**
 * @author affable
 * @description 新增管理员用户
 * @date 2020/7/13 11:30
 */
@Data
public class UserAdminAddVO {

    private String username;

    private String nickname;

    private String password;

    private String phoneNumber;

    private String email;

    private Integer roleId;

}
