package com.lemsun.client.core.lmstable;


/**
 * 表格的单元格计算公式
 * User: zongxudong
 * Date: 13-8-16
 * Time: 下午3:50
 *
 */
public class TableExpression {

    private int row;
    private int col;
    private String formula;


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableExpression that = (TableExpression) o;

        if (col != that.col) return false;
        if (row != that.row) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (Integer.toString(row) + "," + Integer.toString(row)).hashCode();
    }
}