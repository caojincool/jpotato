package com.lemsun.web.manager.controller.model.query;


import com.lemsun.core.BaseCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created with IntelliJ IDEA.
 * User: lmy
 * Date: 13-8-29
 * Time: 上午9:23
 * To change this template use File | Settings | File Templates.
 */
public class WebSkinQuery extends ComponentQuery {

    @Override
    public Query createQuery() {
        super.setCategory(BaseCategory.WEB_SKIN.getCategory());
        Query query = super.createQuery();
        query.fields().include("contextType")
                .include("cache")
                .include("cacheTime")
                .include("page");
        return query;
    }
}
