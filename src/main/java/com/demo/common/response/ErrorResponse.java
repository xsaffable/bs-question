package com.demo.common.response;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author sxs
 * @description 错误的响应实体
 * @date 2020/5/11 15:31
 */
@Getter
@Setter
@ToString
public class ErrorResponse extends BaseResponse {

    public ErrorResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
