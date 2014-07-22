package com.lemsun.data.lmstable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lemsun.core.LemsunException;
import com.lemsun.ldbc.TableData;

import java.util.List;

/**
 * 专门用来承载表格组件数据的模型, 并且会对应表格组件的同步字段生成子同步表格
 * Created by xudong on 13-12-18.
 */
public class TableResourceData extends TableData {

    private Table5Resource resource;

    /**
     * 构造一个表格对象
     * @param resource
     */
    public TableResourceData(Table5Resource resource) {
        if(resource == null) {
            throw new LemsunException("构造函数不能传入空值");
        }

        this.resource = resource;
        Init();
    }

    @JsonIgnore
    public Table5Resource getResource() {
        return resource;
    }

    protected void Init() {

        Table5Resource r = getResource();

        if(r.getCate() == TableCategory.FORM) {
            //单据表需要构造成父子结构
            List<Column> syncColumns = r.getSyncColumns();
            TableData syncType = new TableData();
            setTableColumns(syncType, syncColumns);

            List<Column> columns = r.getExcludeSyncColumn();
            setTableColumns(this, columns);
            setSyncTable(syncType);
        }
        else {
            //构造对应的表格结构
            List<Column> columns = r.getColumns();
            setTableColumns(this, columns);
        }


    }


    //设置传输数据的表结构
    private void setTableColumns(TableData table, List<Column> columns) {
        String[] cols = new String[columns.size()];
        int[] types = new int[columns.size()];


        for(int i=0; i<columns.size(); i++) {
            cols[i] = columns.get(i).getCol();
            types[i] = columns.get(i).getCategory();
        }

        table.setCols(cols, types);
    }

}
