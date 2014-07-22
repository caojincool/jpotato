package com.lemsun.data.tables;

import java.util.Date;
import java.util.List;

/**
 * 表示一个表格组下的, 物理表的信息
 * User: 宗旭东
 * Date: 13-3-25
 * Time: 下午1:28
 */
public class TableInfo {

    private String pid;
    private String name;
    private int cate;
    private int remark;
    private String dbPid;
    private Date dateTime;
    private boolean enable;
    private String timeFormat;
    private int version;
    private List<TableColumn> columns;
    private FaceSetting face;
    private String dbtable;
    private String code;
    private Date lastUpdate;


    /**
     * 获取表格组件的PID
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置表格组件
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取物理表名
     */
    public String getDbtable() {
        return dbtable;
    }

    /**
     * 设置物理表名
     */
    public void setDbtable(String dbtable) {
        this.dbtable = dbtable;
    }

    /**
     * 获取表格代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置表格代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取更新时间
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * 设置更新时间
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * 获取表名称
     */
    public String getName() {
        return name;
    }

    /**
     * 表名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取表类别
     */
    public int getCate() {
        return cate;
    }

    /**
     * 设置表类表
     */
    public void setCate(int cate) {
        this.cate = cate;
    }

    /**
     * 获取表说明
     */
    public int getRemark() {
        return remark;
    }

    /**
     * 设置表说明
     */
    public void setRemark(int remark) {
        this.remark = remark;
    }

    /**
     * 获取数据源组件编码
     */
    public String getDbPid() {
        return dbPid;
    }

    /**
     * 设置数据源组件编码
     */
    public void setDbPid(String dbPid) {
        this.dbPid = dbPid;
    }

    /**
     * 获取创建日期
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * 设置创建日期
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * 获取表是否启用状态
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * 设置表是否启用状态
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /**
     * 获取日期转换字符串
     */
    public String getTimeFormat() {
        return timeFormat;
    }

    /**
     * 设置日期转换字符串
     */
    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    /**
     * 获取表版本
     */
    public int getVersion() {
        return version;
    }

    /**
     * 设置表版本
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * 获取表的列信息
     */
    public List<TableColumn> getColumns() {
        return columns;
    }

    /**
     * 设置表的列信息
     */
    public void setColumns(List<TableColumn> columns) {
        this.columns = columns;
    }

    /**
     * 获取表的外观信息
     */
    public FaceSetting getFace() {
        return face;
    }

    /**
     * 设置表的外观信息
     */
    public void setFace(FaceSetting face) {
        this.face = face;
    }
}
