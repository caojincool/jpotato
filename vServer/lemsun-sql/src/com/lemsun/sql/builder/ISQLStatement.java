package com.lemsun.sql.builder;

import java.util.List;

/**
 * 标记一个 SQL 语句, 标识这个语句是一个完整的SQL语句
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午2:32
 */
public interface ISQLStatement extends ISQLSegment {

    /**
     * 在一个完整的语句中返回当前的计数, 每次返回都增加 1
     */
    int nextIndex();

    /**
     * 返回语句中的全部传参参数列表
     */
    List<SParam> getParamters();

}
