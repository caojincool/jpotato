package com.lemsun.client.core.mvc.lmsview;

import com.lemsun.client.core.Host;
import com.lemsun.client.core.IAccount;
import com.lemsun.client.core.IWebViewResource;
import com.lemsun.client.core.service.IFormulaService;
import org.mozilla.javascript.Function;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 视图渲染的上下文
 * User: xudong
 * Date: 13-12-11
 * Time: 下午2:36
 */
public class ViewScope {

    /**
     * 当前账户对象
     */
    private IAccount account;

    /**
     * 当前作用域的开始参数
     */
    private Map<String, ?> param;

    /**
     * 当前作用域的视图解析引擎
     */
    private IViewEngine engine;

    /**
     * 当前作用域的日志操作对象
     */
    private Logger log;

    /**
     * 当前作用域的视图导航对象
     */
    private LmsNativeObject localScope;
    /**
     * 当前作用域的web组件
     */
    private IWebViewResource resource;

    /**
     * 当前作用域的视图输出对象
     */
    private IViewWriter writer;

    /**
     * 视图请求对象
     */
    private HttpServletRequest request;

    /**
     * 视图响应对象
     */
    private HttpServletResponse response;

    /**
     * 当前视图是否提交状态
     */
    private boolean postback;

    /**
     * 当前作用域的主机信息
     */
    private Host host;

    /**
     * 当前视图中作用域中的父作用域
     */
    private ViewScope parentScope;

    /**
     * 当前视图作用域的的错误信息
     */
    private List<String> errors;

    /**
     * 当前组件视图
     */
    private ILmsView view;

    /**
     * 当前作用域中的结束函数
     */
    private List<Function> finishFuns; //最终渲染输出运行的函数

    /**
     * 是否返回当前作用域
     */
    private boolean isReturn;


    public ViewScope(
            HttpServletRequest request,
            HttpServletResponse response,
            IAccount account,
            Host host,
            IViewEngine viewEngine,
            boolean postback,
            List<String> errors,
            LmsNativeObject localScope,
            Logger log)
    {
        this.request = request;
        this.response = response;
        this.postback = postback;
        this.account = account;
        this.host = host;
        this.engine = viewEngine;
        this.errors = errors;
        this.log = log;
        this.localScope = localScope;
        this.localScope.setViewScope(this);
    }


    private ViewScope()
    {
    }

    /**
     * 获取当前的视图对象
     */
    public ILmsView getView() {
        return view;
    }

    void setView(ILmsView view) {
        this.view = view;
    }

    /**
     * 获取当前的异常列表
     */
    public List<String> getErrors() {
        return parentScope != null ?  parentScope.errors : errors;
    }

    /**
     * 获取当前的视图渲染是否还有父视图
     */
    public ViewScope getParentScope() {
        return parentScope;
    }



    /**
     * 获取主机信息
     */
    public Host getHost() {
        return parentScope != null ? parentScope.host : host;
    }

    /**
     * 获取账号信息
     * @return 账号信息
     */
    public IAccount getAccount() {
        return parentScope != null ? parentScope.account : account;

    }
    /**
     * 获取系统容器
     */
    public ApplicationContext getContext() {
        return Host.getApplicationContext();
    }


    /**
     * 获取上下文参数
     */
    public Map<String, ?> getParam() {
        return param;
    }

    /**
     * 设置上下文参数
     */
    void setParam(Map<String, ?> param) {
        this.param = param;
    }

    /**
     * 获取视图引擎
     */
    public IViewEngine getEngine() {
        return parentScope != null ? parentScope.engine : engine;
    }

    /**
     * 获取日志对象
     */
    public Logger getLog() {
        return parentScope != null ? parentScope.log : log;
    }


    /**
     * 获取当前组件对象
     */
    public IWebViewResource getResource() {
        return resource;
    }

    /**
     * 设置当前的组件对象
     */
    void setResource(IWebViewResource resource) {
        this.resource = resource;
    }

    /**
     * 获取当前视图的脚本上下文
     */
    public LmsNativeObject getLocalScope() {
        return parentScope != null ? parentScope.localScope : localScope;
    }


    /**
     * 获取输出对象
     */
    public IViewWriter getWriter() {
        return writer;
    }

    void setWriter(IViewWriter writer) {
        this.writer = writer;
    }

    /**
     * 获取页面请求对象
     */
    public HttpServletRequest getRequest() {
        return parentScope != null ? parentScope.request : request;
    }

    /**
     * 获取页面回复对象
     */
    public HttpServletResponse getResponse() {

        return parentScope != null ? parentScope.response : response;
    }

    public IFormulaService getFormula() {
        return getContext().getBean(IFormulaService.class);
    }

    /**
     * 获取当前的视图是否是提交的页面
     */
    public boolean isPostback() {
        return parentScope != null ? parentScope.postback : postback;
    }


    /**
     * 创建一个新的视图上下文. 专门给子视图渲染使用
     */
    public ViewScope createScope()
    {
        ViewScope child = new ViewScope();
        child.parentScope = this;
        child.setParam(getParam());
        return child;
    }


    /**
     * 获取渲染完成后执行的函数
     */
    public List<Function> getFinishFuns()
    {
        return finishFuns;
    }

    /**
     * 在运行结束后执行的函数
     * @param fun
     */
    public void addFinishFun(Function fun)
    {
        if(parentScope != null) {
            parentScope.addFinishFun(fun);
        }
        else {
            if(finishFuns == null) finishFuns = new ArrayList<>();
            finishFuns.add(fun);
        }
    }

    public boolean isReturn() {
        return getParentScope()!=null?getParentScope().isReturn():isReturn;
    }

    public void setReturn(boolean isReturn) {
        if (getParentScope()!=null)
            setReturn(isReturn);
        else
            this.isReturn = isReturn;
    }

    //    /**
//     * 是否继续解析
//     * @return 继续解析视图
//     */
//    public boolean isCarryOn() {
//        return getParentScope() != null ? getParentScope().isCarryOn() : carryOn;
//    }
//
//    /**
//     * 是否继续解析
//     * @param carryOn
//     */
//    public void setCarryOn(boolean carryOn) {
//        if(getParentScope() != null)
//            getParentScope().setCarryOn(carryOn);
//            else
//            this.carryOn = carryOn;
//    }

    public void close()
    {
        if(getParentScope() != null) {
            ViewScope scope = getParentScope();
            scope.getLocalScope().setViewScope(scope);
        }
    }

    @Override
    public String toString() {
        return getWriter().toString();
    }
}
