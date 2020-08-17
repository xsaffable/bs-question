package com.demo.dao;

import com.demo.entity.po.Question;
import com.demo.entity.po.Questionnaire;
import com.demo.entity.po.QuestionnaireGroupByType;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 问卷表(Questionnaire)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public interface QuestionnaireDao {

    List<Question> listPage(Question question, @Param("offset") int offset, @Param("limit") int limit);

    Long count(Question question);

    Long countByTime(String startTime, String endTime);

    List<QuestionnaireGroupByType> countGroupByType();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Questionnaire queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Questionnaire> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param questionnaire 实例对象
     * @return 对象列表
     */
    List<Questionnaire> queryAll(Questionnaire questionnaire);

    /**
     * 新增数据
     *
     * @param questionnaire 实例对象
     * @return 影响行数
     */
    int insert(Questionnaire questionnaire);

    /**
     * 修改数据
     *
     * @param questionnaire 实例对象
     * @return 影响行数
     */
    int update(Questionnaire questionnaire);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}