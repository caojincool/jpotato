package com.lemsun.channel.command;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-11
 * Time: 上午11:54
 */
public class QueryModel {

	private String sql;
	private String db;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}
}
