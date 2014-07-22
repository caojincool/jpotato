package com.lemsun.web.manager.controller.model.component;

import com.lemsun.form.WebPageParam;
import com.lemsun.form.WebPageResource;

import java.util.Set;

/**
 * 基本组件模型(单据显示类型)
 * User: dpyang
 * Date: 13-3-21
 * Time: 下午1:12
 * To change this template use File | Settings | File Templates.
 */
public class WebPageResourceModel extends LemsunResourceModel {

    private Set<WebPageParam> startParams;
    private String contextType;
    private String initScript;
    private String endScript;
    private boolean cache;
    private int cacheTime;
    private boolean page;
    private String context;
    private String formScript;

    public Set<WebPageParam> getStartParams() {
        return startParams;
    }

    public void setStartParams(Set<WebPageParam> startParams) {
        this.startParams = startParams;
    }

    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    public String getInitScript() {
        return initScript;
    }

    public void setInitScript(String initScript) {
        this.initScript = initScript;
    }

    public String getEndScript() {
        return endScript;
    }

    public void setEndScript(String endScript) {
        this.endScript = endScript;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public int getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(int cacheTime) {
        this.cacheTime = cacheTime;
    }

    public boolean isPage() {
        return page;
    }

    public void setPage(boolean page) {
        this.page = page;
    }

    /**
     * 获取web内容
     */
    public String getContext() {
        return context;
    }

    /**
     * 设置web内容
     */
    public void setContext(String context) {
        this.context = context;
    }

    public String getFormScript() {
        return formScript;
    }

    public void setFormScript(String formScript) {
        this.formScript = formScript;
    }

    /**
     * 全新封装一个Web组件
     */
    public WebPageResource encapsulationWebPageResource() {
        WebPageResource webResource = new WebPageResource(getName());

        return encapsulationWebPageResource(webResource);
    }

    /**
     * 封装WEB组件信息
     *
     */
    public WebPageResource encapsulationWebPageResource(WebPageResource wpr) {

        wpr.setEndScript(getEndScript());
        wpr.setInitScript(getInitScript());
        wpr.setRemark(getRemark());
        wpr.setCache(isCache());
        wpr.setCacheTime(getCacheTime());
        wpr.setPage(isPage());
        wpr.setContextType(getContextType());
        wpr.setParentPid(getParentPid());
        wpr.setStrParams(getStrParams());
        wpr.setStartParam(getStartParams());
        wpr.setFormScript(getFormScript());
        wpr.setName(getName());
        return wpr;
    }
}
