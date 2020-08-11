package com.demo.util;

import com.demo.entity.vo.index.TimeVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author affable
 * @description 时间相关的工具方法
 * @date 2020/8/3 19:49
 */
public class DateUtils {

    /**
     * 获取今年每个月份起止时间
     * 包括起始时间，不包括截止时间
     * @return List<TimeVO>
     */
    public static List<TimeVO> getYearMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<TimeVO> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String date = sdf.format(calendar.getTime());
        while (calendar.getTime().before(new Date())) {
            calendar.add(Calendar.MONTH, 1);
            TimeVO timeVO = new TimeVO();
            timeVO.setStartTime(date);
            date = sdf.format(calendar.getTime());
            timeVO.setEndTime(date);
            list.add(timeVO);
        }
        return list;
    }

}
