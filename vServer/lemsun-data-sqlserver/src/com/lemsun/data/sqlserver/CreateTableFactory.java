package com.lemsun.data.sqlserver;

import com.lemsun.core.IAccount;
import com.lemsun.core.SpringContextUtil;
import com.lemsun.core.util.ZipCodeStream;
import com.lemsun.data.TableResourceCreateEvent;
import com.lemsun.data.connection.DbManager;
import com.lemsun.data.tables.*;
import com.lemsun.form.controls.RootControl;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 创建数据表工厂
 * User: Xudong
 * Date: 12-11-16
 * Time: 上午11:55
 */
@Deprecated
public class CreateTableFactory {

    private DbManager dbManager;
    private TableResource resource;
    private IAccount account;
    private static final Logger log = LoggerFactory.getLogger(CreateTableFactory.class);


    public CreateTableFactory(DbManager dbManager, TableResource resource, IAccount owner) {
        this.dbManager = dbManager;
        this.resource = resource;
        this.account = owner;
    }


    /**
     * 创建表格, 在创建表格前需要如果表格没有进行初始化需要调用初始化方法
     *
     * @throws Exception 执行异常
     */
    public void create() throws Exception {
//		if(!(account.getSession() instanceof IDataSession))
//		{
//			throw new Exception("当前用户的会话对象不支持数据库操作. 请确认用户产生的Session对象. 或者登录的入口平台");
//		}

        Assert.notNull(resource.getDbConfig());
        //IDataSession dataSession = (IDataSession)account.getSession();
        //String dbName = dbManager.getDataSourceByPid(resource.getParentPid()).getConfigResource().getName();


        if (resource.getVersion() == TableGroupResource.VI) {
            //连接4代数据表

        } else if (resource.getVersion() == TableGroupResource.V) {
            createTable5(resource);
        }

    }

