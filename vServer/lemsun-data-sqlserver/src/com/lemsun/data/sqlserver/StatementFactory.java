package com.lemsun.data.sqlserver;

import com.lemsun.data.connection.DbPageStatementEvent;

/**
 * 执行语句片段工厂.
 * User: Xudong
 * Date: 12-12-3
 * Time: 下午1:23
 */
@Deprecated
public final class StatementFactory {


	/**
	 * 执行数据库分页查询
	 * @param event 分页事件
	 */
	public static void doDbPageStatementEvent(DbPageStatementEvent event) {
		String sql = event.getSql();



	}

}
