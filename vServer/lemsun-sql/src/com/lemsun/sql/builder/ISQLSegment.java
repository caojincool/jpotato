package com.lemsun.sql.builder;

import java.util.List;

/**
 * 定义一个 SQL 语句的片段. 任何一个SQL语句的内容都是由不同类型的片段组成
 *
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午2:31
 */
public interface ISQLSegment {

    /**
     * 获取一个语句的片段隶属于哪个完整的语句
     */
    ISQLStatement getStatement();


    /**
     * 获取当前的片段的父节点
     */
    ISQLSegment getParent();

    /**
     * 设置当前的片段的父节点. <b>当一个SQL语句的片段重新设置父节点后. 相当于从之前的SQL语句中移除. 并附加到给定的父节点的语句中</b>
     */
    void setParent(ISQLSegment parent);

    /**
     * 获取当前片段的子节点, 只获取当前节点下的元素
     */
    List<ISQLSegment> getChild();

    /**
     * 获取当前的执行语句中全部的片段, 包括子节点的
     */
    List<ISQLSegment> getAllSegments();

    /**
     * 将自己从一个语句树中移除
     */
    void remove();

    /**
     *  验证当前的片段是否逻辑是否合理. 在构造完成语句后. 就需要对每个片段的逻辑进行验证. 会抛出验证异常
     */
    void validate() throws SqlBuilderException;

    /**
     * 将SQL 语句片段转换成 对应的 SQL 语句结构
     */
    String toSQL(ISQLAdapter adapter) throws SqlBuilderException;

    /**
     *  将当前的片段转换成 SQL 语句
     */
    String toSQL() throws SqlBuilderException;

}
