package com.lemsun.channel.command.table;

import com.lemsun.data.tables.ColumnCategory;
import com.lemsun.data.tables.TableCategory;
import com.lemsun.data.tables.TableColumn;
import com.lemsun.data.tables.TableResource;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Xudong
 * Date: 12-12-25
 * Time: 上午9:24
 * 接收表格数据
 */
public class TableData {

    private String[] columns;
    private int colCount;
    private int rowCount;
    private List<TableRow> data;
    private String pid;
    private Date actionDate;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    /**
     * 获取当前表格的列信息
     */
    public String[] getColumns() {
        return columns;
    }

    /**
     * 设置表格的列信息
     */
    public void setColumns(String[] cols) {
        this.columns = cols;
    }

    /**
     * 获取表格列的数量
     */
    public int getColCount() {
        return colCount;
    }

    /**
     * 设置表格列的数量
     */
    public void setColCount(int colCount) {
        this.colCount = colCount;
    }

    /**
     * 获取当前表格数据行的数量
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * 设置表格数据行的数量
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * 获取表格数据
     */
    public List<TableRow> getData() {
        return data;
    }

    /**
     * 设置表格数据
     */
    public void setData(List<TableRow> data) {
        this.data = data;
    }

    private int statusIndex = -1;

    /**
     * 获取当前数据的行
     */
    public int getStatusIndex() {
        if(statusIndex == -1 && columns != null) {
            statusIndex = ArrayUtils.indexOf(columns, RowStatus.ColumnName);
        }
        return statusIndex;
    }

    /**
     * 执行更新
     */
    public void execute(Connection conn, TableResource resource) throws Exception {

        if(getStatusIndex() == -1) throw new Exception("没有状态标记列 : A0");

        SqlStatement updateStatement = null;

        for (TableRow row : data) {

            //判断执行更新
            if(row.getStatus(statusIndex) == RowStatus.Modified) {
                if(updateStatement == null) updateStatement = createUpdateSql(resource);
                updateStatement.execute(conn, row);
            }

            //判断执行创建

            //判断执行删除
        }

        if(updateStatement != null)
            updateStatement.close();
    }

    /**
     * 创建一个更新的模型
     * @param resource 资源
     * @return
     */
    private SqlStatement createUpdateSql(TableResource resource) {

        StringBuilder builder = new StringBuilder("UPDATE ");

        builder.append(resource.getDbtable()).append("\n");

        boolean isFirst = true;

        ArrayList<SqlParam> params = new ArrayList<>();
        int index = 0;

        for(TableColumn c : resource.getColumns()) {
            String colName = c.getCol();

            if(!c.isReadOnly()
                    && !StringUtils.equals(colName, "A0")
                    && !StringUtils.equals(colName, "A1")
                    && !StringUtils.equals(colName, "A2")
                    && !StringUtils.equals(colName, "A3")
                    && !StringUtils.equals(colName, "A4")
                    && !StringUtils.equals(colName, "A5")
                    && !StringUtils.equals(colName, "A6")
                    && !StringUtils.equals(colName, "A7")
                    && !StringUtils.equals(colName, "A8")
                    && !StringUtils.equals(colName, "A9")
                    && !StringUtils.equals(colName, "A10"))
            {
                if(isFirst)
                {
                    builder.append(" SET ");
                }
                else
                {
                    builder.append(", ");
                }

                builder.append(colName).append(" = ? ");

                SqlParam param = new SqlParam(++index, c, ArrayUtils.indexOf(columns, colName) + 1);
                params.add(param);
                isFirst = false;
            }
        }

        builder.append("\n");

        if(resource.getCate() == TableCategory.YEAR) {

            builder.append(" WHERE A1 = ? and A2 = ? and A3 = ? ");

            params.add(new SqlParam(++index, resource.getColumnByName("A1"),  ArrayUtils.indexOf(columns, "A1") + 1));
            params.add(new SqlParam(++index, resource.getColumnByName("A2"),  ArrayUtils.indexOf(columns, "A2") + 1));
            params.add(new SqlParam(++index, resource.getColumnByName("A3"),  ArrayUtils.indexOf(columns, "A3") + 1));

        }

        return new SqlStatement(builder.toString(), params);
    }


    /**
     * 执行语句封装
     */
    public class SqlStatement
    {
        private String sql;
        private ArrayList<SqlParam> params;
        private PreparedStatement statement;
        public SqlStatement(String sql, ArrayList<SqlParam> params) {
            this.sql = sql;
            this.params = params;
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
         * @param row 行
         */
        public void execute(Connection conn, TableRow row) throws Exception {

            if(statement == null)
                statement = conn.prepareStatement(sql);

            for (SqlParam param : params) {

                TableColumn column = param.getColumn();
                int index = param.getIndex();

                Object value = row.get(param.getValueIndex());
                int category = column.getCategory();

                if(category == ColumnCategory.TEXT) {
                    if(value == null) {
                        statement.setNull(index, Types.NVARCHAR);
                    }
                    else {
                        statement.setNString(index, row.getAsString(param.getValueIndex()));
                    }
                } else if(category == ColumnCategory.INT) {
                    if(value == null) {
                        statement.setNull(index, Types.INTEGER);
                    }
                    else {
                        statement.setInt(index, row.getAsInt(param.getValueIndex()));
                    }
                } else if(category == ColumnCategory.TIME) {
                    if(value == null) {
                        statement.setNull(index, Types.TIME);
                    }
                    else {
                        statement.setTimestamp(index, row.getAsDate(param.getValueIndex()));
                    }
                } else {
                    statement.setObject(param.getIndex(), value);
                }
            }

            statement.execute();
        }


        public void close() throws SQLException {
            if(statement != null) statement.close();
        }
    }

    /**
     * 执行SQL语句的参数信息
     */
    public class SqlParam {

        private int index;
        private TableColumn column;
        private int valueIndex;

        /**
         *
         * @param index 参数位置
         * @param column 引用列
         * @param valueIndex 值在行中的位置
         */
        public SqlParam(int index, TableColumn column, int valueIndex) {
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
        public TableColumn getColumn() {
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
