package com.lemsun.form.web;

import com.lemsun.form.WebPageResource;
import com.lemsun.form.WebScriptResource;
import com.lemsun.form.service.IWebPageResourceService;
import com.lemsun.form.service.IWebScriptResourceService;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-30
 * Time: 下午1:55
 */
@Component
public class JsViewEngine implements IViewEngine, DisposableBean, InitializingBean {

	static boolean useDynamicScope;
	private static final Logger log = LoggerFactory.getLogger(JsViewEngine.class);

	/**
	 * 自定义工厂
	 */
	static class MyFactory extends ContextFactory
	{
		@Override
		protected boolean hasFeature(Context cx, int featureIndex)
		{
			if (featureIndex == Context.FEATURE_DYNAMIC_SCOPE) {
				return useDynamicScope;
			}
			return super.hasFeature(cx, featureIndex);
		}
	}

	static {
		ContextFactory.initGlobal(new MyFactory());
	}

	@Autowired
	public JsViewEngine(ApplicationContext context, IWebPageResourceService pageResourceService, IWebScriptResourceService resourceService)
	{
		this.resourceService = resourceService;
		this.pageResourceService = pageResourceService;
		this.applicationContext = context;
		useDynamicScope = true;
	}

	private IWebScriptResourceService resourceService;
	private IWebPageResourceService pageResourceService;

	private Context context;
	private Scriptable scope;
	private Map<String, JsView> viewCache = new HashMap<>();
	private ApplicationContext applicationContext;


	/**
	 *
	 * @return 返回共享环境的内容
	 */
	public Context getContext() {
		return context;
	}

	/**
	 *
	 * @return 返回共享环境
	 */
	public Scriptable getScope() {
		return scope;
	}

	@Override
	public synchronized View getView(WebPageResource resource)
	{
		JsView view;
		if(viewCache.containsKey(resource.getPid()))
		{
			view = viewCache.get(resource.getPid());

			//需要更新
			if(resource.getUpdateTime().getTime() > view.getUpdateTime().getTime())
			{
				view = (JsView)createView(resource);
				viewCache.remove(resource.getPid());
				viewCache.put(resource.getPid(), view);
			}

		}
		else {
			view = (JsView)createView(resource);
			viewCache.put(resource.getPid(), view);
		}

		return view;

	}


	@Override
	public View createView(WebPageResource resource) {

		String context = pageResourceService.getResourceContext(resource);

		JsTemplate template = new JsTemplate(resource, context);

		String code = template.toCode();
        //WebPageResource删除了getFunctionName方法调用其父类方法
		String funName = resource.getPid();

		if(ScriptableObject.hasProperty(scope, funName))
		{
			ScriptableObject.deleteProperty(scope, funName);
		}


		try
		{
			Context cx = Context.enter();
            //WebPageResource删除了getFunctionName方法调用其父类方法
			cx.evaluateString(scope, code, resource.getPid(), 1, null);
		}
		catch (Throwable ex)
		{
			log.error("编译页面发生异常: ", ex);
		}
		finally {
			Context.exit();
		}




		JsView view = new JsView(applicationContext, resource, this);

		return view;
	}

	/**
	 * 初始化全部的脚本类型
	 */
	public void init()
	{
        if(log.isDebugEnabled())
		log.debug("创建运行内容.");
		context = Context.enter();
		scope = context.initStandardObjects(null, true);


		//初始化共享参数
		ScriptableObject.putProperty(scope, "context", applicationContext);

		List<WebScriptResource> resources = resourceService.getAll();

		if(CollectionUtils.isEmpty(resources))
			return;

		for(WebScriptResource rs : resources)
		{
			try{
				context.evaluateString(scope, rs.getContext(), rs.getName(), 1, null);
			}
			catch (Exception ex) {
				log.error(ex.getMessage());
			}

		}

	}


	@Override
	public void destroy() throws Exception {
		try
		{
			Context.exit();
		}
		catch (Exception ex)
		{

		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}
}
