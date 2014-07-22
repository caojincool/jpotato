package com.lemsun.websocket.commands;

import com.lemsun.websocket.AbstractSocketResponseCommand;

import java.nio.CharBuffer;

/**
 * User: 宗旭东
 * Date: 13-3-26
 * Time: 下午5:48
 */
public class ReTableSaveCommand extends AbstractSocketResponseCommand {

    public static final String CommandName = "re.table.save";

    private boolean success;
    private String msg;

    protected ReTableSaveCommand(String targetId) {
        super(targetId, CommandName);
        success = true;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String executeContext() {
        return isSuccess() ? "OK" : getMsg();
    }
}
