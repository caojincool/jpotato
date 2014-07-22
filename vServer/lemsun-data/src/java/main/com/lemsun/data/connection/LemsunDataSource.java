package com.lemsun.data.connection;

import com.lemsun.ldbc.IdbcDataSource;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * 数据源连接类型
 * User: Xudong
 * Date: 12-11-16
 * Time: 下午2:30
 */
public class LemsunDataSource extends org.apache.commons.dbcp.BasicDataSource implements IdbcDataSource {

	private String pid;
	private DbConfigResource configResource;


	public LemsunDataSource(String pid, DbConfigResource configResource)
	{
		this.pid = pid;
		this.configResource = configResource;
	}

	public String getPid() {
		return pid;
	}

    @Override
    public String getConfigPid() {
        return getConfigResource().getPid();
    }

    public DbConfigResource getConfigResource() {
		return configResource;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return Logger.getLogger(LemsunDataSource.class.getName());
	}
}
