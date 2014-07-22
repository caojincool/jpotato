package com.lemsun.channel;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-9
 * Time: 上午11:12
 */
public class MessageResponse {

	private ISession session;

	private ResponseHeader header;
	private ResponseContext context;

	private Object response;

	public MessageResponse(MessageRequest request) {
		header = new ResponseHeader(this);

		RequestHeader requestHeader = request.getHeader();

		header.setDataFormat(requestHeader.getDataFormat());
		header.setId(requestHeader.getId());
		header.setUserToken(requestHeader.getUserToken());
		header.setActionCommand("response");

		this.session = request.getSession();
		this.context = new ResponseContext(this);
	}


	public ISession getSession() {
		return session;
	}


	public ResponseContext getContext() {
		return context;
	}

	public ResponseHeader getHeader() {
		return header;
	}


	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

}
