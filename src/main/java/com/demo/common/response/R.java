package com.demo.common.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sxs
 * @description 通用返回结果类
 * @date 2020/5/11 15:46
 */
@Getter
@Setter
public class R<T> extends BaseResponse {

    /**
     * 返回数据
     */
    private T data;

    public void success(String msg, T data) {
        this.setCode(0);
        this.setMsg(msg);
        this.setData(data);
    }

}
