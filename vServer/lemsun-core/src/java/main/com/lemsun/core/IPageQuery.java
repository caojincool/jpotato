package com.lemsun.core;

import org.springframework.data.domain.Pageable;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-12-19
 * Time: 上午9:59
 * 分页查询对象
 */
public interface IPageQuery extends IQuery, Pageable {

    /**
     * 获取在分页数据中, 是否需要返回数据的总数
     */
    boolean getDataCount();

}
