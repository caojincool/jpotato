package com.lemsun.client.core.mvc.lmsview;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/**
 * 视图引擎
 * User: 宗旭东
 * Date: 13-12-10
 * Time: 下午6:23
 */
public interface IViewEngine {

    /**
     * 获取脚本运行的共享环境
     */
    public Context getContext();


    /**
     * 获取一个加载了全局脚本的上下文对象
     */
    public ScriptableObject getGlobelScriptable();

}
