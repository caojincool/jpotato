package com.lemsun.data.connection;

import com.lemsun.core.IAccount;
import com.lemsun.core.service.ITransactionManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * 负责事务管理, 一个用户会产生一个管理对象用了来管理用户执行中的事务
 * User: Xudong
 * Date: 12-10-11
 * Time: 下午1:45
 */
public class TransactionManager implements ITransactionManager {

	private DbManager dbManager;
	private boolean trasactionStarted;
	private Hashtable<String, Connection> connectionDictionary = new Hashtable<>();
	private JdbcTemplate template;
    private final static Logger log = LoggerFactory.getLogger(TransactionManager.class);
    private IAccount account;
    private Date startTime;


	public TransactionManager(DbManager dbManager, IAccount account) {
		this.dbManager = dbManager;
		this.template = new JdbcTemplate(this);
        this.account = account;
	}


	public DbManager getDbManager() {
		return dbManager;
	}

	/**
	 *
	 * 获取数据库操作模板
	 */
	public JdbcTemplate getTemplate() {
		return template;
	}

	/**
	 * 获取当前的用户事务是否打开
	 * @return
	 */
	public boolean isTrasactionStarted() {
		return trasactionStarted;
	}


    protected void clear() throws SQLException {

        startTime = null;

        if(connectionDictionary.size() == 0) return;

        for(Connection conn : connectionDictionary.values()) {
            conn.close();
        }

        connectionDictionary.clear();
        trasactionStarted = false;
    }


    public void startTransaction() {

        if(log.isInfoEnabled()) {
            log.info("启动事物 用户 :" + account.getAccount() + " Token: " + account.getToken());
        }

        trasactionStarted = true;
        startTime = new Date();
    }


	public Connection getConnection(String name) throws SQLException {

		if(StringUtils.isEmpty(name)) {
			name = dbManager.getDefaultName();
		}

		Connection conn;
		if(connectionDictionary.containsKey(name)) {
			conn = connectionDictionary.get(name);
		}
		else {
			conn = dbManager.getConnection(name);
			connectionDictionary.put(name, conn);
		}

		conn.setAutoCommit(!isTrasactionStarted());

		return conn;
	}


	public void commit() throws SQLException {
		if(connectionDictionary.size() == 0) return;

        if(log.isInfoEnabled()) {
            log.info("提交数据 用户: " + account.getAccount() + " Token: " + account.getAccount());
        }


        try
        {
            for(Connection conn : connectionDictionary.values()) {
                conn.commit();
            }
        }
        catch (SQLException ex) {

        }
		finally {
            trasactionStarted = false;
            finishExcute();
        }

		clear();



	}

    @Override
    public void execute(String dbpid, String sql) throws SQLException {
        template.execute(dbpid, sql);
    }

    @Override
    public void execute(String dbpid, String sql, Object... args) throws SQLException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T> List<T> query(String dbpid, String sql, RowMapper<T> rowMapper) throws SQLException {
        return template.query(dbpid, sql, rowMapper);
    }

    @Override
    public void query(String dbpid, String sql, RowCallbackHandler rch) throws SQLException {
        template.query(dbpid, sql, rch);
    }

    @Override
    public void query(String dbpid, String sql, RowCallbackHandler rch, Object... args) throws SQLException {
        template.query(dbpid, sql, rch, args);
    }

    @Override
    public Map<String, Object> queryForMap(String dbpid, String sql) throws SQLException {
        return template.queryForMap(dbpid, sql);
    }

    @Override
    public Map<String, Object> queryForMap(String dbpid, String sql, Object... args) throws SQLException {
        return template.queryForMap(dbpid, sql, args);
    }


    @Override
    public void setFetchSize(int fetchSize) {
        template.setFetchSize(fetchSize);
    }

    @Override
    public int getFetchSize() {
        return template.getFetchSize();
    }

    @Override
    public void setMaxRows(int maxRows) {
        template.setMaxRows(maxRows);
    }

    @Override
    public int getMaxRows() {
        return template.getMaxRows();
    }

    @Override
    public void setQueryTimeout(int queryTimeout) {
        template.setQueryTimeout(queryTimeout);
    }

    @Override
    public int getQueryTimeout() {
        return template.getQueryTimeout();
    }

    @Override
    public boolean isBegin() {
        return isTrasactionStarted();
    }

    @Override
    public Date getStartTime() {
        return startTime;
    }

    @Override
    public void start() {
        this.startTransaction();
    }

    /**
	 * 回滚全部的连接. 并清除连接
	 * @throws java.sql.SQLException
	 */
	public void rollback() throws SQLException {

        if(log.isInfoEnabled()) {
            log.info("回滚事物 用户: "+account.getAccount() + " Token: " + account.getToken());
        }


		if(connectionDictionary.size() == 0) return;

		for(Connection conn : connectionDictionary.values()) {
			conn.rollback();
		}
		clear();
	}


	/**
	 * 完成执行后的处理. 如果没有事务挂起. 那么就释放连接.
	 */
	public void finishExcute()  {
		if(!isTrasactionStarted()) {
			for(Connection connection : connectionDictionary.values()) {
                finishExcute(connection);
            }

		}

        connectionDictionary.clear();
	}

    @Override
    public void finishExcute(Connection conn) {
        if(!isBegin()) {

            for(Map.Entry<String, Connection> c : connectionDictionary.entrySet()) {

                if(c.getValue() == conn) {
                    connectionDictionary.remove(c.getKey());
                }

            }
            JdbcUtils.closeConnection(conn);
        }
    }

}
