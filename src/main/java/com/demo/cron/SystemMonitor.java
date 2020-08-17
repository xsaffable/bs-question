package com.demo.cron;

import com.demo.entity.po.SystemResource;
import com.demo.service.SystemResourceService;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.lang.management.ManagementFactory;
import java.util.UUID;

/**
 * @author affable
 * @description 系统监控
 * @date 2020/8/16 20:38
 */
@Configuration
@EnableScheduling
public class SystemMonitor {

    @Resource
    private SystemResourceService systemResourceService;

    /**
     * 把系统占用情况写入 mysql
     */
    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {
        OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        int ramUseRate = (int) ((1 - mem.getFreePhysicalMemorySize() / (double) mem.getTotalPhysicalMemorySize()) * 100);

        double cpuLoad = mem.getSystemCpuLoad();
        int cpuLoadRate = (int) (cpuLoad * 100);

        SystemResource sr = new SystemResource();
        sr.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        sr.setCpuRate(cpuLoadRate);
        sr.setMemoryRate(ramUseRate);

        this.systemResourceService.insert(sr);

    }

}
