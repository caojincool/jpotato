package com.lemsun.web.model;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Xudong
 * Date: 13-1-6
 * Time: 下午2:03
 * 界面上, 头部信息的导航头
 */
public class HeaderTitle {

    /**
     * 从request 中获取表头对象的主键
     */
    public static final String RequestKey = "headerTitle";

    private ArrayList<Header> headers = new ArrayList<>();

    private Header first;
    private boolean disable = true;

    public HeaderTitle() {
        first = new Header(this);
        first.setCurrent(true);
        first.setName("");
        headers.add(first);
    }

    /**
     * 获取表头节点
     */
    public ArrayList<Header> getHeaders() {
        return headers;
    }

    /**
     * 获取当前的视图中是不是没有选择的节点
     */
    public boolean isNotSelected() {
        for (Header h : headers) {
            if(h.isCurrent()) return true;
        }
        return false;
    }


    public Header getFirst() {
        return first;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
