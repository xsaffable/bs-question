package com.demo.util.encryption;

import com.google.common.base.Preconditions;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author affable
 * @description aes算法实现的加解密
 * @date 2019/12/25 16:37
 */
public class EncAndDecByAes implements IEncrypt, IDecrypt {

    /**
     * 加密时使用的密钥
     */
    private final Key KEY;

    /**
     * 生成密钥使用的字符串
     */
    private final String PASSWORD = "9#20=$7a!cF1x564";

    /**
     * 默认的初始化向量值
     */
    private final String IV_DEFAULT = "g8=50drv!!mI2%uR";

    /**
     * 加解密使用的对象
     */
    private Cipher cipher;

    public EncAndDecByAes() {
        try {
            // 生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            // 生成128位的key，SecureRandom是生成安全随机数序列，只要种子相同，每次生成的随机数就相同
            keyGenerator.init(128, new SecureRandom(this.PASSWORD.getBytes(StandardCharsets.UTF_8)));
            // 产生密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 获取密钥
            byte[] keyBytes = secretKey.getEncoded();
            // key转换
            this.KEY = new SecretKeySpec(keyBytes, "AES");
        } catch (Exception e) {
            throw new RuntimeException("生成密钥失败!", e);
        }

    }

    /**
     * 生成加密对象
     * 使用CTR模式
     *   优点：
     *     1. 不需要填充（padding）
     *     2. 可事先进行加密、解密的准备
     *     3. 加密、解密使用相同的结构
     *     4. 对包含某些错误比特的密文进行解密时，只有明文中相对应的比特会出错
     *     5. 支持并行计算（加密、解密）
     *
     *   缺点：
     *     1. 主动攻击者反转密文分组中的某些比特时，明文分组中对应的比特也会被反转
     *
     *   《实用密码学》[Schneier,2003]一书中指出，不应使用ECB模式，而推荐使用CBC模式和CTR模式。
     *   详情可参见《实用密码学》一书
     *
     *   CBC模式支持解密并行计算，加密不支持并行计算
     *
     * @return EncryptAndDecryptByAes
     */
    public EncAndDecByAes withEnc() {
        try {
            //初始化向量器
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_DEFAULT.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, this.KEY, ivParameterSpec);
            this.cipher = cipher;
        } catch (Exception e) {
            throw new RuntimeException("生成加密对象失败!", e);
        }
        return this;
    }

    /**
     * 生成解密对象
     * @return EncryptAndDecryptByAes
     */
    public EncAndDecByAes withDec() {
        try {
            //初始化向量器
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_DEFAULT.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, this.KEY, ivParameterSpec);
            this.cipher = cipher;
        } catch (Exception e) {
            throw new RuntimeException("生成解密对象失败!", e);
        }
        return this;
    }

    /**
     * 解密
     * @param encStr 加密后的字符串
     * @return 解密后的字符串
     */
    @Override
    public EncAndDecResponse decrypt(String encStr) {
        // 检查
        Preconditions.checkNotNull(this.cipher, "请先使用 withDec 方法,生成解密对象!");
        String str = null;
        if (encStr.length() <= 2) {
            // 没有进行过加密
            return EncAndDecResponse.notEnc("");
        }

        try {
            // 解密
            byte[] encStrBytes = Base64.getDecoder().decode(encStr);
            // 待解密数组的长度
            int length = encStrBytes.length;
            // 如果字节数组的第一位加上倒数第二位，等于最后一位，则说明进行过加密
            if ((encStrBytes[0] + encStrBytes[length - 2]) == encStrBytes[length - 1]) {
                // 字节数组解密
                byte[] encStrBytesDe = new byte[length - 1];
                System.arraycopy(encStrBytes, 0, encStrBytesDe, 0, length - 1);
                // 字符串解密
                byte[] result = this.cipher.doFinal(encStrBytesDe);
                str = new String(result);
            } else {
                // 没有进行过加密
                return EncAndDecResponse.notEnc("");

            }

        } catch (IllegalArgumentException e) {
            // base64解码失败，说明没有进行过加密
            return EncAndDecResponse.notEnc("");
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            // 解密失败
            return EncAndDecResponse.decFai("解密失败, " + e.getMessage());
        }
        return EncAndDecResponse.decSuc(str);
    }

    /**
     * 用jdk实现aes加密
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    @Override
    public EncAndDecResponse encrypt(String str) {
        // 检查
        Preconditions.checkNotNull(this.cipher, "请先使用 withEnc 方法,生成加密对象!");
        String encStr = null;
        try {
            // 先查看是否已经加密
            byte[] encStrBytes = Base64.getDecoder().decode(str);
            // 待解密数组的长度
            int encLength = encStrBytes.length;
            // 如果字节数组的第一位加上倒数第二位，等于最后一位，则说明进行过加密
            if ((encStrBytes[0] + encStrBytes[encLength - 2]) == encStrBytes[encLength - 1]) {
                // 直接返回
                return EncAndDecResponse.encSuc(str);
            }

            // 加密
            byte[] result = this.cipher.doFinal(str.getBytes());
            // 加密后字节数组的长度
            int length = result.length;
            byte[] resultEnc = new byte[length + 1];
            // 拷贝到新数组
            System.arraycopy(result, 0, resultEnc, 0, length);
            // 把数组第一位与最后一位相加，追加到数组中，完成对数组的加密
            // 用于在解密时，判断是否进行过加密
            resultEnc[length] = (byte) (result[0] + result[length - 1]);
            encStr = Base64.getEncoder().encodeToString(resultEnc);
        } catch (Exception e) {
            return EncAndDecResponse.encFai("加密失败, " + e.getMessage());
        }

        return EncAndDecResponse.encSuc(encStr);
    }
}
