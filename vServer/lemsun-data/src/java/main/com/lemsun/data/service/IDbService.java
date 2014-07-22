package com.lemsun.data.service;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.connection.DbManager;
import com.lemsun.ldbc.TableData;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 定义数据库服务接口
 * User: Xudong
 * Date: 12-11-14
 * Time: 下午2:03
 */
public interface IDbService {
	/**
	 * 获取数据库管理对象
	 * @return 数据库管理对象
	 */
	DbManager getDbManager();

    /**
	 * 增加一个配置文件
	 * @param configResource 数据库配置文件
	 * @throws Exception 如果增加出错. 抛出异常
	 */
	void addConfig(DbConfigResource configResource) throws Exception;


    /**
     * 修改数据库组件
     * @param configResource DbConfigResource
     */
    void updateConfig(DbConfigResource configResource) throws Exception;

    /**
     * 根据PID获取表组件
     */
    DbConfigResource getDbconfigResource(String pid);

    /**
     * 获取全部的数据库连接组件
     */
    List<DbConfigResource> getAll();


    /**
     * 获取数据库连接组件的数据库名集合
     */
    String[] getDbNames(DbConfigResource resource) throws Exception;

    /**
     * 执行SQL语句
     * @param pid 数据库主键
     * @param sql sql 语句
     * @return 执行后的结果集
     */
    TableData select(String pid, String sql) throws Exception;

    /**
     * 执行SQL语句
     * @param pid 数据库的组件
     * @param sql SQL 语句
     * @throws Exception 执行异常
     */
    void execute(String pid, String sql) throws Exception;

    /**
     * 删除数据库配置
     * @param pid
     */
    public void deleteResource(String pid)throws Exception;

    /**
     * 查询获取分页的组件模型
     */
    Page<DbConfigResource> getPageing(AbstractPageRequest query);
}
