package com.demo.common.exception.base;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * @author sxs
 * @description 自定义断言接口，用于校验参数
 * @date 2020/5/11 13:47
 */
public interface Assert {

    String DATE_REG = "^([1-2][0-9][0-9][0-9]-[0-1]{0,1}[0-9]-[0-3]{0,1}[0-9])\\s(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";

    /** 时间格式 */
    Pattern DATE_PATTERN = Pattern.compile(DATE_REG);

    /** 数字格式 */
    Pattern DIGIT_PATTERN = Pattern.compile("[0-9]*");

    /**
     * 创建异常
     * @return 创建好的异常 BaseException
     */
    BaseException newException();

    /**
     * 使用已抛出的异常创建异常
     * @param t 已抛出的异常
     * @return 创建好的异常 BaseException
     */
    BaseException newException(Throwable t);

    /**
     * 断言判断对象obj为null，如果为null，则抛出异常
     * @param obj 要判断的对象
     */
    default void assertNotNull(Object obj) {
        if (null == obj) {
            throw newException();
        }
    }

    /**
     * 判断是否为空字符串
     * @param str 字符串
     */
    default void assertNotBlank(String str) {
        if (StringUtils.isBlank(str)) {
            throw newException();
        }
    }

    /**
     * 判断字符串是否相同
     * @param str 字符串
     * @param str2 字符串2
     */
    default void assertEquals(String str, String str2, boolean ignoreCase) {
        if (ignoreCase) {
            str = StringUtils.lowerCase(str);
            str2 = StringUtils.lowerCase(str2);
        }
        if (!StringUtils.equals(str, str2)) {
            throw newException();
        }
    }

    /**
     * 判断是否数字
     * @param str 字符串
     */
    default void assertIsDigit(String str) {
        if (!DIGIT_PATTERN.matcher(str).matches()) {
            throw newException();
        }
    }

    /**
     * 校验时间格式
     * @param str 时间字符串
     */
    default void assertDateFormat(String str) {
        if (!DATE_PATTERN.matcher(str).matches()) {
            throw newException();
        }
    }

    /**
     * 校验时间格式
     * @param str 时间字符串
     * @param format 时间格式
     */
    default void assertDateFormat(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            sdf.parse(str);
        } catch (ParseException e) {
            throw newException(e);
        }

    }

}
