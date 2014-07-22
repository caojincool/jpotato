package com.lemsun.core;

/**
 * User: 宗旭东
 * Date: 13-10-22
 * Time: 下午2:40
 */
public class ResourceNotFoundException extends LemsunException {

    public static final String code = "0002";


    /**
     * 创建新的异常
     *
     * @param msg  异常信息
     */
    public ResourceNotFoundException(String msg) {
        super(msg, code, LemsunException.ResourceException);
    }
}
