package com.lemsun.core.query;

import com.lemsun.core.AbstractPageRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created with IntelliJ IDEA.
 * User: Xudong
 * Date: 13-1-10
 * Time: 上午10:55
 * 基本组件分页列表查询模型
 */
public class LemsunResourceQuery extends AbstractPageRequest {

    private String category;
    private String name;

    /**
     * 获取查询的所属类别
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置查询的所属类别
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取查询名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置查询组件的名称
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Query createQuery() {
        Query query = super.createQuery();

		if(StringUtils.isNotEmpty(getCategory())) {
			//表示类型不为ROOT根节点，才加上这个类型查询条件
		    if(!getCategory().equals("ROOT")){
				query.addCriteria(Criteria.where("category").is(getCategory()));
			}
		}

		if(StringUtils.isNotEmpty(getName())) {
			query.addCriteria(Criteria.where("name").regex(".*" + getName() + ".*"));
		}

		return query;
    }
}
