package com.demo.entity.vo.question;

import lombok.Data;

import java.util.List;

/**
 * @author affable
 * @description 新增问卷 VO
 * @date 2020/7/15 11:08
 */
@Data
public class QuestionAddVO {

    /** 问卷 id */
    private String id;

    /** 问卷标题 */
    private String title;

    /** 问卷分类 id */
    private String type;

    private String typeId;

    /** 是否发布 */
    private Integer status;

    /** 问题 */
    private List<Question> questions;

}
