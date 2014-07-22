package com.lemsun.websocket;

import com.lemsun.core.IResponseCommand;

import java.nio.Buffer;

/**
 * 使用Web Socket 回复的消息接口
 * User: 宗旭东
 * Date: 13-2-28
 * Time: 上午9:14
 */
public interface ISocketResponseCommand extends IResponseCommand {


    /**
     * 命令的唯一值
     */
    public final String ID = "ID";

    /**
     * 命令的头部KEY
     */
    public final String COMMAND = "COMMAND";

    /**
     * 内容的类型
     */
    public final String CONTEXTTYPE = "CONTEXT-TYPE";

    /**
     * 内容开始标签
     */
    public final String BODY = "BODY";

    /**
     * 设置一个目标命令
     * @param id
     */
    public void setTargetId(String id);

    /**
     * 获取内容类型
     */
    public void setContextType(String contextType);


    /**
     * 设置输出的内容缓存
     * @param context 缓存内容
     */
    public void setContext(String context);


    /**
     * 获取表头信息
     * @return 表头映射
     */
    public String getHeader();
}
