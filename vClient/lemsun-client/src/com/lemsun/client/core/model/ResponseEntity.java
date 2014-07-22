package com.lemsun.client.core.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lemsun.client.core.IResponseEntity;

/**
 * 远程服务器回复对象模型
 * User: 宗旭东
 * Date: 13-3-8
 * Time: 下午2:44
 */
public class ResponseEntity<T> implements IResponseEntity<T> {

    private boolean success;
    private String code;
    private String message;
    private long totalCount;
    private T entity;


    public ResponseEntity()
    {

    }

    /**
     * 获取执行是否成功
     */
    @Override
    public boolean isSuccess() {
        return success;
    }

    /**
     * 设置执行是否成功
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 获取执行代码
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * 设置执行代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取返回信息
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 设置返回信息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取携带参数
     */
    @Override
    public T getEntity() {
        return entity;
    }

    /**
     * 设置携带参数
     */
    public void setEntity(T entity) {
        this.entity = entity;
    }

    /**
     * 获取内容的记录数
     */
    @Override
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * 设置内容的记录数
     */
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

}


