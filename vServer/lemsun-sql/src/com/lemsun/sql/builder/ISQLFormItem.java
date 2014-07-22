package com.lemsun.sql.builder;

/**
 * 定义一个查询对象
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午3:23
 */
public interface ISQLFormItem extends ISQLSegment {


    /**
     * 查询别名
     */
    String getAlias();

    /**
     * 获取查询名称, 如果定义了别名那么返回别名
     */
    String getName();

    /**
     * 获取当前表格跟其他表格连接条件
     */
    SJoin getJoin();

    /**
     * 由于 JOIN 的渲染需要, 包括在查询对象内. without 如果传入 false, 就只是渲染排除 join 状态的语句
     */
    String toSQL(ISQLAdapter adapter, boolean without) throws SqlBuilderException;

}
