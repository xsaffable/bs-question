package com.demo.service;

import com.demo.entity.po.UserQT;
import java.util.List;

/**
 * 用户填写的问卷问题(UserQT)表服务接口
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public interface UserQTService {

    /**
     * 通过ID查询单条数据
     *
     * @param userQId 主键
     * @return 实例对象
     */
    UserQT queryById(String userQId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserQT> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userQT 实例对象
     * @return 实例对象
     */
    UserQT insert(UserQT userQT);

    /**
     * 修改数据
     *
     * @param userQT 实例对象
     * @return 实例对象
     */
    UserQT update(UserQT userQT);

    /**
     * 通过主键删除数据
     *
     * @param userQId 主键
     * @return 是否成功
     */
    boolean deleteById(String userQId);

}