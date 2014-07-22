package com.lemsun.data.lmstable;

/**
 * 单元格区域
 * User: 宗旭东
 * Date: 13-6-4
 * Time: 下午5:38
 */
public class CellRange extends Cell {
    private int rowCount;
    private int colCount;

    /**
     * 获取单元格的行跨度数
     * @return
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * 设置单元格的行跨度数据
     * @param rowCount
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * 获取列的跨度数
     * @return
     */
    public int getColCount() {
        return colCount;
    }

    /**
     * 设置列的跨度数
     * @param colCount
     */
    public void setColCount(int colCount) {
        this.colCount = colCount;
    }
}
