package com.lemsun.task;

import com.lemsun.core.LemsunException;

/**
 * 任务异常类
 * User: 刘晓宝
 * Date: 14-3-19
 * Time: 下午1:49
 */
public class TaskException  extends LemsunException{

    public TaskException(String msg, String code, LemsunException ex) {
        super(msg, code, ex);
    }

    public TaskException(Exception ex) {
        super(ex);
    }

    public TaskException(String msg) {
        super(msg);
    }

    @Override
    public String getCode() {
        return super.getCode();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public LemsunException getParent() {
        return super.getParent();
    }
}
