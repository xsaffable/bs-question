package com.demo.service;

import com.demo.entity.po.Topic;
import java.util.List;

/**
 * 问题(Topic)表服务接口
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public interface TopicService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Topic queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Topic> queryAllByLimit(int offset, int limit);

    List<Topic> queryAll(Topic topic);

    /**
     * 新增数据
     *
     * @param topic 实例对象
     * @return 实例对象
     */
    Topic insert(Topic topic);

    /**
     * 修改数据
     *
     * @param topic 实例对象
     * @return 实例对象
     */
    int update(Topic topic);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}