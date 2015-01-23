package com.lemsun.client.core.mvc.lmsview;

import com.lemsun.client.core.Host;
import com.lemsun.client.core.IWebViewResource;
import com.lemsun.client.core.model.InnerFunctionDefines;
import com.lemsun.client.core.model.LemsunResource;
import com.lemsun.client.core.model.WebPageParam;
import com.lemsun.client.core.service.IAccountService;
import com.lemsun.client.core.service.ILmsViewService;
import com.lemsun.client.core.service.IResourceService;
import com.lemsun.client.core.service.IScriptService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.select.Elements;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 抽象的视图, 规定了视图的生命周期, 执行顺序等
 * User: xudong
 * Date: 13-12-11
 * Time: 上午11:22
 *
 */
public abstract class AbstractLmsView implements ILmsView {

    private IWebViewResource resource;
    private IScriptService scriptService;
    private IViewEngine viewEngine;
    private IResourceService resourceService;
    private List<Script> innerScript = new ArrayList<>();
    private List<String> errors = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(AbstractLmsView.class);
    private HashMap<String, WebPageParam> startParam;
    private static final String Postback_Token = "page_token";

    protected AbstractLmsView(IWebViewResource resource) {
        this.resource = resource;
        this.scriptService = Host.getApplicationContext().getBean(IScriptService.class);
        this.viewEngine = Host.getApplicationContext().getBean(IViewEngine.class);
        this.resourceService = Host.getApplicationContext().getBean(IResourceService.class);
        Init();
    }


    static {

        //Entities.EscapeMode.base.getMap().clear();
    }

    /**
     * 初始化当前的组件
     */
    protected void Init()
    {
        //整理内部定义的函数
        List<InnerFunctionDefines> funs = getInnerFunctions();

        if(funs != null)
        {
            Context.enter();

            for(InnerFunctionDefines f : funs)
            {
                try
                {

                    Script s = getViewEngine().getContext().compileString(f.toScript(), f.getName(), 1, null);
                    innerScript.add(s);
                }
                catch (Exception ex)
                {
                    errors.add("初始化定义脚本异常:\n" + ex.toString());
                }
            }

            Context.exit();
        }

    }

    /**
     * 获取脚本服务
     */
    public IScriptService getScriptService() {
        return scriptService;
    }

    /**
     * 获取组件服务
     */
    public IResourceService getResourceService() {
        return resourceService;
    }

    /**
     * 获取内部定义的脚本
     */
    public List<Script> getInnerScript() {
        return innerScript;
    }

    /**
     * 获取组件加载的异常
     */
    public List<String> getErrors() {
        return errors;
    }

    @Override
    public IWebViewResource getResource() {
        return resource;
    }

    @Override
    public Date getUpdateTime() {
        return resource.getUpdateTime();
    }

    @Override
    public Map<String, WebPageParam> getStartParam() {

        if(startParam == null)
        {
            startParam = new HashMap<>();

            if(resource.getStartParam() != null)
                for(WebPageParam pageParam : resource.getStartParam())
                {
                    if(pageParam.getCate() == WebPageParam.AERY
                            && StringUtils.equalsIgnoreCase(pageParam.getValue(), "this"))
                    {
                        pageParam.setValue(getResource().getPid());
                    }

                    startParam.put(pageParam.getName(), pageParam);
                }
        }
        return startParam;

    }

    @Override
    public List<InnerFunctionDefines> getInnerFunctions() {
        return scriptService.getInnerScripts(resource.getPid());
    }

    @Override
    public IViewEngine getViewEngine() {
        return viewEngine;
    }

    @Override
    public String getContext() {

        if(resource instanceof LemsunResource)
        {
            return resourceService.getResourceContext((LemsunResource) resource);
        }

        return "";
    }

    @Override
    public String getStartScript() {
        return resource.getInitScript();
    }

    @Override
    public String getPostbackScript() {
        return resource.getFormScript();
    }

    @Override
    public String getEndScript() {
        return resource.getEndScript();
    }

    @Override
    public boolean isCache() {
        return false;
    }

