package com.lemsun.data.connection;

import com.lemsun.core.member.IUserSession;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-16
 * Time: 下午1:07
 */
public interface IDataSession extends IUserSession {
	/**
	 * 获取用户会话中的事务管理器
	 * @return 事务管理器
	 */
	TransactionManager getTransactionManager();
}
