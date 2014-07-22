package com.lemsun.websocket.commands;

import com.lemsun.core.IRequestCommand;
import com.lemsun.websocket.AbstractSocketResponseCommand;
import com.lemsun.websocket.ISocketCommand;

import java.nio.CharBuffer;

/**
 * 回复执行SQL Command 的命令
 * User: 宗旭东
 * Date: 13-2-28
 * Time: 上午9:11
 */
public class ReSqlCommand extends AbstractSocketResponseCommand {

    public static final String Command = "dataresult";

    public ReSqlCommand(String targetId) {
        super(targetId, Command);
    }

    public ReSqlCommand(IRequestCommand command) {
        this(command.getId());
    }

    @Override
    public String executeContext() {
        return "Hello World";
    }
}
