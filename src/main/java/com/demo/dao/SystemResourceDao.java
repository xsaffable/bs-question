package com.demo.dao;

import com.demo.entity.po.SystemResource;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 系统资源表(SystemResource)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public interface SystemResourceDao {

    SystemResource countByTime(String time);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SystemResource queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SystemResource> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param systemResource 实例对象
     * @return 对象列表
     */
    List<SystemResource> queryAll(SystemResource systemResource);

    /**
     * 新增数据
     *
     * @param systemResource 实例对象
     * @return 影响行数
     */
    int insert(SystemResource systemResource);

    /**
     * 修改数据
     *
     * @param systemResource 实例对象
     * @return 影响行数
     */
    int update(SystemResource systemResource);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}