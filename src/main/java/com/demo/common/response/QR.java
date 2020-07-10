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
     * 总数量
     */
    private long count;

    /**
     * 数据
     */
    private T data;

    public void success(String msg, T data, long count) {
        this.setCode(0);
        this.setMsg(msg);
        this.setData(data);
        this.setCount(count);
    }

}
