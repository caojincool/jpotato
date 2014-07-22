/**
 * 5代网页端表处理包
 */
package com.lemsun.client.core.lmstable;

import java.util.List;

/**
 * User: 宗旭东
 * Date: 13-10-25
 * Time: 下午4:21
 */
public class TableFace {

    private int headerRow;
    private int headerColumn;
    private boolean showHeader = true;
    private boolean showRowHeader = true;
    private List<ColumnStyle> headerColumns;
    private List<CellRange> headerSpans;
    private List<Cell> headerCells;
    private List<CellRange> spans;
    private List<Cell> styleCells;
    private List<RowStyle> dataRows;
    private Style headerDefaultStyle;
    private Style dataDefaultStyle;

    public int getHeaderRow() {
        return headerRow;
    }

    public void setHeaderRow(int headerRow) {
        this.headerRow = headerRow;
    }

    public int getHeaderColumn() {
        return headerColumn;
    }

    public void setHeaderColumn(int headerColumn) {
        this.headerColumn = headerColumn;
    }

    public boolean isShowHeader() {
        return showHeader;
    }

    public void setShowHeader(boolean showHeader) {
        this.showHeader = showHeader;
    }

    public boolean isShowRowHeader() {
        return showRowHeader;
    }

    public void setShowRowHeader(boolean showRowHeader) {
        this.showRowHeader = showRowHeader;
    }

    public List<ColumnStyle> getHeaderColumns() {
        return headerColumns;
    }

    public void setHeaderColumns(List<ColumnStyle> headerColumns) {
        this.headerColumns = headerColumns;
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

    public List<RowStyle> getDataRows() {
        return dataRows;
    }

    public void setDataRows(List<RowStyle> dataRows) {
        this.dataRows = dataRows;
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
}
