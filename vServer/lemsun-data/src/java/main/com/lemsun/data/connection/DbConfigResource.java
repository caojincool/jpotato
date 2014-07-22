package com.lemsun.data.connection;

import com.lemsun.core.AbstractLemsunResource;
import com.lemsun.core.BaseCategory;
import com.lemsun.core.ILoadChild;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.ldbc.DbCategory;
import com.lemsun.ldbc.IDbConnectionConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 数据库配置信息资源
 * User: Xudong
 * Date: 12-9-20
 * Time: 上午10:02
 */
@Document(collection = ResourceRepository.ResourceCollectionName)
public class DbConfigResource extends AbstractLemsunResource implements ILoadChild<AbstractDbResource>, IDbConnectionConfig {

	private String username = "";
	private String password = "";
	private int maxActive = 10;
	private int maxIdea = 5;
	private int maxWait = 5000;
	private boolean defaultDb = false;
	private DbCategory dbCategory = DbCategory.SQLSERVER;
    private String server;
    private String dbName;

	@Transient
	private List<AbstractDbResource> child;

    @PersistenceConstructor
	public DbConfigResource(String name) {
		super(name, BaseCategory.DB.getCategory());
	}

    public DbConfigResource(LemsunResource resource) {
        super(resource.getName(), resource.getCategory());
        setId(resource.getId());
        setPid(resource.getPid());
        setRemark(resource.getRemark());
        setStrParams(resource.getStrParams());
        setCreateUser(resource.getCreateUser());
        setUpdateTime(resource.getUpdateTime());
        setBusinessCode(resource.getBusinessCode());
    }

	/**
	 * 获取数据库连接字符串
	 */
	public String getConnStr() {

        String connStr = "";
        if(getDbCategory() == DbCategory.SQLSERVER) {
            connStr = "jdbc:sqlserver://" + getServer()
                    + (StringUtils.isEmpty(getUsername()) ? "" : ";username=" + getUsername())
                    + (StringUtils.isEmpty(getDbName()) ? "" : ";databaseName=" + getDbName())
                    + (StringUtils.isEmpty(getPassword()) ? "" : ";password=" + getPassword() + ";");
        }

		return connStr;
	}

	/**
	 *
	 * @return 用户登录名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 *
	 * @param username 登录名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 *
	 * @return 登录密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 *
	 * @param password 登录密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 *
	 * @return 最大启用连接
	 */
	public int getMaxActive() {
		return maxActive;
	}

	/**
	 *
	 * @param maxActive 最大连接
	 */
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	/**
	 *
	 * @return 最大等待连接时间
	 */
	public int getMaxIdea() {
		return maxIdea;
	}

	/**
	 *
	 * @param maxIdea 等待连接时间
	 */
	public void setMaxIdea(int maxIdea) {
		this.maxIdea = maxIdea;
	}

	/**
	 *
	 * @return 最大等待连接数
	 */
	public int getMaxWait() {
		return maxWait;
	}

	/**
	 *
	 * @param maxWait 最大等待连接数
	 */
	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}

	/**
	 *
	 * @return 是否是默认数据库
	 */
	public boolean isDefaultDb() {
		return defaultDb;
	}

	public void setDefaultDb(boolean defaultDb) {
		this.defaultDb = defaultDb;
	}

	/**
	 *
	 * @return 返回数据库类型
	 */
	public DbCategory getDbCategory() {
		return dbCategory;
	}

	/**
	 *
	 * @param dbCategory 数据类型
	 */
	public void setDbCategory(DbCategory dbCategory) {
		this.dbCategory = dbCategory;
	}


    /**
     * 获取服务器地址
     */
    public String getServer() {
        return server;
    }

    /**
     * 设置服务器地址
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * 获取数据库名称
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * 设置数据库名称
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public List<AbstractDbResource> getChild() {
		return child;
	}

	public void setChild(List<AbstractDbResource> child) {
		this.child = child;
	}

	@Override
	public void loadChild(Collection collection) {
		Assert.notNull(collection);

		List<AbstractDbResource> dbs = new ArrayList<>(collection.size());
		for(Object c : collection)
			if(c instanceof AbstractDbResource)
				dbs.add((AbstractDbResource)c);

		setChild(dbs);
	}
}
