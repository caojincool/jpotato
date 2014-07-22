package com.lemsun.client.core.service;

import com.lemsun.client.core.IAccount;

/**
 * User: 宗旭东
 * Date: 13-3-18
 * Time: 上午11:27
 */
public interface IAccountService {
    /**
     * 获取当前操作者的账号对象, 如果为空. 那么当前操作者还没有登录
     */
    public IAccount getCurrentAccount();

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 账户
     */
    IAccount login(String username,String password);

    /**
     *
     */
    void logout();


}
