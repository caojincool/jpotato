package com.lemsun.web.manager.controller.model.query;

import com.lemsun.core.BaseCategory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * User: 刘晓宝
 * Date: 13-9-13
 * Time: 上午8:47
 */
public class TableGroup5Query  extends ComponentQuery{
    private int tableCategory=-1; //表类型参数
    public void setTableCategory (int tableCategory){
        this.tableCategory=tableCategory;
    }
    public int getTableCategory (){
        return tableCategory;
    }
    @Override
    public Query createQuery() {
        super.setCategory(BaseCategory.DBTABEL_GROUP_5.getCategory());
        Query query = super.createQuery();
        query.fields().include("code");
        query.fields().include("tableCategory");
        if(getTableCategory()>=0)
            query.addCriteria(Criteria.where("tableCategory").is(getTableCategory()));
        return query;
    }
}
