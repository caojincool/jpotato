package com.lemsun.websocket.commands.models;

/**
 * User: 宗旭东
 * Date: 13-2-27
 * Time: 下午4:20
 */
public class SQLModel {
    private String db;
    private String sql;

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
