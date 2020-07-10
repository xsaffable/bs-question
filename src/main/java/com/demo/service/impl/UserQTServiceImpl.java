package com.demo.service.impl;

import com.demo.entity.po.UserQT;
import com.demo.dao.UserQTDao;
import com.demo.service.UserQTService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户填写的问卷问题(UserQT)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
@Service("userQTService")
public class UserQTServiceImpl implements UserQTService {
    @Resource
    private UserQTDao userQTDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userQId 主键
     * @return 实例对象
     */
    @Override
    public UserQT queryById(String userQId) {
        return this.userQTDao.queryById(userQId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserQT> queryAllByLimit(int offset, int limit) {
        return this.userQTDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userQT 实例对象
     * @return 实例对象
     */
    @Override
    public UserQT insert(UserQT userQT) {
        this.userQTDao.insert(userQT);
        return userQT;
    }

    /**
     * 修改数据
     *
     * @param userQT 实例对象
     * @return 实例对象
     */
    @Override
    public UserQT update(UserQT userQT) {
        this.userQTDao.update(userQT);
        return this.queryById(userQT.getUserQId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userQId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userQId) {
        return this.userQTDao.deleteById(userQId) > 0;
    }
}