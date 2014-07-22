package com.lemsun.client.core;

/**
 * User: 宗旭东
 * Date: 13-3-9
 * Time: 上午9:36
 */
public interface IResponseEntity<T> {
    /**
     * 获取执行是否成功
     */
    boolean isSuccess();

    /**
     * 获取执行代码
     */
    String getCode();

    /**
     * 获取返回信息
     */
    String getMessage();

    /**
     * 获取携带参数
     */
    T getEntity();

    /**
     * 获取内容的记录数
     */
    long getTotalCount();
}
