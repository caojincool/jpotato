package com.lemsun.form.service;

import com.lemsun.core.LemsunResource;
import com.lemsun.core.service.IResourceOperaterService;
import com.lemsun.form.ScriptResource;

/**
 * 脚本服务接口
 * Created by dpyang on 2014/6/21.
 */
public interface IScriptService<T extends ScriptResource> extends IResourceOperaterService<T>{

    /**
     * 根据全局脚本组件获取其组件内容
     * @param scriptResource 全局脚本组件
     * @return 脚本组件内容
     */
    String getScriptContent(T scriptResource);

    /**
     * 根据基本组件获取组件内容
     * @param resource 基本组件
     * @return 组件内容
     */
    String getScriptContent(LemsunResource resource);

    /**
     * 根据组件PID获取组件内容
     * @param pid 组件PID
     * @return 组件内容
     */
    String getScriptContent(String pid);

    /**
     * 更新组件内容
     * @param resource 基本组件
     * @param content 组件内容
     */
    void updateScriptContent(LemsunResource resource,String content);

}
