package com.lemsun.channel;

import com.lemsun.core.IAccount;
import com.lemsun.data.connection.DbManager;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;


/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-9-10
 * Time: 下午5:29
 */
public class ReceiveHandler extends IoHandlerAdapter {

	private SessionManager manager;

	private ICommandFactory factory;

	private DbManager dbManager;

	private static Logger log = LoggerFactory.getLogger(ReceiveHandler.class);

	public ReceiveHandler(SessionManager manager, ICommandFactory factory, DbManager dbManager) {
		this.manager = manager;
		this.factory = factory;
		this.dbManager = dbManager;
	}


	@Override
	public void sessionCreated(IoSession session) throws Exception {

		if(log.isDebugEnabled())
			log.debug("发现用户登录 创建 Session " + session.getRemoteAddress());

		//TODO 创建用户账号. 如果没有用户账号
		IAccount account = null;

		ClientSession se = new ClientSession(manager.getChannel(), account, session, dbManager);

		se.setCharset(Charset.forName(manager.getChannel().getChartset()));
		manager.createSession(se);

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		if(log.isDebugEnabled())
			log.debug("打开 Session");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {

		ISession se = (ISession) session.getAttribute(ClientSession.SESSION_KEY);
		if(se == null) return;

		if(log.isDebugEnabled())
		{
			log.debug("用户关闭了一个连接 ->" + se.getAccount().getAccount());
		}

		manager.removeSession(se);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		log.debug("接收发送数据 ->");

		try{
			if(message instanceof MessageRequest) {
				MessageRequest request = (MessageRequest) message;
			    long runTime = System.currentTimeMillis();
				if(log.isDebugEnabled()) {
					log.debug("发现一个请求 :" + request.getHeader().toString());
				}



				IExcuteCommand command = factory.createCommand(request);

				command.excute();

				runTime = System.currentTimeMillis() - runTime;

				if(command instanceof  AbstractExcuteCommand) {

					AbstractExcuteCommand c = (AbstractExcuteCommand) command;
					c.getResponse().getHeader().setId(request.getHeader().getId());
					c.getResponse().getHeader().setRunTime(runTime);

                    RequestHeader header = c.getRequest().getHeader();

                    if(header.isQuit()) {
                        if(log.isDebugEnabled())
                            log.debug("用户执行退出操作 : {}", c.getSession().getId());
                        session.close(false);
                    }

                    session.write(c.getResponse());


				}

				if(log.isDebugEnabled()) {
					log.debug("请求执行完成, 使用时间 (" + runTime + ") 毫秒" );
				}
			}
			else {
				throw new ChannelException("不能识别的请求类型, 请确认提交格式是否正确. 或者版本是否匹配.");

			}
		}
		catch (ChannelException e) {
			session.write(e);
		}
		catch (Exception e) {
			session.write(new ChannelException(e.getMessage()));
		}

	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		if(log.isDebugEnabled())
			log.debug("-> " + ClientSession.getSession(session).getAccount() + " 发送数据 " + message);
	}
}
