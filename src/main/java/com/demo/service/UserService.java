package com.demo.service;

import com.demo.common.response.R;
import com.demo.entity.po.User;
import com.demo.entity.vo.login.UserRememberVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(String id);

    /**
     * 通过用户名查询单条数据
     *
     * @param username 用户名
     * @return 实例对象
     */
    User queryByUsername(String username);

    /**
     * 条件查询
     * @param user User
     * @return User
     */
    List<User> queryAllByUser(User user);

    /**
     * 条件分页查询
     * @param user User
     * @param page 页码
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryByUserLimit(User user, int page, int limit);

    /**
     * 条件分页查询管理员
     * @param user User
     * @param page 页码
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAdminByUserLimit(User user, int page, int limit);

    /**
     * 总记录数
     * @param user User
     * @return long
     */
    long count(User user);

    /**
     * 管理员总记录数
     * @param user User
     * @return long
     */
    long countAdmin(User user);

    /**
     * 登录
     * @param user User
     * @return R<UserRememberVO>
     */
    R<UserRememberVO> login(User user);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}