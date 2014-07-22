package com.lemsun.data.connection;

import com.lemsun.core.IResource;
import org.springframework.context.ApplicationEvent;

import java.sql.Statement;

/**
 * 这个事件发生在执行数据库之前. 当针对不同数据库, 可以拦截事件进行语句的适当修改.
 * User: Xudong
 * Date: 12-12-1
 * Time: 上午11:08
 */
public class DbStatementEvent extends ApplicationEvent {
	private String sql;
	private IResource resource;
	private Object[] params;
	private StatementTypes type;
	private Statement state;

	/**
	 * 创建一个数据库执行事件
	 * @param source 执行对象
	 * @param type 执行类型
	 * @param res 目标执行组件
	 * @param sql 执行的语句
	 * @param params 语句中的参数
	 */
	public DbStatementEvent(Object source, StatementTypes type, Statement state, IResource res, String sql, Object[] params) {
		super(source);
		this.resource = res;
		this.sql = sql;
		this.params = params;
		this.type = type;
		this.state = state;
	}

	/**
	 * 获取执行的语句
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * 使用一个新的SQL语句覆盖就的SQL. 提供执行
	 * @param sql
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * 获取目标执行的组件
	 */
	public IResource getResource() {
		return resource;
	}

	/**
	 * 获取执行的参数
	 */
	public Object[] getParams() {
		return params;
	}

	public StatementTypes getType() {
		return type;
	}

	public Statement getState() {
		return state;
	}
}
