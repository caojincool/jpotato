package com.lemsun.core;

import org.springframework.data.mongodb.core.query.Query;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-12-19
 * Time: 上午9:56
 * 支持查询对象
 */
public interface IQuery {

    /**
     * 创建查询对象
     * @return 查询对象
     */
    public Query createQuery();

}
