package com.demo.util.encryption;

/**
 * @author affable
 * @description 类型
 * @date 2019/12/26 16:15
 */
public enum Type {

    /**
     * response类型
     */
    ENC_SUC("加密成功!", 1),
    ENC_FAI("加密失败!", 2),
    DEC_SUC("解密成功!", 3),
    DEC_FAI("解密失败!", 4),
    NOT_ENC("未加密!", 5);

    private String description;
    private Integer code;

    Type(String description, Integer code) {
        this.description = description;
        this.code = code;
    }

    public String desc() {
        return description;
    }

    public int code() {
        return code;
    }

}
