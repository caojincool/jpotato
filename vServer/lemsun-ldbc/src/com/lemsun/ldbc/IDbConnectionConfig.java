package com.lemsun.ldbc;

/**
 * 数据库连接配置
 * User: 宗旭东
 * Date: 13-6-6
 * Time: 下午2:48
 */
public interface IDbConnectionConfig {


    /**
     * 获取数据源的ID
     */
    public String getPid();

    /**
     * 获取数据源的名称
     */
    public String getName();

    /**
     * 获取连接字符串
     */
    public String getConnStr();

    /**
     * 获取数据库类型
     */
    public DbCategory getDbCategory();



}
