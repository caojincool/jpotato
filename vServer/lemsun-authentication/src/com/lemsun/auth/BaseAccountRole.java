package com.lemsun.auth;

import com.lemsun.core.IRole;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

/**
 * 人员角色表
 * User: dpyang
 * Date: 13-1-30
 * Time: 下午2:10
 */
@Document(collection = "AccountRole")
public class BaseAccountRole {

    @Id
    private ObjectId id;
    private String accountId;
    private String roleId;

    private String createAccount;
    private String updataAccount;
    private Date createTime;
    private Date updateTime;

    /**
     * 获取系统自增长ID
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * 获取帐号ID
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * 设置帐号ID
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * 获取角色ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取创建者名称
     */
    public String getCreateAccount() {
        return createAccount;
    }

    /**
     * 设置创建者名称
     */
    public void setCreateAccount(String createAccount) {
        this.createAccount = createAccount;
    }

    /**
     * 获取修改者名称
     */
    public String getUpdataAccount() {
        return updataAccount;
    }

    /**
     * 设置修改者名称
     */
    public void setUpdataAccount(String updataAccount) {
        this.updataAccount = updataAccount;
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
     * 获取更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
