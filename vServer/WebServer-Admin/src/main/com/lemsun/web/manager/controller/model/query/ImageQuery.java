package com.lemsun.web.manager.controller.model.query;

import com.lemsun.core.BaseCategory;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created with IntelliJ IDEA.
 * User: lmy
 * Date: 13-8-29
 * Time: 上午10:30
 * To change this template use File | Settings | File Templates.
 */
public class ImageQuery extends ComponentQuery{
    private String view="1";//页面展示视图

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    @Override
    public Query createQuery() {
        super.setCategory(BaseCategory.IMAGE.getCategory());
        Query query = super.createQuery();
        query.fields().include("imageName")
                .include("imageSize");
        return query;
    }
}
