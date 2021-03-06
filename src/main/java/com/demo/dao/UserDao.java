package com.demo.dao;

import com.demo.entity.po.TimeCount;
import com.demo.entity.po.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public interface UserDao {

    Long countByDays(String time);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(String id);

    /**
     * 通过ID查询单条数据
     *
     * @param username 主键
     * @return 实例对象
     */
    User queryByUsername(String username);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 通过实体作为筛选条件查询
     * @param user User
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryByUserLimit(@Param("user") User user, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 总记录数
     * @param user User
     * @return long
     */
    long count(User user);

    // 总记录数
    long countAll(String startTime, String endTime);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}