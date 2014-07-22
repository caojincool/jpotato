package com.lemsun.data.respository;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.repository.AbstractLocalRepository;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.data.connection.DbConfigResource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * 操作本地数据库配置信息
 * User: Xudong
 * Date: 12-9-20
 * Time: 上午10:42
 */
@Repository
public class DbConfigRespository extends AbstractLocalRepository {

	private ResourceRepository resourceRepository;
    private static final Logger log = LoggerFactory.getLogger(DbConfigRespository.class);
	@Autowired
	public DbConfigRespository(ResourceRepository resourceRepository, MongoTemplate template)
	{
		super(template);
		this.resourceRepository = resourceRepository;
	}


	/**
	 * 保存一个数据库配置对象
	 * @param dbConfigResource 数据库配置
	 */
	public void save(DbConfigResource dbConfigResource) throws Exception {
		resourceRepository.create(dbConfigResource);
	}


	/**
	 * 返回全部的数据库配置
	 * @return 数据库配置
	 */
	public List<DbConfigResource> getAll() {
		return resourceRepository.getAll(DbConfigResource.class);
	}

    public DbConfigResource getDbconfigResource(String pid)
    {
          return resourceRepository.get(pid,DbConfigResource.class);
    }

	public void update(DbConfigResource configResource) throws Exception {
        if(log.isDebugEnabled())log.debug("修改数据库资源");
        if(StringUtils.isEmpty(configResource.getServer()))
            throw new Exception("服务器地址不能为空");
        if(configResource.getDbCategory()==null)
            throw new Exception("数据库类型不能为空");
        if(configResource.getMaxActive()<=0)
            throw new Exception("最大连接数必须大于0");
        if(configResource.getMaxIdea()<0||configResource.getMaxWait()<0)
            throw new Exception("最小连接数和等待时长不能小于0");
        resourceRepository.update(configResource);
	}

    /**
     * 测试数据库连接资源是否能够正常连接,
     * @param resource 被测试的组件
     * @return true 能够连接, false 不能连接
     */
    public boolean testDbConfigResource(DbConfigResource resource) {
        String connStr = resource.getConnStr();

        try {
            Connection conn = DriverManager.getConnection(connStr);
            conn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public void remove(DbConfigResource dbConfigResource){
        resourceRepository.remove(dbConfigResource.getPid());
    }
    public Page<DbConfigResource> getPageing(AbstractPageRequest query){
        return resourceRepository.getPagging(query,DbConfigResource.class);
    }
}
