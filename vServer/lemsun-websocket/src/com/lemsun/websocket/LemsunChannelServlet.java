package com.lemsun.websocket;

import com.lemsun.auth.AccountManager;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.Host;
import com.lemsun.core.IAccount;
import com.lemsun.core.PlateformInstance;
import com.lemsun.core.service.IPlateformInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * 基于WebSocket的, 监听通道.
 * User: 宗旭东
 * Date: 13-2-22
 * Time: 上午11:50
 */
@ServerEndpoint(
        value = "/channel/{platefrom}/{token}",
        encoders = { CommandEncoder.class },
        decoders = { CommandDecoder.class }
)
public class LemsunChannelServlet {

    private static final Logger log = LoggerFactory.getLogger(LemsunChannelServlet.class);

    @OnOpen
    public void open(Session session,
                     EndpointConfig config,
                     @PathParam("platefrom") String plateform,
                     @PathParam("token") String token) throws Exception {

        IPlateformInstanceService plateformService = Host.getInstance().getContext().getBean(IPlateformInstanceService.class);
        IAccountService accountService = Host.getInstance().getContext().getBean(IAccountService.class);

        PlateformInstance instance = plateformService.get(plateform);
        IAccount account = accountService.getByToken(token);


        if(instance == null || account == null)
        {
            session.close();
        }

        SocketSenter senter = new SocketSenter(session);

        WebSocketSession webSocketSession = new WebSocketSession(session, account, instance, senter, null);

        AccountManager manager = accountService.doLogin(webSocketSession, token);

        session.getUserProperties().put(AccountManager.class.getName(), manager);

        session.getUserProperties().put(SocketSenter.class.getName(), senter);
        if(log.isInfoEnabled())
        {
            log.info("用户打开一个通道 : " + account.getAccount());
        }

    }

    @OnMessage
    public void OnMessage(Session session, ISocketCommand command) throws Exception {

        IAccountService accountService = Host.getInstance().getContext().getBean(IAccountService.class);
        AccountManager manager = (AccountManager)session.getUserProperties().get(AccountManager.class.getName());
        SocketSenter socketSenter = (SocketSenter)session.getUserProperties().get(SocketSenter.class.getName());
        accountService.setCurrentAccountManager(manager);
        command.setResponseSenter(socketSenter);
        command.excute();

    }

    @OnClose
    public void close(Session session)
    {
        if(log.isInfoEnabled())
        {
            AccountManager manager = (AccountManager)session.getUserProperties().get(AccountManager.class.getName());

            log.info("用户的 Web Session 关闭: " + manager.getAccount().getAccount());
        }

    }

    @OnError
    public void error(Session session, Throwable thr) {

    }

}
