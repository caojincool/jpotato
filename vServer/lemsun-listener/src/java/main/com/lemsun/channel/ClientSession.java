package com.lemsun.channel;

import com.lemsun.core.IAccount;
import com.lemsun.data.connection.DataSession;
import com.lemsun.data.connection.DbManager;
import org.apache.mina.core.session.IoSession;

import java.net.InetSocketAddress;
import java.nio.charset.CharsetEncoder;
import java.util.Date;

/**
 * 客户端会话对象. 客户端连接起到最后关闭. 服务器会持有用户的会话对象. 里面可以保存用户的临时对象. 已经变量信息等
 * User: Xudong
 * Date: 12-10-9
 * Time: 上午11:13
 */
class ClientSession extends DataSession implements ISession {

	/**
	 * 当前 Session 中保存用户信息的建
	 */
	public static final String SESSION_KEY = "__context__";

	private IAccount account;
	private Date startTime = new Date();
	private Date lastAction;
	private ZxdChannel channel;
	private IoSession ioSession;
	private String remoteIp;

	public ClientSession(ZxdChannel channel, IAccount account, IoSession session, DbManager dbManager) {
		super(dbManager, account);
		this.channel = channel;
		this.ioSession = session;
		this.remoteIp = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
		session.setAttribute(SESSION_KEY, this);
	}



	private final String charsetKey = "__CharsetEncoder__";
	@Override
	public CharsetEncoder getCharsetEncoder() {

		CharsetEncoder encoder = (CharsetEncoder)getAttribute(charsetKey);
		if(encoder == null) {
			encoder = getCharset().newEncoder();
			setAttribute(charsetKey, encoder);
		}

		return encoder;
	}


	/**
	 *
	 * @return 返回数据层的Session
	 */
	public IoSession getIoSession() {
		return ioSession;
	}

	/**
	 *
	 * @return 跟用户建立的通道
	 */
	public ZxdChannel getChannel() {
		return channel;
	}


	public static ISession getSession(IoSession session) {
		return (ISession)session.getAttribute(SESSION_KEY);
	}

}
