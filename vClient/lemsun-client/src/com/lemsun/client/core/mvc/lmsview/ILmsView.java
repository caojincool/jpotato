/**
 * 5代网页端视图解析包
 */
package com.lemsun.client.core.mvc.lmsview;

import com.lemsun.client.core.IWebViewResource;
import com.lemsun.client.core.model.InnerFunctionDefines;
import com.lemsun.client.core.model.WebPageParam;
import org.springframework.web.servlet.View;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User: 宗旭东
 * Date: 13-12-10
 * Time: 下午4:28
 */
public interface ILmsView extends View {

    /**
     * 获取视图的组件对象
     */
    public IWebViewResource getResource();

    /**
     * 获取视图更新时间
     */
    public Date getUpdateTime();

    /**
     * 获取开始参数
     */
    public Map<String, WebPageParam> getStartParam();


    /**
     * 获取内部的自定义函数列表
     */
    public List<InnerFunctionDefines> getInnerFunctions();


    /**
     * 获取当前的视图引擎
     */
    public IViewEngine getViewEngine();


    /**
     * 获取组件定义的内容
     */
    public String getContext();


    /**
     * 获取启动脚本
     */
    public String getStartScript();


    /**
     * 获取提交返回执行的脚本
     */
    public String getPostbackScript();


    /**
     * 获取结束时候返回执行的脚本
     */
    public String getEndScript();

    /**
     * 获取是否缓存渲染的内容. 如果返回为 true 那么就将之前的渲染内容直接输出
     */
    public boolean isCache();

    /**
     * 渲染当前的视图组件
     * @param scope 当前的视图上下文
     */
    public void render(ViewScope scope);

}
