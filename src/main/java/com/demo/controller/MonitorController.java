package com.demo.controller;

import com.demo.common.response.BaseResponse;
import com.demo.common.response.QR;
import com.demo.common.response.R;
import com.demo.entity.po.SystemResource;
import com.demo.entity.vo.BaseVO;
import com.demo.entity.vo.monitor.UserLoginVO;
import com.demo.service.SystemResourceService;
import com.demo.service.UserLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author affable
 * @description 监控的 Controller
 * @date 2020/8/16 19:10
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private SystemResourceService systemResourceService;

    @PostMapping("/userlogin_listpage")
    public BaseResponse userLoginListPage(@RequestBody BaseVO vo) {
        QR<List<UserLoginVO>> qr = new QR<>();
        List<UserLoginVO> data = this.userLoginService.listByPage(vo.getPage(), vo.getLimit());
        Long count = this.userLoginService.count("0000-00-00", "9999-99-99");
        qr.success("查询成功", data, count);
        return qr;
    }

    @PostMapping("/system_use_rate")
    public BaseResponse systemUseRate() {
        R<List<SystemResource>> r = new R<>();
        List<SystemResource> systemResources = this.systemResourceService.countByTime();
        r.success("查询成功", systemResources);

        return r;
    }

}
