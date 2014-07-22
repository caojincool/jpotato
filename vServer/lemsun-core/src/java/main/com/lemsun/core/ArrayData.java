package com.lemsun.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.List;

/**
 * 基本的数组对象
 * User: 宗旭东
 * Date: 14-1-10
 * Time: 下午2:59
 */
public class ArrayData<RowType extends Collection>  {

    private String[] cols;
    private int colCount = -1;
    private int rowCount = -1;
    private List<RowType> data;

    public String[] getCols() {
        return cols;
    }

    public void setCols(String[] cols) {
        this.cols = cols;
        this.colCount = cols != null ? cols.length : 0;
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
     * 返回携带的数据
     */
    public List<RowType> getData() {
        return data;
    }

    /**
     * 设置数据
     * @param data 数据
     */
    @JsonProperty
    public void setData(List<RowType> data) {
        this.data = data;
        if(data != null) {
            this.rowCount = data.size();
        }
        else {
            this.rowCount = 0;
        }
    }
}
