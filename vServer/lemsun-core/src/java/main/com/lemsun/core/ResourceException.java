package com.lemsun.core;

/**
 * User: dpyang
 * Date: 13-3-25
 * Time: 上午11:37
 * 资源组件类异常
 */
public class ResourceException extends LemsunException {

    public static final ResourceException ResourceIsNull = new ResourceException("组件为空,请检查", "001");

    public static final ResourceException ResourceExists = new ResourceException("该组件已经存在,不能重复插入", "002");

    public static final ResourceException ResourcePidisNull=new ResourceException("组件编码为空,请检查","003");

    /**
     * 创建新的异常
     *
     * @param msg  异常信息
     * @param code 异常代码
     */
    public ResourceException(String msg, String code) {
        super(msg, code, LemsunException.ResourceException);
    }
}
