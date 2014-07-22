package com.lemsun.client.core.lmstable;

/**
 * User: 宗旭东
 * Date: 13-10-25
 * Time: 下午4:23
 */
public class RowStyle {
    private int row;
    private String code;
    private int height;
    private Style style;

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
     * 获取所在的行的编码
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置所在行的编码
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取行高
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * 设置行高
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * 获取行的样式
     * @return
     */
    public Style getStyle() {
        return style;
    }

    /**
     * 设置行的样式
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
    }
}
