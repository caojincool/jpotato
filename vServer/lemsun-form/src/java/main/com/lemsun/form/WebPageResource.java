package com.lemsun.form;

import com.lemsun.core.BaseCategory;
import com.lemsun.core.LemsunResource;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-24
 * Time: 下午2:00
 */
public class WebPageResource extends FormResource {

    public static final String TYPE = "webpage";

    /**
     * 默认构造函�
     * 在repository调用get(pid)方法�
     */
    public WebPageResource(){
        super(null,BaseCategory.WEB_SKIN.getCategory());
    }

    /**
     * 带名字的构造函�
     * 用于手动new WebPageResource
     * @param name
     */
    public WebPageResource(String name) {
        super(name, BaseCategory.WEB_SKIN.getCategory());
    }

    /**
     * 用于组件创建导航用的构造函�
     * @param resource
     */
    public WebPageResource(LemsunResource resource) {
        super(resource.getName(), resource.getCategory());
        setId(resource.getId());
        setPid(resource.getPid());
        setRemark(resource.getRemark());
        setStrParams(resource.getStrParams());
        setCreateUser(resource.getCreateUser());
        setUpdateTime(resource.getUpdateTime());
        setSystem(resource.isSystem());
        setBusinessCode(resource.getBusinessCode());
    }

    private String contextType;
    private String initScript;
    private String endScript;
    private boolean cache;
    private int cacheTime;
    private boolean page;
    private Set<WebPageParam> startParam;
    private String formScript;

    /**
     * 获取页面资源输出类型
     *
     * @return 输出类型
     */
    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    /**
     * 获取页面在执行显示前初始化脚�
     */
    public String getInitScript() {
        return initScript;
    }

    /**
     * 设置页面在初始化前执行的脚本
     */
    public void setInitScript(String initScript) {
        this.initScript = initScript;
    }

    /**
     * 获取页面在执行完成后的执行脚�
     */
    public String getEndScript() {
        return endScript;
    }

    /**
     * 设置页面在执行完成后的执行脚�
     */
    public void setEndScript(String endScript) {
        this.endScript = endScript;
    }

    /**
     * 获取当前的页面是否支持缓�
     */
    public boolean isCache() {
        return cache;
    }

    /**
     * 设置当前的页面是否支持缓�
     */
    public void setCache(boolean cache) {
        this.cache = cache;
    }

    /**
     * 获取缓存的时� 单位�
     */
    public int getCacheTime() {
        return cacheTime;
    }

    /**
     * 设置缓存的时� 单位�
     */
    public void setCacheTime(int cacheTime) {
        this.cacheTime = cacheTime;
    }

    /**
     * 获取当前的组件是否是一个完整的页面
     */
    public boolean isPage() {
        return page;
    }

    /**
     * 设置当前的组件是否是一个完整的页面. 如果设置�True不管用户是否添加�html, head, body 标签. 都有初始化这些标�
     */
    public void setPage(boolean page) {
        this.page = page;
    }

    /**
     * 获取启动参数
     */
    public Set<WebPageParam> getStartParam() {
        return startParam;
    }

    /**
     * 设置启动参数
     */
    public void setStartParam(Set<WebPageParam> startParam) {
        this.startParam = startParam;
    }

    /**
     * 获取提交脚本
     *
     * @return 提交脚本
     */
    public String getFormScript() {
        return formScript;
    }

    /**
     * 设置提交脚本
     *
     * @param formScript 提交脚本
     */
    public void setFormScript(String formScript) {
        this.formScript = formScript;
    }
}
