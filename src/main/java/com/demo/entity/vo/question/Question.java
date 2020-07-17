package com.demo.entity.vo.question;

import lombok.Data;

import java.util.List;

/**
 * @author affable
 * @description 问题
 * @date 2020/7/15 11:40
 */
@Data
public class Question {

    private String id;

    private String qId;

    private String title;

    /** 问题的类型 */
    private Integer type;

    private List<Answer> ans;

}
