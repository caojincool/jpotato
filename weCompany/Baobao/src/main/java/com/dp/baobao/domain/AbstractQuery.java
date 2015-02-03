package com.dp.baobao.domain;

/**
 * Created by dpyang on 2015/1/24.
 */
public abstract class AbstractQuery implements IQuery{
    private String id;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
