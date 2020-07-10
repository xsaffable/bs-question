package com.demo.util.encryption;

/**
 * @author affable
 * @description 加密
 * @date 2019/12/25 16:35
 */
public interface IEncrypt {

    /**
     * 加密
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    EncAndDecResponse encrypt(String str);

}
