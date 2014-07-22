package com.lemsun.ldbc.service;

import com.lemsun.ldbc.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 表格操作接口
 * User: 宗旭�
 * Date: 13-6-6
 * Time: 下午2:12
 */
public interface ITableOperaterService extends IDbType {

    /**
     * 将一个数据表组件持久化到数据库上, 如果执行过程中出现错误抛�DBProcessException 异常
     */
    public void createTableResource(ITableResource resource, IDbConnectionConfig dbConfig) throws DBProcessException;

    /**
     * 获取查询的数据总数
     * @param query 查询条件
     * @return 返回数据数量. 如果�-1 查询异常, 或者没有对应的表格
     */
    public long getQueryCount(TableQuery query);


    /**
     * 对表格查询的Code 值进行判� 并返回Code 对应的� 如果为空返回 -1;
     * @param query
     * @return
     */
    public long getQueryCode(TableQuery query);

    /**
     * 查询获取数据
     * @param query 查询对象
     * @return 返回数据
     */
    public TableData query(TableQuery query) throws Exception;


    /**
     * 将一个表格数据保存到一个表格组件中�
     * @param resource
     * @param data
     */
    public void saveData(ITableResource resource, TableData data) throws Exception;


    /**
     * 更新表格组件
     * @param newResource 新的表格组件
     * @param oldResource 就的表格组件
     */
    public void updatePhysicsTable(ITableResource newResource, ITableResource oldResource);


    /**
     * 更新当前的列的内� 如果 stream 将被看成移除或者清除数� 并返回文件的ID
     */
    public String uploadTableColumn(ITableResource resource, String column, String rowid, InputStream stream) throws Exception;


    /**
     * 在当前的表格列中增加一个文� 如果 stream 将被看成移除或者清除数� 并返回文件的ID
     */
    public String uploadTableColumn(ITableResource resource, String column, String rowid,
                                  String fileName, String type, InputStream stream, String reamrk) throws Exception;

    /**
     * 返回字段中的内容�
     * @throws Exception
     */
    public String getColumnContext(ITableResource resource, String column, String rowid) throws Exception;

    /**
     * 获取一个字段的文件� 写入到Stream 中去
     * @param resource 资源组件
     * @param column 列信息
     * @param rowid 文件ID
     */
    public void getColumnFile(ITableResource resource, String column, String rowid, OutputStream stream) throws Exception;


    /**
     * 获取一个字段中保存文件的信息列�
     * @throws Exception
     */
    public List<ColumnFileInfo> getColumnFileInfos(ITableResource resource, String column, String rowid) throws Exception;


    /**
     * 在数据库中移除一个表组的物理�
     */
    public void removeTable(ITableResource resource) throws SQLException;

    /**
     * 在数据库中移除表组组� 包括移除全部的物理表. 作为表组的删� 并不是简单的将物理表组件进行循环删除.
     * 而是根据表组的命名规则进行批量删� 效率会比较高
     * @param resource
     */
    public void removeTableGroup(ITableGroupResource resource)throws SQLException ;

    /*
     * 删除数据库配�
     * @param dbCategory
     */
    public void removeDbConfig(IDbConnectionConfig dbCategory);
}
