package com.lemsun.web.manager.controller.model.component;

/**
 * 基本组件模型
 * User: dp
 * Date: 13-5-9
 * Time: 下午3:21
 */
public class LemsunResourceModel {

    private String pid;
    private String name;
    private String category;
    private String[] allowRoles;
    private String permissionScript;
    private String parentPid;
    private String remark;
    private String strParams;
    private String createUser;

    /**
     * 获取说明
     *
     * @return 说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置说明
     *
     * @param remark 说明
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取开始参数
     *
     * @return 开始参数
     */
    public String getStrParams() {
        return strParams;
    }

    /**
     * 设置开始参数
     *
     * @param strParams 开始参数
     */
    public void setStrParams(String strParams) {
        this.strParams = strParams;
    }

    /**
     * 获取组件编码
     *
     * @return 组件编码
     */
    public String getPid() {
        return pid;
    }

    /**
     * 获取组件名称
     *
     * @return 组件名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取组件类型
     *
     * @return 组件类型
     */
    public String getCategory() {
        return category;
    }

    /**
     * 获取组件允许的角色名称
     *
     * @return 允许操作组件的角色名称
     */
    public String[] getAllowRoles() {
        return allowRoles;
    }

    /**
     * 获取权限脚本
     *
     * @return
     */
    public String getPermissionScript() {
        return permissionScript;
    }

    /**
     * 获取允许操作组件的角色
     *
     * @param allowRoles 组件的角色
     */
    public void setAllowRoles(String[] allowRoles) {
        this.allowRoles = allowRoles;
    }

    /**
     * 设置权限脚本
     *
     * @param permissionScript
     */
    public void setPermissionScript(String permissionScript) {
        this.permissionScript = permissionScript;
    }

    /**
     * 设置组件编码
     *
     * @param pid 组件编码
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 设置组件名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置组件类别
     *
     * @param category 组件类型
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取父组件编码
     *
     * @return 父组件编码
     */
    public String getParentPid() {
        return parentPid;
    }

    /**
     * 设置父组件编码
     *
     * @param parentPid 父组件编码
     */
    public void setParentPid(String parentPid) {
        this.parentPid = parentPid;
    }

    /**
     *
     */
    public void encapsulation() {

    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
