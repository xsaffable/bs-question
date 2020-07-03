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

}
