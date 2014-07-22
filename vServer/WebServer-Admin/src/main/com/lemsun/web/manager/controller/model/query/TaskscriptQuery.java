package com.lemsun.web.manager.controller.model.query;

import com.lemsun.core.BaseCategory;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created with IntelliJ IDEA.
 * User: lmy
 * Date: 13-8-29
 * Time: 上午10:10
 */
public class TaskscriptQuery extends ComponentQuery{
    @Override
    public Query createQuery() {
        super.setCategory(BaseCategory.TASK_SCRIPT.getCategory());
        Query query = super.createQuery();
        query.fields().include("contextType")
                .include("cache")
                .include("cacheTime")
                .include("page");
        return query;
    }
}
