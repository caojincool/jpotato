package com.lemsun.reporter;

import com.lemsun.core.LemsunResource;

import java.util.Date;

/**
 * 嵌入填报内的信息, 模型对象
 */
public class ReporterInfo {
    private LemsunResource resource;
    private Date updateTime;

    /**
     * 获取组件模型
     */
    public LemsunResource getResource() {
        return resource;
    }

    /**
     * 设置组件模型
     */
    public void setResource(LemsunResource resource) {
        this.resource = resource;
    }

    /**
     * 获取内容的更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置内容的更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
