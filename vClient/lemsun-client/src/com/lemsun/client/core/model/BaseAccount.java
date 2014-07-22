package com.lemsun.client.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lemsun.client.core.IAccount;

import java.util.Date;

/**
 * User: 宗旭东
 * Date: 13-3-11
 * Time: 上午11:44
 */
public class BaseAccount implements IAccount {

    private boolean administrator;
    private String id;
    private String account;
    private String showName;
    private String password;
    private String[] roles;
    private Date createTime;
    private Date lastLoginTime;
    private int loginCount;
    private String loginIp;
    private boolean locked;
    private String token;
    private Date adate;

    public BaseAccount(){

    }

    /**
     * 构造函数
     */
    public BaseAccount(String account) {
        this.setAccount(account);
    }

    /**
     * 获取用户操作的日期
     * @return
     */
    @JsonIgnore
    public Date getAdate() {
        return adate;
    }

    /**
     * 设置用户操作的日期
     * @param adate
     */
    @JsonIgnore
    public void setAdate(Date adate) {
        this.adate = adate;
    }

    /**
     * 获取锁定状态
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * 获取姓名
     */
    @Override
    public String getShowName() {
        return showName;
    }

    /**
     * 获取是否是系统内置账户
     */
    @Override
    public boolean isAdministrator() {
        return administrator;
    }

    /**
     * 获取账户编码
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * 获取账户名称
     */
    @Override
    public String getAccount() {
        return account;
    }

    /**
     * 获取密码
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 获取所属角色组
     */
    @Override
    public String[] getRoles() {
        return roles;
    }

    /**
     * 设置账户姓名(可以同步)
     */
    public void setShowName(String showName) {
        this.showName = showName;
    }

    /**
     * 设置用户是否是管理员用户
     */
    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    /**
     * 设置用户的唯一标示
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 设置用户的登录账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 设置用户的登录密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 设置用户拥有的角色
     */
    public void setRoles(String[] roles) {
        this.roles = roles;
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
     * 获取最后登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取登录次数
     */
    public int getLoginCount() {
        return loginCount;
    }

    /**
     * 设置登录次数
     */
    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    /**
     * 获取登录IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置登录IP
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 设置锁定状态
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * 获取用户登录后的票据信息
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置用户登录后的票据信息
     */
    public void setToken(String token) {
        this.token = token;
    }
}
