package com.demo.util.encryption;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author affable
 * @description 测试
 * @date 2020/8/1 11:25
 */
public class EncAndDecByAesTest {

    @Test
    public void test() {
        EncAndDecByAes encAndDecByAes = new EncAndDecByAes().withDec();

        System.out.println(encAndDecByAes.decrypt("+SU6l21oYQ=="));


    }

    @Test
    public void test2() {
        EncAndDecByAes encAndDecByAes = new EncAndDecByAes().withEnc();
        System.out.println(encAndDecByAes.encrypt("123456"));

    }

}