package com.lemsun.web.manager.controller.model.component;

import com.lemsun.auth.AccountManager;
import com.lemsun.core.LemsunResource;

import java.util.Date;

/**
 * User: Xudong
 * Date: 13-1-10
 * Time: 下午1:57
 * 创建组件请求参数模块
 */
public class ComponentCreate {

    private String category;
    private String name;
    private String remark;
    private String param;

    /**
     * 获取创建组件类型
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置创建组件类型
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置说明
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取组件参数
     */
    public String getParam() {
        return param;
    }

    /**
     * 设置组件参数
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * 创建一个基本的组件资源
     */
    public LemsunResource encapseLmsResource() {
        LemsunResource lr = new LemsunResource(getName(), getCategory());
        lr.setStrParams(getParam());
        lr.setRemark(getRemark());
        lr.setUpdateTime(new Date());
        lr.setCreateUser(AccountManager.getCurrentManager().getAccount().getAccount());
        return lr;
    }
}
