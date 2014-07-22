package com.lemsun.data.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.lmstable.*;
import com.lemsun.data.service.IDbService;
import com.lemsun.data.service.ILmsTableService;
import com.lemsun.ldbc.RowStatus;
import com.lemsun.ldbc.TableData;
import com.lemsun.ldbc.TableRow;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * User: 宗旭东
 * Date: 13-6-9
 * Time: 上午10:47
 */
public class LmsTableServiceImplTest extends TestSupport {

    @Autowired
    private ILmsTableService tableService;

    @Autowired
    private JsonObjectMapper objectMapper;

    @Autowired
    private IDbService dbService;

    private String[] coloum = new String[]{"A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"};

    /**
     * 测试创建24*24列的表格
     */
    @Test
    public void testCreate() {

       /* DbConfigResource dbConfigResource = dbService.getDbconfigResource("C000000656");

        Table5GroupResource table5GroupResource = new Table5GroupResource("test_max_colour_table_group", "test_code", dbConfigResource);

        table5GroupResource.setTableCategory(0);
        table5GroupResource.setRemark("测试24*24列的表格");

        Table5Resource table5Resource = new Table5Resource("test_max_table", dbConfigResource);

        List<Column> columns = new ArrayList<>();

        Column column = null;
        column = new Column();
        column.setCol("_key");
        column.setName("主键");
        column.setCategory(8);
        columns.add(column);

        column = new Column();
        column.setCol("_lastUpdate");
        column.setName("更新标识");
        column.setCategory(9);
        columns.add(column);

        column = new Column();
        column.setCol("Code");
        column.setName("编码");
        column.setCategory(10);
        columns.add(column);

        for (int i = 1; i < 577; i++) {
            column = new Column();
            column.setCol(numToLater(i));
            column.setName("name" + numToLater(i));
            column.setCategory(1);
            columns.add(column);
        }
        table5Resource.setColumns(columns);

        List<Table5Resource> table5Resources = new ArrayList<>();
        table5Resources.add(table5Resource);

        table5GroupResource.setTables(table5Resources);

        try {
            tableService.create(table5GroupResource);
        } catch (Exception e) {
            getLog().error("创建失败是因为:" + e.getMessage());
        }*/
        DbConfigResource dbConfigResource = dbService.getDbconfigResource("C000005169");

        Table5GroupResource table5GroupResource = new Table5GroupResource("test_max_colour_table_group", "test_code", dbConfigResource);

        table5GroupResource.setTableCategory(TableCategory.DAYRECORD);
        table5GroupResource.setRemark("测试24*24列的表格");
        table5GroupResource.setPid("C000005205");
        Table5Resource table5Resource = new Table5Resource("test_max_table", dbConfigResource);

        List<Column> columns = new ArrayList<>();

        Column column = null;
        column = new Column();
        column.setCol("_key");
        column.setName("主键");
        column.setCategory(8);
        columns.add(column);
        column = new Column();
        column.setCol("_adate");
        column.setName("操作日期");
        column.setCategory(ColumnCategory.ADATE);
        columns.add(column);
        column = new Column();
        column.setCol("_lastUpdate");
        column.setName("更新标识");
        column.setCategory(9);
        columns.add(column);

        column = new Column();
        column.setCol("Code");
        column.setName("编码");
        column.setCategory(ColumnCategory.CODE);
        columns.add(column);
        column = new Column();
        column.setCol("D");
        column.setName("nameD");
        column.setCategory(1);
        columns.add(column);
        column = new Column();
        column.setCol("E");
        column.setName("nameE");
        column.setCategory(2);
        columns.add(column);
        column = new Column();
        column.setCol("F");
        column.setName("nameF");
        column.setCategory(3);
        columns.add(column);
        column = new Column();
        column.setCol("G");
        column.setName("nameG");
        column.setCategory(1);
        column.setSync(1);
        columns.add(column);
        column = new Column();
        column.setCol("H");
        column.setName("nameH");
        column.setCategory(1);
        column.setSync(1);
        columns.add(column);

        table5Resource.setColumns(columns);

        List<Table5Resource> table5Resources = new ArrayList<>();
        table5Resources.add(table5Resource);

        table5GroupResource.setTables(table5Resources);

        try {
            tableService.create(table5GroupResource);
        } catch (Exception e) {
            getLog().error("创建失败是因为:" + e.getMessage());
        }
    }

