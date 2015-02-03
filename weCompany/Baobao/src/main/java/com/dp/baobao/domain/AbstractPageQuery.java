package com.dp.baobao.domain;

/**
 * Created by dpyang on 2015/1/26.
 */
public class AbstractPageQuery implements IQuery{
    private String id;
    private int offset,rows;
    private String sortField;
    private String order;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOffset() {
        return offset<3?0:offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getRows() {
        return rows<3?3:rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
