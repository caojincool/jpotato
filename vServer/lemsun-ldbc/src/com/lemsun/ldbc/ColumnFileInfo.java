package com.lemsun.ldbc;

import java.util.Date;

/**
 * 保存在字段里面的文件信息
 * User: 宗旭东
 * Date: 13-7-12
 * Time: 下午12:40
 */
public class ColumnFileInfo {

    private String id;
    private String rowid;
    private Date lastUpdate;
    private String resourceId;
    private String type;
    private String fileName;
    private String column;
    private String remark;


    public ColumnFileInfo(String id, String rowid, Date lastUpdate, String resourceId, String type, String fileName, String column, String remark) {
        this.id = id;
        this.rowid = rowid;
        this.lastUpdate = lastUpdate;
        this.resourceId = resourceId;
        this.type = type;
        this.fileName = fileName;
        this.column = column;
        this.remark = remark;
    }

    /**
     * 获取文件主键
     */
    public String getId() {
        return id;
    }



    /**
     * 获取所属组件
     */
    public String getResourceId() {
        return resourceId;
    }



    /**
     * 获取所属列
     */
    public String getColumn() {
        return column;
    }



    /**
     * 获取所属行的ID
     */
    public String getRowid() {
        return rowid;
    }




    /**
     * 获取更新信息
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }



    /**
     * 获取文件类型
     */
    public String getType() {
        return type;
    }



    /**
     * 获取文件名
     */
    public String getFileName() {
        return fileName;
    }


    public String getRemark() {
        return remark;
    }
}
