package com.demo.aop;

import com.demo.common.request.ReqRestState;
import com.demo.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author sxs
 * @description 日志拦截器
 * @date 2020-05-12 14:40
 */
@Aspect
@Component
@Slf4j(topic = "monitor")
public class WebLogAspect {

    @Pointcut("execution(public * com.demo.controller.*.*(..))")
    public void webLog() { }

    @Pointcut("execution(public * com.demo.common.exception.UnifiedExceptionHandler.*(..))")
    public void exceptionLog() { }

    @Pointcut("execution(public * com.demo.common.exception.UnifiedExceptionHandler.handleServletException(..))")
    public void exceptionPreControllerLog() { }

    /**
     * 前置通知
     * @param joinPoint JoinPoint
     * @throws Throwable 异常
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        StringBuilder sb = new StringBuilder();
        sb.append("URL:{")
                .append(request.getRequestURL())
                .append("},HTTP_METHOD: {")
                .append(request.getMethod())
                .append("},IP: {")
                .append(request.getRemoteAddr())
                .append("}");

        sb.append(",args:[");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            sb.append(arg).append(",");
        }
        sb.append("]");

        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            sb.append(",name:{")
                    .append(name)
                    .append("},value:{")
                    .append(request.getParameter(name))
                    .append("}");
        }
        log.warn(sb.toString());
    }

    /**
     * 异常前置通知
     * @throws Throwable 异常
     */
    @Before("exceptionPreControllerLog()")
    public void doExceptionControllerBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        Object[] args = joinPoint.getArgs();
        String logStr = String.format("{\"url\": \"%s\", \"msg\": \"\", \"state\": \"%s\"}",
                request.getRequestURL(), ReqRestState.REQUEST_FAI.getMessage());
        if (args[0] instanceof Exception) {
            // 去掉换行
            String message = ((Exception) args[0]).getMessage().replaceAll("\n", " ");
            logStr = String.format("{\"url\": \"%s\", \"msg\": \"%s\", \"state\": \"%s\"}",
                    request.getRequestURL(), message, ReqRestState.REQUEST_FAI.getMessage());
        }

        log.warn(logStr);
    }

    /**
     * 后置通知
     * @param ret 返回值
     * @throws Throwable 异常
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfter(Object ret) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String logStr = String.format("{\"url\": \"%s\", \"msg\": \"\", \"state\": \"%s\"}",
                request.getRequestURL(), ReqRestState.RESPONSE_SUC.getMessage());
        if (ret instanceof BaseResponse) {
            String message = ((BaseResponse) ret).getMsg().replaceAll("\n", " ");;
            logStr = String.format("{\"url\": \"%s\", \"msg\": \"%s\", \"state\": \"%s\"}",
                    request.getRequestURL(), message, ReqRestState.RESPONSE_SUC.getMessage());
        }

        log.warn(logStr);
    }

    /**
     * 异常后置通知
     * @param ret 返回值
     * @throws Throwable 异常
     */
    @AfterReturning(returning = "ret", pointcut = "exceptionLog()")
    public void doExceptionAfter(Object ret) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String logStr = String.format("{\"url\": \"%s\", \"msg\": \"\", \"state\": \"%s\"}",
                request.getRequestURL(), ReqRestState.RESPONSE_FAI.getMessage());
        if (ret instanceof BaseResponse) {
            String message = ((BaseResponse) ret).getMsg().replaceAll("\n", " ");;
            logStr = String.format("{\"url\": \"%s\", \"msg\": \"%s\", \"state\": \"%s\"}",
                    request.getRequestURL(), message, ReqRestState.RESPONSE_FAI.getMessage());
        }

        log.warn(logStr);
    }

}
