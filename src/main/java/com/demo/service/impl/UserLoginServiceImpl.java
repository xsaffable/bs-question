package com.demo.service.impl;

import com.demo.entity.po.UserLogin;
import com.demo.dao.UserLoginDao;
import com.demo.service.UserLoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public Long count(String startTime, String endTime) {
        return this.userLoginDao.count(startTime, endTime);
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
}