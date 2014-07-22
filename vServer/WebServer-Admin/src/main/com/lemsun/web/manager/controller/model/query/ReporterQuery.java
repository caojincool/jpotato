package com.lemsun.web.manager.controller.model.query;

import com.lemsun.core.BaseCategory;
import org.springframework.data.mongodb.core.query.Query;

/**
 * 填报界面查询界面
 * Created by 宗 on 2014/5/19 0019.
 */
public class ReporterQuery extends ComponentQuery {
    @Override
    public Query createQuery() {
        super.setCategory(BaseCategory.REPORTER.getCategory());
        Query query = super.createQuery();
        query.fields().include("showToolbar")
                .include("synchronismWindowSize")
                .include("openMode")
                .include("showLocation");
        return query;
    }
}
