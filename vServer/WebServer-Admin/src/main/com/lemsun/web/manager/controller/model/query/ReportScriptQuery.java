package com.lemsun.web.manager.controller.model.query;

import com.lemsun.core.BaseCategory;
import org.springframework.data.mongodb.core.query.Query;

/**
 * 填报脚本组件查询模型
 * Created by dpyang on 2014/5/21.
 */
public class ReportScriptQuery extends ComponentQuery {

    @Override
    public Query createQuery() {
        super.setCategory(BaseCategory.REPORTER_SCRIPT.getCategory());
        Query query = super.createQuery();
        query.fields().include("showToolbar")
                .include("synchronismWindowSize")
                .include("openMode")
                .include("showLocation");
        return query;
    }
}
