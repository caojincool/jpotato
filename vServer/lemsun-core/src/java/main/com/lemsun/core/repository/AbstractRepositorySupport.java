package com.lemsun.core.repository;

import com.lemsun.core.Host;
import com.lemsun.core.IResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

import java.lang.reflect.ParameterizedType;
import java.util.Date;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * 组件的数据库抽象类,提供对组件常规的数据库操作
 * 例如:增加,删除,修改
 * User: Xudong
 * Date: 12-10-25
 * Time: 下午1:16
 */
public abstract class AbstractRepositorySupport<Resource extends IResource> extends AbstractLocalFsRepository {


	private Class<Resource> resourceClass;
    private ResourceRepository resourceRepository;



    protected AbstractRepositorySupport(MongoTemplate template,
                                        GridFsOperations gridFsOperations,
                                        ResourceRepository resourceRepository) {

        super(template, gridFsOperations);

        this.resourceRepository = resourceRepository;
        resourceClass = (Class<Resource>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 获取基本组件操作的对象
     */
    protected ResourceRepository getResourceRepository() {
        return resourceRepository;
    }

    /**
     * 创建一个组件
     */
	public void create(Resource resource)
	{
        getResourceRepository().create(resource);
	}

    /**
     * 移除一个组件
     */
	public void remove(Resource resource)
	{
        getResourceRepository().remove(resource);
	}

    /**
     * 移除一个组件
     */
    public void remove(String pid) {
        getResourceRepository().remove(pid);
    }

    /**
     * 根据组件Id获取组件
     */
	public Resource get(String pid)
	{
		return getResourceRepository().get(pid, resourceClass);
	}

    /**
     * 将给定的组件更新到数据库中
     */
    public void update(Resource resource)
    {
        getResourceRepository().update(resource);
    }
}
