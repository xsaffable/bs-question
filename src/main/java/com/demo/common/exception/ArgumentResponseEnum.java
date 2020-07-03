package com.demo.common.exception;

import com.demo.common.exception.base.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sxs
 * @description 参数响应枚举
 * @date 2020/5/11 17:06
 */
@Getter
@AllArgsConstructor
public enum ArgumentResponseEnum implements BusinessExceptionAssert {

    /**
     * 参数验证失败
     */
    VALID_ERROR(1001, "参数验证失败"),

    NO_PARAM(1002, "缺少参数"),

    /**
     * 缺少用户名
     */
    NO_USER_NAME(1003, "请输入用户名"),

    /**
     * 缺少密码
     */
    NO_PASSWORD(1004, "请输入密码"),

    /**
     * 时间格式错误
     */
    BAD_DATE(1011, "时间格式错误"),


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
