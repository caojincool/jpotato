package com.lemsun.client.core.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lemsun.client.core.Host;
import com.lemsun.client.core.IResponseEntity;
import com.lemsun.client.core.LemsunException;
import com.lemsun.client.core.Utils;
import com.lemsun.client.core.model.InnerFunctionDefines;
import com.lemsun.client.core.model.LemsunResource;
import com.lemsun.client.core.model.ScriptResource;
import com.lemsun.client.core.model.WebPageResource;
import com.lemsun.client.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dpyang
 * Date: 13-11-25
 * Time: 下午3:02
 *
 */
@Service
public class ScriptServiceImpl implements IScriptService {

    private IRemoteService remoteService;
    private IAccountService accountService;
    private Map<String,LemsunResource> cacheResource; //从服务器中拿取的组件缓存起来比较快
    private Host host;

    private static final String ScriptResourceAddress = "script/get";
       private static  final  String ResourceInnerScript="script/inner/get";
    @Autowired
    public ScriptServiceImpl(IRemoteService remoteService,
                               IAccountService accountService,
                               @Qualifier("host") Host host) {
        this.remoteService = remoteService;
        this.host = host;
        this.accountService = accountService;
    }


    @Override
    public List<ScriptResource> getGlobelScriptResources() {
        String url = ScriptResourceAddress;

        IResponseEntity<List<ScriptResource>> entity =
                null;
        try {
            entity = remoteService.getAddressContext(
                    url,
                    new TypeReference<IResponseEntity<List<ScriptResource>>>() {}
            );

            if(!entity.isSuccess())
                throw new IOException(entity.getMessage());
        } catch (IOException e) {
            throw new LemsunException("获取全局脚本异常", e);
        }


        return entity.getEntity();
    }

    @Override
    public List<InnerFunctionDefines> getInnerScripts(String pid) {

        if(!Utils.isResourceId(pid))
            throw new RuntimeException("组件编码不符合规范!");

        String url = ResourceInnerScript+String.format("?pid=%s",pid);

        IResponseEntity<List<InnerFunctionDefines>> entity =
                remoteService.getAddressContext(
                        url,
                        new TypeReference<IResponseEntity<List<InnerFunctionDefines>>>() {}
                );
        if(!entity.isSuccess())
            throw new LemsunException(entity.getMessage());

        return entity.getEntity();

    }

    @Override
    public List<InnerFunctionDefines> getInnerScripts(WebPageResource resource) {

        return getInnerScripts(resource.getPid());
    }
}
