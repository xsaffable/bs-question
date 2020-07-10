package com.demo.service.impl;

import com.demo.service.EncService;
import com.demo.util.encryption.EncAndDecByAes;
import com.demo.util.encryption.EncAndDecResponse;
import com.demo.util.encryption.IEncrypt;
import com.demo.util.encryption.Type;
import org.springframework.stereotype.Service;

/**
 * @author affable
 * @description 加密
 * @date 2020/7/10 11:30
 */
@Service
public class EncServiceImpl implements EncService {

    /**
     * 加密对象
     */
    private final IEncrypt encrypt;

    public EncServiceImpl() {
        this.encrypt = new EncAndDecByAes().withEnc();
    }

    /**
     * 加密密码
     *
     * @param pwd 密码
     * @return 加密后的密码
     */
    @Override
    public String encPwd(String pwd) {
        // 加密
        EncAndDecResponse encResponse = this.encrypt.encrypt(pwd);
        if (Type.ENC_SUC.code() == encResponse.getType().code()) {
            // 加密成功
            return encResponse.getData();
        } else {
            // 加密失败
            throw new RuntimeException(encResponse.getData());
        }
    }

}
