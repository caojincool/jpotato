package com.lemsun.web.manager.controller.model.account;

import com.lemsun.auth.BaseAccount;

import java.util.Date;

/**
 * 账号创建模型. 保存前台提交的账号信息
 * User: Xudong
 * Date: 13-1-8
 * Time: 下午2:12
 */
public class AccountCreateModel {
    private String account;
    private String password;
    private String rePassword;
    private String showName;
    private boolean isLock;
    private String[] roles;

    /**
     * 获取账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取明文密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置明文密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取重复的设置密码
     */
    public String getRePassword() {
        return rePassword;
    }

    /**
     * 设置重复的密码
     */
    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    /**
     * 获取显示名称
     */
    public String getShowName() {
        return showName;
    }

    /**
     * 设置显示名称
     */
    public void setShowName(String showName) {
        this.showName = showName;
    }

    /**
     * 获取锁定状态
     */
    public boolean isLock() {
        return isLock;
    }

    /**
     * 设置锁定状态
     */
    public void setLock(boolean lock) {
        isLock = lock;
    }

    /**
     * 获取角色组
     */
    public String[] getRoles() {
        return roles;
    }

    /**
     * 设置角色组
     */
    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    /**
     * 创建一个账户信息
     */
    public BaseAccount createAccountModel() {
        BaseAccount ac = new BaseAccount(getAccount());

        ac.setAccount(getAccount());
        ac.setShowName(getShowName());
        ac.setPassword(getPassword());
        ac.setCreateTime(new Date());
        ac.setLocked(isLock());

        return ac;
    }
}
