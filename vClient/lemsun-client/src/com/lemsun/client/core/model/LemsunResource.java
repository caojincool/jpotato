package com.lemsun.client.core.model;

import com.lemsun.client.core.Host;
import com.lemsun.client.core.IAccount;
import com.lemsun.client.core.Permission;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 组件对象的基本模型, 承载着组件的基本属性
 * User: 宗旭东
 * Date: 13-3-9
 * Time: 下午12:06
 */
public class LemsunResource {

    private String id;
    private String pid;
    private String name;
    private String category;
    private String createUser;
    private Date updateTime;
    private String[] allowRoles;
    private String permissionScript;
    private String parentPid;
    private boolean system;
    private String remark;
    private String strParams;



    /**
     * 获取数据的物理主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置数据的物理主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取备注信息
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取启动参数
     */
    public String getStrParams() {
        return strParams;
    }

    /**
     * 设置启动参数
     */
    public void setStrParams(String strParams) {
        this.strParams = strParams;
    }

    /**
     * 获取业务主键
     */
    public String getPid() {
        return pid;
    }

    /**
     * 获取组件名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取组件类型
     */
    public String getCategory() {
        return category;
    }

    /**
     * 获取创建人员账号
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 获取更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 获取组件当前允许角色
     */
    public String[] getAllowRoles() {
        return allowRoles;
    }

    /**
     * 获取组件权限执行脚本
     */
    public String getPermissionScript() {
        return permissionScript;
    }

    /**
     * 设置组件的运行角色
     */
    public void setAllowRoles(String[] allowRoles) {
        this.allowRoles = allowRoles;
    }

    /**
     * 设置组件的权限执行脚本
     */
    public void setPermissionScript(String permissionScript) {
        this.permissionScript = permissionScript;
    }

    /**
     * 设置组件的业务id
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 设置组件的名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置组件的类型
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 设置组件的创建人员
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 设置组件的更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取父节组件的PID
     */
    public String getParentPid() {
        return parentPid;
    }
    /**
     * 设置父节组件的PID
     */
    public void setParentPid(String parentPid) {
        this.parentPid = parentPid;
    }

    /**
     * 是否是系统组件
     */
    public boolean isSystem() {
        return system;
    }

    /**
     *
     *设置是否是系统组件
     * */
    public void setSystem(boolean system) {
        this.system = system;
    }


    /***
     * 返回给定账号的权限
     * @param account
     * @return
     */
    public Permission hasPermission(IAccount account) {

        //TODO 去遵循
        if(account == null && getAllowRoles() == null && StringUtils.isEmpty(getPermissionScript()))
        {
            return Permission.Allow;
        }

        if(StringUtils.equals(getPid(), Host.getInstance().getPlateform().getLogon())
                || StringUtils.equals(getPid(), Host.getInstance().getPlateform().getError()))
        {
            return Permission.Allow;
        }


        if(account == null)
        {
            return Permission.Deny;
        }


        if(getAllowRoles() != null)
        {

        }


        return Permission.Deny;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LemsunResource that = (LemsunResource) o;

        return StringUtils.equals(pid, that.pid);

    }


    @Override
    public int hashCode() {
        return StringUtils.isNotEmpty(pid) ? pid.hashCode() : -1;
    }

    @Override
    public String toString() {
        return "资源 : {" +
                "name='" + name + '\'' +
                ", id=" + pid +
                '}';
    }
}
