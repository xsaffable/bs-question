package com.demo.common.exception;

import com.demo.common.exception.base.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sxs
 * @description 服务器内部错误枚举类
 * @date 2020/5/11 16:24
 */
@Getter
@AllArgsConstructor
public enum ServletResponseEnum implements BusinessExceptionAssert {

    /**
     * 没有找到handler异常
     */
    NoHandlerFoundException(500, "没有找到handler异常"),
    /**
     * 请求方法不支持
     */
    HttpRequestMethodNotSupportedException(500, "请求方法不支持"),

    /**
     * 请求体类型不支持
     */
    HttpMediaTypeNotSupportedException(500, "请求体类型不支持"),
    MissingPathVariableException(500, "请求path不匹配"),
    MissingServletRequestParameterException(500, "请求参数不匹配"),
    TypeMismatchException(500, "类型不匹配"),
    HttpMessageNotReadableException(500, "http请求不可读"),
    HttpMessageNotWritableException(500, "http请求不可写"),
    HttpMediaTypeNotAcceptableException(500, "请求媒体类型不接受"),
    ServletRequestBindingException(500, "请求绑定异常"),
    ConversionNotSupportedException(500, ""),
    MissingServletRequestPartException(500, ""),
    AsyncRequestTimeoutException(500, "请求超时")

    ;

    /**
     * 实际的响应码
     */
    private int code;

    /**
     * 响应信息
     */
    private String message;
}
