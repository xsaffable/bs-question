package com.demo.common.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sxs
 * @description 分页返回结果
 * @date 2020/5/11 15:48
 */
@Getter
@Setter
public class QR<T> extends BaseResponse {

    /**
     * 总记录数量
     */
    private int totalCount;

    /**
     * 页码
     */
    private int pageNo;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 数据
     */
    private T records;

}
