package com.lemsun.data.connection;

import com.lemsun.core.IResource;
import org.springframework.data.domain.PageRequest;

import java.sql.Statement;

/**
 * 需要进行分页的SQL语句.
 * User: Xudong
 * Date: 12-12-1
 * Time: 上午11:26
 */
public class DbPageStatementEvent extends DbStatementEvent {

    private PageRequest request;

    /**
     * 创建一个数据库执行事件
     *
     * @param source 执行对象
     * @param type   执行类型
     * @param res    目标执行组件
     * @param sql    执行的语句
     * @param params 语句中的参数
     */
    public DbPageStatementEvent(Object source, PageRequest page, StatementTypes type, Statement state, IResource res, String sql, Object[] params) {
        super(source, type, state, res, sql, params);
        this.request = page;
    }

    /**
     * 获取分页请求对象
     */
    public PageRequest getRequest() {
        return request;
    }
}
