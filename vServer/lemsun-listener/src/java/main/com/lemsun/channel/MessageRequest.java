package com.lemsun.channel;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-9
 * Time: 上午11:12
 */
class MessageRequest implements IMessageRequest {

	private ISession session;

	private RequestHeader header;
	private RequestContext context;


	public MessageRequest(RequestHeader header, ISession session) {
		this.header = header;
		this.session = session;
	}

	public ISession getSession() {
		return session;
	}

	public RequestHeader getHeader() {
		return header;
	}


	public RequestContext getContext() {
		return context;
	}

	public void setContext(RequestContext context) {
		this.context = context;
	}

}
