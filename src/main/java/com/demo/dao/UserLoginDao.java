package com.demo.dao;

import com.demo.entity.po.UserCountGroupByUName;
import com.demo.entity.po.UserLogin;
import com.demo.entity.vo.monitor.UserLoginVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户登录历史(UserLogin)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public interface UserLoginDao {

    List<UserCountGroupByUName> countGroupByUName();

    /**
     * 统计总登录数
     * @param startTime 起始时间(包括)
     * @param endTime 截止时间(不包括)
     * @return Long
     */
    Long count(String startTime, String endTime);

    /**
     * 统计总登录用户数
     * @param startTime 起始时间(包括)
     * @param endTime 截止时间(不包括)
     * @return Long
     */
    Long countUsers(String startTime, String endTime);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserLogin queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserLogin> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userLogin 实例对象
     * @return 对象列表
     */
    List<UserLogin> queryAll(UserLogin userLogin);

    /**
     * 新增数据
     *
     * @param userLogin 实例对象
     * @return 影响行数
     */
    int insert(UserLogin userLogin);

    /**
     * 修改数据
     *
     * @param userLogin 实例对象
     * @return 影响行数
     */
    int update(UserLogin userLogin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 分页查询
     * @param offset 查询起始偏移量
     * @param size 查询的条数
     * @return List<UserLoginVO>
     */
    List<UserLoginVO> listByPage(int offset, int size);

}