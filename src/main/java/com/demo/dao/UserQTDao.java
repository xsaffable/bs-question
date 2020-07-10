package com.demo.dao;

import com.demo.entity.po.UserQT;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户填写的问卷问题(UserQT)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public interface UserQTDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userQId 主键
     * @return 实例对象
     */
    UserQT queryById(String userQId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserQT> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userQT 实例对象
     * @return 对象列表
     */
    List<UserQT> queryAll(UserQT userQT);

    /**
     * 新增数据
     *
     * @param userQT 实例对象
     * @return 影响行数
     */
    int insert(UserQT userQT);

    /**
     * 修改数据
     *
     * @param userQT 实例对象
     * @return 影响行数
     */
    int update(UserQT userQT);

    /**
     * 通过主键删除数据
     *
     * @param userQId 主键
     * @return 影响行数
     */
    int deleteById(String userQId);

}