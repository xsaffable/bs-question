package com.demo.entity.vo.viewindex;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author affable
 * @description 公告的 VO
 * @date 2020/8/4 21:26
 */
@Data
public class NoticeVO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 公告内容
     */
    private String text;

    /**
     * 是否置顶
     0: 不置顶
     1: 置顶
     */
    private Integer top;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updatetime;

}
