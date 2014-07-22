package com.lemsun.ldbc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lemsun.core.ArrayData;
import com.lemsun.core.LemsunException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

/**
 * 接收表格数据
 * User: Xudong
 * Date: 12-12-25
 * Time: 上午9:24
 *
 */
public class TableData extends ArrayData<TableRow> {

    private String adate;
    private int[] colTypes;

    private String tablePid;
    private final static String CODE="Code";
    private TableData syncTable;
    

  
    /**
     * 获取表数据对应的表组件的PID
     */
    public String getTablePid() {
        return tablePid;
    }

    /**
     * 设置表组件对应的 pid
     */
    public void setTablePid(String tablePid) {
        this.tablePid = tablePid;
    }

    /**
     * 获取当前的同步数据表
     */
    public TableData getSyncTable() {
        return syncTable;
    }

    /**
     * 设置当前的同步数据表
     */
    public void setSyncTable(TableData syncTable) {
        this.syncTable = syncTable;
    }

    /**
     * 获取数据列的类型数组
     */
    public int[] getColTypes() {
        return colTypes;
    }

    /**
     * 设置表格列的类型数组
     */
    public void setColTypes(int[] colTypes) {
        this.colTypes = colTypes;
    }

    /**
     * 获取当前数据的操作日期
     */
    public String getAdate() {
        return adate;
    }

    /**
     * 设置当前数据的操作日期
     */
    public void setAdate(String adate) {
        this.adate = adate;
    }


    public void setCols(String[] cols, int[] types) {

        if(cols == null || types == null || types.length != cols.length) {
            throw new LemsunException("数据表设置的栏目跟类型栏目不对应");
        }

        setCols(cols);
        setColTypes(types);

    }

    /**
     * 设置表格数据
     */
    @Override
    @JsonProperty
    public void setData(List<TableRow> data) {
        super.setData(data);
        if(data != null) {
            for(TableRow row : data) {
                row.setTable(this);
            }
        }
    }


    /**
     * 将数据查询集合设置到当前的数据表中
     * @param rs
     */
    public void setData(ResultSet rs) {

        try
        {
            ResultSetMetaData metaData = rs.getMetaData();

            String[] cols = getCols();
            ResultSetMapping[] mappings = new ResultSetMapping[cols.length+1];


            for(int i = 1; i <= metaData.getColumnCount(); i++) {

                String name = metaData.getColumnName(i);

                int index = ArrayUtils.indexOf(cols, name);

                if(index >= 0) {

                    mappings[index] = new ResultSetMapping(i, name);
                }

            }

            List<TableRow> data = new ArrayList<>();
            while (rs.next()) {

                TableRow row = new TableRow();

                for(ResultSetMapping m : mappings) {
                    row.add(m != null ? rs.getObject(m.getIndex()) : null);
                }

                data.add(row);
            }


            setData(data);
        }
        catch (Exception ex) {

        }


    }


    private int statusIndex = -1;


    /**
     * 获取当前的表格跟同步表格是否是一样的表格组件
     */
    @JsonIgnore
    public boolean isSameTable()
    {
        return getSyncTable() != null && StringUtils.equals(getTablePid(), getSyncTable().getTablePid());
    }

    /**
     * 如果有同步表格, 那么返回是否是同一个表格
     */
    @JsonIgnore
    public boolean isDifferentTable() {
        return getSyncTable() != null && !StringUtils.equals(getTablePid(), getSyncTable().getTablePid());
    }

    /**
     * 获取当前数据的状态列
     */
    @JsonIgnore
    public int getStatusIndex() {
        if(statusIndex == -1 && getCols() != null) {
            statusIndex = ArrayUtils.indexOf(getCols(), RowStatus.ColumnName);
        }
        return statusIndex;
    }


    /**
     * 将当前的同步表格结合成整表格
     */
    public void joinSycnTable()
    {
        if(!isSameTable()) return;

        TableData syncData = getSyncTable();

        if(CollectionUtils.isEmpty(getData()) || CollectionUtils.isEmpty(syncData.getData())) return;

        ArrayList<SyncColMapping> colMappings = new ArrayList<>();

        //查找不存在当前表格中的列
        for(int i=0; i<syncData.getCols().length; i++) {

            String col = syncData.getCols()[i];

            if(ArrayUtils.indexOf(getCols(), col) == -1) {

                SyncColMapping m = new SyncColMapping(i, col, syncData.getColTypes() != null ? syncData.getColTypes()[i] : -1);
                colMappings.add(m);
            }

        }

        //缓存同步行
        int syncCodeIndex = syncData.getCodeIndex();//在同步表中得到code所在列位
        Map<Object,TableRow> cacheSyncTableRow=new HashMap<>();//根据code缓存同步行


        for(TableRow r : syncData.getData()) {
            cacheSyncTableRow.put(r.get(syncCodeIndex), r);
        }

        int codeIndex = getCodeIndex();
        //合并当前行
        for(TableRow r : getData()) {
            Object code = r.get(codeIndex);

            if(cacheSyncTableRow.containsKey(code)) {
                TableRow syncRow = cacheSyncTableRow.get(code);
                for(SyncColMapping m : colMappings) {
                    r.add(syncRow.get(m.getSyncIndex()));
                }
            }

        }

        //获取修改后的同步表信息
        String[] syncCols = new String[colMappings.size()];
        int[] syncColTypes = new int[colMappings.size()];

        for(int i=0; i<colMappings.size();i++) {
            SyncColMapping m = colMappings.get(i);
            syncCols[i] = m.getCol();
            syncColTypes[i] = m.getColType();
        }

        setCols(ArrayUtils.addAll(getCols(), syncCols));

        if(getColTypes() != null) {
            setColTypes(ArrayUtils.addAll(getColTypes(), syncColTypes));
        }

    }

    private int codeIndex = -1;

    @JsonIgnore
    public int getCodeIndex() {
        if(codeIndex == -1) {
            codeIndex=ArrayUtils.indexOf(this.getCols(),CODE);

        }
        return codeIndex;
    }
    @JsonIgnore
   public int getCodeType(){
      return this.getColTypes()[this.getCodeIndex()];
   }

    @Override
    public String toString() {
        return "ArrayData{" +
                "data=" + getData() +
                ", cols=" + getCols() +
                '}';
    }


    //内部类用来对应ResultSet
    private class ResultSetMapping {
        private int index;
        private String name;

        private ResultSetMapping(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }
    }

    //用来合并同步表格
    private class SyncColMapping
    {
        private int syncIndex;
        private String col;
        private int colType;


        private SyncColMapping(int syncIndex, String col, int colType) {
            this.syncIndex = syncIndex;
            this.col = col;
            this.colType = colType;
        }


        public int getSyncIndex() {
            return syncIndex;
        }

        public String getCol() {
            return col;
        }

        public int getColType() {
            return colType;
        }
    }

}
