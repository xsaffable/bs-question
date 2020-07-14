package com.demo.entity.vo.usermanage;

import lombok.Data;

/**
 * @author affable
 * @description 新增用户
 * @date 2020/7/13 11:30
 */
@Data
public class UserAddVO {

    private String username;

    private String nickname;

    private String password;

    private String phoneNumber;

    private String email;

    private Integer sex;

}
