package com.lemsun.data.sqlserver.service;

import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.Host;
import com.lemsun.core.SpringContextUtil;
import com.lemsun.core.service.ITransactionManager;

import com.lemsun.data.connection.DbManager;

import com.lemsun.data.lmstable.*;
import com.lemsun.data.service.ILmsTableService;
import com.lemsun.data.sqlserver.SqlTable5ResourceWrap;
import com.lemsun.data.sqlserver.SqlTemplate;
import com.lemsun.data.sqlserver.TableCreateFunction;
import com.lemsun.ldbc.*;
import com.lemsun.ldbc.service.ITableOperaterService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * User: 宗旭东
 * Date: 13-6-6
 * Time: 下午4:59
 */
@Service
public class TableOperaterServiceImpl implements ITableOperaterService {

    private DbManager dbManager;

    private static Logger log = LoggerFactory.getLogger(TableOperaterServiceImpl.class);
    private IAccountService accountService;
    private ILmsTableService lmsTableService=null;
    @Autowired
    public TableOperaterServiceImpl(DbManager dbManager,
                                    IAccountService accountService) {
        this.dbManager = dbManager;
        this.accountService = accountService;

    }

    @Override
    public String uploadTableColumn(ITableResource resource, String column, String rowid, InputStream stream) throws Exception {
        return uploadTableColumn(resource, column, rowid, null, null, stream, null);
    }

    @Override
    public String uploadTableColumn(ITableResource resource, String column, String rowid, String fileName, String type,
                                    InputStream stream, String remark) throws Exception {
        String path = fileName;

        if (resource instanceof Table5Resource) {
            Table5Resource table = (Table5Resource) resource;
            Column col = table.getColumn(column);
            boolean hasFileName = !StringUtils.isEmpty(fileName);


            try (Connection connection = dbManager.getConnection(table.getDbConfig().getPid())) {
                if (col.getCategory() == ColumnCategory.HTML) {
                    if (hasFileName) {
                        return uploadHtmlFile(table, col, rowid, connection, stream, fileName, type, remark);
                    } else {
                        uploadHtmlContent(table, col, rowid, connection, stream);
                        return rowid;
                    }
                }
            }
        }


        return path;
    }

    /**
     * 更新 HTML 字段的内容
     *
     * @param table
     * @param col
     * @param rowid
     * @param connection
     * @param stream
     * @throws Exception
     */
    protected void uploadHtmlContent(Table5Resource table, Column col, String rowid, Connection connection, InputStream stream) throws Exception {

        uploadHtmlFile(table, col, rowid, connection, stream, "__context__", "text/html", null);
    }

