package com.lemsun.core;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import java.util.Date;

/**
 * 帐套组件模型
 * User: dp
 * Date: 13-7-24
 * Time: 下午2:33
 */
@Document(collection = "SetOfBooksResource")
public class SetOfBooksResource {

    private String sid;

    private String resourcePid;

    @Transient
    private String name;

    @Transient
    private String category;

    @Transient
    private String createUser;

    @Transient
    private Date updateTime;

    private String operator;

    private Date hitchTime;

    /**
     * 帐套编码
     */
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * 组件编码
     */
    public String getResourcePid() {
        return resourcePid;
    }

    /**
     * 组件编码
     */
    public void setResourcePid(String resourcePid) {
        this.resourcePid = resourcePid;
    }

    /**
     * 组件名称
     * 数据库忽略保存
     */
    public String getName() {
        return name;
    }

    /**
     * 组件名称
     * 数据库忽略保存
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 组件类别
     * 数据库忽略保存
     */
    public String getCategory() {
        return category;
    }

    /**
     * 组件类别
     * 数据库忽略保存
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 组件创建者
     * 数据库忽略保存
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 组件创建者
     * 数据库忽略保存
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 组件挂载人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 组件挂载人
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 组件挂载时间
     *
     * @return
     */
    public Date getHitchTime() {
        return hitchTime;
    }

    /**
     * 组件挂载时间
     *
     * @param hitchTime
     */
    public void setHitchTime(Date hitchTime) {
        this.hitchTime = hitchTime;
    }

    /**
     * 组件更新时间
     * 数据库忽略保存
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 组件更新时间
     * 数据库忽略保存
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setLemsunRessource(LemsunResource resource) {

        if (resource != null) {
            setCreateUser(resource.getCreateUser());
            setCategory(resource.getCategory());
            setName(resource.getName());
            setUpdateTime(resource.getUpdateTime());
        }
    }
}
