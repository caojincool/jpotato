package com.lemsun.websocket.commands;

import com.lemsun.websocket.AbstractSocketResponseCommand;

/**
 * 执行成功命令, 作为一个标准的执行成功返回对象.
 * User: 宗旭东
 * Date: 13-9-11
 * Time: 上午10:55
 */
public class SuccessCommand extends AbstractSocketResponseCommand {

    /**
     * 针对一个对象的返回
     */
    protected SuccessCommand(String targetId) {
        super(targetId, "re.success");
    }


    @Override
    public String executeContext() {
        return "";
    }
}
