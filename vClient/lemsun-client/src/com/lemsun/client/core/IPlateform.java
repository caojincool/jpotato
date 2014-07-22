package com.lemsun.client.core;

import java.util.Hashtable;

/**
 * User: 宗旭东
 * Date: 13-3-9
 * Time: 上午9:31
 */
public interface IPlateform {

    /**
     * 获取平台主键
     */
    String getPid();

    /**
     * 获取参数
     */
    Hashtable<String, Object> getParam();

    /**
     * 参数表达式
     */
    String getParamstring();

    /**
     * 获取组件
     */
    String getKey();

    /**
     * 获取启动资源
     */
    String getStartResource();

    /**
     * 获取启动脚本
     */
    String getStartScript();

    /**
     * 获取结束脚本
     */
    String getEndScript();

    /**
     * 获取参数
     */
    Object getAttribute(String key);
}
