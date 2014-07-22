package com.lemsun.data.sqlserver;

import com.lemsun.TestSupport;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.connection.DbManager;
import com.lemsun.data.lmstable.*;
import com.lemsun.data.service.ILmsTableService;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * User: 宗旭东
 * Date: 13-7-23
 * Time: 下午6:07
 */
public class TableCreateFunctionTest extends TestSupport {

    @Autowired
    private ILmsTableService tableService;

    private DbManager dbManager;

    @Test
    public void testCreateTrigger()
    {
        Table5Resource resource = tableService.getResource("C000004521");

        resource.getColumn("D").setSync(Column.SyncYes);

        SqlTable5ResourceWrap wrap = new SqlTable5ResourceWrap(resource);
        String sql = TableCreateFunction.generateTriggerUpdate(wrap);

        getLog().debug(sql);
    }
    @Test
    public void testInsertTrigger()
    {
        Table5Resource resource = tableService.getResource("C000004787");

        resource.getColumn("E").setSync(Column.SyncYes);
        resource.getColumn("M").setSync(Column.SyncYes);
        SqlTable5ResourceWrap wrap = new SqlTable5ResourceWrap(resource);
        String sql = TableCreateFunction.generateTriggerInsert(wrap);

        getLog().debug(sql);
    }
    @Test
    public void testCreateTable()
    {

        Table5Resource table = new Table5Resource("测试", null);

        List<Column> columns = new ArrayList<>();

        Column col = new Column();
        col.setCategory(ColumnCategory.KEY);

        columns.add(col);

        col = new Column();
        col.setCol("Code");
        col.setCategory(ColumnCategory.CODE);

        columns.add(col);

        col = new Column();
        col.setCol("_sec1");
        col.setCategory(ColumnCategory.FormSec);

        columns.add(col);
        col = new Column();
        col.setCol("_adate");
        col.setCategory(ColumnCategory.ADATE);
        col.setEmpty(false);
        col.setUnique(true);


        columns.add(col);

        col = new Column();
        col.setCol("A");
        col.setCategory(ColumnCategory.TEXT);
        col.setSync(Column.SyncYes);

        columns.add(col);

        col = new Column();
        col.setCol("B");
        col.setCategory(ColumnCategory.INT);
        col.setCheck("B > 10");
        col.setSync(Column.SyncDown);

        columns.add(col);

        col = new Column();
        col.setCol("C");
        col.setCategory(ColumnCategory.BOOL);
        columns.add(col);

        col = new Column();
        col.setCol("D");
        col.setSync(Column.SyncYes);
        columns.add(col);


        col = new Column();
        col.setCol("_lastUpdate");
        col.setDefaultValue("getdate()");
        col.setCategory(ColumnCategory.Update);
        columns.add(col);


        table.setCate(TableCategory.DAY);

        table.setColumns(columns);

        table.setName("测试");
        table.setDbtable("TEST_2013");
        table.setCode("20130805");

        SqlTable5ResourceWrap wrap = new SqlTable5ResourceWrap(table);

        String sql = TableCreateFunction.generateCreateSql(wrap);

        getLog().debug(sql);

    }

}