    /**
     * 创建一个视图渲染的上下文环境
     * @return 视图渲染的上下文环境
     */
    public LmsNativeObject createScriptScope(HttpServletRequest request, HttpServletResponse response)
    {
        LmsNativeObject sope = null;

        Context.enter();
        Context cx = getViewEngine().getContext();

        sope = new LmsNativeObject(getViewEngine());

        ScriptableObject.putProperty(sope, "request", request);
        ScriptableObject.putProperty(sope, "response", response);
        ScriptableObject.putProperty(sope, "session", request.getSession());
        ScriptableObject.putProperty(sope, "account", Host.getApplicationContext().getBean(IAccountService.class).getCurrentAccount());

        for(Script s : innerScript)
        {
            try
            {
                s.exec(cx, sope);
            }
            catch (Exception ex) {
                errors.add("执行定义脚本异常:\n" + ex.getMessage());
                if(log.isDebugEnabled()) log.debug("执行定义脚本异常:\n" + ex.getMessage());
            }
        }

        Context.exit();

        return sope;
    }



    @Override
    public String getContentType() {
        return resource.getCotnextType();
    }


    @Override
    public void render(ViewScope scope) {
        //创建脚本运行的上下文
        scope.setView(this);
        scope.setResource(resource);
        scope.setWriter(new ViewWriterImpl());
        scope.getLocalScope().setViewScope(scope);
        scope.getLocalScope().put(getResource().getPid(), scope.getLocalScope(), scope);

        //初始化启动脚本
        Context.enter();

        //合并开始参数
        scope.setParam(preparStartParam(scope));


        //执行开始脚本
        executeStartScript(scope);

        //当前上下文有返回:response.sendRedirect();
        if(scope.isReturn())
            return;


        //执行请求脚本
        executePastbackScript(scope);

        //执行渲染内容
        executeContext(scope);

        //执行结束脚本
        executeEndScript(scope);

        //环境退出
        Context.exit();

        String parent = getResource().getParentPid();

        if(!StringUtils.isEmpty(parent))
        {
            ILmsView view = (ILmsView)Host.getApplicationContext().getBean(ILmsViewService.class).getView(parent);
            ViewScope parentScope = scope.createScope();
            try
            {
                view.render(parentScope);
            }
            catch (Exception ex) {
                log.error("父组件渲染异常:"+ex.getMessage());
                if(log.isDebugEnabled())
                    log.debug("渲染父组件出现异常:"+ex.getMessage());
            }
            finally {
                scope.setWriter(parentScope.getWriter());
                parentScope.close();
            }

        }


        scope.close();
    }

    /**
     * 将当前的视图直接渲染到页面
     * @param model
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String token = request.getParameter(Postback_Token);

        boolean postback = StringUtils.equals(token, (String)request.getSession().getAttribute(Postback_Token));

        request.getSession().setAttribute(Postback_Token, UUID.randomUUID().toString());

        //TODO 检查请求是否是提交请求
        ViewScope scope = new ViewScope(request,
                response,
                Host.getApplicationContext().getBean(IAccountService.class).getCurrentAccount(),
                Host.getInstance(),
                getViewEngine(),
                postback,
                new ArrayList<String>(),
                createScriptScope(request, response),
                log);

        scope.setParam(model);

        render(scope);

        //子视图中有返回也直接跳出
        if(scope.isReturn()) {
            response.getOutputStream().close();
            return;
        }

        //执行整个视图渲染完成后输出部分
        executeRenderFinish(scope);
    }


    /**
     * 执行开始参数
     */
    public void executeStartScript(ViewScope scope) {
        try
        {
            String startScript = getStartScript();
            if(StringUtils.isNotEmpty(startScript))
            {
                getViewEngine().getContext().evaluateString(scope.getLocalScope(), startScript, "组件开始脚本", 1, null);
            }
        }
        catch (Exception ex) {
            scope.getErrors().add("开始脚本执行异常:\n" + ex.getMessage());
            if (log.isDebugEnabled()) log.debug("开始脚本执行异常:\n" + ex.getMessage());
        }
    }

    /**
     * 如果当前页面是提交, 那么执行提交脚本
     */
    public void executePastbackScript(ViewScope scope) {
        try
        {
            if(!scope.isPostback()) return;

            String script = getPostbackScript();
            if(StringUtils.isNotEmpty(script))
            {
                getViewEngine().getContext().evaluateString(scope.getLocalScope(), script, "组件提交脚本", 1, null);
            }
        }
        catch (Exception ex) {
            errors.add("组件提交脚本执行异常:\n" + ex.getMessage());
            log.error("组件提交脚本执行异常:"+ex.getMessage());
        }
    }


