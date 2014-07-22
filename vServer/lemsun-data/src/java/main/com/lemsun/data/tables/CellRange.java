package com.lemsun.data.tables;

import org.springframework.data.annotation.PersistenceConstructor;

/**
 * 单元区域
 * User: Xudong
 * Date: 12-11-29
 * Time: 下午5:03
 */
public class CellRange {

    protected int rowCount;
    protected int colCount;
    protected int row;
    protected int col;

    @PersistenceConstructor
    public CellRange(int row, int col, int rowCount, int colCount) {
        this.row = row;
        this.col = col;
        this.rowCount = rowCount;
        this.colCount = colCount;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }


    /**
     * 判断是否初与同列中
     */
    public boolean equalsCol(CellRange range) {
        return col == range.getCol() && colCount == range.getColCount();
    }

    /**
     * 判断是否初与同行中
     */
    public boolean equalsRow(CellRange range) {
        return row == range.getRow() && rowCount == range.getRowCount();
    }

    /**
     * 判断当前的区域是否有合并单元格
     */
    public boolean isSpanRange() {
        return colCount > 1 || rowCount > 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CellRange cellRange = (CellRange) o;

        return equalsCol(cellRange) && equalsRow(cellRange);
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        result = 31 * result + rowCount;
        result = 31 * result + colCount;
        return result;
    }

}
