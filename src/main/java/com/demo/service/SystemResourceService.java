package com.demo.service;

import com.demo.entity.po.SystemResource;
import java.util.List;

/**
 * 系统资源表(SystemResource)表服务接口
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public interface SystemResourceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SystemResource queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SystemResource> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param systemResource 实例对象
     * @return 实例对象
     */
    SystemResource insert(SystemResource systemResource);

    /**
     * 修改数据
     *
     * @param systemResource 实例对象
     * @return 实例对象
     */
    SystemResource update(SystemResource systemResource);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}