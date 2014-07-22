package com.lemsun.data.sqlserver;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * SQL 语句生产模板
 * User: 宗旭东
 * Date: 13-7-23
 * Time: 下午5:28
 */
public class SqlTemplate {

    private SqlTemplate()
    {

    }
    private static SqlTemplate template;
    private static final Logger Log = LoggerFactory.getLogger(SqlTemplate.class);

    public static final String CREATETABLE = "com/lemsun/data/sqlserver/statements/create_table.vm";
    public static final String UPDATETABLE_ADD_COLUMN = "com/lemsun/data/sqlserver/statements/update_table_add_column.vm";
    public static final String UPDATETABLE_DEL_COLUMN = "com/lemsun/data/sqlserver/statements/update_table_delete_column.vm";
    public static final String CREATETABLE_TRIGGER_UPDATE = "com/lemsun/data/sqlserver/statements/create_table_trigger_update.vm";
    public static final String CREATETABLE_TRIGGER_INSERT = "com/lemsun/data/sqlserver/statements/create_table_trigger_insert.vm";
    public static final String CREATETABLE_TRIGGER_DELETE = "com/lemsun/data/sqlserver/statements/create_table_trigger_delete.vm";
    public static final String CREATETABLE_CHILD_TRIGGER_DELETE = "com/lemsun/data/sqlserver/statements/create_table_and_trigger_delete.vm";
    public static final String TABLE_COUNT = "com/lemsun/data/sqlserver/statements/table_count.vm";
    public static final String TABLE_DATA = "com/lemsun/data/sqlserver/statements/table_data.vm";
    public static final String Master_TABLE_DATA = "com/lemsun/data/sqlserver/statements/table_data_master.vm";
    public static final String TABLE_EXT_COLUMN_DATA = "com/lemsun/data/sqlserver/statements/table_ext_column_data.vm";
    public static final String TABLE_EXT_COLUMN_UPDATE = "com/lemsun/data/sqlserver/statements/table_ext_column_update.vm";
    public static final String QUERY_TABLENAME = "com/lemsun/data/sqlserver/statements/query_tableName.vm";
    public static final String QUERY_TABLE_TRIGGERNAME = "com/lemsun/data/sqlserver/statements/query_table_triggerName.vm";
    public Template getCreateTableTrUpdate() {
        return getTemplate(CREATETABLE_TRIGGER_UPDATE);
    }

    public Template getCreateFileTableTrDelete() {
        return getTemplate(CREATETABLE_CHILD_TRIGGER_DELETE);
    }

    public Template getCreateTableTrInsert() {
        return getTemplate(CREATETABLE_TRIGGER_INSERT);
    }

    public Template getCreateTableTrDelete() {
        return getTemplate(CREATETABLE_TRIGGER_DELETE);
    }


    public Template getTableCount() {
        return getTemplate(TABLE_COUNT);
    }

    public Template getTableDate() {
        return getTemplate(TABLE_DATA);
    }
    public Template getMasterTableDate() {
        return getTemplate(Master_TABLE_DATA);
    }
    /**
     * 获取创建表格的模板
     */
    public Template getCreateTable()
    {
        return getTemplate(CREATETABLE);
    }


    /**
     * 获取当列为扩展表格的时候, 行的数据获取
     */
    public Template getColumnExtData()
    {
        return getTemplate(TABLE_EXT_COLUMN_DATA);
    }


    /**
     * 获取扩展列的更新语句
     */
    public Template getColumnExtUpdate()
    {
        return getTemplate(TABLE_EXT_COLUMN_UPDATE);
    }



    /**
     * 通过模板名称获取模板, 定义的模板名称可以查看当前类的成员变量.
     * @param name
     * @return
     */
    public Template getTemplate(String name) {
        return Velocity.getTemplate(name);
    }


    /**
     * 初始化模板数据
     * @return
     */
    private static SqlTemplate init()
    {
        if(Log.isDebugEnabled())
            Log.debug("开始加载 SqlServer 的 SQL 模板");

        SqlTemplate temp = new SqlTemplate();

        Properties ps = new Properties();
        ClassPathResource resource = new ClassPathResource("com/lemsun/data/sqlserver/statements/config.properties");

        try {
            ps.load(resource.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("没有找到数据库映射的配置文件 com/lemsun/data/sqlserver/statements/config.properties");
        }

        Velocity.init(ps);

        return temp;
    }






    /**
     * 获取模板实例
     */
    public static SqlTemplate instance()
    {
        if(template == null)
        {
            template = init();
        }

        return template;
    }




}
