package com.lemsun.data.lmstable;

/**
 * 表格的单元格对象, 并带有样式信息
 *
 * User: 宗旭东
 * Date: 13-6-4
 * Time: 下午5:38
 */
public class Cell {

    private int row;
    private int col;
    private Style style;
    private Object value;

    /**
     * 获取单元格的值
     * @return
     */
    public Object getValue() {
        return value;
    }

    /**
     * 设置单元格的值
     * @param value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * 获取所在的行
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * 设置所在的行
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * 获取列
     * @return
     */
    public int getCol() {
        return col;
    }

    /**
     * 设置列
     * @param col
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * 获取单元格样式
     * @return
     */
    public Style getStyle() {
        return style;
    }

    /**
     * 设置单元格样式
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
    }
}
