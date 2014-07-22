package com.lemsun.client.core.service;

import com.lemsun.client.core.IAccount;
import com.lemsun.client.core.model.WebPageResource;

import java.io.IOException;

/**
 * 页面组件服务接口
 * User: dpyang
 * Date: 13-5-7
 * Time: 下午1:52
 */
public interface IWebPageResourceService{

    /**
     * 根据账户信息获取页面组件资源
     * @param account 账户
     * @return 页面组件资源
     */
    WebPageResource getHomeWebPageResource(IAccount account);

    /**
     * 根据账户和组件编码获取组件资源
     * @param account 账户
     * @param pid 页面组件编码
     * @return 页面组件资源
     */
    WebPageResource getWebPageResource(IAccount account, String pid);

    /**
     * 使用操作用户的账号获取组件
     * @param pid 组件id
     * @return 组件对象
     * @throws java.io.IOException 获取异常
     */
    WebPageResource getWebPageResource(String pid);

    /**
     * 获取页面组件的附加文件
     * @param resourcePid 组件
     * @param fileName 文件名称
     * @return 文件内容
     * @throws IOException
     */
    byte[] getWebPageResourceAttachContext(String resourcePid, String fileName);

    /**
     * 获取页面组件的内容
     * @param resource 页面组件对象
     * @return 内容 ( 如果是二进制的以 base64 进行编码)
     * @throws IOException
     */
    String getWebPageResourceContext(WebPageResource resource);
}
