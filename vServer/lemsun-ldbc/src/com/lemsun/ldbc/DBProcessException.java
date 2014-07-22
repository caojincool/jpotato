package com.lemsun.ldbc;

import com.lemsun.core.LemsunException;

/**
 * User: 宗旭东
 * Date: 13-6-6
 * Time: 下午2:25
 */
public class DBProcessException extends LemsunException {
    /**
     * 创建新的异常
     *
     * @param msg  异常信息
     * @param code 异常代码
     */
    public DBProcessException(String msg, String code) {
        super(msg, code, LemsunException.DbProcessException);
    }
}
