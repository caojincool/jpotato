package com.lemsun.client.core.model;

import com.lemsun.client.core.IWebViewResource;

import java.util.List;

/**
 * web组件资源
 * User: dpyang
 * Date: 13-5-7
 * Time: 下午1:35
 */
public class WebPageResource extends LemsunResource implements IWebViewResource{

    //这里把webpageResource的属性都放在这里,目的是要处理对webpageResource的属性处理
    private String initScript;
    private String endScript;
    private String formScript;
    private boolean cache;
    private int cacheTime;
    private List<WebPageParam> startParam;
    private String contextType;

    /**
     * 获取开始请求脚本
     */
    public String getInitScript() {
        return initScript;
    }

    /**
     * 设置开始请求脚本
     * @param initScript
     */
    public void setInitScript(String initScript) {
        this.initScript = initScript;
    }

    /**
     * 获取结束请求脚本
     * @return
     */
    public String getEndScript() {
        return endScript;
    }

    /**
     * 设置教书请求脚本
     * @param endScript
     */
    public void setEndScript(String endScript) {
        this.endScript = endScript;
    }

    /**
     * 获取组件是否缓存
     * @return
     */
    public boolean isCache() {
        return cache;
    }

    /**
     * 设置组件是否缓存
     * @param cache
     */
    public void setCache(boolean cache) {
        this.cache = cache;
    }

    /**
     * 获取组件缓存时间
     * @return
     */
    public int getCacheTime() {
        return cacheTime;
    }

    /**
     * 设置组件缓存时间
     * @param cacheTime
     */
    public void setCacheTime(int cacheTime) {
        this.cacheTime = cacheTime;
    }

    /**
     * 获取组件开始参数
     * @return
     */
    public List<WebPageParam> getStartParam() {
        return startParam;
    }

    /**
     * 设置组件开始参数
     * @param startParam
     */
    public void setStartParam(List<WebPageParam> startParam) {
        this.startParam = startParam;
    }

    /**
     * 获取视图的类型
     */
    @Override
    public String getCotnextType() {
        return contextType;
    }
    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    /**
     * 获取组件的提交脚本
     * @return
     */
    public String getFormScript() {
        return formScript;
    }

    /**
     * 设置组件的提交脚本
     * @param formScript
     */
    public void setFormScript(String formScript) {
        this.formScript = formScript;
    }
}
