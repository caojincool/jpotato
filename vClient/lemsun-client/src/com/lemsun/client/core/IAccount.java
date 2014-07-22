package com.lemsun.client.core;

import java.util.Date;

/**
 * 账号模型接口
 * User: 宗旭东
 * Date: 13-3-11
 * Time: 上午10:49
 */
public interface IAccount {

    /**
     * 获取账号的显示名称
     */
    String getShowName();

    /**
     * 返回当前的用户是否是超级管理员
     * @return 返回当前的用户是否是超级管理员
     */
    boolean isAdministrator();

    /**
     * 返回账号的主键
     * @return 返回账号的主键
     */
    String getId();

    /**
     * 返回账号
     * @return 返回账号
     */
    String getAccount();

    /**
     * 获取账号票据
     */
    String getToken();


    /**
     * 返回用户的密码
     * @return 用户加密密码
     */
    String getPassword();

    /**
     * 用户的所有角色
     * @return 角色集合
     */
    String[] getRoles();

    /**
     * 获取账号创建时间
     */
    Date getCreateTime();


    /**
     * 获取账号最后一次登录时间
     */
    Date getLastLoginTime();

    /**
     * 获取账号是否锁定
     * @return true 锁定
     */
    boolean isLocked();

    /**
     * 同步日期
     * @return 同步日期
     */
    Date getAdate();
}
