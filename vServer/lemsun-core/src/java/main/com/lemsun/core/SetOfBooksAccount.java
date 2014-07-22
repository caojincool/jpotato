package com.lemsun.core;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-7-24
 * Time: 下午2:53
 */
@Document(collection = "SetOfBooksAccount")
public class SetOfBooksAccount {

    private String sid;

    private String account;

    private Date hitchTime;

    private String operator;

    @Transient
    private String showName;

    @Transient
    private Date createTime;

    @Transient
    private String id;

    @Transient
    private Date lastLoginTime;

    /**
     * 帐套编码
     */
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * 账户名称
     */
    public String getAccount() {
        return account;
    }

    /**
     * 账户名称
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 显示姓名
     * 不保存到数据库
     */
    public String getShowName() {
        return showName;
    }

    /**
     * 显示姓名
     * 不保存到数据库
     */
    public void setShowName(String showName) {
        this.showName = showName;
    }

    /**
     * 创建日期
     * 不保存到数据库
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建日期
     * 不保存到数据库
     */
    public void setCreateTime(Date createDate) {
        this.createTime = createDate;
    }

    /**
     * 上次登录日期
     * 不保存到数据库
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 上次登录日期
     * 不保存到数据库
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 挂载操作者
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 挂载操作者
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 挂载时间
     */
    public Date getHitchTime() {
        return hitchTime;
    }

    /**
     * 挂载时间
     */
    public void setHitchTime(Date hitchTime) {
        this.hitchTime = hitchTime;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBaseAccount(IAccount iAccount){
        if (iAccount==null){
            setCreateTime(null);
            setLastLoginTime(null);
            setShowName(null);
        }else{
            setCreateTime(iAccount.getCreateTime());
            setLastLoginTime(iAccount.getLastLoginTime());
            setShowName(iAccount.getShowName());
        }
    }
}