    public void createTable5(TableResource resource) throws Exception {
        if (resource.getVersion() != TableGroupResource.V)
            throw new Exception("传入的表格对象不属于5代的表格对象");

        String sql = generateSql();

        try (Connection connection = dbManager.getConnection(resource.getDbConfig().getPid())) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                TableResourceCreateEvent event = new TableResourceCreateEvent(this, sql, resource, statement);
                SpringContextUtil.getApplicationContext().publishEvent(event);
                statement.execute();
            }
        }
    }

    /**
     * 表格初始化方法
     *
     * @throws Exception 初始化异常
     */
    public void init() throws Exception {
        Assert.notNull(resource.getDbConfig());

        if (resource.getVersion() == TableGroupResource.VI) {
            try (Connection conn = dbManager.getConnection(resource.getDbConfig().getPid())) {
                //连接4代数据表
                loadTableInfo(conn, resource);
            }
        } else if (resource.getVersion() == TableGroupResource.V) {

            resource.setDbtable(resource.getPid());
            loadColumnInfo(resource);
            //设置表格头部的合并内容
            setHeaderSpan(resource);

        }
    }

    /**
     * 加载五代的列信息, 并补上必要的字段信息
     */
    protected void loadColumnInfo(TableResource resource) {
        if (resource.getVersion() == TableGroupResource.V) {
            List<TableColumn> cols = resource.getColumns();

            for (int i = 0; i < cols.size(); i++) {
                TableColumn c = cols.get(i);
                c.setCol(TableColumn.getColumnCode(i));
            }
            TableColumn keyCol = new TableColumn("_key", ColumnCategory.KEY);
            keyCol.setVisible(false);
            keyCol.setNull(false);

            TableColumn updateCol = new TableColumn("_lastUpdate", ColumnCategory.LastUpdate);
            updateCol.setVisible(false);

            TableColumn codeCol = new TableColumn("_code", ColumnCategory.CODE);
            codeCol.setName("编码");
            codeCol.setVisible(true);

            cols.add(0, codeCol);
            cols.add(0, updateCol);
            cols.add(0, keyCol);
        }
    }

    /**
     * 将四代表格的日期转换成固定的日期
     *
     * @param resource
     */
    protected void loadTableDate(TableResource resource) {
        //加载表格的日期
        int cate = resource.getCate();
        Date tableDate = new Date(0);

        String tableName = resource.getDbtable();
        int tableNameLength = tableName.length();
        String dateStr;
        if (cate == TableCategory.YEAR) {
            dateStr = tableName.substring(tableNameLength - 4);
            tableDate = DateUtils.setYears(tableDate, Integer.parseInt(dateStr));
        } else if (cate == TableCategory.MONTH) {
            dateStr = tableName.substring(tableNameLength - 7);
            tableDate = DateUtils.setYears(tableDate, Integer.parseInt(dateStr.substring(0, 4)));
            tableDate = DateUtils.setMonths(tableDate, Integer.parseInt(dateStr.substring(5)) - 1);
        } else if (cate == TableCategory.DAY) {

        }

        resource.setCurrentTimeFormat(DateFormatUtils.format(tableDate, "yyyyMMdd"));
    }


    /**
     * 加载旧表格的信息
     */
    protected void loadTableInfo(Connection conn, TableResource resource) throws Exception {

        loadTableDate(resource);

        PreparedStatement statement = conn.prepareStatement(
                String.format("select top(1) A1, A11, A12 from SYSTEM_REPORTS_ACHIEVES where A1 = '%1$s'", resource.getDbtable())
        );

        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            tableColumn(rs.getString("A11"), resource);
            tableFace(rs.getString("A12"), resource);
        }
        rs.close();
        statement.close();
    }

    /**
     * 创建一个皮肤组件.
     *
     * @param faceStr 皮肤字符
     */
    protected void createFaceComponent(String faceStr) {

    }


    /**
     * 加载表格的界面设置信息
     */
    protected void tableFace(String faceStr, TableResource resource) {
        setHeaderSpan(resource);
        try {
            String xmlStr = getFaceControl(faceStr);

            if (StringUtils.isNotEmpty(xmlStr) && xmlStr.contains("Lemsun.Win.UILibrary.RootContainer")) {
                SAXBuilder builder = new SAXBuilder();
                ByteArrayInputStream stream = new ByteArrayInputStream(("<?xml version=\"1.0\" encoding=\"gb2312\"?>" + xmlStr).getBytes());
                Document doc = builder.build(stream);
                Element root = doc.getRootElement();

                RootControl control = new RootControl(resource);

                control.loadVIProperty(root);
                //resource.setContent(control.toXaml());
                resource.setControl(control);
                stream.close();
            }
        } catch (Exception ex) {
            if(log.isErrorEnabled())

                log.error("解析4代表格的单据设计器数据出错: " + resource.getDbtable() + " -> " + ex.getMessage());
        }


    }


    protected String getFaceControl(String faceStr) throws Exception {

        if (StringUtils.isEmpty(faceStr) && !StringUtils.containsAny(faceStr, "Lemsun.Win.UILibrary.RootContainer")) {
            return null;
        }

        byte[] data = Base64.decodeBase64(faceStr);

        return org.apache.commons.codec.binary.StringUtils.newStringUtf8(ZipCodeStream.decompressBytes(data));
    }

    /**
     * 解析表头的合并内容
     *
     * @param resource
     */
    protected void setHeaderSpan(TableResource resource) {

        //设置表头属性
        List<TableColumn> cols = new ArrayList<>();

        int colCount = 0;
        TableFace face = resource.getFace();

        TableColumn codeColumn = null;

        for (TableColumn c : resource.getColumns()) {
            if (c.isVisible() && !StringUtils.equals(c.getCol(), "A0")) {
                colCount++;
                cols.add(c);
            }

        }


        face.setHeaderColCount(colCount);
        int rowCount = 0;

        for (TableColumn c : resource.getColumns()) {
            if (c.isVisible()) {
                if (StringUtils.isNotEmpty(c.getName()) && rowCount < 2) rowCount = 2;
                if (StringUtils.isNotEmpty(c.getName1()) && rowCount < 3) rowCount = 3;
                if (StringUtils.isNotEmpty(c.getName2()) && rowCount < 4) rowCount = 4;
            }
        }

        face.setHeaderRowCount(rowCount);

        //填充表头
        ArrayList<ArrayList<CellRangeText>> rows = new ArrayList<>(rowCount - 1);

        for (int r = rowCount; r > 0; r--) {
            ArrayList<CellRangeText> tempCols = new ArrayList<>(colCount);
            for (int c = 0; c < colCount; c++) {
                TableColumn tempCol = cols.get(c);
                tempCols.add(new CellRangeText(rowCount - r, c, 1, 1, tempCol.getText(r - 1)));
            }
            rows.add(tempCols);
        }

        //Collections.reverse(rows);

        //进行行合并
        for (int r = 0; r < rows.size(); r++) {
            ArrayList<CellRangeText> tempCols = rows.get(r);
            int c = 0;
            CellRangeText last = tempCols.get(c);
            while (c < tempCols.size() - 1) {
                CellRangeText next = tempCols.get(c + 1);
                if (next.getName() != null && StringUtils.equals(next.getName(), last.getName())) {
                    last.setColCount(last.getColCount() + 1);
                    tempCols.remove(c + 1);
                } else {
                    c++;
                    last = next;
                }
            }

        }

        //进行列合并
        for (int r = 0; r < rows.size() - 1; r++) {
            ArrayList<CellRangeText> tempCols = rows.get(r);
            for (CellRangeText col : tempCols) {

                for (int nextR = r + 1; nextR < rows.size() - 1; nextR++) {
                    boolean isNextRowSpan = false;
                    ArrayList<CellRangeText> nextCols = rows.get(nextR);
                    int index = 0;
                    while (index < nextCols.size()) {
                        if (col.equalsColText(nextCols.get(index))) {
                            col.setRowCount(col.getRowCount() + 1);
                            nextCols.remove(index);
                            isNextRowSpan = true;
                        } else {
                            index++;
                        }
                    }
                    if (!isNextRowSpan) break;
                }
            }
        }


        //转换成合并单元格
        ArrayList<CellRange> ranges = new ArrayList<>();
        for (int r = 0; r < rows.size(); r++) {
            ArrayList<CellRangeText> tempCols = rows.get(r);
            for (CellRangeText col : tempCols) {
                if (col.isSpanRange())
                    ranges.add(new CellRange(col.getRow(), col.getCol(), col.getRowCount(), col.getColCount()));
            }
        }

        face.setHeaderSpan(ranges);

    }


    class CellRangeText extends CellRange {

        private String name;

        public CellRangeText(int row, int col, int rowCount, int colCount, String name) {
            super(row, col, rowCount, colCount);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setColCount(int colCount) {
            this.colCount = colCount;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
        }


        /**
         * 判断是否处于同列中. 并且名字也一样
         */
        public boolean equalsColText(CellRangeText rangeText) {
            return equalsCol(rangeText) && StringUtils.equals(name, rangeText.getName());
        }
    }


    /**
     * 加载表格的列信息
     */
    protected void tableColumn(String colStr, TableResource resource) throws Exception {

        Assert.notNull(colStr);
        String[] items = colStr.split("~");
        Assert.notEmpty(items);

        String colHeaderStr = items[1];
        if (StringUtils.isEmpty(colHeaderStr)) {
            throw new Exception("表头的字符为空. 请检查表头字符串.");
        }

        String[] cols = colHeaderStr.split(",");

        if (cols == null || cols.length == 0)
            throw new Exception("表头字符串内没有表头信息. 或者表头信息为 0 :" + colHeaderStr);

        List<TableColumn> tableColumns = new ArrayList<>(cols.length);
        for (String col : cols) {
            TableColumn t = parseColumn(col, resource);
            if (t != null) tableColumns.add(t);
        }

        resource.setColumns(tableColumns);
    }


    /**
     * 解析单列信息
     */
    private static TableColumn parseColumn(String colStr, TableResource resource) throws Exception {
        String[] items = colStr.split(":");
        if (items.length < 10)
            //throw new Exception("当前列的信息不完整 : " + colStr);
            return null;

        TableColumn col = new TableColumn(items[0], ColumnCategory.parseCategory(items[3]));

        String[] names = items[1].split("&");

        if (names != null) {
            if (names.length > 0) col.setName(names[0].trim());
            if (names.length > 1) col.setName1(names[1].trim());
            if (names.length > 2) col.setName2(names[2].trim());
        }

        //col.setDecimalDigits(Integer.parseInt(items[2]));

        col.setFilter(items[4]);
        col.setWidth(Integer.parseInt(items[5]));

        //行高6暂时没法设置

        if (items.length > 7) col.setFlag(items[7]);
        if (items.length > 8) col.setAlign(Integer.parseInt(items[8]));
        if (items.length > 9) col.setVisible(Integer.parseInt(items[9]) == 1);
        if (items.length > 10) col.setReadOnly(Integer.parseInt(items[10]) == 1);

        //col.setAlign(Integer.parseInt(items[]));
        return col;
    }

    /**
     * @return 返回组件对象创建的SQL语句
     */
    protected String generateSql() {
        StringBuilder sql = new StringBuilder("create table ");
        sql.append(resource.getPid()).append("(");

        for (TableColumn col : resource.getColumns()) {
            sql.append(generateColSql(col));
        }

        sql.append(")");

        return sql.toString();
    }

    /**
     * @param col 列
     * @return 创建列的
     */
    protected static String generateColSql(TableColumn col) {

        String tempNUll = (col.isNull()) ? " NOT NULL" : "";
        String tempUnique = (col.isUnique()) ? " UNIQUE" : "";
        String tempCheck = (StringUtils.isEmpty(col.getCheck())) ? "" : " CHECK(" + col.getCheck() + ")";
        String tempDefault = (StringUtils.isEmpty(col.getDefaultvalue())) ? "" : " DEFAULT ('" + col.getDefaultvalue() + "')";

        return col.getCol() + " " + generateColType(col) + tempNUll + tempUnique + tempCheck + tempDefault + ",\n";
    }

    /**
     * @param col 列
     * @return 类型
     */
    protected static String generateColType(TableColumn col) {
        int cate = col.getCategory();
        int length = col.getLength();

        switch (cate) {

            case ColumnCategory.TEXT:
                return "nvarchar(" + (length == 0 ? "MAX" : Integer.toString(length)) + ")";

            case ColumnCategory.INT:
                return "int";

            case ColumnCategory.DOUBLE:
                return "float";

            case ColumnCategory.TIME:
                return "[datetime]";

            case ColumnCategory.BOOL:
                return "bit";

            case ColumnCategory.DATA:
                return "numeric";

            case ColumnCategory.STREAM:
                return "varbinary(MAX)";

            case ColumnCategory.KEY:
                return "[uniqueidentifier] ROWGUIDCOL PRIMARY KEY DEFAULT NEWID()";

            case ColumnCategory.LastUpdate:
                return "[datetime] DEFAULT GETDATE()";

            case ColumnCategory.CODE:
                return "varchar(500)  UNIQUE";

            default:
                return "ntext";
        }
    }

}
