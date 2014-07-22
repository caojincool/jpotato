package com.lemsun.data.connection;

import com.lemsun.core.formula.IFCol;

import com.lemsun.data.lmstable.Column;
import com.lemsun.data.lmstable.Table5Resource;
import com.lemsun.ldbc.TableData;
import com.lemsun.ldbc.TableRow;
import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 对应表格的数据集合
 * User: Xudong
 * Date: 12-10-11
 * Time: 下午4:36
 */
public class TableSet extends TableData {

    /**
     * 通过结果集和5代数据表构造表集合,可以获取列信息和表内容
     * @param resultSet
     * @param resource
     * @throws SQLException
     */
    public TableSet(ResultSet resultSet, Table5Resource resource) throws SQLException {

        init(resultSet, resource);
    }

    /**
     * 通过结果集简单的获取表内容,不能取得表的列类型信息
     * 适用于执行查询sql语句
     * @param resultSet
     * @throws SQLException
     */
    public TableSet(ResultSet resultSet) throws SQLException {

        init(resultSet);
    }

    /**
     * 通过结果集初始化表内容,不能取得表的列类型信息
     * @param resultSet
     * @throws SQLException
     */
    private  void init(ResultSet resultSet)  throws SQLException{
        ResultSetMetaData meta = resultSet.getMetaData();

        setColCount(meta.getColumnCount());

        String[] cols = new String[getColCount()];

        for (int i = 1; i <= getColCount(); i++) {
            cols[i - 1] = meta.getColumnName(i);
        }

        setCols(cols);

        List<TableRow> data = new ArrayList<>();
        int rows = 0;
        while (resultSet.next()) {
            TableRow row = new TableRow();
            for (int i = 1; i <= getColCount(); i++) {
                row.add(resultSet.getObject(i));
            }
            data.add(row);
            rows++;
        }

        setData(data);
    }

    /**
     * 对查询结果集进行封装
     *
     * @param resultSet 结果集
     * @param resource  指定了 查询对象
     * @throws SQLException
     */
    private void init(ResultSet resultSet, Table5Resource resource) throws SQLException {

        ResultSetMetaData meta = resultSet.getMetaData();

        setColCount(meta.getColumnCount());

        String[] cols = new String[getColCount()];

        int[] colTypes = null;

        if (resource != null ) colTypes = new int[getColCount()];

        for (int i = 1; i <= getColCount(); i++) {
            cols[i - 1] = meta.getColumnName(i);
            if (colTypes != null) {

                    colTypes[i - 1] = resource.getColumn(cols[i - 1]).getCategory();

            }

        }

        setCols(cols, colTypes);

        List<TableRow> data = new ArrayList<>();
        int rows = 0;
        while (resultSet.next()) {
            TableRow row = new TableRow();
            for (int i = 1; i <= getColCount(); i++) {
                row.add(resultSet.getObject(i));
            }
            data.add(row);
            rows++;
        }

        setData(data);
    }
}
