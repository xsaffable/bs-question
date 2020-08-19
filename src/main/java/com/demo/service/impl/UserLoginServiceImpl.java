package com.demo.service.impl;

import com.demo.entity.po.UserCountGroupByUName;
import com.demo.entity.po.UserLogin;
import com.demo.dao.UserLoginDao;
import com.demo.entity.vo.index.TimeVO;
import com.demo.entity.vo.monitor.UserLoginVO;
import com.demo.service.UserLoginService;
import com.demo.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 用户登录历史(UserLogin)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {
    @Resource
    private UserLoginDao userLoginDao;

    @Override
    public List<UserCountGroupByUName> countGroupByUName() {
        return this.userLoginDao.countGroupByUName();
    }

    @Override
    public Long count(String startTime, String endTime) {
        return this.userLoginDao.count(startTime, endTime);
    }

    /**
     * 当年每月的访问量
     * @return List<LineVO>
     */
    @Override
    public List<Long> countMonth() {
        List<Long> list = new ArrayList<>();
        List<TimeVO> yearMonth = DateUtils.getYearMonth();
        for (TimeVO timeVO : yearMonth) {
            Long count = this.userLoginDao.count(timeVO.getStartTime(), timeVO.getEndTime());
            list.add(count);
        }

        return list;
    }

    @Override
    public Long countUsers(String startTime, String endTime) {
        return this.userLoginDao.countUsers(startTime, endTime);
    }

    @Override
    public double incRate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String nowEndDate = sdf.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String nowStartDate = sdf.format(calendar.getTime());

        Long nowCount = this.userLoginDao.count(nowStartDate, nowEndDate);

        calendar.add(Calendar.MONTH, -1);
        String preStartDate = sdf.format(calendar.getTime());
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        String preEndDate = sdf.format(calendar.getTime());

        Long preCount = this.userLoginDao.count(preStartDate, preEndDate);
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

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserLogin queryById(String id) {
        return this.userLoginDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserLogin> queryAllByLimit(int offset, int limit) {
        return this.userLoginDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userLogin 实例对象
     * @return 实例对象
     */
    @Override
    public UserLogin insert(UserLogin userLogin) {
        this.userLoginDao.insert(userLogin);
        return userLogin;
    }

    /**
     * 修改数据
     *
     * @param userLogin 实例对象
     * @return 实例对象
     */
    @Override
    public UserLogin update(UserLogin userLogin) {
        this.userLoginDao.update(userLogin);
        return this.queryById(userLogin.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.userLoginDao.deleteById(id) > 0;
    }

    @Override
    public List<UserLoginVO> listByPage(int pageIndex, int pageSize) {
        int offset = (pageIndex - 1) * pageSize;
        int size = pageSize;
        return this.userLoginDao.listByPage(offset, size);
    }
}