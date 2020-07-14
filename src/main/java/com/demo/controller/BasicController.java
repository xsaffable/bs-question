package com.demo.controller;

import com.demo.common.response.BaseResponse;
import com.demo.common.response.R;
import com.demo.config.SessionFields;
import com.demo.entity.po.QuestionType;
import com.demo.entity.po.User;
import com.demo.service.QuestionTypeService;
import com.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    private UserService userService;

    /**
     * 问卷类型列表查询 无分页
     * @return BaseResponse
     */
    @PostMapping("/qtype/list")
    public BaseResponse list() {
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
    public BaseResponse addQType(HttpSession session, @PathVariable("name") String name) {
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
    public BaseResponse delete(@PathVariable("id") String id) {
        BaseResponse response = new BaseResponse();

        boolean b = this.questionTypeService.deleteById(id);
        if (b) {
            response.success("删除成功");
        } else {
            response.fail("删除失败");
        }

        return response;
    }

}
