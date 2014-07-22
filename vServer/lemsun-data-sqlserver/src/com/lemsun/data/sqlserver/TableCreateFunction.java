package com.lemsun.data.sqlserver;

import com.lemsun.core.SpringContextUtil;
import com.lemsun.data.lmstable.Column;
import com.lemsun.data.lmstable.Table5Resource;
import com.lemsun.ldbc.*;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.sql.*;
import java.util.List;

/**
 * 在数据库中创建表格,
 * <p/>
 * 表格对象 -> 触发器
 * <p/>
 * <p/>
 * User: 宗旭东
 * Date: 13-7-20
 * Time: 上午11:27
 */
public class TableCreateFunction {

    private static TemplateUtils util = new TemplateUtils();
    public static final String TABLE="TABLE";
    public static final String TRIGGER="TRIGGER";
    private static Logger log = LoggerFactory.getLogger(TableCreateFunction.class);

    /**
     * 创建表格资源
     *
     * @param excuter    执行创建的对象
     * @param table      表格组件对象
     * @param dbConfig   数据库配置信息
     * @param connection 连接接口
     * @throws DBProcessException 创建异常
     */
    public static void createTableResource(Object excuter, Table5Resource table, IDbConnectionConfig dbConfig, Connection connection) {

        if (table.getKeyColumn() == null)
            throw new DBProcessException("不能创建一个没有Key字段的表格." + table.getName(), "002");
        SqlTable5ResourceWrap wrap = new SqlTable5ResourceWrap(table);
        String sql = generateCreateSql(wrap);
        excuteStatement(excuter, table, connection, sql);

        doTableTringger(excuter, table, connection, wrap);


    }
    /**
     * 执行创建表格触发器
     * @param excuter
     * @param table
     * @param connection
     * @param wrap
     */
    private static void doTableTringger(Object excuter, Table5Resource table, Connection connection, SqlTable5ResourceWrap wrap) {
        String sql;
        sql = generateTriggerUpdate(wrap);
        excuteStatement(excuter, table, connection, sql);

        sql = generateTriggerInsert(wrap);
        excuteStatement(excuter, table, connection, sql);

        sql = generateTriggerDelete(wrap);
        excuteStatement(excuter, table, connection, sql);

       for(Column col : wrap.getSyncBigColumn())
       {
           sql = generateFileTriggerDelete(wrap, col);
           excuteStatement(excuter, table, connection, sql);
       }
    }


    /**
     * 更新表格资源
     *
     * @param excuter    执行更新的对象
     * @param table      表格组件对象
     * @param dbConfig   数据库配置信息
     * @param connection 连接接口
     * @throws DBProcessException 异常
     */
    public static void  updateTable(Object excuter, Table5Resource table, Table5Resource oldTable, IDbConnectionConfig dbConfig, Connection connection){
        SqlTable5ResourceWrap wrap = new SqlTable5ResourceWrap(table);
        deleteTableTrigger(excuter, table, connection, wrap);
        updateTableColumn(excuter, table, oldTable, connection, wrap);
        doTableTringger(excuter, table, connection, wrap);
    }

    /**
     * 执行删除触发器
     * @param excuter
     * @param table
     * @param connection
     *
     * @param wrap
     */
      private static void deleteTableTrigger(Object excuter, Table5Resource table, Connection connection, SqlTable5ResourceWrap wrap){
          String select=generateResult(wrap,SqlTemplate.QUERY_TABLE_TRIGGERNAME);
          StringBuilder drop=new StringBuilder();
          try {
             queryTableName(drop,connection.createStatement(), select,TRIGGER);
          } catch (SQLException e) {
              throw new DBProcessException("查询触发器名称出错"+e.getMessage(), "200");
          }
          excuteStatement(excuter, table, connection, drop.toString());
      }

