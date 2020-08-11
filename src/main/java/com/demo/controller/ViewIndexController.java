package com.demo.controller;

import com.demo.common.response.BaseResponse;
import com.demo.common.response.R;
import com.demo.entity.po.Notice;
import com.demo.entity.vo.viewindex.NoticeVO;
import com.demo.service.NoticeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author affable
 * @description 用户首页的控制器
 * @date 2020/8/4 21:20
 */
@RestController
@RequestMapping("/view_index")
public class ViewIndexController {

    @Resource
    private NoticeService noticeService;

    @PostMapping("/notice_list")
    public BaseResponse noticeList() {
        R<List<NoticeVO>> r = new R<>();
        List<Notice> notices = noticeService.queryAllByLimit(1, 5);
        List<NoticeVO> list = new ArrayList<>();
        for (Notice notice : notices) {
            NoticeVO vo = new NoticeVO();
            vo.setUsername(notice.getUsername());
            vo.setText(notice.getText());
            vo.setTop(notice.getTop());
            vo.setUpdatetime(notice.getUpdatetime());
            list.add(vo);
        }
        r.success("查询成功", list);
        return r;
    }

}
