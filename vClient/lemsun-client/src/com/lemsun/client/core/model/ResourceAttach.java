package com.lemsun.client.core.model;

import java.util.Date;

/**
 * 组件的附件信息
 * Created by 宗 on 2014/6/19 0019.
 */
public class ResourceAttach {

    private String pid;
    private String category;
    private Date updateTime;
    private String fileid;
    private String md5;
    private String name;
    private String type;
    private long size;

    /**
     * 构造一个空的附件对象
     */
    public ResourceAttach() {

    }


    /**
     * 获取附件的组件名称
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置附件的组件名称
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取组件的类型
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置组件的类型
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取文件名称, 不包含后缀名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置文件的名称. 不包含后缀名
     */
    public void setName(String filename) {
        this.name = filename;
    }

    /**
     * 获取文件的类型
     * */
    public String getType() {
        return type;
    }

    /**
     * 设置文件的类型
     * @param fileType
     */
    public void setType(String fileType) {
        this.type = fileType;
    }

    /**
     * 获取文件的更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置文件的更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取文件的长度
     */
    public long getSize() {
        return size;
    }

    /**
     * 设置文件的长度
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * 获取文件的 ID
     */
    public String getFileid() {
        return fileid;
    }

    /**
     * 设置文件的 ID
     */
    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    /**
     * 获取文件的签名值
     */
    public String getMd5() {
        return md5;
    }

    /**
     * 设置文件的签名值
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
