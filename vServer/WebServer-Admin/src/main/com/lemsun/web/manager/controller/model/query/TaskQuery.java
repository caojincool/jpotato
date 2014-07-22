package com.lemsun.web.manager.controller.model.query;

import com.lemsun.core.BaseCategory;
import org.springframework.data.mongodb.core.query.Query;

/**
 * User: 刘晓宝
 * Date: 14-3-19
 * Time: 下午2:09
 */
public class TaskQuery extends ComponentQuery{
    @Override
    public Query createQuery() {
        super.setCategory(BaseCategory.TASK.getCategory());
        Query query = super.createQuery();
        return query;
    }
}
