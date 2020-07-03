package com.demo.common.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author sxs
 * @description 响应的基类
 * @date 2020/5/11 15:44
 */
@Getter
@Setter
@ToString
public class BaseResponse {

    /**
     * 响应码
     */
    int code;

    /**
     * 响应信息
     */
    String msg;

    public void success(String msg) {
        this.setCode(0);
        this.setMsg(msg);
    }

    public void fail(String msg) {
        this.setCode(1);
        this.setMsg(msg);
    }

}
