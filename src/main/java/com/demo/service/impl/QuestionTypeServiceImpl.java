package com.demo.service.impl;

import com.demo.entity.po.QuestionType;
import com.demo.dao.QuestionTypeDao;
import com.demo.service.QuestionTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 问卷类别(QuestionType)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
@Service("questionTypeService")
public class QuestionTypeServiceImpl implements QuestionTypeService {
    @Resource
    private QuestionTypeDao questionTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public QuestionType queryById(String id) {
        return this.questionTypeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<QuestionType> queryAllByLimit(int offset, int limit) {
        return this.questionTypeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param questionType 实例对象
     * @return 实例对象
     */
    @Override
    public QuestionType insert(QuestionType questionType) {
        this.questionTypeDao.insert(questionType);
        return questionType;
    }

    /**
     * 修改数据
     *
     * @param questionType 实例对象
     * @return 实例对象
     */
    @Override
    public QuestionType update(QuestionType questionType) {
        this.questionTypeDao.update(questionType);
        return this.queryById(questionType.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.questionTypeDao.deleteById(id) > 0;
    }
}