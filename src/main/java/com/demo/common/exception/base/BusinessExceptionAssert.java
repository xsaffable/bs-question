package com.demo.common.exception.base;

/**
 * @author sxs
 * @description 业务异常断言
 * @date 2020/5/11 14:15
 */
public interface BusinessExceptionAssert extends IResponseEnum, Assert {

    /**
     * 创建异常
     * @return BaseException
     */
    @Override
    default BaseException newException() {
        return new BusinessException(this);
    }

    /**
     * 创建异常
     * @param t 已抛出的异常
     * @return BaseException
     */
    @Override
    default BaseException newException(Throwable t) {
        return new BusinessException(this, t);
    }

}
