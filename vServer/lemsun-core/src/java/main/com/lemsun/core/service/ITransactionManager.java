package com.lemsun.core.service;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 事物管理
 * User: 宗旭东
 * Date: 13-9-24
 * Time: 下午4:14
 */
public interface ITransactionManager {

    /**
     * 设置批处理的数量
     */
    public void setFetchSize(int fetchSize);

    /**
     * 返回批处理的数据
     */
    public int getFetchSize();

    /**
     * 设置一个结果的最大行数
     */
    public void setMaxRows(int maxRows);

    /**
     * 返回一个操作的结果最大值
     */
    public int getMaxRows();

    /**
     * 设置事物执行的超时时间
     */
    public void setQueryTimeout(int queryTimeout);

    /**
     * 返回事物执行的超时时间
     */
    public int getQueryTimeout();


    /**
     * 获取是否已经启动了事物, 并正在进行操作
     */
    public boolean isBegin();

    /**
     * 获取事物启动的时间
     */
    public Date getStartTime();

    /**
     * 启动一个事物
     */
    public void start();

    /**
     * 回滚挂起的事物
     */
    public void rollback() throws SQLException;

    /**
     * 提交已经执行的操作
     */
    public void commit() throws SQLException;

    /**
     * 执行 SQL 语句, 如果发生异常报错
     * @param dbpid
     * @param sql
     * @throws SQLException
     */
    public void execute(String dbpid, String sql) throws SQLException;


    /**
     * 使用参数执行SQL语句
     * @param dbpid
     * @param sql
     * @param args
     * @throws SQLException
     */
    public void execute(String dbpid, String sql, Object... args) throws SQLException;


    /**
     * 查询一个列表对象
     * @param dbpid
     * @param sql
     * @param rowMapper
     * @param <T>
     * @return
     * @throws SQLException
     */
    public <T> List<T> query(String dbpid, String sql, RowMapper<T> rowMapper) throws SQLException;

    /**
     * 执行查询, 并使用 RowCallbackHandler 返回数据
     * @param dbpid
     * @param sql
     * @param rch
     * @throws SQLException
     */
    public void query(String dbpid, String sql, RowCallbackHandler rch) throws SQLException;

    /**
     * 执行查询, 并使用 RowCallbackHandler 返回数据
     * @param dbpid
     * @param sql
     * @param rch
     * @param args
     */
    public void query(String dbpid, String sql, RowCallbackHandler rch, Object... args) throws SQLException;

    /**
     * 执行一个查询
     *
     * @param dbpid
     * @param sql
     * @return
     * @throws SQLException
     */
    public Map<String, Object> queryForMap(String dbpid, String sql) throws SQLException;


    /**
     * 使用参数执行查询
     *
     * @param dbpid
     * @param sql
     * @param args
     * @return
     * @throws SQLException
     */
    public Map<String, Object> queryForMap(String dbpid, String sql, Object... args) throws SQLException;


    /**
     * 在当前事物管理器中获取一个数据库连接对象. 如果事物打开, 事物就必须手动提交否则为自动提交.
     * <br/>
     * <b>获取到数据库连接后不能直接关闭 Connection 如果当前启动操作包含事物, 就会不能执行正确的结果</b>
     * @param pid
     * @return
     */
    public Connection getConnection(String pid) throws SQLException;


    /**
     * 当手动获取 Connection 后. 执行完成的关闭操作
     */
    public void finishExcute();

    /**
     * 当手动获取 Connection 后. 执行完成的关闭操作
     * @param conn
     */
    public void finishExcute(Connection conn);

}
