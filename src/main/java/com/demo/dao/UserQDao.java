package com.demo.dao;

import com.demo.entity.po.UserQ;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户填写试卷(UserQ)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public interface UserQDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserQ queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserQ> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    Long countByTime(String startTime, String endTime);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param userQ 实例对象
     * @return 对象列表
     */
    List<UserQ> queryAll(UserQ userQ);

    /**
     * 新增数据
     *
     * @param userQ 实例对象
     * @return 影响行数
     */
    int insert(UserQ userQ);

    /**
     * 修改数据
     *
     * @param userQ 实例对象
     * @return 影响行数
     */
    int update(UserQ userQ);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}