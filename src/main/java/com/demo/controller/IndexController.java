package com.demo.controller;

import com.demo.common.response.BaseResponse;
import com.demo.common.response.R;
import com.demo.entity.po.DTCount;
import com.demo.entity.vo.index.TimeVO;
import com.demo.service.*;
import com.demo.util.DateUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    private QuestionTypeService questionTypeService;

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

    /**
     * 获取所有的问卷类型
     * @return BaseResponse
     */
    @PostMapping("/all_qt")
    public BaseResponse getAllQType() {
        R<List<String>> r = new R<>();
        List<String> strings = this.questionTypeService.queryAll();
        r.success("查询成功", strings);
        return r;
    }

    @PostMapping("/dtcounts")
    public BaseResponse getDTCounts(@RequestBody TimeVO timeVO) {
        R<Map<String, List<Integer>>> r = new R<>();
        List<DTCount> dtCounts = this.userQService.queryDTCount(timeVO.getStartTime(), timeVO.getEndTime());
        List<String> days = DateUtils.getBetween7Days();
        // 类别 -> 所有时间对应的 count 组成的 list
        Map<String, List<Tuple2<String, Integer>>> map = new HashMap<>(16);
        dtCounts.forEach(dtCount -> {
            List<Tuple2<String, Integer>> value = map.getOrDefault(dtCount.getType(), new ArrayList<>());
            value.add(new Tuple2<>(dtCount.getDateStr(), dtCount.getCount()));
            map.put(dtCount.getType(), value);
        });
        // 结果 map
        Map<String, List<Integer>> resultMap = new HashMap<>(map.size());
        map.forEach((k, v) -> {
            Integer[] ss = new Integer[days.size()];
            Arrays.fill(ss, 0);
            v.forEach(t2 -> {
                int index = days.indexOf(t2._1());
                ss[index] = t2._2();
            });
            resultMap.put(k, Arrays.asList(ss));
        });
        r.success("查询成功", resultMap);

        return r;

    }

    class Tuple2<U, V> {
        private U t1;
        private V t2;

        public Tuple2(U t1, V t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        public U _1() { return t1; }

        public V _2() { return t2; }

    }
    
}