    private String numToLater(int num) {
        if (num < 0 || num > 577) {
            throw new RuntimeException("超过索引");
        }
        if (num > 0 && num <= 26) {
            return coloum[num - 1];
        } else {
            int temp = num % 26;
            int tempa = num / 26;
            return coloum[tempa - 1] + coloum[temp];
        }
    }

    @Test
    public void testGet() throws IOException {
        Table5Resource resource = tableService.getResource("C000001242");
        getLog().debug(objectMapper.writeValueAsString(resource));
    }
    @Test
    public void testGetLastFormNo()  {
        long index= tableService.getLastFormNo("C000004562", "");
        System.err.println("++"+index);
    }
    @Test
    public void testUpdateLastFormNo()  {
        tableService.updateLastFormNo("C000004562", "", 3);
        long index= tableService.getLastFormNo("C000004562", "");
        System.err.println(index);
    }
    @Test
    public void testRemoveLastFormNo()  {
        long index= tableService.getLastFormNo("C000004562", "");
        System.err.println(index);
        tableService.removeLastFormNo("C000004562");
        index= tableService.getLastFormNo("C000004562", "");
        System.err.println(index);
    }

    /**
     * 新增测试通过
     */
    @Test
    public void testSaveData(){

        TableData data= new TableData();
        String[] cols =new String[6];
        cols[0]="_key";
        cols[1]="Code";
        cols[2]="D";
        cols[3]="E";
        cols[4]="F";
        cols[5]="_";
        Integer[] colTypes=new Integer[6];

        colTypes[0]= ColumnCategory.KEY;
        colTypes[1]=ColumnCategory.FormNO;
        colTypes[2]=ColumnCategory.DATA;
        colTypes[3]=ColumnCategory.FormSec;
        colTypes[4]=ColumnCategory.DOUBLE;
        colTypes[5]=ColumnCategory.INT;//状态列
        data.setCols(cols);
        data.setColCount(6);
        data.setAdate("2013-11-28 16:06:09");
        //data.setColTypes(colTypes);
        data.setTablePid("C000004643");

        List<TableRow> dataRows=new ArrayList<>();
        for(int i=0;i<4;i++){
            TableRow row= new TableRow();
            row.add(UUID.randomUUID());
            row.add("新000"+i);
            row.add("000"+i);
            row.add("000"+i);
            row.add("000"+i);
            row.add(RowStatus.Added);
            TableRow row2= new TableRow();
            row2.add(UUID.randomUUID());
            row2.add("新000"+i);
            row2.add("000"+i);
            row2.add("000"+i);
            row2.add("000"+i);
            row2.add(RowStatus.Added);

            dataRows.add(row);
            dataRows.add(row2);
        }
        data.setData(dataRows);
        data.setRowCount(8);

        TableData syncTable= new TableData();


        String[] cols2 =new String[3];
        cols2[0]="Code";
        cols2[1]="G";
        cols2[2]="H";
        Integer[] colTypes2=new Integer[3];
        colTypes2[0]=ColumnCategory.FormNO;
        colTypes2[1]=4;
        colTypes2[2]=4;

        syncTable.setCols(cols2);
        syncTable.setColCount(3);
        syncTable.setAdate("2013-11-28 16:06:09");
        //syncTable.setColTypes(colTypes);
        syncTable.setTablePid("C000004643");

        List<TableRow> dataRows2=new ArrayList<>();
        for(int i=0;i<4;i++){
            TableRow row= new TableRow();
            row.add("新000"+i);
            row.add("000"+i);
            row.add("2013-11-28 16:06:09");
            dataRows2.add(row);
        }
        syncTable.setData(dataRows2);
        syncTable.setRowCount(4);
        syncTable.setTablePid("C000004643");
        data.setSyncTable(syncTable);
        try{
            tableService.saveData("C000004643",data);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     *  只测试通过修改一条数据时通过
     * 同时新增和修改时有问题
     * 修改触发器未执行 触发器存在问题
     * insert触发器未创建
     *
     *
     */
    @Test
    public void testSaveDataUpdate(){
        TableData data= new TableData();
        String[] cols =new String[6];
        cols[0]="_";
        cols[1]="_key";
        cols[2]="Code";
        cols[3]="D";
        cols[4]="E";
        cols[5]="F";
        Integer[] colTypes=new Integer[6];
        colTypes[0]=ColumnCategory.INT;//状态列
        colTypes[1]= ColumnCategory.KEY;
        colTypes[2]=ColumnCategory.CODE;
        colTypes[3]=ColumnCategory.TEXT;
        colTypes[4]=ColumnCategory.INT;
        colTypes[5]=ColumnCategory.DOUBLE;

        data.setCols(cols);
        data.setColCount(6);
        data.setAdate("2013-11-28 16:06:09");
        //data.setColTypes(colTypes);
        data.setTablePid("C000004643");

        List<TableRow> dataRows=new ArrayList<>();

        TableRow row= new TableRow();
        row.add(RowStatus.Modified);
        row.add("139F1DBD-0B5C-4F79-BA59-F7A2EB281006");
        row.add("0002");
        row.add("修改测试2");
        row.add(2);
        row.add(2);


        TableRow row2= new TableRow();
        row2.add(RowStatus.Added);
        row2.add(UUID.randomUUID());
        row2.add("0002");
        row2.add("测试新增");
        row2.add(5);
        row2.add(5);


        TableRow rowDel= new TableRow();
        rowDel.add(RowStatus.Deleted);
        rowDel.add("2DBB7BBF-FBA9-48CA-961B-ABC2B6F9AC3C");

        TableRow rowDel2= new TableRow();
        rowDel2.add(RowStatus.Deleted);
        rowDel2.add("293AE63D-2B6E-4570-9D84-E623F2F54624");

        dataRows.add(row);
        dataRows.add(row2);
        dataRows.add(rowDel);
        dataRows.add(rowDel2);
        data.setData(dataRows);
        data.setRowCount(2);

        TableData syncTable= new TableData();


        String[] cols2 =new String[3];
        cols2[0]="Code";
        cols2[1]="G";
        cols2[2]="H";
        Integer[] colTypes2=new Integer[3];
        colTypes2[0]=ColumnCategory.CODE;
        colTypes2[1]=ColumnCategory.TEXT;
        colTypes2[2]=ColumnCategory.TEXT;

        syncTable.setCols(cols2);
        syncTable.setColCount(3);
        syncTable.setAdate("2013-11-28 16:06:09");
        //syncTable.setColTypes(colTypes);
        syncTable.setTablePid("C000004643");

        List<TableRow> dataRows2=new ArrayList<>();
        for(int i=0;i<1;i++){
            TableRow row3= new TableRow();
            row3.add("0002");
            row3.add("新增修改 删除 一起 ");
            row3.add("2014-11-28 16:06:09");
            dataRows2.add(row3);
        }
        syncTable.setData(dataRows2);
        syncTable.setRowCount(4);
        syncTable.setTablePid("C000004643");
        data.setSyncTable(syncTable);
        try{
            tableService.saveData("C000004643",data);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 测试修改
     */
    @Test
    public void testUpdate(){

        Table5Resource oldResource = tableService.getResource("C000000019");
        List<Column> columns=new ArrayList<>();
        for(Column col :oldResource.getColumns()){
            if(!StringUtils.equalsIgnoreCase(col.getCol(),"Y")){
                if(StringUtils.equalsIgnoreCase(col.getCol(),"E")){
                    col.setSync(1);
                }
                columns.add(col);
            }
        }
        Column column=null;


        column = new Column();
        column.setCol("X");
        column.setName("SS");
        column.setCategory(ColumnCategory.Files);

        columns.add(column);
        oldResource.setColumns(columns);
        tableService.updateResource(oldResource);
    }
}
