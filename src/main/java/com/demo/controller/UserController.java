package com.demo.controller;

import com.demo.common.response.BaseResponse;
import com.demo.common.response.QR;
import com.demo.common.response.R;
import com.demo.entity.po.User;
import com.demo.entity.vo.usermanage.UserConditionVO;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author affable
 * @description 用户相关的 controller
 * @date 2020/7/10 13:40
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 列表查询
     * @param userConditionVO UserConditionVO
     * @return BaseResponse
     */
    @PostMapping("/list")
    public BaseResponse listPage(@RequestBody UserConditionVO userConditionVO) {
        QR<List<User>> r = new QR<>();

        User user = new User();
        user.setUsername(userConditionVO.getUsername());
        user.setNickname(userConditionVO.getNickname());
        user.setEmail(userConditionVO.getEmail());
        user.setSex(userConditionVO.getSex());
        List<User> users = this.userService.queryByUserLimit(user, userConditionVO.getPage(), userConditionVO.getLimit());
        long count = this.userService.count(user);
        r.success("查询成功", users, count);

        return r;
    }

    /**
     * 通过用户名查询用户
     * @param vo 用户名
     * @return 查询到的用户
     */
    @PostMapping("/get_by_username")
    public BaseResponse getByUsername(@RequestBody UserConditionVO vo) {
        R<User> r = new R<>();
        User user = this.userService.queryByUsername(vo.getUsername());
        r.success("查询成功", user);
        return r;
    }

    @PostMapping("/update")
    public BaseResponse updateByUsername(@RequestBody User user) {
        BaseResponse response = new BaseResponse();
        this.userService.update(user);
        response.success("更新成功");
        return response;
    }

}
