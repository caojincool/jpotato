package com.lemsun.web.manager.controller.model.component;

import com.lemsun.data.lmstable.Column;

import java.util.List;

/**
 * User: 刘晓宝
 * Date: 14-1-8
 * Time: 下午4:04
 */
public class TableGroup5 {
    private String pid;
    private int cate;
    private List<Column> cols;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getCate() {
        return cate;
    }

    public void setCate(int cate) {
        this.cate = cate;
    }

    public List<Column> getCols() {
        return cols;
    }

    public void setCols(List<Column> cols) {
        this.cols = cols;
    }
}
