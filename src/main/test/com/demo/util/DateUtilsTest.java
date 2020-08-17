package com.demo.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import com.demo.entity.vo.index.TimeVO;
import com.sun.management.OperatingSystemMXBean;
import org.junit.Test;

import java.lang.management.ManagementFactory;
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

    @Test
    public void test3() {
        DateTime date = DateUtil.date();
        DateTime dateTime = DateUtil.offsetDay(date, -1);
        String s = DateUtil.formatDate(dateTime);
        Console.log("offsetDay 0: {}", dateTime);
        Console.log("format: {}", s);
    }

    @Test
    public void test4() {
        Console.log(DateUtils.getBetween7Days());
        Console.log(DateUtils.getBetween12Hours());
    }

    @Test
    public void test5() {
        OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        System.out.println("Total RAM：" + mem.getTotalPhysicalMemorySize() / 1024 / 1024 + "MB");
        System.out.println("Available　RAM：" + mem.getFreePhysicalMemorySize() / 1024 / 1024 + "MB");
        int ramUseRate = (int) ((1 - mem.getFreePhysicalMemorySize() / (double) mem.getTotalPhysicalMemorySize()) * 100);
        System.out.println(ramUseRate);

        double cpuLoad = mem.getSystemCpuLoad();
        int percentCpuLoad = (int) (cpuLoad * 100);
        System.out.println(percentCpuLoad);
    }

}