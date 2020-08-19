package com.demo.entity.vo.question;

import com.demo.entity.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author affable
 * @description 条件查询
 * @date 2020/7/15 10:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionConditionVO extends BaseVO {

    private String qId;

    private String username;

    private String title;

    private String qType;

    private Integer state;

}
