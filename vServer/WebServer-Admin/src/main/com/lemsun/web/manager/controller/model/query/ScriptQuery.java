package com.lemsun.web.manager.controller.model.query;

import com.lemsun.core.BaseCategory;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created with IntelliJ IDEA.
 * User: lmy
 * Date: 13-8-29
 * Time: 上午10:14
 * To change this template use File | Settings | File Templates.
 */
public class ScriptQuery extends ComponentQuery {
    @Override
    public Query createQuery() {
        super.setCategory(BaseCategory.SCRIPT.getCategory());
        Query query = super.createQuery();
        query.fields().include("context");
        return query;
    }
}
