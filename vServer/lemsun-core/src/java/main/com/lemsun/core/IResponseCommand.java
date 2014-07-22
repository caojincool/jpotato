package com.lemsun.core;

import java.nio.Buffer;

/**
 * 发送命令给客户端
 * User: 宗旭东
 * Date: 13-2-25
 * Time: 下午5:06
 */
public interface IResponseCommand {
    /**
     * 获取发送的命令. 作用在那个命令基础上
     */
    public String getTargetId();

    /**
     * 获取命令
     */
    public String getCommand();

    /**
     * 获取内容类型
     */
    public String getContextType();


    /**
     * 获取发送的内容
     */
    public String getContext();

    /**
     * 执行发送命令. <br/>
     * 在调用前线执行这个方法. 然后解析出缓存对象
     */
    public void execute();
}
