package com.lemsun.core;

import java.util.Date;

/**
 * 定义的账号接口
 * User: Xudong
 * Date: 12-10-9
 * Time: 下午8:12
 */
public interface IAccount {

    /**
     * 固定的系统账号
     */
    public static final IAccount System = new IAccount() {

        private Date createTime = new Date();


        @Override
        public String getShowName() {
            return "system";
        }

        @Override
        public boolean isAdministrator() {
            return true;
        }

        @Override
        public String getId() {
            return "system";
        }

        @Override
        public String getAccount() {
            return "system";
        }

        @Override
        public String getToken() {
            return null;
        }

        @Override
        public String getPassword() {
            return null;
        }

        @Override
        public String[] getRoles() {
            return new String[0];
        }

        @Override
        public Date getCreateTime() {
            return createTime;
        }

        @Override
        public Date getLastLoginTime() {
            return createTime;
        }

        @Override
        public boolean isLocked() {
            return false;
        }
    };

    /**
     * 获取账号的显示名称
     */
    String getShowName();

	/**
	 *
	 * @return 返回当前的用户是否是超级管理员
	 */
	boolean isAdministrator();

	/**
	 *
	 * @return 返回账号的主键
	 */
	String getId();

	/**
	 *
	 * @return 返回账号
	 */
	String getAccount();

    /**
     * 获取账号票据
     * @return
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
}
