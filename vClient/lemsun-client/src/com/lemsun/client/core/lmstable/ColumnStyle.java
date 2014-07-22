package com.lemsun.client.core.lmstable;


/**
 * 列的样式属性
 * User: 宗旭东
 * Date: 13-6-4
 * Time: 下午5:36
 */
public class ColumnStyle {
    private int width;
    private int align;
    private Style style;
    private int index;

    /**
     * 获取列的位置
     */
    public int getIndex() {
        return index;
    }

    /**
     * 设置列的位置
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * 获取列的宽度
     */
    public int getWidth() {
        return width;
    }

    /**
     * 设置列的宽度
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * 获取列的位置
     */
    public int getAlign() {
        return align;
    }

    /**
     * 设置列的位置
     */
    public void setAlign(int align) {
        this.align = align;
    }

    /**
     * 获取列的扩展样式
     */
    public Style getStyle() {
        return style;
    }

    /**
     * 设置列的扩展样式
     */
    public void setStyle(Style style) {
        this.style = style;
    }
}
