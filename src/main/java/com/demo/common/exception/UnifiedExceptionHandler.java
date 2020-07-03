package com.demo.common.exception;

import com.demo.common.exception.base.BaseException;
import com.demo.common.exception.base.BusinessException;
import com.demo.common.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author sxs
 * @description 统一异常处理类
 * ConditionalOnWebApplication 当Spring为web服务时，才使注解的类生效
 * ConditionalOnMissingBean(UnifiedExceptionHandler.class) 在没有这个bean的时候，才注入这个bean
 * @date 2020/5/11 15:14
 */
@Slf4j(topic = "monitor")
@Component
@ControllerAdvice(basePackages = "com.demo.controller")
@ConditionalOnWebApplication
// @ConditionalOnMissingBean(UnifiedExceptionHandler.class)
public class UnifiedExceptionHandler {

    /**
     * 生产环境
     */
    private final static String ENV_PROD = "prod";

    /**
     * 当前环境
     */
    @Value("${spring.profiles.active}")
    private String profile;

    /**
     * 获取消息
     * @param e 异常
     * @return 异常消息
     */
    private String getMessage(BaseException e) {
        // TODO: 此处可以考虑对message做一些操作，例如 国际化
        return e.getMessage();
    }

    /**
     * 处理业务异常
     * @param e 异常
     * @return 错误响应
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ErrorResponse handleBusiness(BaseException e) {
        log.error(e.getMessage(), e);

        return new ErrorResponse(e.getResponseEnum().getCode(), getMessage(e));
    }

    /**
     * 自定义异常
     * @param e 异常
     * @return 错误响应
     */
    @ResponseBody
    @ExceptionHandler(value = BaseException.class)
    public ErrorResponse handleBaseException(BaseException e) {
        log.error(e.getMessage(), e);

        return new ErrorResponse(e.getResponseEnum().getCode(), getMessage(e));
    }

    /**
     * controller上一层异常
     * @param e 异常
     * @return ErrorResponse
     */
    @ResponseBody
    @ExceptionHandler({
            // TODO: 404、405这两个异常捕获不到，原因不明
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    public ErrorResponse handleServletException(Exception e) {
        log.error(e.getMessage(), e);
        int code = CommonResponseEnum.REQUEST_ERROR.getCode();
        try {
            // 获取实际错误码
            ServletResponseEnum servletResponseEnum = ServletResponseEnum.valueOf(e.getClass().getSimpleName());
            code = servletResponseEnum.getCode();
        } catch (IllegalArgumentException exception) {
            log.error("class [{}] not defined in enum {}", e.getClass().getName(), ServletResponseEnum.class.getName());
        }

        if (ENV_PROD.equals(profile)) {
            // 在生产环境，不适合把具体的异常信息展示给用户
            // 定义展示给用户的返回码
            code = CommonResponseEnum.REQUEST_ERROR.getCode();
            BaseException baseException = new BaseException(CommonResponseEnum.REQUEST_ERROR);
            String message = getMessage(baseException);
            return new ErrorResponse(code, message);
        }

        return new ErrorResponse(code, e.getMessage());

    }

    /**
     * 参数绑定异常
     * @param e 异常
     * @return 返回响应
     */
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public ErrorResponse handleBindException(BindException e) {
        log.error("参数绑定异常", e);

        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 参数校验异常
     * @param e 异常
     * @return 异常结果
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ErrorResponse handleValidException(MethodArgumentNotValidException e) {
        log.error("参数校验异常", e);

        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 包装绑定异常
     * @param bindingResult 绑定结果
     * @return 异常结果
     */
    private ErrorResponse wrapperBindingResult(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();
        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(", ");
            if (error instanceof FieldError) {
                msg.append(((FieldError) error).getField()).append(": ");
            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());
        }

        return new ErrorResponse(ArgumentResponseEnum.VALID_ERROR.getCode(), msg.substring(2));

    }

}
