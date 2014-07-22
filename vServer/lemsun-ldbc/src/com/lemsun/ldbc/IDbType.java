package com.lemsun.ldbc;

/**
 * 提供当前的支持的数据库种类
 * User: 宗旭东
 * Date: 13-6-6
 * Time: 下午12:00
 */
public interface IDbType {

    /**
     *  获取支持的数据库类型
     */
    public DbCategory getSupportCategory();

}
