package com.lemsun.channel.command.table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lemsun.core.jackson.CustomJsonDateDeserializer;

import java.util.Date;

/**
 * User: Xudong
 * Date: 12-12-25
 * Time: 上午9:16
 * 表格资源保存更新模型. 接收用户请求上来的需要修改的数据对象
 */
public class TableSaveModel {
    private String pid;
    private TableData entity;
    private Date date;

    /**
     * 获取操作表格的主键
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置表格的主键
     * @param pid 主键
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取携带的表格
     */
    public TableData getEntity() {
        return entity;
    }

    /**
     * 设置携带的表格
     */
    public void setEntity(TableData entity) {
        this.entity = entity;
    }

    /**
     * 设置获取操作时间
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置获取操作时间
     */
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setDate(Date date) {
        this.date = date;
    }
}
