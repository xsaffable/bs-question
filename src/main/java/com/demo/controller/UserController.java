package com.demo.controller;

import com.demo.common.response.BaseResponse;
import com.demo.common.response.QR;
import com.demo.common.response.R;
import com.demo.config.SessionFields;
import com.demo.config.UserFields;
import com.demo.entity.po.User;
import com.demo.entity.vo.usermanage.UserAddVO;
import com.demo.entity.vo.usermanage.UserAdminAddVO;
import com.demo.entity.vo.usermanage.UserConditionVO;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
     * 新增用户
     * @param vo UserAddVO
     * @return BaseResponse
     */
    @PostMapping("/add")
    public BaseResponse add(@RequestBody UserAddVO vo) {
        BaseResponse response = new BaseResponse();

        User user = new User();
        user.setUsername(vo.getUsername());
        List<User> users = this.userService.queryAllByUser(user);
        if (users.size() > 0) {
            // 已经有此 username
            response.fail("用户名已存在");
        } else {
            user.setNickname(vo.getNickname());
            user.setPassword(vo.getPassword());
            user.setPhoneNumber(vo.getPhoneNumber());
            user.setEmail(vo.getEmail());
            user.setSex(vo.getSex());
            // 只能添加普通用户
            user.setRole(UserFields.ROLE_USER);
            // 插入数据库
            this.userService.insert(user);
            response.success("新增成功");
        }

        return response;
    }

    /**
     * 新增管理员用户
     * @param vo UserAddVO
     * @return BaseResponse
     */
    @PostMapping("/admin/add")
    public BaseResponse adminAdd(@RequestBody UserAdminAddVO vo) {
        BaseResponse response = new BaseResponse();

        User user = new User();
        user.setUsername(vo.getUsername());
        List<User> users = this.userService.queryAllByUser(user);
        if (users.size() > 0) {
            // 已经有此 username
            response.fail("用户名已存在");
        } else {
            user.setNickname(vo.getNickname());
            user.setPassword(vo.getPassword());
            user.setPhoneNumber(vo.getPhoneNumber());
            user.setEmail(vo.getEmail());
            user.setRole(vo.getRoleId());
            // 插入数据库
            this.userService.insert(user);
            response.success("新增成功");
        }

        return response;
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
     * 管理员列表查询
     * @param userConditionVO UserConditionVO
     * @return BaseResponse
     */
    @PostMapping("/admin/list")
    public BaseResponse adminListPage(HttpSession session, @RequestBody UserConditionVO userConditionVO) {
        QR<List<User>> r = new QR<>();

        Integer roleId = (Integer) session.getAttribute(SessionFields.ROLE_ID);
        if (UserFields.ROLE_SUPER_ADMIN == roleId) {
            // 只有是超级管理员，才可以查看管理员列表
            User user = new User();
            user.setUsername(userConditionVO.getUsername());
            user.setNickname(userConditionVO.getNickname());
            user.setEmail(userConditionVO.getEmail());
            user.setSex(userConditionVO.getSex());
            List<User> users = this.userService.queryAdminByUserLimit(user, userConditionVO.getPage(), userConditionVO.getLimit());
            long count = this.userService.countAdmin(user);
            r.success("查询成功", users, count);
        } else {
            r.fail("您没有此权限");
        }

        return r;
    }

    /**
     * 删除用户
     * @param userConditionVOs UserConditionVO
     * @return BaseResponse
     */
    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody List<UserConditionVO> userConditionVOs) {
        BaseResponse response = new BaseResponse();
        boolean state = true;
        for (UserConditionVO userConditionVO : userConditionVOs) {
            state &= this.userService.deleteById(userConditionVO.getId());
        }
        if (state) {
            response.success("删除成功");
        } else {
            response.fail("删除失败");
        }

        return response;
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

    /**
     * 修改用户信息
     * @param user User
     * @return BaseResponse
     */
    @PostMapping("/update")
    public BaseResponse updateByUsername(@RequestBody User user) {
        BaseResponse response = new BaseResponse();
        User update = this.userService.update(user);
        if (update != null) {
            response.success("修改成功");
        } else {
            response.fail("修改失败");
        }

        return response;
    }

}
