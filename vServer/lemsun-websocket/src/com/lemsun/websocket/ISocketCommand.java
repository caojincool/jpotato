package com.lemsun.websocket;

import com.lemsun.core.IRequestCommand;
import com.lemsun.core.IResponseSenter;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.nio.Buffer;
import java.util.Map;

/**
 * 通道接口
 * User: 宗旭东
 * Date: 13-2-25
 * Time: 下午5:45
 */
public interface ISocketCommand extends IRequestCommand {

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
     * 设置内容
     * @param context
     */
    public void setContext(String context);


    /**
     * 设置表头信息
     * @param header
     */
    public void setHeader(Map<String, String> header) throws WebsocketException;

    /**
     * 设置发送信息请求对象
     * @param senter 发送者
     */
    public void setResponseSenter(IResponseSenter senter);

    /**'
     * 获取回复信息发送对象
     */
    public IResponseSenter getResponseSenter();


}
