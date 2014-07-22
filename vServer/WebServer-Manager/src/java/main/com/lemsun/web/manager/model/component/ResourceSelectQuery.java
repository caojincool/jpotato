package com.lemsun.web.manager.model.component;

import com.lemsun.web.model.ExtPageRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-12-11
 * Time: 下午2:57
 * To change this template use File | Settings | File Templates.
 */
public class ResourceSelectQuery extends ExtPageRequest {
    private String name;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
