package com.lemsun.web.manager.controller.model.query;

/**
 * 被选帐号查询对
 * User: dp
 * Date: 13-8-30
 * Time: 上午10:15
 */
public class AccountCheckedQuery extends AccountQuery {
    private String sid;

    /**
     * 帐套编码
     */
    public String getSid() {
        return sid;
    }

    /**
     * 帐套编码
     */
    public void setSid(String sid) {
        this.sid = sid;
    }
}
