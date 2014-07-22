package com.lemsun.websocket;

import com.lemsun.core.IResponseCommand;
import com.lemsun.core.IResponseSenter;
import javax.websocket.Session;
import java.io.IOException;

/**
 * User: 宗旭东
 * Date: 13-2-27
 * Time: 下午4:45
 */
public class SocketSenter implements IResponseSenter {

    private Session outbound;


    public SocketSenter(Session outbound) {
        this.outbound = outbound;
    }


    @Override
    public void sent(IResponseCommand command) throws Exception {

        try {
            outbound.getBasicRemote().sendObject(command);
        } catch (IOException e) {
            throw WebsocketException.ExecuteExcption;
        }

    }
}
