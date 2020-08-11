package com.demo.service.impl;

import com.demo.entity.po.Question;
import com.demo.entity.po.Questionnaire;
import com.demo.dao.QuestionnaireDao;
import com.demo.entity.vo.index.TimeVO;
import com.demo.service.QuestionnaireService;
import com.demo.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
     * 分页列表
     *
     * @param page 查询页码
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Question> listPage(Question question, int page, int limit) {
        int offset = (page - 1) * limit;
        return this.questionnaireDao.listPage(question, offset, limit);
    }

    @Override
    public Long count(Question question) {
        return this.questionnaireDao.count(question);
    }

    @Override
    public Long countByTime(String startTime, String endTime) {
        return this.questionnaireDao.countByTime(startTime, endTime);
    }

    @Override
    public List<Long> countMonth() {
        List<Long> list = new ArrayList<>();
        List<TimeVO> yearMonth = DateUtils.getYearMonth();
        for (TimeVO timeVO : yearMonth) {
            Long count = this.questionnaireDao.countByTime(timeVO.getStartTime(), timeVO.getEndTime());
            list.add(count);
        }

        return list;
    }

    @Override
    public double incRate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String nowEndDate = sdf.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String nowStartDate = sdf.format(calendar.getTime());

        Long nowCount = this.questionnaireDao.countByTime(nowStartDate, nowEndDate);

        calendar.add(Calendar.MONTH, -1);
        String preStartDate = sdf.format(calendar.getTime());
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        String preEndDate = sdf.format(calendar.getTime());

        Long preCount = this.questionnaireDao.countByTime(preStartDate, preEndDate);
        if (preCount == 0) {
            return 0;
        }
        if (nowCount - preCount <= 0) {
            return 0;
        }
        if (nowCount - preCount >= preCount) {
            return 1;
        }

        return (nowCount - preCount) / (double) preCount;
    }

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