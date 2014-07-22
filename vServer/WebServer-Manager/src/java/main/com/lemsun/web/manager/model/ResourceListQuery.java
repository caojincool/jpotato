package com.lemsun.web.manager.model;

import com.lemsun.web.model.ExtPageRequest;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zongxudong
 * Date: 12-10-26
 * Time: 上午10:30
 */
public class ResourceListQuery extends ExtPageRequest {
	private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
	private List<String> category;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}


}
