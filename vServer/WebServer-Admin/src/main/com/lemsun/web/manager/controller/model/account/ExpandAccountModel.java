package com.lemsun.web.manager.controller.model.account;

import com.lemsun.auth.ExpandAccount;

/**
 * 扩展的账户信息
 * User: dpyang
 * Date: 13-3-16
 * Time: 下午2:17
 */
public class ExpandAccountModel {

    private String accountId;
    private String email;
    private String mobile;

    /**
     * 获取手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取电子邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取账户编码
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * 设置账户编码
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * 获取账户扩展信息
     * @return
     */
    public ExpandAccount getExpandAccount(){
        ExpandAccount expand=new ExpandAccount(getAccountId());
        expand.setMobile(getMobile());
        expand.setEmail(getEmail());

        return expand;
    }
}