    /**
     * 执行显示内容
     */
    public abstract void executeContext(ViewScope scope);


    /**
     * 执行结束脚本
     */
    public void executeEndScript(ViewScope scope) {
        try
        {
            String startScript = getEndScript();
            if(StringUtils.isNotEmpty(startScript))
            {
                getViewEngine().getContext().evaluateString(scope.getLocalScope(), startScript, "组件开始脚本", 1, null);
            }
        }
        catch (Exception ex) {
            errors.add("开始脚本执行异常:\n" + ex.getMessage());
            if(log.isDebugEnabled())
                log.error("开始脚本执行异常:\n"+ex.getMessage());
        }
    }

    /**
     * 执行最后的渲染输出
     * @param scope 最终的视图会话
     */
    public void executeRenderFinish(ViewScope scope)
    {
        //获取最终输出内容
        String html = scope.toString();
        HttpServletRequest request = scope.getRequest();
        HttpServletResponse response = scope.getResponse();

        Document doc = executeHtmlRender(html, scope, scope.getRequest(), scope.getResponse());
        //doc.outputSettings().escapeMode();
        doc.outputSettings().charset("utf-8");
        List<Function> funs = scope.getFinishFuns();

        if(funs != null)
        {
            Context.enter();

            Context cx = scope.getEngine().getContext();
            for(Function f : funs)
            {
                try
                {
                    f.call(cx, scope.getLocalScope(), scope.getLocalScope(), new Object[]{ doc });
                }
                catch (Exception ex)
                {
                    scope.getErrors().add("注册的最终函数 " + f.getClassName() + "运行异常:\n" + ex.getMessage());
                    if(log.isInfoEnabled()) log.info(ex.getMessage());
                }
            }

            Context.exit();
        }


        html = doc.html();

        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType(getContentType());
            response.getWriter().write(html);
            response.getWriter().flush();
        } catch (IOException e) {
            scope.getErrors().add("输出HTML异常:\n" + e.getMessage());
            log.error("输出HTML异常"+e.getMessage());
        }

    }


    /**
     * 执行完成渲染后的工作, 比如整理连接, 应用设置的属性等.
     * @param scope 视图会话
     * @param request 请求对象
     * @param response 回复对象
     * @return 视图内容
     */
    public Document executeHtmlRender(String page,
                                    ViewScope scope,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {

        String baseUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/" + resource.getPid() +"/";

        Document doc = Jsoup.parse(page, baseUrl);

        //转换相对路径
        Elements elements = doc.getElementsByAttribute("href");

        for(Element element : elements) {
            String url = element.attr("href");

            if(StringUtils.isNotEmpty(url) && !url.startsWith("#") && !url.contains("ilemsun://")) {
                String abs = element.attr("abs:href");



                element.attr("href", abs);
            }
        }

        elements = doc.getElementsByAttribute("src");

        for(Element element : elements) {
            String url = element.attr("src");
            if(StringUtils.isNotEmpty(url)) {
                element.attr("src", element.attr("abs:src"));
            }
        }


        elements = doc.getElementsByTag("form");
        for(Element element : elements) {
            Element hinput = element.appendElement("input");
            hinput.attr("name", Postback_Token);
            hinput.attr("type", "hidden");
            hinput.attr("value", (String)request.getSession().getAttribute(Postback_Token));
        }


        return doc;
    }



    /**
     * 准备开始参数
     */
    public Map<String, ?> preparStartParam(ViewScope scope) {

        //自身定义的参数
        Map<String, WebPageParam> startparam = getStartParam();

        //传入的参数
        Map<String, ?> param = scope.getParam();

        HashMap<String, Object> viewParam = new HashMap<String, Object>(startparam);


        if(param != null) {
            for (String key : param.keySet()) {

                if(viewParam.containsKey(key))
                {
                    viewParam.remove(key);
                }
                viewParam.put(key, param.get(key));
            }
        }
        return viewParam;
    }
}
