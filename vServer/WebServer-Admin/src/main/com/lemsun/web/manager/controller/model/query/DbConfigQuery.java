package com.lemsun.web.manager.controller.model.query;

import com.lemsun.core.BaseCategory;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created with IntelliJ IDEA.
 * User: lmy
 * Date: 13-8-29
 * Time: 上午10:40
 */
public class DbConfigQuery extends ComponentQuery{
    @Override
    public Query createQuery() {
        super.setCategory(BaseCategory.DB.getCategory());
        Query query = super.createQuery();
        query.fields().include("username")
                .include("password")
                .include("maxActive")
                .include("maxIdea")
                .include("maxWait")
                .include("defaultDb")
                .include("dbName")
                .include("dbCategory")
                .include("server");
        return query;
    }
}
