package com.demo.controller;

import com.demo.common.response.BaseResponse;
import com.demo.entity.po.UserQ;
import com.demo.entity.po.UserQT;
import com.demo.entity.vo.viewindex.UserQTVO;
import com.demo.entity.vo.viewindex.UserQVO;
import com.demo.service.UserQService;
import com.demo.service.UserQTService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author affable
 * @description 问卷相关的 Controller
 * @date 2020/8/9 20:09
 */
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {

    @Resource
    private UserQService userQService;

    @Resource
    private UserQTService userQTService;

    /**
     * 插入问卷填写记录
     * @param vo VO
     * @return BaseResponse
     */
    @PostMapping("/insertq")
    public BaseResponse insertQ(@RequestBody UserQVO vo) {
        BaseResponse response = new BaseResponse();
        String id = UUID.randomUUID().toString().replaceAll("-", "");

        UserQ userQ = new UserQ();
        userQ.setId(id);
        userQ.setUid(vo.getUid());
        userQ.setQid(vo.getQid());
        userQ.setQTypeId(vo.getQtypeId());
        userQ.setQTitle(vo.getQtitle());
        this.userQService.insert(userQ);

        for (UserQTVO uqt : vo.getTs()) {
            UserQT userQT = new UserQT();
            userQT.setUserQId(id);
            userQT.setQid(vo.getQid());
            userQT.setTid(uqt.getTid());
            userQT.setTOrder(uqt.getTorder());
            userQT.setTTitle(uqt.getTtitle());
            userQT.setAnsId(uqt.getAnsId());
            userQT.setAnsOrder(uqt.getAnsOrder());
            userQT.setAnsTitle(uqt.getAnsTitle());
            this.userQTService.insert(userQT);
        }

        response.success("插入成功");
        return response;
    }

}
