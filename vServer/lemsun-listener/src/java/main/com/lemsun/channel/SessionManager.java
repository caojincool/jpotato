package com.lemsun.channel;

import java.util.Hashtable;

/**
 * 用户会话管理
 * User: Xudong
 * Date: 12-10-9
 * Time: 上午11:33
 */
public class SessionManager {

	private Hashtable<String, ISession> sessions = new Hashtable<>();
	private ZxdChannel channel;


	public SessionManager(ZxdChannel channel) {
		this.channel = channel;
	}

	public ZxdChannel getChannel() {
		return channel;
	}

	/**
	 * 保存创建的用户Session
	 * @param session 会话
	 */
	public void createSession(ISession session) {
		String id = session.getId();
		if(!sessions.containsKey(id)) {
			sessions.put(id, session);
		}

	}


	public void removeSession(ISession session) {
		String id = session.getId();
		if(sessions.containsKey(id)) {
			sessions.remove(id);
			session.getIoSession().removeAttribute(ClientSession.SESSION_KEY);
		}
	}


	/**
	 * 获取用户的 Session 如果没有发现用户的Session 那么返回的是个Null
	 * @param id Session的主键
	 * @return Session
	 */
	public ISession getSession(String id) {
		if(sessions.containsKey(id)){
			return sessions.get(id);
		}
		return null;
	}

}
