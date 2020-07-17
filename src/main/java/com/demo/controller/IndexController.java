package com.demo.controller;

import com.demo.common.response.BaseResponse;
import com.demo.common.response.R;
import com.demo.entity.vo.index.TimeVO;
import com.demo.service.UserLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    
}
