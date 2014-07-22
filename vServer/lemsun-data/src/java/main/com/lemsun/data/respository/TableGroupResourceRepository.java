package com.lemsun.data.respository;

import com.lemsun.core.repository.AbstractLocalRepository;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.data.tables.TableGroupResource;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * 集合类表格的操作层
 * User: Xudong
 * Date: 12-11-24
 * Time: 下午3:57
 */
@Repository
@Deprecated
public class TableGroupResourceRepository extends AbstractLocalRepository {

	private ResourceRepository resourceRepository;

	@Autowired
	public TableGroupResourceRepository(ResourceRepository resourceRepository, MongoTemplate template) {
		super(template);
		this.resourceRepository = resourceRepository;
	}


	public void save(TableGroupResource resource) throws Exception {
		resourceRepository.create(resource);
	}

    public TableGroupResource getTableGroupResourceByPid(String pid)
    {
        return resourceRepository.get(pid,TableGroupResource.class);
    }


	/**
	 * 获取基本的组件操作对象
	 * @return 操作对象
	 */
	public ResourceRepository getResourceRepository() {
		return resourceRepository;
	}

	/**
	 * 移除表格组资源
	 * @param pid 主键
	 */
	public void remove(String pid) {
		resourceRepository.remove(pid);
	}

    /**
     * 根据CODE得到4代表组
     * @param code
     * @return  4代表组
     */
    public TableGroupResource getFourByCode(ObjectId objectId, String code)
    {


        return getTemplate().findOne(query(where("code").is(code).and("version").is(4).and("dbConfig.$id").is(objectId)), TableGroupResource.class, ResourceRepository.ResourceCollectionName);
    }

}
