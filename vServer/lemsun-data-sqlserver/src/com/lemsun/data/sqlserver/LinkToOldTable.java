package com.lemsun.data.sqlserver;

import com.lemsun.data.tables.TableResource;

import java.sql.Connection;

/**
 * 创建5代程序中挂载4代表的转换
 * User: Xudong
 * Date: 12-11-23
 * Time: 上午11:13
 */
@Deprecated
public class LinkToOldTable {

	private Connection conn;
	private TableResource resource;


	public LinkToOldTable(Connection conn, TableResource resource) {
		this.conn = conn;
		this.resource = resource;
	}


	/**
	 *
	 * @return 返回转换后的5代资源数据
	 */
	public TableResource convert()
	{
		return resource;
	}


	protected void getGridHeaderStr()
	{

	}


}
