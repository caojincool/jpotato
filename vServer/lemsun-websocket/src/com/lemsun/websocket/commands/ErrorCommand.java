package com.lemsun.websocket.commands;

import com.lemsun.websocket.AbstractSocketResponseCommand;

/**
 * User: 宗旭东
 * Date: 13-3-4
 * Time: 下午8:20
 */
public class ErrorCommand extends AbstractSocketResponseCommand {

    private String msg;
    /**
     * 构造一个异常的回复对象
     * @param targetId
     * @param msg 异常信息
     */
    public ErrorCommand(String targetId, String msg) {
        super(targetId, "re.error");
         this.msg = msg;
    }

    @Override
    public String executeContext() {
        return "";
    }


}
