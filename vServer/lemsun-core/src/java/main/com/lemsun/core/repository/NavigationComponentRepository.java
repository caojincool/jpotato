package com.lemsun.core.repository;

import com.lemsun.core.NavigationComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-7
 * Time: 下午3:30
 */
@Repository
public class NavigationComponentRepository extends AbstractLocalRepository  {

	private ResourcePrimaryRepository primaryRepository;

	public static final String navigationcomponentName = "NavigationComponent";

	@Autowired
	public NavigationComponentRepository(ResourcePrimaryRepository primaryRepository, MongoTemplate template)
	{
		super(template);
		this.primaryRepository = primaryRepository;
	}

	public void create(NavigationComponent navigationComponent)
	{
		getTemplate().save(navigationComponent);
	}

	public void update(NavigationComponent navigationComponent) throws Exception {
		if(navigationComponent.getId() == null)
			throw new Exception("更新不能没有主键");
		getTemplate().save(navigationComponent,navigationcomponentName);
	}

	public NavigationComponent getNavigationComponentBypid(String pid) throws Exception {
		if(pid == null || pid.equals(""))
			throw new Exception("更新不能没有主键");
		return getTemplate().findOne(query(where("pid").is(pid)), NavigationComponent.class);
	}
}
