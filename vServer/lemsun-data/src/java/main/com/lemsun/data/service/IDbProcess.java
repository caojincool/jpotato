package com.lemsun.data.service;

import com.lemsun.core.IAccount;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.tables.TableGroupResource;
import com.lemsun.data.tables.TableResource;
import com.lemsun.ldbc.DbCategory;
import org.springframework.context.ApplicationListener;

import java.sql.SQLException;

/**
 * 定义数据库处理器接口, 可针对不同数据库做不同实现
 * User: Xudong
 * Date: 12-11-14
 * Time: 下午4:26
 */
@Deprecated
public interface IDbProcess extends ApplicationListener {
	/**
	 *
	 * @return 返回处理器支持的类型
	 */
	DbCategory getCategory();

	/**
	 * 获取创建表格的SQL语句, 如果表单存在就不需要重新创建.
	 * @param resource 表格对象
	 */
	void createTable(TableResource resource, IAccount owner) throws Exception;


	/**
	 * 初始化表格. 对表格需要的数据进行填充. 如果有需要加载的数据需要进行加载
	 */
	void initTable(TableResource resource, IAccount owner) throws Exception;

	/**
	 * 创建一个关系表组组件
	 * @param resource 表组
	 * @param owner 所属人员
	 * @throws Exception 创建异常
	 */
	void createTableGroup(TableGroupResource resource, IAccount owner) throws Exception;

	/**
	 * 初始化表格组. 如果表组内的表格需要加载解析. 那就需要自动的初始化后再加载表格对象.
	 */
	void initTableGroup(TableGroupResource resource, IAccount owner) throws Exception;

	/**
	 * 移除一个资源表
	 */
	void dropTable(TableResource resource, IAccount account) throws Exception;

	/**
	 * 移除一个资源组对象
	 * @param resource 表组
	 * @param owner 删除人员账号
	 * @throws Exception 创建异常
	 */
	void dropTableGroup(TableGroupResource resource, IAccount owner) throws Exception;


    /**
     * 获取数据源中的数据库列表
     */
    String[] getDatabaseNames(DbConfigResource resource) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException;
}
