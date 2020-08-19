package com.demo.service.impl;

import com.demo.entity.po.DTCount;
import com.demo.entity.po.HotQuestionnaire;
import com.demo.entity.po.UserQ;
import com.demo.dao.UserQDao;
import com.demo.entity.po.UserQCountGroupByQTitle;
import com.demo.entity.vo.index.TimeVO;
import com.demo.service.UserQService;
import com.demo.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 用户填写试卷(UserQ)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
@Service("userQService")
public class UserQServiceImpl implements UserQService {
    @Resource
    private UserQDao userQDao;

    @Override
    public List<HotQuestionnaire> hotQuestionnaires() {
        return this.userQDao.hotQuestionnaires();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserQ queryById(String id) {
        return this.userQDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserQ> queryAllByLimit(int offset, int limit) {
        return this.userQDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<Long> countMonth() {
        List<Long> list = new ArrayList<>();
        List<TimeVO> yearMonth = DateUtils.getYearMonth();
        for (TimeVO timeVO : yearMonth) {
            Long count = this.userQDao.countByTime(timeVO.getStartTime(), timeVO.getEndTime());
            list.add(count);
        }

        return list;
    }

    @Override
    public double incRate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String nowEndDate = sdf.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String nowStartDate = sdf.format(calendar.getTime());

        Long nowCount = this.userQDao.countByTime(nowStartDate, nowEndDate);

        calendar.add(Calendar.MONTH, -1);
        String preStartDate = sdf.format(calendar.getTime());
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        String preEndDate = sdf.format(calendar.getTime());

        Long preCount = this.userQDao.countByTime(preStartDate, preEndDate);
        if (preCount == 0) {
            return 1;
        }
        if (nowCount - preCount <= 0) {
            return 0;
        }
        if (nowCount - preCount >= preCount) {
            return 1;
        }

        return (nowCount - preCount) / (double) preCount;
    }

    @Override
    public List<DTCount> queryDTCount(String startTime, String endTime) {
        return this.userQDao.queryDTCount(startTime, endTime);
    }

    @Override
    public List<UserQCountGroupByQTitle> countGroupByQTitle() {
        return this.userQDao.countGroupByQTitle();
    }

    /**
     * 新增数据
     *
     * @param userQ 实例对象
     * @return 实例对象
     */
    @Override
    public UserQ insert(UserQ userQ) {
        this.userQDao.insert(userQ);
        return userQ;
    }

    /**
     * 修改数据
     *
     * @param userQ 实例对象
     * @return 实例对象
     */
    @Override
    public UserQ update(UserQ userQ) {
        this.userQDao.update(userQ);
        return this.queryById(userQ.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.userQDao.deleteById(id) > 0;
    }
}