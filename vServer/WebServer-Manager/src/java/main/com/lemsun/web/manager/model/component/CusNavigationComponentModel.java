package com.lemsun.web.manager.model.component;

import com.lemsun.core.NavigationComponent;
import com.lemsun.web.model.ExtPageRequest;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-9
 * Time: 下午5:24
 */
public class CusNavigationComponentModel  extends ExtPageRequest {

	private String id;
	private String name;
	private String category;
	private String createUser;
	private Date updateTime;
	private String resourcePid;
	private String parentid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getResourcePid() {
		return resourcePid;
	}

	public void setResourcePid(String resourcePid) {
		this.resourcePid = resourcePid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	@Override
	public Query createQuery() {

//		if(StringUtils.isEmpty(getParentid()))

        ObjectId ob= new ObjectId(getParentid());
		Criteria w = where("parent.$id").is(ob);

		return query(w).limit(getPageSize()).skip(getOffset());
	}
}
