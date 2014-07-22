package com.lemsun.client.core;

import com.lemsun.client.core.model.WebPageParam;

import java.util.Date;
import java.util.List;

/**
 * 定义网页视图的组件接口
 * User: 宗旭东
 * Date: 13-3-12
 * Time: 下午4:00
 */
public interface IWebViewResource {

    /**
     * 组件ID
     */
    String getPid();

    /**
     * 获取组件设置的父组件模板
     */
    String getParentPid();

    /**
     * 获取组件的名称
     */
    String getName();

    /**
     * 获取视图的类型
     */
    String getCotnextType();

    /**
     * 获取组件更新的时间
     */
    Date getUpdateTime();

    /**
     * 获取开始请求脚本
     */
    String getInitScript();

    /**
     * 获取结束请求脚本
     */
    String getEndScript();

    /**
     * 获取提交脚本
     */
    String getFormScript();

    /**
     * 获取组件开始开始参数
     */
    List<WebPageParam> getStartParam();
}
