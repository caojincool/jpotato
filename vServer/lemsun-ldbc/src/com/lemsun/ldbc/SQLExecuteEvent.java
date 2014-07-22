package com.lemsun.ldbc;

import org.springframework.context.ApplicationEvent;

import java.sql.Connection;
import java.sql.Statement;

/**
 * 数据库SQL执行事件
 * User: 宗旭东
 * Date: 13-6-6
 * Time: 下午10:02
 */
public class SQLExecuteEvent extends ApplicationEvent {

    private ITableResource resource;
    private Connection conn;
    private Statement statement;
    private DbCategory category;

    public SQLExecuteEvent(Object source, ITableResource resource, Connection conn, Statement statement, DbCategory category) {
        super(source);

        this.resource = resource;
        this.conn = conn;
        this.statement = statement;
        this.category = category;
    }

    /**
     * 获取执行组件
     */
    public ITableResource getResource() {
        return resource;
    }

    /**
     * 获取数据库连接
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * 获取执行文本
     */
    public Statement getStatement() {
        return statement;
    }

    /**
     * 获取执行数据库的类型
     */
    public DbCategory getCategory() {
        return category;
    }
}
