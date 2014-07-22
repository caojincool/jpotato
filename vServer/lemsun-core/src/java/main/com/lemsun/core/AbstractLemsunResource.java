package com.lemsun.core;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lemsun.core.engines.IResourcePermissionEngine;
import com.lemsun.core.jackson.CustomJsonDateSerializer;
import com.lemsun.core.jackson.ObjectIdDeserializer;
import com.lemsun.core.jackson.ObjectIdSerializer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;
import java.util.List;

/**
 * 表示一个在程序中的全部资源. 这个资源. 可以是一个单据. 可以是一个数据源. 也可以是一个报表
 * User: Xudong
 * Date: 12-10-16
 * Time: 下午2:15
 */
public abstract class AbstractLemsunResource implements IResource {

    @Id
    private ObjectId _id;

    @Indexed
    private String pid;

    private String name;
    private String category;
    private String createUser;
    private Date createTime;
    private Date updateTime;
    private String[] allowRoles;
    private List<ResourcePermission> rpList;
    private String permissionScript;
    private String parentPid;
    private boolean system;
    private String remark;
    private String strParams;
    private int state;
    private String businessCode;

    public AbstractLemsunResource(String name, String category) {
        this.name = name;
        this.category = category;
    }

    protected AbstractLemsunResource() {
    }

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
     * 获取开始参数(系统全局参数)
     *
     * @return 开始参数(系统全局参数)
     */
    public String getStrParams() {
        return strParams;
    }

    /**
     * 设置开始参数(系统全局参数)
     *
     * @param strParams 开始参数(系统全局参数)
     */
    public void setStrParams(String strParams) {
        this.strParams = strParams;
    }

    /**
     * 获取数据库的组件编码
     *
     * @return
     */
    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId getId() {
        return _id;
    }

    @JsonDeserialize(using = ObjectIdDeserializer.class)
    protected void setId(ObjectId id) {
        _id = id;
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
     * 获取组件创建者
     *
     * @return 组件创建者
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 获取组件更新日期
     *
     * @return 更新日期
     */
    @Override
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 获取组件允许的角色名称
     *
     * @return 允许操作组件的角色名称
     */
    @Override
    public String[] getAllowRoles() {
        return allowRoles;
    }

    /**
     * 获取权限脚本
     *
     * @return
     */
    @Override
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
     * 设置组件创建作者
     *
     * @param createUser 组件创建作者
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 设置组件更新|创建时间
     *
     * @param updateTime 组件更新| 创建时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
     * 获取是否系统组件
     *
     * @return 系统组件
     */
    public boolean isSystem() {
        return system;
    }

    /**
     * 设置是否系统组件
     *
     * @param system 系统组件
     */
    public void setSystem(boolean system) {
        this.system = system;
    }

    /**
     * 获取组件权限
     *
     * @param account 账号
     * @return 某个账户对组件的权限
     */
    @Override
    public Permission getPermission(IAccount account) {

        if (account.isAdministrator()) {
            return Permission.Allow;
        }

        if (!StringUtils.isEmpty(getPermissionScript())) {
            Permission p = SpringContextUtil.getApplicationContext()
                    .getBean(IResourcePermissionEngine.class)
                    .CheckByScript(account, getPermissionScript(), this);

            if (p != Permission.Unkonw) {
                return p;
            }
        }

        String[] pm = getAllowRoles();

        if (ArrayUtils.isNotEmpty(pm)) {
            String[] rs = account.getRoles();

            if (ArrayUtils.isNotEmpty(rs))
                for (String r : pm)
                    if (ArrayUtils.contains(rs, r)) {
                        return Permission.Allow;
                    }
        }

        if (StringUtils.equals(account.getAccount(), getCreateUser())) {
            return Permission.Allow;
        }

        return Permission.Unkonw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractLemsunResource that = (AbstractLemsunResource) o;

        return StringUtils.equals(pid, that.pid);

    }

    @Override
    public int hashCode() {
        return StringUtils.isNotEmpty(pid) ? pid.hashCode() : -1;
    }

    /**
     * 获取状态信息
     */
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "资源 : {" +
                "name='" + name + '\'' +
                ", id=" + pid +
                '}';
    }

    public List<ResourcePermission> getRpList() {
        return rpList;
    }

    public void setRpList(List<ResourcePermission> rpList) {
        this.rpList = rpList;
    }

    /**
     * 使用一个标准组件加载信息
     * @param resource
     */
    public void copyResource(LemsunResource resource) {
        this._id = resource.getId();
        this.setName(resource.getName());
        this.setCategory(resource.getCategory());
        this.setCreateUser(resource.getCreateUser());
        this.setRemark(resource.getRemark());
    }

    /**
     * 创建时间
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @return
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }
}
