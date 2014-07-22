package com.lemsun.sql.builder;

/**
 * 连接类型
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午3:38
 */
public enum SJoinType {

    /**
     * 表示一个开始表格
     */
    Start,

    /**
     * 默认连接
     */
    Join,

    /**
     * 左连接
     */
    Left,

    /**
     * 右连接
     */
    Right,

    /**
     * 全连接
     */
    JoinAll

}
