package com.lemsun.data.sqlserver;

import com.lemsun.core.IAccount;
import com.lemsun.data.connection.DbManager;
import com.lemsun.data.tables.TableGroupResource;
import com.lemsun.data.tables.TableResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建打组表的工厂
 * User: Xudong
 * Date: 12-11-23
 * Time: 下午2:34
 */
@Deprecated
public class CreateTableGroupFactory {

    private DbManager dbManager;
    private TableGroupResource resource;
    private IAccount account;
    private static final Logger log = LoggerFactory.getLogger(CreateTableGroupFactory.class);

    public CreateTableGroupFactory(DbManager dbManager, TableGroupResource resource, IAccount account) {
        this.dbManager = dbManager;
        this.resource = resource;
        this.account = account;
    }


    public void create() throws Exception {

    }

    /**
     * 初始化表格组的数据, 如内有表格子节点需要加载. 那么就先创建子表, 后期再进行初始化
     *
     * @throws Exception
     */
    public void init() throws Exception {
        Assert.notNull(resource.getDbConfig());
        try (Connection conn = dbManager.getConnection(resource.getDbConfig().getPid())) {
            if (resource.getVersion() == TableGroupResource.VI) {
                loadTableGroupInfo(resource, conn);
            } else if (resource.getVersion() == TableGroupResource.V) {
                resource.setCreateUser(account.getAccount());
            }
        }

        //加载表格后, 给每个表格进行编码
        for (int i = 0; i < resource.getTables().size(); i++) {

        }
    }

    /**
     * 加载信息
     *
     * @param resource 资源对象
     */
    public void loadTableGroupInfo(TableGroupResource resource, Connection conn) throws Exception {
        try {
            OldTableGroupInfo info = getGroupInfo(conn, resource.getCode());
            if (info == null)
                throw new Exception("没有对应的资源对象");

            resource.setName(info.getName());
            resource.setKey(info.getKey());
            resource.setNameFormat(info.getCodeFormat());
            resource.setTables(getTables(conn, resource));

        } catch (Exception ex) {
            if(log.isErrorEnabled())

                log.error("连接旧版本的组表异常. {}", ex);
            throw new Exception("旧版本的组件不能连接, (" + ex.getMessage() + ")");
        }
    }


    /**
     * 获取表组的信息
     *
     * @param conn 数据库连接
     * @param code 组代码
     * @return 信息对象
     * @throws SQLException 异常
     */
    protected OldTableGroupInfo getGroupInfo(Connection conn, String code) throws SQLException {
        Statement state = conn.createStatement();

        ResultSet rs = state.executeQuery(String.format("select B3, A14, A10, A9 from system_reports where A1 = '%s'", code));
        OldTableGroupInfo info = null;

        while (rs.next() && info == null) {
            info = new OldTableGroupInfo(
                    rs.getString("B3"),
                    rs.getNString("A14"),
                    rs.getString("A10"),
                    rs.getInt("A9"));

        }

        rs.close();
        state.close();

        return info;
    }


    public List<TableResource> getTables(Connection conn, TableGroupResource resource) throws SQLException {
        Statement state = conn.createStatement();

        ResultSet rs = state.executeQuery(String.format("select top(30) A1, A9 from SYSTEM_REPORTS_ACHIEVES where B3 = '%s'", resource.getKey()));
        List<TableResource> tables = new ArrayList<>();
        //获取表格列表
        while (rs.next()) {
            TableResource table = new TableResource(resource.getName(), resource.getPid(), resource.getDbConfig());
            table.setDbtable(rs.getString("A1"));
            table.setCode(resource.getCode());
            table.setCreateUser(resource.getCreateUser());
            table.setCate(rs.getInt("A9"));
            table.setVersion(resource.getVersion());
            tables.add(table);
        }

        rs.close();
        state.close();

        return tables;
    }

    /**
     * 旧的数据更新
     */
    class OldTableGroupInfo {
        private String key;
        private String name;
        private String codeFormat;
        private int category;

        OldTableGroupInfo(String key, String name, String codeFormat, int category) {
            this.key = key;
            this.name = name;
            this.codeFormat = codeFormat;
            this.category = category;
        }


        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

        public String getCodeFormat() {
            return codeFormat;
        }

        public int getCategory() {
            return category;
        }
    }
}
