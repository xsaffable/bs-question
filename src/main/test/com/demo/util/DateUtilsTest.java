package com.demo.util;

import com.demo.entity.vo.index.TimeVO;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author affable
 * @description 测试
 * @date 2020/8/3 19:54
 */
public class DateUtilsTest {

    @Test
    public void test() {
        List<TimeVO> yearMonth = DateUtils.getYearMonth();
        System.out.println(yearMonth);
    }

    @Test
    public void test2() {
        double a = 0.004222;

        System.out.println(String.format("%.0f", a*100));

    }

}