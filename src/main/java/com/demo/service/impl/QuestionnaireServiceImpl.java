package com.demo.service.impl;

import com.demo.entity.po.Questionnaire;
import com.demo.dao.QuestionnaireDao;
import com.demo.service.QuestionnaireService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 问卷表(Questionnaire)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
@Service("questionnaireService")
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Resource
    private QuestionnaireDao questionnaireDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Questionnaire queryById(String id) {
        return this.questionnaireDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Questionnaire> queryAllByLimit(int offset, int limit) {
        return this.questionnaireDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param questionnaire 实例对象
     * @return 实例对象
     */
    @Override
    public Questionnaire insert(Questionnaire questionnaire) {
        this.questionnaireDao.insert(questionnaire);
        return questionnaire;
    }

    /**
     * 修改数据
     *
     * @param questionnaire 实例对象
     * @return 实例对象
     */
    @Override
    public Questionnaire update(Questionnaire questionnaire) {
        this.questionnaireDao.update(questionnaire);
        return this.queryById(questionnaire.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.questionnaireDao.deleteById(id) > 0;
    }
}