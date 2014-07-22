package com.lemsun.data.connection;

import com.lemsun.core.LemsunException;

/**
 * 管理连接异常
 * User: 宗旭东
 * Date: 13-2-24
 * Time: 上午10:03
 */
public class ConnectionException extends LemsunException {
    /**
     * 创建新的异常
     *
     * @param msg  异常信息
     * @param code 异常代码
     */
    public ConnectionException(String msg, String code) {
        super(msg, code, LemsunException.DbConnectionException);
    }


    public static ConnectionException NotFindDatasource = new ConnectionException("没有发现数据库连接", "001");
}
