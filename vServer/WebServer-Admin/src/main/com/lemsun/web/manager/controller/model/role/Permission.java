package com.lemsun.web.manager.controller.model.role;

/**
 * 完整的角色权限信息
 * User: dpyang
 * Date: 13-2-1
 * Time: 上午11:51
 */
public class Permission extends BasePermission {
    private String key;
    private String name;
    private String remark;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
