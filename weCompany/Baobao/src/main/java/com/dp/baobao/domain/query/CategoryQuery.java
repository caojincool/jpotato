package com.dp.baobao.domain.query;

import com.dp.baobao.domain.AbstractQuery;

/**
 * Created by dpyang on 2015/1/26.
 */
public class CategoryQuery extends AbstractQuery {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
