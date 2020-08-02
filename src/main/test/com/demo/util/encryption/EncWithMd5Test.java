package com.demo.util.encryption;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author affable
 * @description TODO
 * @date 2020/8/1 11:41
 */
public class EncWithMd5Test {

    @Test
    public void test() {
        System.out.println(EncWithMd5.enc("123456"));
    }

}