    /**
     * 创建一个 HTML 的附加文件, 如果有同名的文件进行替换
     *
     * @param table      表格资源
     * @param col        更新列
     * @param rowid      更新行号
     * @param connection 更新链接
     * @param stream     更新内容
     * @param filename   文件名
     * @param type       文件类型
     * @param remark     备注信息
     * @throws Exception 更新异常
     */
    protected String uploadHtmlFile(Table5Resource table, Column col, String rowid,
                                    Connection connection, InputStream stream, String filename,
                                    String type, String remark) throws Exception {

        SqlTable5ResourceWrap wrap = new SqlTable5ResourceWrap(table);

        //清除旧文件
        String clearSql = TableCreateFunction.generateExtColumnUpdate(wrap, col, 1);
        try (PreparedStatement statement = connection.prepareStatement(clearSql)) {
            statement.setString(1, rowid);
            statement.setString(2, filename);
            if (StringUtils.isNotEmpty(type)) {
                statement.setString(3, type);
            } else {
                statement.setNull(3, Types.VARCHAR);
            }

            statement.execute();
        }


        if (stream == null) return "";
        String  _file_key="";
        String fileKeySql = TableCreateFunction.generateExtColumnData(wrap, col, 5);
        try (PreparedStatement statement = connection.prepareStatement(fileKeySql)) {
            statement.setString(1, rowid);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    {
                        _file_key = rs.getString("_file_key");
                    }
                }
            }
        }
        //增加新文件

        String insertSql = TableCreateFunction.generateExtColumnUpdate(wrap, col, 2);

        try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
            UUID id = UUID.randomUUID();
            statement.setString(1, id.toString());
            statement.setString(2, _file_key);
            statement.setNString(3, filename);

            if (StringUtils.isNotEmpty(type)) {
                statement.setString(4, type);
            } else {
                statement.setNull(4, Types.VARCHAR);
            }

            if (StringUtils.isNotEmpty(remark)) {
                statement.setNString(5, remark);
            } else {
                statement.setNull(5, Types.NVARCHAR);
            }

            statement.setBinaryStream(6, stream);

            statement.execute();


            return id.toString();
        }

    }

    @Override
    public String getColumnContext(ITableResource resource, String column, String rowid) throws Exception {

        if (resource instanceof Table5Resource) {
            Table5Resource table = (Table5Resource) resource;

            Column col = table.getColumn(column);

            if (col == null) throw new Exception("数据表中没有对应的字段名称 :" + column);

            String context = null;

            try (Connection connection = dbManager.getConnection(table.getDbConfig().getPid())) {

                if (col.getCategory() == ColumnCategory.HTML) {
                    String sql = TableCreateFunction.generateExtColumnData(new SqlTable5ResourceWrap(table), col, 4);

                    try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
                        statement.setString(1, rowid);

                        try (ResultSet rs = statement.executeQuery()) {
                            while (rs.next()) {
                                try(InputStream inputStream = rs.getBinaryStream("_file"))
                                {
                                    context = IOUtils.toString(inputStream, "UTF-8");
                                }
                            }
                        }

                    }

                }
            }

            return context;
        }

        return null;
    }

    @Override
    public void getColumnFile(ITableResource resource, String column, String fileid, OutputStream stream) throws Exception {

        if (resource instanceof Table5Resource) {
            Table5Resource table = (Table5Resource) resource;
            Column col = ((Table5Resource) resource).getColumn(column);

            if (col == null) throw new Exception("数据表中没有对应的字段名称 :" + column);

            try (Connection connection = dbManager.getConnection(table.getDbConfig().getPid())) {

                if (col.getCategory() == ColumnCategory.HTML) {
                    String sql = TableCreateFunction.generateExtColumnData(new SqlTable5ResourceWrap(table), col, 2);

                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(1, fileid);
                        try (ResultSet rs = statement.executeQuery()) {
                            while (rs.next()) {
                                InputStream inputStream = rs.getBinaryStream("_file");

                                IOUtils.copy(inputStream, stream);

                                inputStream.close();
                                return;
                            }
                        }

                    }

                }
            }

        }
    }

    @Override
    public List<ColumnFileInfo> getColumnFileInfos(ITableResource resource, String column, String rowid) throws Exception {
        List<ColumnFileInfo> infos = null;
        if (resource instanceof Table5Resource) {
            Table5Resource table = (Table5Resource) resource;
            Column col = ((Table5Resource) resource).getColumn(column);


            try (Connection connection = dbManager.getConnection(table.getDbConfig().getPid())) {

                if (col.getCategory() == ColumnCategory.HTML) {
                    String sql = TableCreateFunction.generateExtColumnData(new SqlTable5ResourceWrap(table), col, 3);

                    try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
                        statement.setString(1, rowid);

                        try (ResultSet rs = statement.executeQuery()) {
                            infos = new ArrayList<>();

                            while (rs.next()) {
                                String id = rs.getString("_key");
                                String parentid = rs.getString("_parent_key");
                                String filename = rs.getNString("_filename");
                                String type = rs.getString("_type");
                                Date update = rs.getDate("_lastUpdate");
                                String remark = rs.getNString("_remark");

                                ColumnFileInfo info = new ColumnFileInfo(id, parentid, update, table.getPid(), type, filename, col.getCol(), remark);

                                infos.add(info);
                            }
                        }

                    }

                }
            }

        }

        return infos;
    }

    /**
     * 1、获取表子表
     * 2、拼接删表语句
     * 3、执行删除
     *
     * @param resource
     * @throws SQLException
     */
    @Override
    public void removeTable(ITableResource resource) throws SQLException {

        if(resource instanceof Table5Resource) {
            Table5Resource table5Resource = (Table5Resource) resource;

            ITransactionManager manager = accountService.getCurrentAccountManager().getTransactionManager();
            String tableName=table5Resource.getDbtable();
            try
            {
                StringBuilder drop=new StringBuilder();

                Connection connection = manager.getConnection(table5Resource.getDbConfig().getPid());
                //获取连接
                Statement statement = connection.createStatement();
                createDropTableStatement(table5Resource, drop,  statement);

                //执行删除
                statement.executeUpdate(drop.toString());

                statement.close();
            }
            catch (Exception ex) {
                log.error("删除表{}失败,错误信息为：{}",tableName,ex.getMessage());
                throw ex;
            }
            finally {
                manager.finishExcute();
            }

        }
    }

    /**
     * 创建删除表及子表语句
     * @param table 表
     * @param drop 装置返回语句
     * @param statement 执行查询
     * @throws SQLException
     */
    public void createDropTableStatement(Table5Resource table, StringBuilder drop, Statement statement) throws SQLException {
        SqlTable5ResourceWrap wrap=new SqlTable5ResourceWrap(table);
        String select=TableCreateFunction.generateResult(wrap, SqlTemplate.QUERY_TABLENAME);
        TableCreateFunction.queryTableName(drop,statement, select,TableCreateFunction.TABLE);
        if (log.isInfoEnabled()){
            log.info("生成sql为："+drop.toString());
        }
    }

    @Override
    public void removeTableGroup(ITableGroupResource resource)  {
        if(resource instanceof Table5GroupResource) {
            Table5GroupResource groupResource=(Table5GroupResource) resource;
            List<Table5Resource>  table5s=groupResource.getTables();

            ITransactionManager manager = accountService.getCurrentAccountManager().getTransactionManager();
            try{

                Connection connection = manager.getConnection(groupResource.getDbConfig().getPid());
                Statement statement = connection.createStatement();
                //拼接删除表语句
                StringBuilder drop=new StringBuilder();

                for(Table5Resource table5:table5s){
                    createDropTableStatement(table5, drop,  statement);
                }
                //执行删除
                statement.executeUpdate(drop.toString());
                statement.close();
                if(log.isDebugEnabled())
                log.debug("生成sql为："+drop.toString());

            }
            catch (Exception ex) {
                throw new DBProcessException("删除表组出错" + ex.getMessage(), "004");
            }
            finally {
                manager.finishExcute();
            }

        }
    }


    @Override
    public void createTableResource(ITableResource resource, IDbConnectionConfig dbConfig) {
        if (resource instanceof Table5Resource) {
            Table5Resource table = (Table5Resource) resource;

            ITransactionManager transactionManager = accountService.getCurrentAccountManager().getTransactionManager();

            Connection connection = null;

            try {
                connection = transactionManager.getConnection(dbConfig.getPid());

                TableCreateFunction.createTableResource(this, table, dbConfig, connection);

            } catch (Exception ex) {
                throw new DBProcessException(ex.getMessage(), "100");
            }
            finally {
                transactionManager.finishExcute(connection);
            }
        }else{
            throw new DBProcessException("不支持的数据表类型创建 SQLSERVER type:" + resource.getClass(), "001");
        }
    }

    @Override
    public long getQueryCount(TableQuery query) {

        ITableResource resource = query.getTableResource();

        if (resource instanceof Table5Resource) {
            Table5Resource table5Resource = (Table5Resource) resource;
            SqlTable5ResourceWrap wrap = new SqlTable5ResourceWrap(table5Resource);

            if(wrap.isForm()) {
                //转换 code 的查询数据
                long code = getQueryCode(query);
                if(code != -1) {
                    query.setCode(code);
                };
            }

            String sql = TableCreateFunction.generateTableCount(wrap, query);

            try (Connection connection = dbManager.getConnection(table5Resource.getDbConfig().getPid())) {

                try (PreparedStatement statement = connection.prepareStatement(sql)) {

                    SQLExecuteEvent event = new SQLExecuteEvent(this, resource, connection, statement, getSupportCategory());

                    SpringContextUtil.getApplicationContext().publishEvent(event);

                    try (ResultSet rs = statement.executeQuery()) {
                        long count = -1;

                        while (rs.next()) {
                            count = rs.getLong(1);
                        }

                        return count;
                    }

                }
            } catch (Exception ex) {
                return -1;
            }
        }

        return 0;
    }

    @Override
    public long getQueryCode(TableQuery query) {

        Object mask = query.getCode();

        ITableResource resource = query.getTableResource();
        long code = -1;

        if(resource instanceof Table5Resource) {

            Table5Resource table5Resource = (Table5Resource) resource;

            try(Connection conn = dbManager.getConnection(table5Resource.getDbConfig().getPid()))
            {
                String sql = null;

                if(TableQuery.FirstCode.equals(mask)) {
                    sql = "select min(Code) as code from " + table5Resource.getDbtable();
                }
                else if(TableQuery.LastCode.equals(mask)) {
                    sql = "select max(Code) as code from " + table5Resource.getDbtable();
                }
                else if(TableQuery.NewCode.equals(mask)) {
                    sql = "select isnull(max(Code), 0) + 1 as code from " + table5Resource.getDbtable();
                }

                if(sql != null) {
                    try(PreparedStatement statement =  conn.prepareStatement(sql)){
                        try(ResultSet rs = statement.executeQuery())
                        {
                            while (rs.next()) {
                                code = rs.getLong(1);
                            }
                        }
                    }
                }

            }
            catch (Exception ex) {

            }
        }



        return code;
    }

    @Override
    public TableData query(TableQuery query) throws Exception {

        ITableResource resource = query.getTableResource();
        TableData data = null;

        if (resource instanceof Table5Resource) {
            Table5Resource table5Resource = (Table5Resource) resource;

            TableResourceData reData = new TableResourceData(table5Resource);

            data = reData;

            SqlTable5ResourceWrap wrap = new SqlTable5ResourceWrap(table5Resource);

            if(wrap.isForm()) {
                //转换 code 的查询数据
                long code = getQueryCode(query);
                if(code != -1) {
                    query.setCode(code);
                };
            }

            String sub = TableCreateFunction.generateTableData(wrap, query);

            try (Connection connection = dbManager.getConnection(table5Resource.getDbConfig().getPid())) {

                try (PreparedStatement statement = connection.prepareStatement(sub)) {

                    SQLExecuteEvent event = new SQLExecuteEvent(this, resource, connection, statement, getSupportCategory());

                    SpringContextUtil.getApplicationContext().publishEvent(event);

                    try (ResultSet rs = statement.executeQuery()) {
                        reData.setData(rs);
                    }

                }

                //如果是单据表格并且是数据的第一页, 那么加载同步表格
                if(table5Resource.getCate() == TableCategory.FORM && query.getStart() < 1) {

                    String masterSql =  TableCreateFunction.generateMasterTableData(wrap, query);

                    try (PreparedStatement statement = connection.prepareStatement(masterSql)) {

                        SQLExecuteEvent event = new SQLExecuteEvent(this, resource, connection, statement, getSupportCategory());

                        SpringContextUtil.getApplicationContext().publishEvent(event);

                        try (ResultSet rs = statement.executeQuery()) {

                            reData.getSyncTable().setData(rs);

                        }

                    }
                }


                if(query.isReturnDataCount()) {
                    reData.setRowCount((int) getQueryCount(query));
                }


            } catch (Exception ex) {
                throw ex;
            }
        }

        return data;
    }


    /**
     * 执行保存数据方法
     *
     * @param resource
     * @param data
     * @throws Exception
     */
    @Override
    public void saveData(ITableResource resource, TableData data) {

        if (resource instanceof Table5Resource) {
            Table5Resource table = (Table5Resource) resource;
            ITransactionManager transactionManager = accountService.getCurrentAccountManager().getTransactionManager();

            Connection connection = null;
            try {
                connection=transactionManager.getConnection(table.getDbConfig().getPid());
                //如果有同步数据先处理同步数据

                SqlStatement updateStatement = null, insertStatement = null, deleteStatement = null;

                //执行同步数据
                if(data.getSyncTable() != null && !CollectionUtils.isEmpty(data.getSyncTable().getData())) {

                    TableData syncTable = data.getSyncTable();
                    int syst = syncTable.getStatusIndex();

                    for(TableRow syncRow : syncTable.getData()) {
                        int status = syncRow.getStatus(syst);

                        if(status == RowStatus.Modified) {
                            if(updateStatement == null) updateStatement = createUpdateSql(table, syncTable, true);
                            updateStatement.execute(connection, syncRow);
                        }
                        else if(status == RowStatus.Deleted) {
                            if(deleteStatement == null) deleteStatement = createRemoveSql(table, syncTable, true);
                            deleteStatement.execute(connection, syncRow);
                        }

                    }

                    if (updateStatement != null) updateStatement.close();
                    if (insertStatement != null) insertStatement.close();
                    if (deleteStatement != null) deleteStatement.close();

                }

                if(!CollectionUtils.isEmpty(data.getData()))
                {
                    updateStatement = null; insertStatement = null; deleteStatement = null;

                    int st = data.getStatusIndex();
                    //这里是不是年月日表分别对待....
                    HashMap<String, Integer> codeCache = new HashMap<>();//根据code缓存同步行

                    for (TableRow row : data.getData()) {
                        int status = row.getStatus(st);
                        if (status == RowStatus.Added) {
                            if(row.isNewCode()) {
                                changeCodeValue(row, data.getAdate(), codeCache);
                            }
                            if (insertStatement == null) insertStatement = createInsertSql(table, data);
                            insertStatement.execute(connection, row);
                        } else if (status == RowStatus.Deleted) {
                            if (deleteStatement == null) deleteStatement = createRemoveSql(table, data, false);
                            deleteStatement.execute(connection, row);
                        } else if (status == RowStatus.Modified) {
                            if (updateStatement == null) updateStatement = createUpdateSql(table, data, false);
                            updateStatement.execute(connection, row);
                        }

                    }


                    if (updateStatement != null) updateStatement.close();
                    if (insertStatement != null) insertStatement.close();
                    if (deleteStatement != null) deleteStatement.close();
                }


            }catch (Exception e){
                throw new DBProcessException("执行数据库数据更新出错. ", "01501");
            } finally {
                if(connection!=null){
                    transactionManager.finishExcute(connection);
                }
            }


        }
    }

    /**
     * 2、比较列（列名称 列类型） 获得到一般列 新增列 删除列 修改列  打字段 新增列 和删除列
     * 3、开始处理 组装sql
     * 4、开启事物
     * 5、批量执行
     * 6、重新生成新增和修改触发器
     * 7、提交事物
     * @param newResource 新的表格组件
     * @param oldResource 就的表格组件
     */
    @Override
    public void updatePhysicsTable(ITableResource newResource, ITableResource oldResource) {
        if(newResource instanceof Table5Resource&&oldResource instanceof Table5Resource){
            Table5Resource newTbale = (Table5Resource) newResource;
            Table5Resource oldTable = (Table5Resource) oldResource;

            IDbConnectionConfig dbConfig=oldTable.getDbConfig();
            Connection connection = null;
            ITransactionManager transactionManager = accountService.getCurrentAccountManager().getTransactionManager();
            try {
                connection=transactionManager.getConnection(oldTable.getDbConfig().getPid());

                TableCreateFunction.updateTable(this, newTbale,  oldTable, dbConfig, connection);
            }catch (Exception e){
                throw new DBProcessException("执行数据库数据更新出错. ", "01501");
            } finally {
                if(connection!=null){
                        transactionManager.finishExcute(connection);
                }
            }
        }else{
            throw new DBProcessException("不支持的数据表类型更新 SQLSERVER type:" + newResource.getClass(), "001");
        }

    }

    @Override
    public void removeDbConfig(IDbConnectionConfig dbCategory) {
        ITransactionManager manager = accountService.getCurrentAccountManager().getTransactionManager();
        try
        {
            if(lmsTableService==null){
                lmsTableService=Host.getInstance().getBean(ILmsTableService.class);
            }
            List<Table5GroupResource> resources=lmsTableService.getTable5GroupResource(dbCategory);
            StringBuilder drop=new StringBuilder();
            Connection connection = manager.getConnection(dbCategory.getPid());
            //获取连接
            Statement statement = connection.createStatement();
            for (Table5GroupResource table5Group:resources){
                List<Table5Resource>  tables=lmsTableService.getTable5Resources(table5Group.getPid());
                for(Table5Resource table5Resource:tables){
                    this.createDropTableStatement(table5Resource, drop, statement);
                }
            }
            statement.executeUpdate(drop.toString());
            statement.close();
            for (Table5GroupResource table5Group:resources){
                this.removeTableGroup(table5Group);
            }
        }catch (Exception ex) {
            if(log.isErrorEnabled())
                log.error("删除数据库配置时执行删除表组时出错:"+ex.getMessage());
            ex.printStackTrace();
            throw new DBProcessException("执行删除数据库配置出错. "+ex.getMessage(), "01601");
        }finally {
            manager.finishExcute();
        }
    }
    private Object lock = new Object();

    private void changeCodeValue(TableRow row, String adate, HashMap<String, Integer> codeCache) {
            String oldCode = row.getCode();
            int codeIndex=0;
            if(codeCache.containsKey(oldCode)) {
                codeIndex = codeCache.get(oldCode);
            }
            else {
                if(lmsTableService==null){
                    lmsTableService=Host.getInstance().getBean(ILmsTableService.class);
                }

                synchronized(lock){
                    String pid=row.getTable().getTablePid();


                    int lastIndex = (int)lmsTableService.getLastFormNo(pid, adate);
                    codeIndex = ++lastIndex;
                    codeCache.put(oldCode, lastIndex);
                    lmsTableService.updateLastFormNo(pid, adate, lastIndex);
                }
            }
            row.setCode(codeIndex);
    }


    @Override
    public DbCategory getSupportCategory() {
        return DbCategory.SQLSERVER;
    }

    protected SqlStatement createRemoveSql(Table5Resource table, TableData data, boolean isSyncDel) {
        StringBuilder builder = new StringBuilder("DELETE FROM ")
                .append(table.getDbtable());

        ArrayList<SqlParam> params = new ArrayList<>(1);


        if(isSyncDel) {
            builder.append(" WHERE Code = ?");
            Column codeColumn = table.getCodeColumn();
            params.add(new SqlParam(1, codeColumn, data.getCodeIndex()));

        }
        else {

            builder.append(" WHERE _key = ?");
            Column keyColumn = table.getKeyColumn();
            params.add(new SqlParam(1, keyColumn, 1));
        }


        return new SqlStatement(builder.toString(), params, data);
    }

    /**
     * 创建更新的执行语句
     *
     * @param table 更新的表格组件
     * @param data  更新数据
     * @param isSyncUpdate 是否是同步字段的更新
     * @return 返回执行对象
     */
    protected SqlStatement createUpdateSql(Table5Resource table, TableData data, boolean isSyncUpdate) {

        StringBuilder builder = new StringBuilder("UPDATE ").append(table.getDbtable()).append("\n");
        String[] tableCos = data.getCols();

        boolean isFirst = true;

        ArrayList<SqlParam> params = new ArrayList<>();
        int index = 0;

        for(int i=0; i<tableCos.length; i++) {

            String col = tableCos[i];
            Column column = table.getColumn(col);

            if(column == null) continue;

            int cate = column.getCategory();

            if (cate == ColumnCategory.Files
                    || cate == ColumnCategory.HTML
                    || cate == ColumnCategory.KEY
                    || cate == ColumnCategory.STREAM
                    || cate == ColumnCategory.Pic) {
                continue;
            }

            if (column.isReadOnly()) {
                continue;
            }


            if (isFirst) {
                builder.append(" SET ");
            } else {
                builder.append(", ");
            }


            if (cate == ColumnCategory.Update) {
                builder.append(column.getCol()).append(" = getdate() ");
            } else {
                builder.append("[").append(column.getCol()).append("]").append(" = ? ");
                SqlParam param = new SqlParam(++index, column, i);
                params.add(param);
            }

            isFirst = false;

        }


        builder.append("\n");


        if(isSyncUpdate) {
            builder.append("WHERE Code = ? \n");
            SqlParam param = new SqlParam(++index, table.getKeyColumn(), getColIndex(tableCos, "Code"));
            params.add(param);
        }
        else {
            builder.append("WHERE _key = ? \n");



            SqlParam param = new SqlParam(++index, table.getKeyColumn(), getColIndex(tableCos, "_key"));
            params.add(param);
        }


        return new SqlStatement(builder.toString(), params, data);
    }


    private int getColIndex(String[] cols, String col)
    {
        for(int i=0; i<cols.length; i++) {
            if(StringUtils.equalsIgnoreCase(cols[i], col)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 创建插入语句
     */
    protected SqlStatement createInsertSql(Table5Resource resource, TableData table) {
        StringBuilder builder = new StringBuilder("INSERT INTO ").append(resource.getDbtable());
        //insert sql 语句的头部信息
        builder.append(" (");
        String[] tableCos = table.getCols();

        int index = 0;
        boolean isFirst = true;

        ArrayList<SqlParam> params = new ArrayList<>();
        for(int i=0; i<tableCos.length; i++) {

            String col = tableCos[i];
            Column column = resource.getColumn(col);

            if(column == null) continue;

            int cate = column.getCategory();

            if (cate == ColumnCategory.Update
                    || cate == ColumnCategory.Files
                    || cate == ColumnCategory.HTML
                    || cate == ColumnCategory.Pic
                    || cate == ColumnCategory.STREAM) {
                continue;
            }

            if (!isFirst) builder.append(", ");

            builder.append("[").append(column.getCol()).append("]");

            SqlParam param = new SqlParam(++index, column, i);

            params.add(param);
            isFirst = false;

        }

        builder.append(")");

        //insert sql 语句的 values 信息
        builder.append(" VALUES ( ");
        for (int i = 0; i < params.size(); i++) {
            if (i > 0) builder.append(", ");
            builder.append("?");
        }

        builder.append(")");

        return new SqlStatement(builder.toString(), params, table);
    }

    /**
     * 执行语句封装
     */
    public class SqlStatement {
        private String sql;
        private ArrayList<SqlParam> params;
        private PreparedStatement statement;
        private TableData table;

        public SqlStatement(String sql, ArrayList<SqlParam> params, TableData table) {
            this.sql = sql;
            this.params = params;
            this.table = table;
        }

        /**
         * 获取执行的SQL语句
         */
        public String getSql() {
            return sql;
        }

        /**
         * 获取执行的参数
         */
        public ArrayList<SqlParam> getParams() {
            return params;
        }


        /**
         * 执行一个行的更新
         *
         * @param row 行
         */
        public void execute(Connection conn, TableRow row) throws Exception {

            if (statement == null) {
                statement = conn.prepareStatement(sql);
                if (log.isDebugEnabled())
                    log.debug("创建执行语句: {}", sql);
            }


            for (SqlParam param : params) {

                Column column = param.getColumn();
                int index = param.getIndex();

                Object value = row.get(param.getValueIndex());

                int category = column.getCategory();


                if (log.isDebugEnabled())
                    log.debug("设置参数值, Index : {}, Category: {}, Value:{}", index, category, value);


                if (category == ColumnCategory.TEXT) {
                    if (value == null) {
                        statement.setNull(index, Types.NVARCHAR);
                    } else {
                        statement.setNString(index, (String) value);
                    }
                } else if (category == ColumnCategory.INT||category==ColumnCategory.FormNO||category==ColumnCategory.FormSec) {
                    if (value == null) {
                        statement.setNull(index, Types.INTEGER);
                    } else {
                        statement.setInt(index, row.getAsInt(param.getValueIndex()));
                    }
                } else if (category == ColumnCategory.TIME) {
                    if (value == null) {
                        statement.setNull(index, Types.TIME);
                    } else {
                        statement.setTimestamp(index, row.getAsDate(param.getValueIndex()));
                    }
                } else if (category == ColumnCategory.KEY) {

                    if (value == null) {
                        statement.setNull(index, Types.ROWID);
                    } else {
                        statement.setString(index, row.getAsString(param.getValueIndex()));
                    }
                } else if (category == ColumnCategory.ADATE) {

                    String adate = row.getAsString(param.getValueIndex());

                    if(StringUtils.isEmpty(adate))
                    {
                        statement.setString(index, table.getAdate());
                    }
                    else
                    {
                        statement.setString(index, adate);
                    }


                } else if (category == ColumnCategory.CODE) {

                    statement.setString(index, (String) value);
                } else if(category == ColumnCategory.BOOL) {

                    if(value == null) {
                        statement.setNull(index, Types.BOOLEAN);
                        break;
                    }

                    boolean b = false;
                    if(value instanceof String) {
                        String v = (String) value;
                        if(StringUtils.isEmpty(v)) {
                            statement.setNull(index, Types.BOOLEAN);
                        }
                        else {
                            b = Boolean.parseBoolean((String)value);
                            statement.setBoolean(index, b);
                        }
                    }
                    else if(value instanceof Boolean)
                    {
                        statement.setBoolean(index, (Boolean)b);
                    }
                    else if (value instanceof Integer) {
                        statement.setBoolean(index, ((int) value) > 0);
                    }
                    else {
                        statement.setBoolean(index, true);
                    }

                } else {
                    statement.setObject(index, value);
                }
            }

            statement.execute();
        }


        public void close() throws SQLException {
            if (statement != null) statement.close();
        }
    }

    /**
     * 执行SQL语句的参数信息
     */
    public class SqlParam {

        private int index;
        private Column column;
        private int valueIndex;

        /**
         * @param index      参数位置
         * @param column     引用列
         * @param valueIndex 值在行中的位置
         */
        public SqlParam(int index, Column column, int valueIndex) {
            this.index = index;
            this.column = column;
            this.valueIndex = valueIndex;
        }

        /**
         * 获取参数的位置
         */
        public int getIndex() {
            return index;
        }

        /**
         * 获取参数的列信息
         */
        public Column getColumn() {
            return column;
        }

        /**
         * 获取值在行中的位置
         */
        public int getValueIndex() {
            return valueIndex;
        }
    }


}
