package com.demo.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author affable
 * @description 问卷
 * @date 2020/7/15 10:40
 */
@Data
public class Question {

    private String id;

    /** 作者 */
    private String username;

    private String userId;

    /** 问卷类型 */
    private String type;

    private String typeId;

    /** 问卷标题 */
    private String title;

    /** 问卷状态 */
    private Integer state;

    /** 创建时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createtime;

}
