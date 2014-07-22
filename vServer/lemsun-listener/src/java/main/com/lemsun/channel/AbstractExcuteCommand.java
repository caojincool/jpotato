package com.lemsun.channel;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 抽象的执行命令, 一般情况下用户执行的命令都继承这个抽象类. 这个类也提供了获取命令在执行中所关心的对象
 * User: Xudong
 * Date: 12-10-10
 * Time: 上午10:44
 */
public abstract class AbstractExcuteCommand implements IExcuteCommand, ApplicationContextAware {

	private MessageRequest request;
	private MessageResponse response;
	private RequestContext context;
	private ISession session;
    private ApplicationContext applicationContext;

	public MessageRequest getRequest() {
		return request;
	}

	void setRequest(MessageRequest request) {
		this.request = request;
	}

	public MessageResponse getResponse() {
		return response;
	}

	void setResponse(MessageResponse response) {
		this.response = response;
	}

	public RequestContext getContext() {
		return context;
	}

	void setContext(RequestContext context) {
		this.context = context;
	}

	public ISession getSession() {
		return session;
	}

	void setSession(ISession session) {
		this.session = session;
	}


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
