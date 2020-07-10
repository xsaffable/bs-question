package com.demo.util.encryption;

/**
 * @author affable
 * @description 解密
 * @date 2019/12/25 16:36
 */
public interface IDecrypt {

    /**
     * 解密
     * @param encStr 加密后的字符串
     * @return 解密后的字符串
     */
    EncAndDecResponse decrypt(String encStr);

}
