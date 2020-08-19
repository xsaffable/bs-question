package com.demo.controller;

import com.demo.common.response.BaseResponse;
import com.demo.common.response.QR;
import com.demo.common.response.R;
import com.demo.entity.po.HotQuestionnaire;
import com.demo.entity.po.Notice;
import com.demo.entity.po.Question;
import com.demo.entity.vo.question.QuestionConditionVO;
import com.demo.entity.vo.viewindex.NoticeVO;
import com.demo.service.NoticeService;
import com.demo.service.QuestionnaireService;
import com.demo.service.UserQService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author affable
 * @description 用户首页的控制器
 * @date 2020/8/4 21:20
 */
@RestController
@RequestMapping("/view_index")
public class ViewIndexController {

    @Resource
    private NoticeService noticeService;

    @Resource
    private UserQService userQService;

    @Resource
    private QuestionnaireService questionnaireService;

    @PostMapping("/notice_list")
    public BaseResponse noticeList() {
        R<List<NoticeVO>> r = new R<>();
        List<Notice> notices = noticeService.queryAllByLimit(1, 5);
        List<NoticeVO> list = new ArrayList<>();
        for (Notice notice : notices) {
            NoticeVO vo = new NoticeVO();
            vo.setUsername(notice.getUsername());
            vo.setText(notice.getText());
            vo.setTop(notice.getTop());
            vo.setUpdatetime(notice.getUpdatetime());
            list.add(vo);
        }
        r.success("查询成功", list);
        return r;
    }

    /**
     * 查看热点问卷
     * @return BaseResponse
     */
    @PostMapping("/hotq")
    public BaseResponse hotQuestionnaires() {
        R<List<HotQuestionnaire>> r = new R<>();
        List<HotQuestionnaire> hotQuestionnaires = this.userQService.hotQuestionnaires();
        r.success("查询成功", hotQuestionnaires);
        return r;
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
        question.setState(vo.getState());
        List<Question> questions = this.questionnaireService.listPage(question, vo.getPage(), vo.getLimit());
        Long count = this.questionnaireService.count(question);
        qr.success("查询成功", questions, count);

        return qr;
    }

}
