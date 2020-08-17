package com.demo.entity.vo.monitor;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author affable
 * @description 用户登录记录 VO
 * @date 2020/8/16 19:02
 */
@Data
public class UserLoginVO {

    private String id;

    private String username;

    private String nickname;

    private String phoneNumber;

    private String email;

    private Integer sex;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createtime;

}
