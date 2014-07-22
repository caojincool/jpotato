package com.lemsun.ldbc.service;

import com.lemsun.ldbc.IDbType;
import com.lemsun.ldbc.IdbcDataSource;
import com.lemsun.ldbc.TableData;


/**
 * 定义执行SQL语句服务
 * User: 宗旭东
 * Date: 13-6-19
 * Time: 下午4:39
 */
public interface ISqlOperaterService extends IDbType {

    /**
     * 执行sql语句查询
     *
     * @param pid 数据源组件编码
     * @param sql sql语句
     * @return 查询结果
     */
    TableData select(String pid, String sql);

    /**
     * 执行查询的SQL语句
     *
     * @param sql 语句
     * @return 查询数据
     */
    TableData select(IdbcDataSource dataSource, String sql) throws Exception;

    /**
     * 执行SQL语句
     *
     * @param sql 语句
     */
    void execute(IdbcDataSource dataSource, String sql) throws Exception;

}
