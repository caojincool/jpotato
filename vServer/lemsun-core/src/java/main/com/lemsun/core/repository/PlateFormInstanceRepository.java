package com.lemsun.core.repository;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.PlateformInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * 系统实例操作层
 * User: Administrator
 * Date: 12-12-1
 * Time: 上午9:16
 */
@Repository
public class PlateFormInstanceRepository {

	public static final String PlateformInstanceName = "PlateformInstance";
    private ResourcePrimaryRepository resourcePrimaryRepository;
    private MongoTemplate template;

	@Autowired
	public PlateFormInstanceRepository(ResourcePrimaryRepository resourcePrimaryRepository, MongoTemplate template) {
		this.resourcePrimaryRepository = resourcePrimaryRepository;
        this.template = template;
	}

    public void create(PlateformInstance resource) {

        resource.setId(resourcePrimaryRepository.generatorPlateform(resource));

        template.save(resource, PlateformInstanceName);
    }

	/**
	 * 更新系统实例资源
	 * @param resource 系统实例
	 * @throws Exception 更新异常
	 */
	public void update(PlateformInstance resource) throws Exception {
		if(resource.getId() == null)
			throw new Exception("更新不能没有主键");

        //resource.setId(resourcePrimaryRepository.generatorPlateform(resource));
		template.save(resource, PlateformInstanceName);
	}


    public void updateLogin(PlateformInstance resource) throws Exception {

        template.updateFirst(
                query(where("id").is(resource.getId())),
                Update.update("token", resource.getToken()),
                PlateformInstanceName);
    }

    /**
     * 使用票据获取平台实例
     * @param token 票据
     * @return 平台实例
     */
    public PlateformInstance getByToken(String token) {
        return template.findOne(query(where("token").is(token)), PlateformInstance.class);
    }

	/**
	 * 根据pid,删除此系统实例
	 * @param pid 要删除的系统实例的pid
	 */
	public void delete(String pid) throws Exception{
		if(StringUtils.isEmpty(pid))
			throw new Exception("pid为空，无法删除!");
		template.remove(template.findOne(query(where("id").is(pid)), PlateformInstance.class));
	}

	/**
	 * 获取所有系统实例集合
	 * @return  系统实例集合
	 */
	public List<PlateformInstance> getAllInstance()
	{
		return template.findAll(PlateformInstance.class, PlateformInstanceName);
	}

	/**
	 * 系统实例分页
	 * @param request 页面信息
	 * @return 返回组件
	 */
	public Page<PlateformInstance> getPagging(AbstractPageRequest request)
	{
		Query query = request.createQuery();

		long total = template.count(query, PlateformInstanceName);

		List<PlateformInstance> data = template.find(query, PlateformInstance.class, PlateformInstanceName);

		return new PageImpl<>(data, request, total);
	}


    public PlateformInstance get(String pid) {
        return template.findOne(query(where("id").is(pid)), PlateformInstance.class);
    }
}
