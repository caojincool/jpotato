package com.lemsun.web.manager.controller.model.query;

import com.lemsun.web.model.ExtPageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-8-27
 * Time: 上午10:59
 */
public class NavComponentQuery extends ExtPageRequest {
    private String navPid;


    public String getNavPid() {
        return navPid;
    }

    public void setNavPid(String navPid) {
        this.navPid = navPid;
    }

    @Override
    public Query createQuery() {
        Query query=super.createQuery();
        query.addCriteria(Criteria.where("navPid").is(getNavPid()));

        query.fields().include("id")
                .include("resourcePid")
                .include("navPid")
                .include("operator")
                .include("hitchTime");

        return query;
    }
}
