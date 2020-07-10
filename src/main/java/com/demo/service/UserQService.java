package com.demo.service;

import com.demo.entity.po.UserQ;
import java.util.List;

/**
 * 用户填写试卷(UserQ)表服务接口
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public interface UserQService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserQ queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserQ> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userQ 实例对象
     * @return 实例对象
     */
    UserQ insert(UserQ userQ);

    /**
     * 修改数据
     *
     * @param userQ 实例对象
     * @return 实例对象
     */
    UserQ update(UserQ userQ);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}