package com.lemsun.sql.builder;

/**
 * 针对 SQL 对象结构生成对应的语句. 生成的时候需要根据不同的数据库进行匹配
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午2:43
 */
public interface ISQLAdapter {

    /**
     * 获取选择语句的片段
     * @param select
     * @param withpage
     * @return
     * @throws SqlBuilderException
     */
    String getSelectStatement(SSelect select, boolean withpage) throws SqlBuilderException;

    /**
     * 获取列的片段
     * @param col
     * @return
     * @throws SqlBuilderException
     */
    String getColStatement(SCol col) throws SqlBuilderException;


    String getFormStatement(SForm form) throws SqlBuilderException;

    String getGroupByStatement(SGroupBy groupBy) throws SqlBuilderException;

    String getJoinStatement(SJoin join) throws SqlBuilderException;

    String getOrderStatement(SOrder order) throws SqlBuilderException;

    String getPageStatement(SPage page) throws SqlBuilderException;

    String getParamStatement(SParam param) throws SqlBuilderException;

    String getParamArrayStatement(SParamArray paramArray) throws SqlBuilderException;




    String getTableStatement(STable table, boolean withjoin) throws SqlBuilderException;

    String getWhereStatement(SWhere where) throws SqlBuilderException;

    /**
     * 获取条件片段
     * @param expre
     * @return
     * @throws SqlBuilderException
     */
    String getExpreStatement(SExpre expre) throws SqlBuilderException;

    /**
     * 获取操作符的数据库的符号
     */
    String getOperator(SOperator op) throws SqlBuilderException;

    /**
     * 获取逻辑操作符
     */
    String getCondition(SCondition condition) throws SqlBuilderException;

    /**
     * 获取数据源的参数符号
     */
    String getParamSymbol();

    /**
     * 获取 null
     */
    String getNULL();

    /**
     * 获取给定的值. 在SQL语句中的表示
     */
    String getSQLValue(Object value) throws SqlBuilderException;
}
