package com.demo.common.request;

import lombok.Getter;

/**
 * @author sxs
 * @description 请求 响应的状态
 * @date 2020/5/13 9:28
 */
@Getter
public enum ReqRestState {
    /**
     * 请求成功
     */
    REQUEST_SUC(1, "request success"),

    REQUEST_FAI(-1, "request failure"),

    RESPONSE_SUC(2, "response success"),

    RESPONSE_FAI(-2, "response failure")


    ;


    ReqRestState(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 编码
     */
    private int code;

    /**
     * 信息
     */
    private String message;


}
