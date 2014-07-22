package com.lemsun.core.service;

import com.lemsun.core.IAccount;

import java.io.InputStream;
import java.util.Date;

/**
 * 核心账号服务接口
 *
 * 负责对账号的
 * User: 宗旭东
 * Date: 13-9-24
 * Time: 下午2:42
 */
public interface IAccountCoreService {

    /**
     * 通过组件获取账号对象
     * @param pid
     * @return
     */
    public IAccount get(String pid);

    /**
     * 使用账号获取用户对象
     * @param account
     * @return
     */
    public IAccount getByAccount(String account);

    /**
     * 获取当前的正在进行的账号管理对象
     * @return
     */
    public IAccountManager getCurrentAccountManager();

    /**
     * 获取一个当前的操作日期, 如果没有就返回当前日期
     * @return
     */
    public Date getCurrentAdate();

    /**
     * 获取账户的头像修
     * @param account 账户
     * @return 一个账户的头像流
     */
    InputStream getAvatar(IAccount account,int size);

    /**
     * 获取账户的头像流
     * @param accountId 账户ID
     * @return 一个账户的头像流
     */
    InputStream getAvatar(String accountId,int size);

}
