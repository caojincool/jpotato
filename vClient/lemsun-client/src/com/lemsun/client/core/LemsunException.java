package com.lemsun.client.core;

/**
 * User: 宗旭东
 * Date: 13-10-15
 * Time: 下午3:36
 */
public class LemsunException extends RuntimeException {
    public LemsunException(String message) {
        super(message);
    }

    public LemsunException(String message, Throwable cause) {
        super(message, cause);
    }

    public LemsunException(Throwable cause) {
        super(cause);
    }

    public LemsunException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
