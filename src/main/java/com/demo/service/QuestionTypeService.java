package com.demo.service;

import com.demo.entity.po.QuestionType;
import java.util.List;

/**
 * 问卷类别(QuestionType)表服务接口
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public interface QuestionTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    QuestionType queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<QuestionType> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param questionType 实例对象
     * @return 实例对象
     */
    QuestionType insert(QuestionType questionType);

    /**
     * 修改数据
     *
     * @param questionType 实例对象
     * @return 实例对象
     */
    QuestionType update(QuestionType questionType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}