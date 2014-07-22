package com.lemsun.client.core.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lemsun.client.core.*;
import com.lemsun.client.core.model.LemsunResource;
import com.lemsun.client.core.model.WebPageResource;
import com.lemsun.client.core.service.IAccountService;
import com.lemsun.client.core.service.IRemoteService;
import com.lemsun.client.core.service.IResourceService;
import com.lemsun.client.core.service.IWebPageResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * 页面组件服务实现类
 * User: dpyang
 * Date: 13-5-7
 * Time: 下午1:56
 */
@Service
@Qualifier("webPageResourceService")
public class WebPageResourceServiceImpl implements IWebPageResourceService {

    private IRemoteService remoteService;             //远程服务专门为页面组件服务
    private IResourceService resourceService;         //把页面组件需要的组件内容传递给基本组件让其为自己服务
    private IAccountService accountService;
    private Map<String,LemsunResource> cacheResource; //从服务器中拿取的组件缓存起来比较快
    private Host host;

    private static final String ResourceAddress = "resource/get";
    private static final String ResourceContextAttachAddress = "resource/content/attach/get";
    private static final Logger log= LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    public WebPageResourceServiceImpl(IRemoteService remoteService,
                               IAccountService accountService,
                               IResourceService resourceService,
                               @Qualifier("host") Host host) {
        this.remoteService = remoteService;
        this.host = host;
        this.accountService = accountService;
        this.resourceService=resourceService;
    }

    /**
     * 根据账户信息获取页面组件资源
     *
     * @param account 账户
     * @return 页面组件资源
     */
    @Override
    public WebPageResource getHomeWebPageResource(IAccount account) {
        String homePid = host.getPlateform().getStartPage();
        if(!Utils.isResourceId(homePid)){
            homePid=host.getPlateform().getOwner().getPid();
        }
        return getWebPageResource(account,homePid);
    }

    /**
     * 根据账户和组件编码获取组件资源
     *
     * @param account 账户
     * @param pid     页面组件编码
     * @return 页面组件资源
     */
    @Override
    public WebPageResource getWebPageResource(IAccount account, String pid) {
        String url = ResourceAddress
                + String.format("?type=webpage&pid=%s&token=%s", pid, account == null ? "" : account.getToken());

        IResponseEntity<WebPageResource> entity =
                remoteService.getAddressContext(
                        url,
                        new TypeReference<IResponseEntity<WebPageResource>>() {}
                );

        if(!entity.isSuccess()) {
            throw new LemsunException("通信获取异常");
        }

        return entity.getEntity();
    }

    /**
     * 使用操作用户的账号获取组件
     *
     * @param pid 组件id
     * @return 组件对象
     * @throws java.io.IOException 获取异常
     */
    @Override
    public WebPageResource getWebPageResource(String pid) {
        return getWebPageResource(accountService.getCurrentAccount(), pid);
    }

    /**
     * 获取资源组件的附加文件
     *
     * @param resourcePid 组件
     * @param fileName    文件名称
     * @return 文件内容
     * @throws java.io.IOException
     */
    @Override
    public byte[] getWebPageResourceAttachContext(String resourcePid, String fileName) {

        //这里直接从基本组件提供的服务获取
        return resourceService.getResourceAttachContext(resourcePid,fileName);
    }

    /**
     * 获取页面组件的内容
     *
     * @param resource 页面组件对象
     * @return 内容 ( 如果是二进制的以 base64 进行编码)
     * @throws java.io.IOException
     */
    @Override
    public String getWebPageResourceContext(WebPageResource resource) {

        //这里也直接从基本组件提供的服务获取
        return resourceService.getResourceContext(resource);
    }
}
