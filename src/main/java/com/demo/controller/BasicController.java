package com.demo.controller;

import com.demo.common.response.BaseResponse;
import com.demo.common.response.QR;
import com.demo.common.response.R;
import com.demo.config.SessionFields;
import com.demo.entity.po.*;
import com.demo.entity.vo.BaseVO;
import com.demo.entity.vo.question.Answer;
import com.demo.entity.vo.question.QuestionAddVO;
import com.demo.entity.vo.question.QuestionConditionVO;
import com.demo.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author affable
 * @description 基本信息管理
 * @date 2020/7/14 18:53
 */
@RestController
@RequestMapping("/basic")
public class BasicController {

    @Resource
    private QuestionTypeService questionTypeService;

    @Resource
    private QuestionnaireService questionnaireService;

    @Resource
    private TopicService topicService;

    @Resource
    private NoticeService noticeService;

    @Resource
    private UserService userService;

    /**
     * 问卷类型列表查询 无分页
     * @return BaseResponse
     */
    @PostMapping("/qtype/list")
    public BaseResponse qtList() {
        R<List<QuestionType>> r = new R<>();
        List<QuestionType> questionTypes = this.questionTypeService.queryAllByLimit(0, Integer.MAX_VALUE);
        r.success("查询成功", questionTypes);
        return r;
    }

    /**
     * 添加分类
     * @param session HttpSession
     * @param name String
     * @return BaseResponse
     */
    @PostMapping("/qtype/add/{name}")
    public BaseResponse qtAdd(HttpSession session, @PathVariable("name") String name) {
        BaseResponse response = new BaseResponse();
        QuestionType questionType = new QuestionType();
        questionType.setName(name);
        // 查询当前登录用户的 id
        User user = new User();
        user.setUsername((String) session.getAttribute(SessionFields.USERNAME));
        String userId = this.userService.queryAllByUser(user).get(0).getId();
        questionType.setUid(userId);
        // 插入数据库
        QuestionType insert = this.questionTypeService.insert(questionType);
        if (insert != null) {
            response.success("添加成功");
        } else {
            response.fail("添加失败");
        }
        return response;
    }

    /**
     * 删除问卷分类
     * @param id 要删除的问卷 id
     * @return 删除是否成功
     */
    @PostMapping("/qtype/delete/{id}")
    public BaseResponse qtDelete(@PathVariable("id") String id) {
        BaseResponse response = new BaseResponse();

        boolean b = this.questionTypeService.deleteById(id);
        if (b) {
            response.success("删除成功");
        } else {
            response.fail("删除失败");
        }

        return response;
    }

    /**
     * 更新问卷分类
     * @param questionType QuestionType
     * @return BaseResponse
     */
    @PostMapping("/qtype/update")
    public BaseResponse qtUpdate(@RequestBody QuestionType questionType) {
        BaseResponse response = new BaseResponse();
        QuestionType update = this.questionTypeService.update(questionType);
        if (update != null) {
            response.success("更新成功");
        } else {
            response.fail("更新失败");
        }
        return response;
    }

    /**
     * 问卷列表查询
     * @return BaseResponse
     */
    @PostMapping("/q/list")
    public BaseResponse qList(@RequestBody QuestionConditionVO vo) {
        QR<List<Question>> qr = new QR<>();

        Question question = new Question();
        question.setId(vo.getQId());
        question.setUsername(vo.getUsername());
        question.setTitle(vo.getTitle());
        question.setTypeId(vo.getQType());
        List<Question> questions = this.questionnaireService.listPage(question, vo.getPage(), vo.getLimit());
        Long count = this.questionnaireService.count(question);
        qr.success("查询成功", questions, count);

        return qr;
    }

    /**
     * 新增问卷
     * @param vo QuestionAddVO
     * @return BaseResponse
     */
    @PostMapping("/q/add")
    public BaseResponse qAdd(HttpSession session, @RequestBody QuestionAddVO vo) {
        BaseResponse response = new BaseResponse();

        // 插入问卷
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        questionnaire.setTitle(vo.getTitle());
        questionnaire.setTypeId(vo.getType());
        questionnaire.setState(vo.getStatus());
        String username = (String) session.getAttribute(SessionFields.USERNAME);
        User user = new User();
        user.setUsername(username);
        questionnaire.setUid(this.userService.queryAllByUser(user).get(0).getId());
        this.questionnaireService.insert(questionnaire);

        // 该问卷所有的问题
        List<com.demo.entity.vo.question.Question> questions = vo.getQuestions();
        AtomicInteger topicOrder = new AtomicInteger(1);
        questions.forEach(question -> {
            // 问题的标题
            String title = question.getTitle();
            // 问题类型 单选/多选
            Integer type = question.getType();
            // 问题的选择项
            List<Answer> ans = question.getAns();
            AtomicInteger ansOrder = new AtomicInteger(1);
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            ans.forEach(an -> {
                Topic topic = new Topic();
                topic.setId(id);
                topic.setQId(questionnaire.getId());
                topic.setOrder(topicOrder.get());
                topic.setTitle(title);
                topic.setType(type);
                topic.setAnsId(UUID.randomUUID().toString().replaceAll("-", ""));
                topic.setAnsOrder(ansOrder.getAndIncrement());
                topic.setAnsTitle(an.getAnsTitle());
                topic.setAnsText(an.getAnsVal());
                // 插入问卷问题与选项
                this.topicService.insert(topic);
            });
            topicOrder.getAndIncrement();

        });

        response.success("新增成功");
        return response;
    }

