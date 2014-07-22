package com.lemsun.form.web;

import com.lemsun.core.IAccount;
import com.lemsun.form.WebPageResource;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * 视图的环境对象.
 * User: Xudong
 * Date: 12-10-30
 * Time: 下午3:31
 */
public class ViewScope {

	private IAccount account;
	private ApplicationContext context;
	private WebPageResource resource;
	private Map<String, ?> param;
	private IViewEngine engine;
	private Logger log;

	public IAccount getAccount() {
		return account;
	}

	void setAccount(IAccount account) {
		this.account = account;
	}

	public ApplicationContext getContext() {
		return context;
	}

	void setContext(ApplicationContext context) {
		this.context = context;
	}

	public WebPageResource getResource() {
		return resource;
	}

	void setResource(WebPageResource resource) {
		this.resource = resource;
	}

	public Map<String, ?> getParam() {
		return param;
	}

	void setParam(Map<String, ?> param) {
		this.param = param;
	}

	public IViewEngine getEngine() {
		return engine;
	}

	void setEngine(IViewEngine engine) {
		this.engine = engine;
	}

	public Logger getLog() {
		return log;
	}

	void setLog(Logger log) {
		this.log = log;
	}
}
