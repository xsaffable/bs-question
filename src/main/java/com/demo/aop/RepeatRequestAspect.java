package com.demo.aop;

import com.demo.common.response.ErrorResponse;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author affable
 * @description 处理重复请求的切面
 * @date 2020/6/11 17:04
 */
@Slf4j(topic = "cache")
@Aspect
@Configuration
public class RepeatRequestAspect {

    /** 最大缓存数量 */
    @Value("${itf.cache.maxsize}")
    private String maxCacheSize;

    /** 过期时间，单位: 分钟 */
    @Value("${itf.cache.expiretime}")
    private String expireTime;

    /** 本地缓存 */
    private Cache<String, Object> cache;

    /**
     * 获取缓存
     * @return Cache<String, Object>
     */
    private Cache<String, Object> getCache() {
        if (cache == null) {
            cache = CacheBuilder.newBuilder()
                    // 最大缓存个数
                    .maximumSize(Integer.parseInt(maxCacheSize))
                    // 设置过期时间，单位: 分钟
                    .expireAfterWrite(Integer.parseInt(expireTime), TimeUnit.MINUTES)
                    .build();

        }
        return cache;
    }

    /**
     * 对所有带有改注解的方法定义切点
     */
    @Pointcut("@annotation(com.demo.annotation.RepeatRequest)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object interceptor(ProceedingJoinPoint pjp) {
        // 返回结果
        Object result = null;
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 方法
        Method method = signature.getMethod();
        // 类名
        String className = pjp.getTarget().getClass().getName();
        // 参数
        Object[] args = pjp.getArgs();

        String cacheKey = getCacheKey(className, method, args);
        if (!StringUtils.isBlank(cacheKey)) {
            Object cacheResult = getCache().getIfPresent(cacheKey);
            if (cacheResult == null) {
                // 第一次请求，放入缓存
                try {
                    result = pjp.proceed(args);
                    getCache().put(cacheKey, result);
                } catch (Throwable throwable) {
                    log.error("缓存失败 ==> key: {}, val: {}", cacheKey, result);
                    return new ErrorResponse(500, "系统偷懒啦，请稍后再试");
                }
                return result;
            } else {
                log.info("命中缓存 ==> key: {}, val: {}, 目前缓存数量: {}", cacheKey, cacheResult, getCache().size());
                return cacheResult;
            }
        }
        return null;
    }

    /**
     * 获取缓存的 key
     * @param className 类名
     * @param method 方法
     * @param args 参数
     * @return key
     */
    private String getCacheKey(String className, Method method, Object[] args) {
        StringBuilder sb = new StringBuilder(className + "_" + method.getName() + "_");
        for (Object arg : args) {
            sb.append(arg);
        }
        return sb.toString();
    }

}
