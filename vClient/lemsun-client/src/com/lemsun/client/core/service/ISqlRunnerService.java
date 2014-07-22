package com.lemsun.client.core.service;

import com.lemsun.client.core.data.ArrayData;

import java.io.IOException;

/**
 * 与服务端交互执行Sql语句
 * User: dp
 * Date: 13-6-19
 * Time: 下午5:27
 */
public interface ISqlRunnerService {
    /**
     * 通过数据源组件编码,执行sql查询语句
     * 注意只能执行查询语句
     * @param pid 数据源组件编码
     * @param sql sql 查询语句例如select top 5 * from employee
     * @return 查询语句结果
     */
    ArrayData select(String pid,String sql);

    /**
     * 通过数据源组件编码,执行sql语句
     * @param pid 数据源组件编码
     * @param sql sql 执行语句,
     * @return 影响行数
     */
    String excute(String pid,String sql);
}
