package com.lemsun.form.service;

import com.lemsun.core.LemsunResource;
import com.lemsun.core.service.IResourceOperaterService;
import com.lemsun.form.ScriptResource;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-12-20
 * Time: 下午4:08
 */
public interface IScriptResourceService extends IScriptService<ScriptResource>{

    /**
     * 根据全局脚本组件获取其组件内容
     * @param scriptResource 全局脚本组件
     * @return 脚本组件内容
     */
    String getScriptContent(ScriptResource scriptResource);

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
