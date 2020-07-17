package com.demo.service;

import com.demo.entity.po.Notice;
import java.util.List;

/**
 * 公告(Notice)表服务接口
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public interface NoticeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Notice queryById(String id);

    /**
     * 查询多条数据
     *
     * @param page 页码
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Notice> queryAllByLimit(int page, int limit);

    Long count();

    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    Notice insert(Notice notice);

    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    Notice update(Notice notice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}