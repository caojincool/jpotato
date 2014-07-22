package com.lemsun.data.sqlserver.service;

import com.lemsun.data.connection.DbManager;
import com.lemsun.data.connection.LemsunDataSource;
import com.lemsun.data.connection.TableSet;
import com.lemsun.ldbc.DbCategory;
import com.lemsun.ldbc.IdbcDataSource;
import com.lemsun.ldbc.TableData;
import com.lemsun.ldbc.service.ISqlOperaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * User: 宗旭东
 * Date: 13-6-19
 * Time: 下午5:04
 */
@Service
public class SqlOperaterServiceImpl implements ISqlOperaterService {

    private DbManager dbManager;

    @Autowired
    public SqlOperaterServiceImpl(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public TableData select(String pid, String sql) {

        LemsunDataSource dataSource = dbManager.getDataSource(pid);

        TableSet table = null;
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement state = conn.prepareStatement(sql)) {
                try (ResultSet rs = state.executeQuery()) {
                    table = new TableSet(rs);
                }
            }
        } catch (SQLException ex) {

        }

        return table;
    }

    @Override
    public TableData select(IdbcDataSource dataSource, String sql) throws Exception {
        return select(dataSource.getPid(), sql);
    }

    @Override
    public void execute(IdbcDataSource dataSource, String sql) throws Exception {



        try (Connection conn = dbManager.getConnection(dataSource.getConfigPid())) {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.execute();
            }
        }
    }

    @Override
    public DbCategory getSupportCategory() {
        return DbCategory.SQLSERVER;
    }
}
