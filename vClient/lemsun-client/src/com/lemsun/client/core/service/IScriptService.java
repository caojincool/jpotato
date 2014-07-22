package com.lemsun.client.core.service;

import com.lemsun.client.core.model.InnerFunctionDefines;
import com.lemsun.client.core.model.ScriptResource;
import com.lemsun.client.core.model.WebPageResource;

import java.io.IOException;
import java.util.List;

/**
 * 脚本服务接口
 * User: 宗旭东
 * Date: 13-2-20
 * Time: 下午4:45
 */
public interface IScriptService {

    /**
     * 获取全局脚本和网页脚本
     *
     * @return 全局脚本和网页脚本
     */
    List<ScriptResource> getGlobelScriptResources();

    /**
     * 根据组件编码获取该组件定义的函数脚本
     * @param pid 网页组件编码
     * @return 如果是网页组件存在并且定义有函数列表,就返回对应的函数列表.否则就返回空
     * @throws IOException 读写异常
     */
    List<InnerFunctionDefines> getInnerScripts(String pid);

    /**
     * 根据网页组件获取该组件定义的函数脚本
     * @param resource 网页组件
     * @return 如果是网页组件存在并且定义有函数列表,就返回对应的函数列表.否则就返回空
     * @throws IOException 读写异常
     */
    List<InnerFunctionDefines> getInnerScripts(WebPageResource resource);
}
