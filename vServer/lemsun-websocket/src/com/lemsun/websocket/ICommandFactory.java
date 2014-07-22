package com.lemsun.websocket;

/**
 * 创建命令工厂接口
 * User: 宗旭东
 * Date: 13-2-26
 * Time: 上午9:02
 */
public interface ICommandFactory {

    /**
     * 使用命令创建命令对象
     * @param command 命令
     * @return 命令对象
     */
    public ISocketCommand createSocketCommand(String command);

}
