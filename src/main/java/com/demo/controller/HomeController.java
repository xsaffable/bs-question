package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author affable
 * @description 页面跳转控制器
 * @date 2020/5/15 14:54
 */
@Controller
public class HomeController {

    @RequestMapping("/login")
    public String login() {
        return "user/login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/reg")
    public String reg() {
        return "user/reg";
    }

    /** 首页 */
    @RequestMapping("/home/console")
    public String console() {
        return "home/console";
    }

    /** 设置用户基本信息 */
    @RequestMapping("/set/user_info")
    public String useInfo() {
        return "set/user_info";
    }

    /** 重设用户密码 */
    @RequestMapping("/set/user_repwd")
    public String reUsePwd() {
        return "set/user_repwd";
    }

    /** 用户管理 */
    @RequestMapping("/user_manage/user")
    public String userList() {
        return "user_manage/user/list";
    }

    /** 用户管理 form */
    @RequestMapping("/user_manage/user_form")
    public String userForm() {
        return "user_manage/user/userform";
    }

    /** 管理员管理 */
    @RequestMapping("/admin_manage/user")
    public String adminList() {
        return "user_manage/admin/list";
    }

    /** 管理员管理 form */
    @RequestMapping("/admin_manage/user_form")
    public String adminForm() {
        return "user_manage/admin/adminform";
    }

    /** 问卷类型管理 */
    @RequestMapping("/basic_manage/qtype/list")
    public String questionTypeList() {
        return "basic_manage/question_type/tags";
    }

    /** 问卷类型管理 form */
    @RequestMapping("/basic_manage/qtype/form")
    public String questionTypeForm() {
        return "basic_manage/question_type/tagsform";
    }

    /** 问卷管理 */
    @RequestMapping("/basic_manage/q/list")
    public String questionList() {
        return "basic_manage/question/list";
    }

    /** 问卷管理 form */
    @RequestMapping("/basic_manage/q/form")
    public String questionForm() {
        return "basic_manage/question/listform";
    }

}
