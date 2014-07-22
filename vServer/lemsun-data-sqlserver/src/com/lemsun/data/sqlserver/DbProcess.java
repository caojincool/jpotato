package com.lemsun.data.sqlserver;

import com.lemsun.core.IAccount;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.connection.DbManager;
import com.lemsun.data.connection.DbPageStatementEvent;
import com.lemsun.data.service.IDbProcess;
import com.lemsun.data.tables.TableGroupResource;
import com.lemsun.data.tables.TableResource;
import com.lemsun.ldbc.DbCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

/**
 * 针对 SQLServer 数据库的处理
 * User: Xudong
 * Date: 12-11-14
 * Time: 下午10:47
 */
@Deprecated
@Component
public class DbProcess implements IDbProcess {

	private DbManager dbManager;

	@Autowired
	public DbProcess(DbManager dbManager) {
		this.dbManager = dbManager;
	}

    /**
     * 返回一个数据支持的SQLServer 常量
     */
	@Override
	public DbCategory getCategory() {
		return DbCategory.SQLSERVER;
	}

	@Override
	public void createTable(TableResource resource, IAccount owner) throws Exception {
		CreateTableFactory factory = new CreateTableFactory(dbManager, resource, owner);
		factory.create();
	}

	@Override
	public void initTable(TableResource resource, IAccount owner) throws Exception {
		CreateTableFactory factory = new CreateTableFactory(dbManager, resource, owner);
		factory.init();
	}

    /**
     * 创建 表组 的组件
     * @param resource 表组
     * @param owner 所属人员
     * @throws Exception
     */
	@Override
	public void createTableGroup(TableGroupResource resource, IAccount owner) throws Exception {
		CreateTableGroupFactory factory = new CreateTableGroupFactory(dbManager, resource, owner);
		factory.create();
	}

    /**
     * 初始化表格组件
     * @param resource 表格组件
     * @param owner 所属人员
     * @throws Exception
     */
	@Override
	public void initTableGroup(TableGroupResource resource, IAccount owner) throws Exception {
		CreateTableGroupFactory factory = new CreateTableGroupFactory(dbManager, resource, owner);
		factory.init();
	}

	@Override
	public void dropTable(TableResource resource, IAccount account) throws Exception {

	}

	@Override
	public void dropTableGroup(TableGroupResource resource, IAccount owner) throws Exception {

	}

    @Override
    public String[] getDatabaseNames(DbConfigResource resource) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        ArrayList<String> names = new ArrayList<>();

        Class.forName(resource.getDbCategory().getDriverClassName()).newInstance();

        Connection conn = DriverManager.getConnection(resource.getConnStr());
        PreparedStatement statement = conn.prepareStatement("select [name] from master.dbo.sysdatabases where status = 65536 or status = 65544 order by [name]");

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            names.add(rs.getNString("name"));
        }


        rs.close();
        statement.close();
        conn.close();
        return names.toArray(new String[names.size()]);
    }


    @Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof DbPageStatementEvent) {
			StatementFactory.doDbPageStatementEvent((DbPageStatementEvent)event);
		}
	}
}
