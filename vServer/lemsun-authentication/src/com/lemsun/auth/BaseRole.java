package com.lemsun.auth;

import com.lemsun.core.IRole;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * 基本的角色模型对象
 * User: Xudong
 * Date: 13-1-14
 * Time: 下午2:48
 * To change this template use File | Settings | File Templates.
 */
@Document(collection = "Roles")
public class BaseRole implements IRole {

    @Id
    private ObjectId id;

    private String name;
    private String describe;
    private Date createTime;
    private Date updateTime;
    private String createAccount;
    private List<PermissionKey> permissions;
    private boolean isSystem;

    /**
     * 获取更新时间
     * @return 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 获取更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取角色的权限
     * @return 角色权限
     */
    public List<PermissionKey> getPermissions() {
        return permissions;
    }

    /**
     * 设置角色权限
     * @param permissions 角色权限
     */
    public void setPermissions(List<PermissionKey> permissions) {
        this.permissions = permissions;
    }

    /**
     * 获取数据库自定义ID
     * @return 数据库自定义角色ID
     */
    public ObjectId getId() {
        return id;
    }


    /**
     * 获取角色名称
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * 获取备注信息
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置备注信息
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 获取创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建账号
     */
    public String getCreateAccount() {
        return createAccount;
    }

    /**
     * 设置创建账号
     */
    public void setCreateAccount(String createAccount) {
        this.createAccount = createAccount;
    }

    /**
     * 判断当前角色是否是系统角色
     */
    public boolean getisSystem() {
        return isSystem;
    }

    /**
     * 设置当前角色是否是系统角色
     */
    public void setisSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }
}
