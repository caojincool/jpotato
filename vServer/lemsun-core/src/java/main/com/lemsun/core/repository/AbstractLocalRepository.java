package com.lemsun.core.repository;

import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * 组件抽象类,目前提供数据库操作对象
 * User: Xudong
 * Date: 12-10-10
 * Time: 下午2:29
 */
public abstract class AbstractLocalRepository implements IRepository {

	private MongoTemplate template;

	public AbstractLocalRepository(MongoTemplate template) {
		this.template = template;
	}


	public MongoTemplate getTemplate() {
		return template;
	}
}
