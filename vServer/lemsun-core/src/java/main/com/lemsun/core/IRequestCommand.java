package com.lemsun.core;

import java.nio.Buffer;

/**
 * 发送来的请求命令
 * User: 宗旭东
 * Date: 13-2-25
 * Time: 下午5:06
 */
public interface IRequestCommand {

    /**
     * 获取唯一标示
     */
    public String getId();

    /**
     * 获取命令
     */
    public String getCommand();

    /**
     * 获取内容类型
     */
    public String getContextType();

    /**
     * 获取内容流
     */
    public String getContext();

    /**
     * 执行命令
     */
    void excute() throws Exception;

}
