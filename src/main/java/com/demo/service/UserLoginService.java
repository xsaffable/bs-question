package com.demo.service;

import com.demo.entity.po.UserLogin;
import java.util.List;

/**
 * 用户登录历史(UserLogin)表服务接口
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public interface UserLoginService {


    Long count(String startTime, String endTime);

    Long countUsers(String startTime, String endTime);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserLogin queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserLogin> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userLogin 实例对象
     * @return 实例对象
     */
    UserLogin insert(UserLogin userLogin);

    /**
     * 修改数据
     *
     * @param userLogin 实例对象
     * @return 实例对象
     */
    UserLogin update(UserLogin userLogin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}