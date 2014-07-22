package com.lemsun.web.manager.controller.model.query;

import com.lemsun.web.model.ExtPageRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created with IntelliJ IDEA.
 * User: lmy
 * Date: 13-8-21
 * Time: 上午8:59
 */
public class RoleQuery extends ExtPageRequest {
    private String name;
    private String describe;
    private String[] names;

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public Query createQuery() {
        Query query = super.createQuery();

        if(names!=null){
            query.addCriteria(Criteria.where("name").nin(getNames()));
        }
        if(StringUtils.isNotEmpty(getName())) {
            query.addCriteria(Criteria.where("name").regex(".*" + getName() + ".*"));
        }
        if(StringUtils.isNotEmpty(getDescribe())) {
            query.addCriteria(Criteria.where("describe").regex(".*" + getDescribe() + ".*"));
        }
        return query;
    }
}