    /**
     * 执行查询 和创建删除语句
     * @param drop
     * @param statement
     * @param select
     * @param operateName 执行删除对象 有触发器和表
     * @return
     */
    public static StringBuilder queryTableName( StringBuilder drop, Statement statement, String select,String operateName) {
        try  {
                ResultSet rs=statement.executeQuery(select.toString());
                //拼接删除表语句
                while (rs.next()) {
                    String childTableName = rs.getString("name");
                    drop.append("DROP  ");
                    drop.append(operateName);
                    drop.append(" ");
                    drop.append(childTableName);
                    drop.append(";\n");
                }
                rs.close();
                log.debug("生成sql为："+drop.toString());
        } catch (Exception ex) {
            throw new DBProcessException("删除触发器出错"+ex.getMessage(), "200");
        }
        return drop;
    }

    /**
     * 修改表格字段
     * @param excuter
     * @param table
     * @param oldTable
     * @param connection
     * @param wrap
     */
    private static void updateTableColumn(Object excuter, Table5Resource table, Table5Resource oldTable, Connection connection, SqlTable5ResourceWrap wrap) {
        List<Column> oldCols= oldTable.getColumns();
        //删除字段
        String sql = generateAlterTableSql(wrap,wrap.getDelColumn(oldCols), SqlTemplate.UPDATETABLE_DEL_COLUMN);
        excuteStatement(excuter, table, connection, sql);
        //新增字段
        String sqlAdd = generateAlterTableSql(wrap,wrap.getAddColumn(oldCols),SqlTemplate.UPDATETABLE_ADD_COLUMN);
        excuteStatement(excuter, table, connection, sqlAdd);
        //修改字段
        List<Column> clos=wrap.getUpdateDelColumn(oldCols);
        String sqlUpdate = generateAlterTableSql(wrap,clos,SqlTemplate.UPDATETABLE_DEL_COLUMN);
        excuteStatement(excuter, table, connection, sqlUpdate);
        List<Column> addClos=wrap.getUpdateAddColumn(oldCols);
        String sqlUpdateAdd = generateAlterTableSql(wrap,addClos,SqlTemplate.UPDATETABLE_ADD_COLUMN);
        excuteStatement(excuter, table, connection, sqlUpdateAdd);
    }

