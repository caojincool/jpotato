package com.lemsun.helper;

import com.lemsun.web.model.ExtPageRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;

/**
 * User: 刘晓宝
 * Date: 13-12-3
 * Time: 上午8:55
 */
public class HelpQuery extends ExtPageRequest {
    private String search;
    private String[] category;


    @Override
    public Query createQuery() {
        Query query=super.createQuery();
        if(category!=null&&category.length>0){
            query.addCriteria(Criteria.where("category").in(category));
        }
        if(StringUtils.isNotEmpty(search)){
            query.addCriteria(new Criteria().
                    orOperator(
                            new Criteria("pid").regex(".*" + search + ".*"),
                            new Criteria("businessCode").regex(".*" + search + ".*"),
                            new Criteria("name").regex(".*" + search + ".*"),
                            new Criteria("remark").regex(".*" + search + ".*")
                    )
            );


        }
        query.sort().on("updateTime", Order.DESCENDING);
        return query;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String[] getCategory() {
        return category;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }
}
