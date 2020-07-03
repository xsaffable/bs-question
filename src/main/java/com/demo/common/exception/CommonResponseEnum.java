package com.demo.common.exception;

import com.demo.common.exception.base.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sxs
 * @description 响应
 * @date 2020/5/11 14:18
 */
@Getter
@AllArgsConstructor
public enum CommonResponseEnum implements BusinessExceptionAssert {

    /**
     * 请求方式不对
     */
    REQUEST_ERROR(2001, "请换个姿势再来一次"),

    /**
     * 服务器内部错误
     */
    SERVER_ERROR(2002, "系统偷懒啦，请稍候再试"),




    ;

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应信息
     */
    private String message;

}
