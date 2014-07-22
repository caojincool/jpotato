package com.lemsun.websocket;

import com.lemsun.websocket.ISocketResponseCommand;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * 命令发送的编码类
 * User: xudong
 * Date: 13-11-29
 * Time: 下午2:48
 */
public class CommandEncoder implements Encoder.Text<ISocketResponseCommand> {
    @Override
    public String encode(ISocketResponseCommand iSocketResponseCommand) throws EncodeException {

        if(iSocketResponseCommand == null) return "";

        iSocketResponseCommand.execute();

        return iSocketResponseCommand.getContext();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {
    }
}
