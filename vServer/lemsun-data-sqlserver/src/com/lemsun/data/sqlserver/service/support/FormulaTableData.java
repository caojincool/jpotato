package com.lemsun.data.sqlserver.service.support;

import com.lemsun.core.ArrayData;
import com.lemsun.core.LemsunException;
import com.lemsun.core.formula.IFCol;

import com.lemsun.data.lmstable.Column;
import com.lemsun.data.lmstable.Table5Resource;
import com.lemsun.ldbc.ColumnInfo;
import com.lemsun.ldbc.ITableResource;
import com.lemsun.ldbc.TableData;
import com.lemsun.ldbc.TableRow;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: 刘晓宝
 * Date: 14-3-7
 * Time: 上午10:18
 */
public class FormulaTableData extends ArrayData<TableRow> {
    private String adate;
    private ColumnInfo[] columnInfos;
    private String tablePid;

    private FormulaTableData syncTable;

    public FormulaTableData(ResultSet resultSet, List<ISelectCol> allCols) throws SQLException {
        init(resultSet, allCols);
    }

    /**
     * 对查询结果集进行封装
     *
     * @param resultSet 结果集
     *
     * @param allCols   指定查询结果列
     * @throws java.sql.SQLException
     */
    private void init(ResultSet resultSet,  List<ISelectCol> allCols) throws SQLException {

        ResultSetMetaData meta = resultSet.getMetaData();
        int columnCount=meta.getColumnCount();
        setColCount(meta.getColumnCount());
        String[] cols=new String[columnCount] ;
        for(int i = 1; i <= meta.getColumnCount(); i++) {
            String name = meta.getColumnName(i);
            cols[i-1]=name;
        }
        super.setCols(cols);
        List<TableRow> data = new ArrayList<>();

        while (resultSet.next()) {
            TableRow row = new TableRow();
            for (int i = 1; i <= getColCount(); i++) {
                row.add(resultSet.getObject(i));
            }
            data.add(row);
        }
        setData(data);
        //设置查询列信息
        if (allCols != null) {
            columnInfos=new ColumnInfo[allCols.size()];
            int i = 0;
            for (ISelectCol sel : allCols) {
                IFCol fCol = sel.getFormulaCol();
                ITableResource tableResource= sel.getTableResource();
                String ref=null;
                if (tableResource instanceof Table5Resource){
                    ref=((Table5Resource)tableResource).getPid();
                }
                ColumnInfo info=new ColumnInfo(ref,sel.getCol().getCol(),allCols.get(i).getCol().getCategory());
                columnInfos[i]=info;
                if (StringUtils.isEmpty(fCol.getFun())) {
                    Column column = sel.getCol();
                    column.getCategory();
                    if ((column.isCodeCol() || column.isKeyCol()) && sel.isFlag()) {
                        info.setCateType(ColumnInfo.BACK_COLUMN);
                    } else {
                        info.setCateType(ColumnInfo.COMMON_COLUMN);
                    }
                } else {
                    info.setCateType(ColumnInfo.MERGE_DATA);
                }
                i++;
            }
            setColumnInfos(columnInfos);
        }

    }

    /**
     * 操作日期
     */
    public String getAdate() {
        return adate;
    }

    /**
     * 设置操作日期
     */
    public void setAdate(String adate) {
        this.adate = adate;
    }

    /**
     * 获取同步表
     * @return
     */
    public FormulaTableData getSyncTable() {
        return syncTable;
    }

    /**
     * 设置同步表
     * @param syncTable
     */
    public void setSyncTable(FormulaTableData syncTable) {
        this.syncTable = syncTable;
    }

    /**
     * 获取查询的第一张表Pid
     * @return
     */
    public String getTablePid() {
        return tablePid;
    }

    /**
     * 设置查询第一张表Pid
     * @param tablePid
     */
    public void setTablePid(String tablePid) {
        this.tablePid = tablePid;
    }

    /**
     * 查询结果列说明
     * @return
     */
    public ColumnInfo[] getColumnInfos() {
        return columnInfos;
    }

    /**
     * 设置列说明
     * @param columnInfos
     */
    public void setColumnInfos(ColumnInfo[] columnInfos) {
        this.columnInfos = columnInfos;
    }
}
