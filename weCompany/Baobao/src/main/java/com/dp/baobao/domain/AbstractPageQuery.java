package com.dp.baobao.domain;

/**
 * 分页查询基础对象，如果有查询分页的请继承这个类
 * Created by dpyang on 2015/1/26.
 */
public class AbstractPageQuery implements IQuery{
    private String id;
    private int page,rows;
    private String sort;
    private String order;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPage() {
        return page<3?0:page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows<3?3:rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
