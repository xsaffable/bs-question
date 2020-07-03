package com.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author affable
 * @description 登录拦截器
 * @date 2020/5/15 16:31
 */
@Slf4j
@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        Object username = session.getAttribute("username");
        if (null != username) {
            // 已登录
            return true;
        } else {
            // 未登录
            // 直接重定向到登录页面
            response.sendRedirect("/login");
            return false;
        }
    }
}
