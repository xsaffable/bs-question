package com.demo.controller;

import com.demo.common.response.BaseResponse;
import com.demo.common.response.R;
import com.demo.entity.po.QuestionnaireGroupByType;
import com.demo.entity.po.UserCountGroupByUName;
import com.demo.entity.po.UserQCountGroupByQTitle;
import com.demo.service.QuestionnaireService;
import com.demo.service.UserLoginService;
import com.demo.service.UserQService;
import com.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author affable
 * @description 分析 controller
 * @date 2020/8/15 18:58
 */
@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    @Resource
    private QuestionnaireService questionnaireService;

    @Resource
    private UserQService userQService;

    @Resource
    private UserService userService;

    @Resource
    private UserLoginService userLoginService;

    /**
     * 按类型分组统计问卷数量
     * @return BaseResponse
     */
    @PostMapping("/count_groupby_type")
    public BaseResponse getCountGroupByType() {
        R<List<QuestionnaireGroupByType>> r = new R<>();
        List<QuestionnaireGroupByType> questionnaireGroupByTypes = this.questionnaireService.countGroupByType();
        r.success("查询成功", questionnaireGroupByTypes);
        return r;
    }

    @PostMapping("/count_uq_groupby_title")
    public BaseResponse getUserQCountGroupByTitle() {
        R<List<UserQCountGroupByQTitle>> r = new R<>();
        List<UserQCountGroupByQTitle> userQCountGroupByQTitles = this.userQService.countGroupByQTitle();
        r.success("查询成功", userQCountGroupByQTitles);
        return r;
    }

    @PostMapping("/questionnaire_count_days")
    public BaseResponse questionnaireCountDays() {
        R<List<Long>> r = new R<>();
        List<Long> longs = this.questionnaireService.countDay();
        r.success("查询成功", longs);
        return r;
    }

    @PostMapping("/user_count_days")
    public BaseResponse userCountDays() {
        R<List<Long>> r = new R<>();
        List<Long> timeCounts = this.userService.countByDays();
        r.success("查询成功", timeCounts);
        return r;
    }

    @PostMapping("/user_count_by_uname")
    public BaseResponse userCountByUName() {
        R<List<UserCountGroupByUName>> r = new R<>();
        List<UserCountGroupByUName> counts = this.userLoginService.countGroupByUName();
        r.success("查询成功", counts);
        return r;
    }

}
