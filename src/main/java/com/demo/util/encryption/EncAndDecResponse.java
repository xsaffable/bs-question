package com.demo.util.encryption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author affable
 * @description 加解密响应
 * @date 2019/12/26 15:54
 */
@AllArgsConstructor
@ToString
@Getter
public class EncAndDecResponse {

    private Type type;

    private String data;

    /**
     * 加密成功
     * @param data String
     * @return EncAndDecResponse
     */
    public static EncAndDecResponse encSuc(String data) {
        return new EncAndDecResponse(Type.ENC_SUC, data);
    }

    /**
     * 加密失败
     * @param data String
     * @return EncAndDecResponse
     */
    public static EncAndDecResponse encFai(String data) {
        return new EncAndDecResponse(Type.ENC_FAI, data);
    }

    /**
     * 解密成功
     * @param data String
     * @return EncAndDecResponse
     */
    public static EncAndDecResponse decSuc(String data) {
        return new EncAndDecResponse(Type.DEC_SUC, data);
    }

    /**
     * 解密失败
     * @param data String
     * @return EncAndDecResponse
     */
    public static EncAndDecResponse decFai(String data) {
        return new EncAndDecResponse(Type.DEC_FAI, data);
    }

    /**
     * 未加密
     * @param data String
     * @return EncAndDecResponse
     */
    public static EncAndDecResponse notEnc(String data) {
        return new EncAndDecResponse(Type.NOT_ENC, data);
    }

}
