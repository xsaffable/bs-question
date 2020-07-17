package com.demo.service.impl;

import com.demo.entity.po.Topic;
import com.demo.dao.TopicDao;
import com.demo.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 问题(Topic)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
@Service("topicService")
public class TopicServiceImpl implements TopicService {
    @Resource
    private TopicDao topicDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Topic queryById(String id) {
        return this.topicDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Topic> queryAllByLimit(int offset, int limit) {
        return this.topicDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<Topic> queryAll(Topic topic) {
        return this.topicDao.queryAll(topic);
    }

    /**
     * 新增数据
     *
     * @param topic 实例对象
     * @return 实例对象
     */
    @Override
    public Topic insert(Topic topic) {
        this.topicDao.insert(topic);
        return topic;
    }

    /**
     * 修改数据
     *
     * @param topic 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Topic topic) {
        return this.topicDao.update(topic);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.topicDao.deleteById(id) > 0;
    }
}