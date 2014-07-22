package com.lemsun.data.connection;

import com.lemsun.core.LemsunException;
import com.lemsun.core.util.ResourceUtil;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * 数据库管理连接
 * User: Xudong
 * Date: 12-9-19
 * Time: 下午2:28
 */
public class DbManager {

    private final static Logger log = LoggerFactory.getLogger(DbManager.class);

    //private HashSet<String> dbNames = new HashSet<String>();
    private HashMap<String, String> dataMap = new HashMap<>();
    private HashMap<String, LemsunDataSource> pidMap = new HashMap<>();
    private HashMap<ObjectId, String> objMap = new HashMap<>();

    private String defaultName;

    public DbManager() {
        //reloadDb();
    }


    /**
     * 获取默认数据库的名称
     *
     * @return 名称
     */
    public String getDefaultName() {
        return defaultName;
    }

    /**
     * 设置默认数据库的名称
     *
     * @param defaultName 名称
     */
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    /**
     * 获取数据源的名称集合
     */
    public String[] getDbNames() {
        //return (String[]) dbNames.toArray();
        Set<String> strings = dataMap.keySet();
        return strings.toArray(new String[strings.size()]);
    }


    /**
     * 获取数据源
     *
     * @param pid 数据源名称
     * @return 数据源
     */
    public LemsunDataSource getDataSource(String pid) {
        if (StringUtils.isEmpty(pid))
            pid = defaultName;

        if (log.isDebugEnabled())
            log.debug("获取数据库配置 : {}", pid);

        if (!ResourceUtil.isRecourcePid(pid)) {
            if(log.isErrorEnabled())
            log.error("有个数据库的连接获取不是使用的PID, 请检查代码: " + pid);
        }


        //pid = ResourceUtil.isRecourcePid(pid) ? pid : dataMap.get(pid.toLowerCase());

        return getDataSourceByPid(pid);
    }

    /**
     * 使用数据库主键获取连接对象
     *
     * @param id 数据库主键
     * @return 连接对象
     */
    public LemsunDataSource getDataSource(ObjectId id) throws ConnectionException {

        if (id == null || !objMap.containsKey(id))
            throw ConnectionException.NotFindDatasource;
        String pid = objMap.get(id);
        return getDataSourceByPid(pid);
    }

    /**
     * 获取数据源配置信息
     *
     * @param pid 主键
     * @return 数据源
     */
    public LemsunDataSource getDataSourceByPid(String pid) {
        if (log.isDebugEnabled()) log.debug("获取数据库配置 : {}", pid);

        LemsunDataSource ds = pidMap.get(pid);

        return ds;
    }


    /**
     * 获取数据库连接
     *
     * @param pid 数据库主键
     * @return 连接对象
     * @throws java.sql.SQLException
     */
    @Deprecated
    public synchronized Connection getConnection(String pid) {

        Connection conn = null;
        try {
            conn = getDataSource(pid).getConnection();
        } catch (SQLException e) {
            throw new LemsunException("数据库不能打开对应的连接.");
        }

        return conn;
    }

    @Deprecated
    public Connection getConnection(DbConfigResource dbconfig) {
        return getConnection(dbconfig.getPid());
    }


    /**
     * 获取数据库执行的模板, 使用的是 Spring 模板
     *
     * @param name 数据库名称
     * @return 执行模板
     */
    public JdbcTemplate getSpringTemplate(String name) {
        return new JdbcTemplate(getDataSource(name));
    }

    /**
     * 增加一个数据库连接配置
     *
     * @param dbConfigResource 数据库配置
     */
    public void addDbConnection(DbConfigResource dbConfigResource) {
        LemsunDataSource ds = new LemsunDataSource(dbConfigResource.getPid(), dbConfigResource);
        ds.setUrl(dbConfigResource.getConnStr());

        ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ds.setUsername(dbConfigResource.getUsername());
        ds.setPassword(dbConfigResource.getPassword());
        ds.setMaxActive(dbConfigResource.getMaxActive());
        ds.setMaxIdle(dbConfigResource.getMaxIdea());
        ds.setMaxWait(dbConfigResource.getMaxWait());


        addDbConnection(dbConfigResource.getName(), ds);

        if (dbConfigResource.isDefaultDb()) defaultName = dbConfigResource.getName();
    }

    public void addDbConnection(String name, LemsunDataSource dataSource) {
        if (log.isDebugEnabled())
            log.debug("加载数据库: " + name + " " + dataSource);
        String pid = dataSource.getPid();
        dataMap.put(name.toLowerCase(), pid);
        pidMap.put(pid, dataSource);
        objMap.put(dataSource.getConfigResource().getId(), pid);
    }

    /**
     * 移除一个数据库连接
     * @param dbConfigResource
     */
   public void removeDbConnection(DbConfigResource dbConfigResource){
       if (log.isDebugEnabled())
           log.debug("删除数据库配置名称为{}  PID为{}: ",dbConfigResource.getName() ,dbConfigResource.getPid());

       dataMap.remove(dbConfigResource.getName().toLowerCase());
       pidMap.remove(dbConfigResource.getPid());
       objMap.remove(dbConfigResource.getId());
   }

    public void clear() {
        dataMap.clear();
        pidMap.clear();
        objMap.clear();
    }

    /**
     * 获取全部的连接资源
     */
    public Collection<LemsunDataSource> getAllDataSource() {
        return pidMap.values();
    }
}
