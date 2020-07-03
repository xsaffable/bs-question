package com.demo.common.exception.base;

/**
 * @author sxs
 * @description 业务异常
 * @date 2020/5/11 13:57
 */
public class BusinessException extends BaseException {

    public BusinessException(IResponseEnum responseEnum) {
        super(responseEnum);
    }

    public BusinessException(IResponseEnum responseEnum, Throwable cause) {
        super(responseEnum, cause);
    }

}
