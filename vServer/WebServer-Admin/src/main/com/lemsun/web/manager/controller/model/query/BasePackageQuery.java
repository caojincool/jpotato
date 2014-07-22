package com.lemsun.web.manager.controller.model.query;

import com.lemsun.web.model.ExtPageRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * 组件包基本查询条件模型
 * User: lmy
 * Date: 13-8-27
 * Time: 下午3:56
 */
public class BasePackageQuery  extends ExtPageRequest {
    /**
     * 组件包名称
     */
    private String name;
    private String lid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    @Override
    public Query createQuery() {
        Query query = super.createQuery();

        if(StringUtils.isNotEmpty(getLid())){
            query.addCriteria(Criteria.where("lid").regex(".*"+getLid()+".*"));
        }
        if(StringUtils.isNotEmpty(getName())) {
            query.addCriteria(Criteria.where("name").regex(".*" + getName() + ".*"));
        }

        return query;
    }
}
