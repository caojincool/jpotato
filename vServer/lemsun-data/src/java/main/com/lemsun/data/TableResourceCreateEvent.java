package com.lemsun.data;

import com.lemsun.data.tables.TableResource;
import org.springframework.context.ApplicationEvent;

import java.sql.Statement;

/**
 * 创建表格事件
 * User: 宗旭东
 * Date: 13-3-20
 * Time: 上午10:43
 */
public class TableResourceCreateEvent extends ApplicationEvent {

    private String sql;
    private TableResource resource;
    private Statement statement;


    public TableResourceCreateEvent(Object source, String sql, TableResource resource, Statement statement) {
        super(source);
        this.sql = sql;
        this.resource = resource;
        this.statement = statement;
    }

    /**
     * 获取执行语句
     */
    public String getSql() {
        return sql;
    }

    /**
     * 获取创建的表格资源对象
     */
    public TableResource getResource() {
        return resource;
    }

    /**
     * 获取执行的语句对象
     */
    public Statement getStatement() {
        return statement;
    }
}
