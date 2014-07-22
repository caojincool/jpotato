package com.lemsun.websocket;

import com.lemsun.core.IAccount;
import com.lemsun.core.IResponseCommand;
import com.lemsun.core.IResponseSenter;
import com.lemsun.core.PlateformInstance;
import com.lemsun.core.member.AbstractUserSession;
import javax.websocket.Session;
import java.io.IOException;

/**
 * User: 宗旭东
 * Date: 13-3-4
 * Time: 下午5:10
 */
public class WebSocketSession extends AbstractUserSession {
    private Session session;
    private IResponseSenter commandSender;

    /**
     * 
     * @param session
     * @param account
     * @param plateform
     * @param commandSender
     */
    public WebSocketSession(Session session, IAccount account, PlateformInstance plateform, IResponseSenter commandSender, String ip) {
        super(account, plateform, ip);
        this.session = session;
        this.commandSender = commandSender;
    }

    /**
     * 获取一个真实的 Http WebSession
     */
    public Session getHttpSession() {
        return session;
    }

    @Override
    public String getSessionId() {
        return session.getId();
    }

    @Override
    public Object getAttribute(String key) {
        return session.getUserProperties().get(key);
    }

    @Override
    public void setAttribute(String key, Object value) {
        session.getUserProperties().put(key, value);
    }

    @Override
    public void SendMessage(IResponseCommand command) {
        try {
            commandSender.sent(command);
        } catch (Exception e) {

        }
    }

    @Override
    public void close() {
        try {
            session.close();
        } catch (IOException e) {

        }
    }
}
