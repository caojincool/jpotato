package com.lemsun.web.manager.controller.model.query;


import com.lemsun.web.model.ExtPageRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * User: Xudong
 * Date: 13-1-8
 * Time: 上午9:47
 * 基本的账号查询模型
 */
public class TaskLogQuery extends ExtPageRequest {

    private String pid;
    private String name;
    private int state;

    public TaskLogQuery() {
        if(getLimit()==0)
            setPageSize(25);
        if (getPage()==0)
            setPageNumber(1);
    }



    @Override
    public Query createQuery() {
        Query query = super.createQuery();
        if (StringUtils.isNotEmpty(getPid())){
            query.addCriteria(Criteria.where("pid").is(getPid()));
        }
        if (StringUtils.isNotEmpty(getName())){
                query.addCriteria(Criteria.where("taskName").regex(".*" + getName() + ".*"));
        }
        if (getState()>0){
            query.addCriteria(Criteria.where("state").is(getState()));
        }
        return query;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
