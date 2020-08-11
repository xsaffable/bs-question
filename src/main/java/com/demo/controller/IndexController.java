package com.demo.controller;

import com.demo.common.response.BaseResponse;
import com.demo.common.response.R;
import com.demo.entity.vo.index.TimeVO;
import com.demo.service.QuestionnaireService;
import com.demo.service.UserLoginService;
import com.demo.service.UserQService;
import com.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author affable
 * @description 首页的 controller
 * @date 2020/7/17 14:36
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private UserService userService;

    @Resource
    private QuestionnaireService questionnaireService;

    @Resource
    private UserQService userQService;

    /**
     * 访问量
     * @param vo TimeVO
     * @return BaseResponse
     */
    @PostMapping("/visit_count")
    public BaseResponse visitCount(@RequestBody TimeVO vo) {
        R<Long> r = new R<>();
        Long count = this.userLoginService.count(vo.getStartTime(), vo.getEndTime());
        r.success("查询成功", count);
        return r;
    }

    @PostMapping("/user_count")
    public BaseResponse userCount(@RequestBody TimeVO vo) {
        R<Long> r = new R<>();
        long count = this.userService.countAll(vo.getStartTime(), vo.getEndTime());
        r.success("查询成功", count);
        return r;
    }

    @PostMapping("/active_user_count")
    public BaseResponse activeUserCount(@RequestBody TimeVO vo) {
        R<Long> r = new R<>();
        long count = this.userLoginService.countUsers(vo.getStartTime(), vo.getEndTime());
        r.success("查询成功", count);
        return r;
    }

    @PostMapping("/questionnaire_count")
    public BaseResponse questionnaireCount(@RequestBody TimeVO vo) {
        R<Long> r = new R<>();
        long count = this.questionnaireService.countByTime(vo.getStartTime(), vo.getEndTime());
        r.success("查询成功", count);
        return r;
    }

    /**
     * 今年每个月访问量
     * @return BaseResponse
     */
    @PostMapping("/visit_count_month")
    public BaseResponse visitCountMonth() {
        R<List<Long>> r = new R<>();
        List<Long> counts = this.userLoginService.countMonth();

        r.success("查询成功", counts);

        return r;
    }

    /**
     * 今年每个月问卷新增量
     * @return BaseResponse
     */
    @PostMapping("/questionnaire_count_month")
    public BaseResponse questionnaireCountMonth() {
        R<List<Long>> r = new R<>();
        List<Long> counts = this.questionnaireService.countMonth();
        r.success("查询成功", counts);

        return r;
    }

    /**
     * 问卷同期增长率
     * @return BaseResponse
     */
    @PostMapping("/questionnaire_inc_rate")
    public BaseResponse questionnaireIncRate() {
        R<String> r = new R<>();
        double rate = this.questionnaireService.incRate();

        r.success("查询成功", String.format("%.0f", rate * 100));
        return r;
    }

    /**
     * 访问量同期增长率
     * @return BaseResponse
     */
    @PostMapping("/visit_inc_rate")
    public BaseResponse visitIncRate() {
        R<String> r = new R<>();
        double rate = this.userLoginService.incRate();
        r.success("查询成功", String.format("%.0f", rate * 100));
        return r;
    }

    /**
     * 每个月问卷填写量
     * @return BaseResponse
     */
    @PostMapping("/questionnaire_edit_count_month")
    public BaseResponse questionnaireEditCountMonth() {
        R<List<Long>> r = new R<>();
        List<Long> counts = this.userQService.countMonth();
        r.success("查询成功", counts);
        return r;
    }

    /**
     * 问卷填写量同期增长
     * @return BaseResponse
     */
    @PostMapping("/questionnaire_edit_inc_rate")
    public BaseResponse questionnaireEditIncRate() {
        R<String> r = new R<>();
        double incRate = this.userQService.incRate();
        r.success("查询成功", String.format("%.0f", incRate * 100));
        return r;
    }
    
}
