package com.lemsun.task;

import com.lemsun.core.LemsunException;

/**
 * User: 宗旭东
 * Date: 14-3-19
 * Time: 上午9:32
 */
public class TaskExcepiton extends LemsunException {
    public TaskExcepiton(String msg, String code, LemsunException ex) {
        super(msg, code, ex);
    }

    public TaskExcepiton(Exception ex) {
        super(ex);
    }

    public TaskExcepiton(String msg) {
        super(msg);
    }
}
