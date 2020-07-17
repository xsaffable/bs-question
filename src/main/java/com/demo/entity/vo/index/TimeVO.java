package com.demo.entity.vo.index;

import lombok.Data;

/**
 * @author affable
 * @description 时间相关的 vo
 * @date 2020/7/17 14:38
 */
@Data
public class TimeVO {

    /** 开始时间(包括) */
    private String startTime;

    /** 结束时间(不包括) */
    private String endTime;

}
