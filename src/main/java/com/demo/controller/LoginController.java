package com.demo.controller;

import com.demo.common.response.BaseResponse;
import com.demo.config.SessionFields;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author affable
 * @description 登陆控制器
 * @date 2020/5/15 14:56
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    /**
     * 登录验证
     * @return 响应
     */
    @PostMapping("/login")
    public BaseResponse login(HttpSession session) {
        BaseResponse response = new BaseResponse();

        response.success("登录成功!");
        // 把 username 放入 session
        session.setAttribute(SessionFields.USERNAME, "xxx");

        return response;
    }

    /**
     * 退出
     * @return 响应
     */
    @RequestMapping("/logout")
    public BaseResponse logout(HttpSession session) {
        BaseResponse response = new BaseResponse();

        response.success("退出成功!");
        // 移除 session 中的 username
        session.removeAttribute(SessionFields.USERNAME);

        return response;
    }


}
