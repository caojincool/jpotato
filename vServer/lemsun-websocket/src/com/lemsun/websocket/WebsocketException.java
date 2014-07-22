package com.lemsun.websocket;

import com.lemsun.core.LemsunException;

/**
 * User: 宗旭东
 * Date: 13-2-25
 * Time: 下午2:31
 */
public class WebsocketException extends LemsunException {



    /**
     * 解码异常
     */
    public static final WebsocketException DecodingException = new WebsocketException("解码异常, 可能是由于发送的消息不全. 或者是消息不规范", "001");

    /**
     * 编码异常
     */
    public static final WebsocketException EecodingException = new WebsocketException("编码异常. 内容不能进行编码传递.", "002");

    /**
     * 协议格式不对
     */
    public static final WebsocketException HeaderException = new WebsocketException("协议信息不对", "003");

    /**
     * 执行出错
     */
    public static final WebsocketException ExecuteExcption = new WebsocketException("执行出错", "004");

    /**
     * 创建新的异常
     *
     * @param msg  异常信息
     * @param code 异常代码
     */
    public WebsocketException(String msg, String code) {
        super(msg, code, LemsunException.ConnectionExcepiton);
    }


    /**
     * 提示内容信息的时候.
     * @param msg 信息
     * @param we 复制代码
     */
    public WebsocketException(String msg, WebsocketException we) {
        this(msg, we.getCode());
    }
}
