package com.lemsun.data.lmstable;

import com.lemsun.core.SpringContextUtil;
import com.lemsun.core.jackson.JsonObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * 五代的皮肤信息
 * User: 宗旭东
 * Date: 13-6-4
 * Time: 下午5:27
 */
public class TableFace {

    private int headerRow;
    private int headerColumn;
    private List<ColumnStyle> headerColumnStyles;
    private List<RowStyle> headerRowStyles;
    private List<CellRange> headerSpans;
    private List<Cell> headerCells;
    private List<CellRange> spans;
    private List<Cell> styleCells;
    private List<RowStyle> rows;
    private Style headerDefaultStyle;
    private Style dataDefaultStyle;
    private boolean showHeader = true;
    private boolean showRowHeader = true;

    /**
     * 获取是否显示行表头
     */
    public boolean isShowRowHeader() {
        return showRowHeader;
    }

    /**
     * 设置是否显示行表头
     */
    public void setShowRowHeader(boolean showRowHeader) {
        this.showRowHeader = showRowHeader;
    }

    /**
     * 是否显示表头
     */
    public boolean isShowHeader() {
        return showHeader;
    }

    /**
     * 设置是否显示表头
     */
    public void setShowHeader(boolean showHeader) {
        this.showHeader = showHeader;
    }

    /**
     * 获取表头行
     *
     * @return
     */
    public int getHeaderRow() {
        return headerRow;
    }

    /**
     * 设置表头行
     *
     * @param headerRow
     */
    public void setHeaderRow(int headerRow) {
        this.headerRow = headerRow;
    }

    public int getHeaderColumn() {
        return headerColumn;
    }

    public void setHeaderColumn(int headerColumn) {
        this.headerColumn = headerColumn;
    }

    public List<ColumnStyle> getHeaderColumnStyles() {
        return headerColumnStyles;
    }

    public void setHeaderColumnStyles(List<ColumnStyle> headerColumnStyles) {
        this.headerColumnStyles = headerColumnStyles;
    }

    public List<RowStyle> getHeaderRowStyles() {
        return headerRowStyles;
    }

    public void setHeaderRowStyles(List<RowStyle> headerRowStyles) {
        this.headerRowStyles = headerRowStyles;
    }

    public List<CellRange> getHeaderSpans() {
        return headerSpans;
    }

    public void setHeaderSpans(List<CellRange> headerSpans) {
        this.headerSpans = headerSpans;
    }

    public List<Cell> getHeaderCells() {
        return headerCells;
    }

    public void setHeaderCells(List<Cell> headerCells) {
        this.headerCells = headerCells;
    }

    public List<CellRange> getSpans() {
        return spans;
    }

    public void setSpans(List<CellRange> spans) {
        this.spans = spans;
    }

    public List<Cell> getStyleCells() {
        return styleCells;
    }

    public void setStyleCells(List<Cell> styleCells) {
        this.styleCells = styleCells;
    }

    public List<RowStyle> getRows() {
        return rows;
    }

    public void setRows(List<RowStyle> rows) {
        this.rows = rows;
    }

    public Style getHeaderDefaultStyle() {
        return headerDefaultStyle;
    }

    public void setHeaderDefaultStyle(Style headerDefaultStyle) {
        this.headerDefaultStyle = headerDefaultStyle;
    }

    public Style getDataDefaultStyle() {
        return dataDefaultStyle;
    }

    public void setDataDefaultStyle(Style dataDefaultStyle) {
        this.dataDefaultStyle = dataDefaultStyle;
    }

    /**
     * 将皮肤设置信息转换成JSON数据
     */
    public String toJson() {
        JsonObjectMapper mapper = SpringContextUtil.getBean(JsonObjectMapper.class);

        try {
            return mapper.writeValueAsString(this);
        } catch (IOException e) {
            try {
                return "{error: " + mapper.writeValueAsString(e.getMessage()) + " }";
            } catch (IOException e1) {

            }

            return null;
        }
    }
}
