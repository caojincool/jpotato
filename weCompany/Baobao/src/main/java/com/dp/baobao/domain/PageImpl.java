package com.dp.baobao.domain;

import java.util.List;

/**
 * Created by dpyang on 2015/1/26.
 */
public class PageImpl<T> implements Page<T> {

    private int total;
    private List<T> content;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    @Override
    public int getTotal() {
        return total;
    }

    @Override
    public List<T> getContent() {
        return content;
    }
}
