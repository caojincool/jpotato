package com.lemsun.form.web;

import com.lemsun.form.WebPageResource;
import org.apache.commons.lang3.StringUtils;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-30
 * Time: 下午2:54
 */
public class JsView implements View {

	private WebPageResource resource;
	private JsViewEngine engine;
	private ApplicationContext context;
	//private Function fun;
	private static final Logger log = LoggerFactory.getLogger(JsView.class);


	public JsView(ApplicationContext context, WebPageResource resource, JsViewEngine engine)
	{
		this.context = context;
		this.resource = resource;
		this.engine = engine;
	}

    /**
     *
     * @return
     */
	public Date getUpdateTime()
	{
		return resource.getUpdateTime();
	}


	@Override
	public String getContentType() {
		String t = resource.getContextType();
		if(StringUtils.isEmpty(t)) return "text/html";
		return t;
	}


	public String renderContext(Map<String, ?> stringMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(log.isDebugEnabled()) log.debug("开始准备参数");


		ViewScope scope = new ViewScope();

		scope.setResource(resource);
		scope.setParam(stringMap);
		scope.setEngine(engine);
		scope.setContext(context);
		scope.setLog(log);

		ViewWriter writer = new ViewWriter();

		Context cx = Context.enter();

		Scriptable localScope = cx.newObject(engine.getScope());
		localScope.setPrototype(engine.getScope());
		localScope.setParentScope(null);

		localScope.put("scope", localScope, scope);
		localScope.put("writer", localScope, writer);
		localScope.put("request", localScope, request);
		localScope.put("response", localScope, response);
		localScope.put("param", localScope, stringMap);

		try
		{
			if(log.isDebugEnabled())
			{
				log.debug("开始页面渲染: {},  {}", resource.getName(), resource.getPid());
			}

			//fun.call(cx, localScope, localScope, param);
			cx.evaluateString(localScope, resource.getPid() + "()", "test", 1, null);

			if(log.isDebugEnabled())
			{
				String page = writer.toString();
				log.debug("渲染完成:");
				log.debug(page);
			}


			//response.getWriter().write(page);

		}
		catch (Exception ex)
		{
			throw new Exception("组件模板定义异常, 请检查内容. (" + ex.getMessage() + ")");
		}
		finally {
			Context.exit();
		}

		return writer.toString();
	}

	@Override
	public void render(Map<String, ?> stringMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cx = renderContext(stringMap, request, response);
		response.setCharacterEncoding("UTF-8");
		response.setContentType(getContentType());
		response.getWriter().write(cx);
		response.getWriter().flush();
	}
}
