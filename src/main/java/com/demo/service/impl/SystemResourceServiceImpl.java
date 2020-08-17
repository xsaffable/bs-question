package com.demo.service.impl;

import com.demo.entity.po.SystemResource;
import com.demo.dao.SystemResourceDao;
import com.demo.service.SystemResourceService;
import com.demo.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统资源表(SystemResource)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
@Service("systemResourceService")
public class SystemResourceServiceImpl implements SystemResourceService {
    @Resource
    private SystemResourceDao systemResourceDao;

    @Override
    public List<SystemResource> countByTime() {
        List<SystemResource> list = new ArrayList<>();
        List<String> between12Hours = DateUtils.getBetween12Hours();
        between12Hours.forEach(d -> {
            list.add(this.systemResourceDao.countByTime(d));
        });

        return list;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SystemResource queryById(String id) {
        return this.systemResourceDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SystemResource> queryAllByLimit(int offset, int limit) {
        return this.systemResourceDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param systemResource 实例对象
     * @return 实例对象
     */
    @Override
    public SystemResource insert(SystemResource systemResource) {
        this.systemResourceDao.insert(systemResource);
        return systemResource;
    }

    /**
     * 修改数据
     *
     * @param systemResource 实例对象
     * @return 实例对象
     */
    @Override
    public SystemResource update(SystemResource systemResource) {
        this.systemResourceDao.update(systemResource);
        return this.queryById(systemResource.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.systemResourceDao.deleteById(id) > 0;
    }
}