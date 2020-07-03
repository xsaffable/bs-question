package com.demo.controller;

import com.demo.common.response.BaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author affable
 * @description 登陆控制器
 * @date 2020/5/15 14:56
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * 登录验证
     * @return 响应
     */
    @ResponseBody
    @PostMapping("/login")
    public BaseResponse login(HttpSession session) {
        BaseResponse response = new BaseResponse();

        response.success("登录成功!");
        // 放入session
        session.setAttribute("username", "xxx");

        return response;
    }

    /**
     * 退出
     * @return 响应
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("username");

        return "redirect:/login";
    }


}
