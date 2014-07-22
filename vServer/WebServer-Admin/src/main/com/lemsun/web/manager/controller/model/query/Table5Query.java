package com.lemsun.web.manager.controller.model.query;

import com.lemsun.core.BaseCategory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * User: 刘晓宝
 * Date: 13-9-14
 * Time: 上午10:11
 */
public class Table5Query extends ComponentQuery{

    private int cate;

    public int getCate() {
        return cate;
    }

    public void setCate(int cate) {
        this.cate = cate;
    }

    @Override
    public Query createQuery() {
        super.setCategory(BaseCategory.DBTABEL_5.getCategory());
        Query query = super.createQuery();
        query.fields().include("code");
        query.fields().include("cate");
        query.fields().include("reamrk");
        query.fields().include("dateTime");
        query.fields().include("enable");
        query.fields().include("enableTime");
        query.fields().include("createTime");
        query.fields().include("dbtable");
        if (getCate()>0)
            query.addCriteria(Criteria.where("cate").is(getCate()));

        return query;
    }


}
