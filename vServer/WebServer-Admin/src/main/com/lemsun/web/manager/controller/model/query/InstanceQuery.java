package com.lemsun.web.manager.controller.model.query;


import com.lemsun.web.model.ExtPageRequest;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;



/**
 * 系统实例分页
 * User: gm
 * Date: 13-1-25
 * Time: 下午5:34
 */
public class InstanceQuery extends ExtPageRequest {

	private String name;
	private ObjectId id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @Override
	public Query createQuery() {
        Query query = super.createQuery();
        if(StringUtils.isNotEmpty(getName())) {
            query.addCriteria(Criteria.where("name").regex(".*" + getName() + ".*"));
        }
        if(getId()!=null) {
            query.addCriteria(Criteria.where("owner.$id").is( getId()));
        }
		return query;
	}
}
