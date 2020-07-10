package com.demo.controller;

import com.demo.common.exception.ArgumentResponseEnum;
import com.demo.common.response.BaseResponse;
import com.demo.common.response.R;
import com.demo.config.SessionFields;
import com.demo.config.UserFields;
import com.demo.entity.po.User;
import com.demo.entity.po.UserLogin;
import com.demo.entity.vo.login.UserRegVO;
import com.demo.entity.vo.login.UserRememberVO;
import com.demo.entity.vo.login.UserVO;
import com.demo.service.UserLoginService;
import com.demo.service.UserService;
import com.demo.util.verify.IVerifyCodeGen;
import com.demo.util.verify.SimpleCharVerifyCodeGenImpl;
import com.demo.util.verify.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author affable
 * @description 登陆控制器
 * @date 2020/5/15 14:56
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    private final UserLoginService userLoginService;

    @Autowired
    public LoginController(UserService userService, UserLoginService userLoginService) {
        this.userService = userService;
        this.userLoginService = userLoginService;
    }

    /**
     * 生成验证码
     * @param request 请求
     * @param response 响应
     */
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {

        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            //将VerifyCode绑定session
            request.getSession().setAttribute(SessionFields.VERIFY_CODE, code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * 登录验证
     * @return 响应
     */
    @PostMapping("/login")
    public BaseResponse login(HttpSession session, @RequestBody @Valid UserVO userVO) {
        R<UserRememberVO> response = null;

        // 校验验证码
        ArgumentResponseEnum.VERIFY_FAIL.assertEquals((String) session.getAttribute(SessionFields.VERIFY_CODE),
                userVO.getVerifyCode(), true);

        User user = new User();
        user.setUsername(userVO.getUsername());
        user.setPassword(userVO.getPassword());
        response = this.userService.login(user);
        if (response.getCode() == 0) {
            // 有此用户，登录成功
            // 把 username 放入 session
            session.setAttribute(SessionFields.USERNAME, userVO.getUsername());
            session.setAttribute(SessionFields.ROLE_ID, response.getData().getRoleId());

            // 写入登录日志表
            UserLogin userLogin = new UserLogin();
            userLogin.setUid(response.getData().getUserId());
            this.userLoginService.insert(userLogin);
        }

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

    /**
     * 用户注册
     * @param vo UserRegVO
     * @return BaseResponse
     */
    @PostMapping("/reg")
    public BaseResponse reg(@RequestBody @Valid UserRegVO vo) {
        BaseResponse response = new BaseResponse();

        User user = new User();
        user.setUsername(vo.getUsername());
        List<User> users = this.userService.queryAllByUser(user);
        if (users.size() > 0) {
            // 已经有此 username
            response.fail("用户名已存在");
        } else {
            user.setPassword(vo.getPassword());
            // 只能注册普通用户
            user.setRole(UserFields.ROLE_USER);
            // 插入数据库
            this.userService.insert(user);
            response.success("注册成功");
        }

        return response;
    }


}
