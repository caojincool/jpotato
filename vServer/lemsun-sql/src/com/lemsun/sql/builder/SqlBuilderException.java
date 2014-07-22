package com.lemsun.sql.builder;

/**
 * SQL 语句构造异常
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午2:43
 */
public class SqlBuilderException extends Exception {

    public SqlBuilderException() {
    }

    public SqlBuilderException(String message) {
        super(message);
    }

    public SqlBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlBuilderException(Throwable cause) {
        super(cause);
    }

    public SqlBuilderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
