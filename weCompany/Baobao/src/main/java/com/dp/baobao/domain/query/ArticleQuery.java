package com.dp.baobao.domain.query;

import com.dp.baobao.domain.AbstractPageQuery;

/**
 * 文章查询对象
 * Created by dpyang on 2015/1/26.
 */
public class ArticleQuery extends AbstractPageQuery {
    private String code;
    private boolean isEnabled;
    private String categoryId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
