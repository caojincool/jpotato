package com.lemsun.auth.service.impl.model;

import com.lemsun.core.AbstractPageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * 角色过滤查询模型
 * User: dpyang
 * Date: 13-9-12
 * Time: 上午9:56
 */
public class RoleQuery extends AbstractPageRequest{
    private List<String> names;
    private String[] ab;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public String[] getAb() {
        return ab;
    }

    public void setAb(String[] ab) {
        this.ab = ab;
    }

    @Override
    public Query createQuery() {
        Query query=new Query();

        query.addCriteria(Criteria.where("name").nin(ab));

        return query;
    }

}
