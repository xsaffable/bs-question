package com.demo.entity.po;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * 系统资源表(SystemResource)实体类
 *
 * @author makejava
 * @since 2020-07-10 09:36:57
 */
public class SystemResource implements Serializable {
    private static final long serialVersionUID = 420466748526376544L;
    /**
    * id
    */
    private String id;
    /**
    * cpu使用率
    */
    private Integer cpuRate;
    /**
    * 内存使用率
    */
    private Integer memoryRate;
    
    private Timestamp createtime;
    
    private Timestamp updatetime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCpuRate() {
        return cpuRate;
    }

    public void setCpuRate(Integer cpuRate) {
        this.cpuRate = cpuRate;
    }

    public Integer getMemoryRate() {
        return memoryRate;
    }

    public void setMemoryRate(Integer memoryRate) {
        this.memoryRate = memoryRate;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

}