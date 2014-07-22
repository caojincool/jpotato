package com.lemsun.ldbc;

/**
 * 数据源连接接口
 * User: 宗旭东
 * Date: 13-7-23
 * Time: 下午3:57
 */
public interface IdbcDataSource {


    /**
     * 获取数据源的主键
     * @return
     */
    public String getPid();

    /**
     * 获取配置当前数据源的主键
     */
    public String getConfigPid();
}