    /**
     * 执行语句出错
     * @param excuter
     * @param table
     * @param connection
     * @param sql
     */
    private static void excuteStatement(Object excuter, Table5Resource table, Connection connection, String sql) {
        if(log.isInfoEnabled())
            log.info("执行sql为{}",sql);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            SQLExecuteEvent event = new SQLExecuteEvent(excuter, table, connection, statement, DbCategory.SQLSERVER);
            SpringContextUtil.getApplicationContext().publishEvent(event);
            statement.execute();
        } catch (Exception ex) {
           if(log.isErrorEnabled())
               log.error("sql：{}执行错误为：{}",sql,ex.getMessage());
            throw new DBProcessException(ex.getMessage(), "100");
        }
    }

    /**
     * 产生一个修改表 SQl 语句
     */
    public static String generateAlterTableSql(SqlTable5ResourceWrap resourceWrap,  List<Column> cols,String templateName) {
        Template ts = SqlTemplate.instance().getTemplate(templateName);
        VelocityContext context = new VelocityContext();
        context.put("cols", cols);
        return generateResult(resourceWrap, ts, context);
    }
    /**
     * 产生一个创建SQl 语句
     */
    public static String generateCreateSql(SqlTable5ResourceWrap resourceWrap) {
        Template ts = SqlTemplate.instance().getCreateTable();
        VelocityContext context = new VelocityContext();
        return generateResult(resourceWrap, ts, context);
    }
    /**
     * 生成表格必须的触发器
     *
     * @param resourceWrap 表格组件包装对象
     * @return 同步触发器语句
     */
    public static String generateTriggerUpdate(SqlTable5ResourceWrap resourceWrap) {
        Template st = SqlTemplate.instance().getCreateTableTrUpdate();
        VelocityContext context = new VelocityContext();
        return generateResult(resourceWrap, st, context);
    }

    /**
     * 生成创建表格新增数据的触发器
     *
     * @param resourceWrap
     * @return
     */
    public static String generateTriggerInsert(SqlTable5ResourceWrap resourceWrap) {
        Template st = SqlTemplate.instance().getCreateTableTrInsert();
        VelocityContext context = new VelocityContext();
        return generateResult(resourceWrap, st, context);
    }

    /**
     * 生成创建表格删除数据的触发器
     *
     * @param resourceWrap
     * @return
     */
    public static String generateFileTriggerDelete(SqlTable5ResourceWrap resourceWrap, Column col) {
        Template st = SqlTemplate.instance().getCreateFileTableTrDelete();
        VelocityContext context = new VelocityContext();
        context.put("col", col);
        return generateResult(resourceWrap, st, context);
    }
    /**
     * 生成创建表格删除数据的触发器
     *
     * @param resourceWrap
     * @return
     */
    public static String generateTriggerDelete(SqlTable5ResourceWrap resourceWrap) {
        Template st = SqlTemplate.instance().getCreateTableTrDelete();
        VelocityContext context = new VelocityContext();
        return generateResult(resourceWrap, st, context);
    }

    /**
     * 生成查询表格数据的条目数的SQL语句
     *
     * @param resourceWrap
     * @param query
     * @return
     */
    public static String generateTableCount(SqlTable5ResourceWrap resourceWrap, TableQuery query) {
        Template st = SqlTemplate.instance().getTableCount();
        VelocityContext context = new VelocityContext();
        context.put("query", query);
        return generateResult(resourceWrap, st, context);
    }



    /**
     * 生成查询子表格数据的SQL语句
     *
     * @param resourceWrap
     * @param query
     * @return
     */
    public static String generateTableData(SqlTable5ResourceWrap resourceWrap, TableQuery query) {
        Template st = SqlTemplate.instance().getTableDate();
        VelocityContext context = new VelocityContext();
        context.put("query", query);
        return generateResult(resourceWrap, st, context);
    }

    /**
     * 生成查询子表格数据的SQL语句
     *
     * @param resourceWrap
     * @param query
     * @return
     */
    public static String generateMasterTableData(SqlTable5ResourceWrap resourceWrap, TableQuery query) {
        Template st = SqlTemplate.instance().getMasterTableDate();
        VelocityContext context = new VelocityContext();
        context.put("query", query);
        return generateResult(resourceWrap, st, context);
    }


    /**
     * 生成获取扩展列数据行的SQL语句.
     * @param cate 获取SQL语句的类型
     *             1. 按照文件名获取 rowid = ? AND filename = ?
     *             2. 按照文件ID 获取 _key = ?
     *
     */
    public static String generateExtColumnData(SqlTable5ResourceWrap resourceWrap, Column col, int cate) {
        Template st = SqlTemplate.instance().getColumnExtData();
        VelocityContext context = new VelocityContext();
        context.put("col", col);
        context.put("cate", cate);
        return generateResult(resourceWrap, st, context);
    }

    /**
     * 生成获取扩展列更新数据行的SQL语句.
     * @param cate 获取SQL语句的类型
     *             1. 清除文件的语句
     *             2. 插入文件的语句
     *
     */
    public static String generateExtColumnUpdate(SqlTable5ResourceWrap resourceWrap, Column col, int cate) {
        Template st = SqlTemplate.instance().getColumnExtUpdate();
        VelocityContext context = new VelocityContext();
        context.put("col", col);
        context.put("cate", cate);
        return generateResult(resourceWrap, st, context);
    }

    /**
     *创建语句
     * @param resourceWrap 表格包装对象
     * @param templateName sql模板名称
     * @return
     */
    public static String generateResult(SqlTable5ResourceWrap resourceWrap,String templateName){
        Template st = SqlTemplate.instance().getTemplate(templateName);
        VelocityContext context = new VelocityContext();
        return generateResult(resourceWrap, st, context);
    }
    /**
     * 创建语句
     * @param templateName sql模板名称
     * @return
     */
    public static String generateResult(String templateName){
        Template st = SqlTemplate.instance().getTemplate(templateName);
        VelocityContext context = new VelocityContext();
        return generateResult(null, st, context);
    }
    /**
     * 执行模板生成sql语句
     * @param resourceWrap
     * @param st
     * @param context
     * @return
     */
    private static String generateResult(SqlTable5ResourceWrap resourceWrap, Template st, VelocityContext context) {
        context.put("rs", resourceWrap);
        context.put("util", util);
        StringWriter writer = new StringWriter();
        st.merge(context, writer);
        writer.flush();
        return writer.toString();
    }
}
