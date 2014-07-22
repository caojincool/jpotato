package com.lemsun.web.manager.controller.model.query;

import com.lemsun.core.BaseCategory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.text.ParseException;
import java.util.Date;

/**
 * User: 刘晓宝
 * Date: 14-3-19
 * Time: 下午2:09
 */
public class TaskMutiQuery extends ComponentQuery{
    private String createTime;
    private String executeTime;
    @Override
    public Query createQuery() {
        super.setCategory(BaseCategory.TASK.getCategory());
        Query query = super.createQuery();
        query.fields().include("state");
        if(StringUtils.isNotEmpty(executeTime)){
                query.addCriteria(Criteria.where("executeDate").is(executeTime));
        }
        return query;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }
}
