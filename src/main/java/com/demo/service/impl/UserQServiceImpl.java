package com.demo.service.impl;

import com.demo.entity.po.UserQ;
import com.demo.dao.UserQDao;
import com.demo.service.UserQService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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