package com.lemsun.web.manager.controller.model.query;


import com.lemsun.web.model.ExtPageRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * User: Xudong
 * Date: 13-1-8
 * Time: 上午9:47
 * 基本的账号查询模型
 */
public class AccountQuery extends ExtPageRequest {

    private String account;
    private String name;
    private String sid;
    private List<String> accounts;

    public AccountQuery() {
        if(getLimit()==0)
            setPageSize(25);
        if (getPage()==0)
            setPageNumber(1);
    }

    /**
     * 帐套编码,帐套查询账户用
     */
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * 获取查询账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置查询账号. 可以使用正则表达式查询
     */
    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<String> accounts) {
        this.accounts = accounts;
    }

    @Override
    public Query createQuery() {
        Query query = super.createQuery();


        if (StringUtils.isNotEmpty(getName())) {
            query.addCriteria(Criteria.where("showName").regex(".*" + getName() + ".*"));
        }
        if (StringUtils.isNotEmpty(getAccount())) {
            query.addCriteria(Criteria.where("account").regex(".*" + getAccount() + ".*"));
        }
        if (accounts != null) {
            query.addCriteria(Criteria.where("account").nin(getAccounts()));
        }
        return query;
    }
}
