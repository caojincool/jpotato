package com.lemsun.form.service.impl;

import com.lemsun.core.LemsunException;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.service.impl.AbstractResourceSupportService;
import com.lemsun.form.ScriptResource;
import com.lemsun.form.WebScriptResource;
import com.lemsun.form.service.IWebScriptResourceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-29
 * Time: 下午2:02
 */
@Service
public class WebScriptResourceServiceImpl extends AbstractScriptServiceImpl<WebScriptResource> implements IWebScriptResourceService {
    /**
     * 构造一个对组件的基础操作支持的对象
     *
     * @param resourceService
     */
    @Autowired
    protected WebScriptResourceServiceImpl(IResourceService resourceService) {
        super(resourceService);
    }

    @Override
    public String getScriptContent(WebScriptResource scriptResource) {

        return getScriptContent(scriptResource.getPid());
    }
//
}
