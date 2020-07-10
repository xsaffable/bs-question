package com.demo.interceptor;

import com.demo.config.SessionFields;
import com.demo.config.UserFields;
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
        Object username = session.getAttribute(SessionFields.USERNAME);
        if (null != username) {
            // 已登录
            // 获取请求路径
            String path = request.getServletPath();
            Integer roleId = (Integer) session.getAttribute(SessionFields.ROLE_ID);
            if (UserFields.ADMIN_INDEX.equals(path)) {
                // 访问管理员首页
                // 需要校验是否管理员角色
                if (UserFields.ROLE_USER == roleId) {
                    response.sendRedirect(UserFields.VIEW_INDEX);
                    return false;
                }
            }

            return true;
        } else {
            // 未登录
            // 直接重定向到登录页面
            response.sendRedirect("/login");
            return false;
        }
    }
}
