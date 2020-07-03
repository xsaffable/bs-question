package com.demo.common.exception.base;

import lombok.Getter;

/**
 * @author sxs
 * @description 异常的基类
 * @date 2020/5/11 13:49
 */
@Getter
public class BaseException extends RuntimeException {

    private IResponseEnum responseEnum;

    public BaseException(IResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;
    }

    public BaseException(IResponseEnum responseEnum, Throwable cause) {
        super(responseEnum.getMessage(), cause);
        this.responseEnum = responseEnum;
    }

}
