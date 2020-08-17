package com.demo.service.impl;

import com.demo.common.response.R;
import com.demo.config.SessionFields;
import com.demo.config.UserFields;
import com.demo.entity.po.TimeCount;
import com.demo.entity.po.User;
import com.demo.dao.UserDao;
import com.demo.entity.vo.login.UserRememberVO;
import com.demo.entity.vo.login.UserVO;
import com.demo.service.EncService;
import com.demo.service.UserService;
import com.demo.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private EncService encService;

    @Override
    public List<Long> countByDays() {
        List<String> between7Days = DateUtils.getBetween7Days();
        List<Long> counts = new ArrayList<>();
        for (int i = 0; i < between7Days.size(); i++) {
            Long count = this.userDao.countByDays(between7Days.get(i));
            counts.add(count);
        }
        return counts;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(String id) {
        return this.userDao.queryById(id);
    }

    /**
     * 通过用户名查询单条数据
     *
     * @param username 用户名
     * @return 实例对象
     */
    @Override
    public User queryByUsername(String username) {
        return this.userDao.queryByUsername(username);
    }

    /**
     * 条件查询
     *
     * @param user User
     * @return User
     */
    @Override
    public List<User> queryAllByUser(User user) {
        // 如果条件有密码，则先对密码加密
        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(this.encService.encPwd(user.getPassword()));
        }
        return this.userDao.queryAll(user);
    }

    /**
     * 登录
     *
     * @param user User
     * @return R<UserRememberVO>
     */
    @Override
    public R<UserRememberVO> login(User user) {
        R<UserRememberVO> response = new R<>();
        String pwd = user.getPassword();
        List<User> users = queryAllByUser(user);
        if (users.size() > 0) {
            // 有此用户，登录成功
            Integer role = users.get(0).getRole();
            UserRememberVO rememberVO = new UserRememberVO();
            UserVO vo = new UserVO();
            vo.setUsername(users.get(0).getUsername());
//            vo.setPassword(users.get(0).getPassword());
            vo.setPassword(pwd);
            rememberVO.setUserVO(vo);
            rememberVO.setUserId(users.get(0).getId());
            rememberVO.setRoleId(role);
            // 根据用户角色跳转不同的页面
            if (UserFields.ROLE_SUPER_ADMIN == role || UserFields.ROLE_ADMIN == role) {
                rememberVO.setIndex("/index");
            } else {
                rememberVO.setIndex("/view/index");
            }
            response.success("登录成功!", rememberVO);
        } else {
            response.fail("用户名/密码错误");
        }
        return response;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 条件分页查询
     *
     * @param user   User
     * @param page 页码
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryByUserLimit(User user, int page, int limit) {
        int offset = (page - 1) * limit;
        user.setRole(UserFields.ROLE_USER);
        return this.userDao.queryByUserLimit(user, offset, limit);
    }

    /**
     * 条件分页查询管理员
     *
     * @param user  User
     * @param page  页码
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAdminByUserLimit(User user, int page, int limit) {
        int offset = (page - 1) * limit;
        // 查询普通管理员
        user.setRole(UserFields.ROLE_ADMIN);
        List<User> adminUsers = this.userDao.queryByUserLimit(user, offset, limit);
        // 查询超级管理员
        user.setRole(UserFields.ROLE_SUPER_ADMIN);
        List<User> superUsers = this.userDao.queryByUserLimit(user, offset, limit);
        adminUsers.addAll(superUsers);
        return adminUsers;
    }

    /**
     * 总记录数
     *
     * @param user User
     * @return long
     */
    @Override
    public long count(User user) {
        user.setRole(UserFields.ROLE_USER);
        return this.userDao.count(user);
    }

    /**
     * 管理员总记录数
     *
     * @param user User
     * @return long
     */
    @Override
    public long countAdmin(User user) {
        user.setRole(UserFields.ROLE_ADMIN);
        long count = this.userDao.count(user);
        user.setRole(UserFields.ROLE_SUPER_ADMIN);
        long superCount = this.userDao.count(user);
        return count + superCount;
    }

    @Override
    public long countAll(String startTime, String endTime) {
        return this.userDao.countAll(startTime, endTime);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        // 对密码加密
        user.setPassword(this.encService.encPwd(user.getPassword()));
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);

        return this.queryByUsername(user.getUsername());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.userDao.deleteById(id) > 0;
    }
}