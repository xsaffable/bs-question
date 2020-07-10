package com.demo.entity.vo;

import lombok.Data;

/**
 * @author affable
 * @description vo 父类
 * @date 2020/7/10 16:12
 */
@Data
public class BaseVO {

    /** 页码 */
    private Integer page;

    /** 每页数量 */
    private Integer limit;

}
