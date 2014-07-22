package com.lemsun.auth;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * 扩展的账户信息
 * User: dpyang
 * Date: 13-3-12
 * Time: 下午6:30
 */
@Document(collection = "Account_Expand")
public class ExpandAccount{

    @Id
    private ObjectId id;
    private String accountId;

    private String email;
    private String mobile;

    public ExpandAccount(){}

    /**
     * 创建扩展账户的时候需要账户编码
     * @param accountid
     */
    public ExpandAccount(String accountid){
        this.accountId=accountid;
    }

    /**
     * 获取扩展信息的ID
     * @return 扩展信息ID
     */
    public ObjectId getId() {
        return id;
    }

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
}
