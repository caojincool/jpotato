package com.lemsun.core.service;

import com.lemsun.core.IAccount;

/**
 * 账号管理接口. 一般针对一个已经登录的账号进行设置信息, 一个账号对应会有一个独立的管理对象
 * 获取在登录的账号上进行操作等, 比如说获取在当前用户上启用的事物. 等
 * User: 宗旭东
 * Date: 13-9-24
 * Time: 下午3:23
 */
public interface IAccountManager {

    /**
     * 获取当前被管理的账号
     * @return
     */
    IAccount getAccount();


    /**
     * 获取当前账号的事物管理对象
     */
    ITransactionManager getTransactionManager();

}