    /**
     * 查看问卷详细信息
     * @return BaseResponse
     */
    @PostMapping("/q/detail/{id}")
    public BaseResponse qDetail(@PathVariable("id") String id) {
        R<QuestionAddVO> r = new R<>();

        QuestionAddVO questionAddVO = new QuestionAddVO();

        // 查询问卷
        Questionnaire questionnaire = this.questionnaireService.queryById(id);
        questionAddVO.setId(questionnaire.getId());
        questionAddVO.setTitle(questionnaire.getTitle());
        questionAddVO.setStatus(questionnaire.getState());
        questionAddVO.setTypeId(questionnaire.getTypeId());
        questionAddVO.setType(this.questionTypeService.queryById(questionnaire.getTypeId()).getName());

        Topic topic = new Topic();
        topic.setQId(id);
        List<Topic> topics = this.topicService.queryAll(topic);
        List<com.demo.entity.vo.question.Question> questionList = new ArrayList<>();
        Map<Integer, List<Topic>> map = new TreeMap<>(Integer::compareTo);
        for (Topic t : topics) {
            if (map.get(t.getOrder()) == null) {
                List<Topic> l = new ArrayList<>();
                l.add(t);
                map.put(t.getOrder(), l);
            } else {
                map.get(t.getOrder()).add(t);
            }
        }
        for (Map.Entry<Integer, List<Topic>> entry : map.entrySet()) {
            com.demo.entity.vo.question.Question question = new com.demo.entity.vo.question.Question();
            List<Topic> topicList = entry.getValue();
            question.setId(topicList.get(0).getId());
            question.setQId(topicList.get(0).getQId());
            question.setTitle(topicList.get(0).getTitle());
            question.setType(topicList.get(0).getType());
            List<Answer> ans = new ArrayList<>();
            for (Topic t : topicList) {
                Answer an = new Answer();
                an.setId(t.getAnsId());
                an.setAnsTitle(t.getAnsTitle());
                an.setAnsVal(t.getAnsText());
                ans.add(an);
            }
            question.setAns(ans);
            questionList.add(question);
        }

        questionAddVO.setQuestions(questionList);

        r.success("查询成功", questionAddVO);

        return r;
    }

    /**
     * 删除
     * @return BaseResponse
     */
    @PostMapping("/q/delete/{id}")
    public BaseResponse qDelete(@PathVariable("id") String id) {
        BaseResponse response = new BaseResponse();
        Questionnaire questionnaire = this.questionnaireService.queryById(id);
        // 删除问卷
        this.questionnaireService.deleteById(id);
        Topic t = new Topic();
        t.setQId(id);
        List<Topic> topicList = this.topicService.queryAll(t);
        // 删除该问卷下所有的问题
        topicList.forEach(topic -> {
            this.topicService.deleteById(topic.getId());
        });

        response.success("删除成功");
        return response;
    }

    /**
     * 更新问卷
     * @param vo QuestionAddVO
     * @return BaseResponse
     */
    @PostMapping("/q/update")
    public BaseResponse qUpdate(@RequestBody QuestionAddVO vo) {
        BaseResponse response = new BaseResponse();
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId(vo.getId());
        questionnaire.setState(vo.getStatus());
        questionnaire.setTypeId(vo.getType());
        questionnaire.setTitle(vo.getTitle());
        // 更新问卷
        this.questionnaireService.update(questionnaire);
        // 更新选项
        List<com.demo.entity.vo.question.Question> questions = vo.getQuestions();
        for (com.demo.entity.vo.question.Question question : questions) {
            List<Answer> ans = question.getAns();
            for (Answer an : ans) {
                Topic t = new Topic();
                t.setTitle(question.getTitle());
                t.setType(question.getType());
                t.setAnsTitle(an.getAnsTitle());
                t.setAnsText(an.getAnsVal());
                t.setId(question.getId());
                t.setQId(question.getQId());
                t.setQId(questionnaire.getId());
                t.setAnsId(an.getId());
                this.topicService.update(t);
            }
        }

        response.success("更新成功 ");
        return response;
    }

    /**
     * 公共列表查询
     * @return BaseResponse
     */
    @PostMapping("/n/list")
    public BaseResponse nList(@RequestBody BaseVO vo) {
        QR<List<Notice>> qr = new QR<>();
        List<Notice> notices = this.noticeService.queryAllByLimit(vo.getPage(), vo.getLimit());
        Long count = this.noticeService.count();
        qr.success("查询成功", notices, count);
        return qr;
    }

    /**
     * 新增公告
     * @param n Notice
     * @return BaseResponse
     */
    @PostMapping("/n/add")
    public BaseResponse nAdd(HttpSession session, @RequestBody Notice n) {
        BaseResponse response = new BaseResponse();
        // 设置 id
        n.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        // 查询登陆用户的 id
        String username = (String) session.getAttribute(SessionFields.USERNAME);
        User user = this.userService.queryByUsername(username);
        n.setUid(user.getId());
        this.noticeService.insert(n);
        response.success("新增成功");

        return response;
    }

    /**
     * 更新公告
     * @param n Notice
     * @return BaseResponse
     */
    @PostMapping("/n/update")
    public BaseResponse nUpdate(@RequestBody Notice n) {
        BaseResponse response = new BaseResponse();
        this.noticeService.update(n);
        response.success("更新成功");
        return response;
    }

    /**
     * 删除公告
     * @param id String
     * @return BaseResponse
     */
    @PostMapping("/n/delete/{id}")
    public BaseResponse nDelete(@PathVariable("id") String id) {
        BaseResponse response = new BaseResponse();
        boolean b = this.noticeService.deleteById(id);
        if (b) {
            response.success("删除成功");
        } else {
            response.fail("删除失败");
        }

        return response;
    }

}
