package com.demo.service;

import com.demo.entity.po.Question;
import com.demo.entity.po.Questionnaire;
import java.util.List;

/**
 * 问卷表(Questionnaire)表服务接口
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public interface QuestionnaireService {

    /**
     * 分页列表
     * @param page 查询页码
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Question> listPage(Question question, int page, int limit);

    Long count(Question question);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Questionnaire queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Questionnaire> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param questionnaire 实例对象
     * @return 实例对象
     */
    Questionnaire insert(Questionnaire questionnaire);

    /**
     * 修改数据
     *
     * @param questionnaire 实例对象
     * @return 实例对象
     */
    Questionnaire update(Questionnaire questionnaire);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}