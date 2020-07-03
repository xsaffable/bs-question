package com.demo.common.exception.base;

/**
 * @author sxs
 * @description 响应
 * @date 2020/5/11 13:56
 */
public interface IResponseEnum {

    /**
     * 响应码
     * @return int
     */
    int getCode();

    /**
     * 响应信息
     * @return String
     */
    String getMessage();

}
