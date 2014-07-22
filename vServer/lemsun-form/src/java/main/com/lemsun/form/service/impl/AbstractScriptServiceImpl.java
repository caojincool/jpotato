package com.lemsun.form.service.impl;

import com.lemsun.core.LemsunException;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.service.impl.AbstractResourceSupportService;
import com.lemsun.form.ScriptResource;
import com.lemsun.form.service.IScriptService;
import org.apache.commons.lang.StringUtils;

/**
 * Created by dpyang on 2014/6/21.
 */
public abstract class AbstractScriptServiceImpl<T extends ScriptResource> extends AbstractResourceSupportService<T> implements IScriptService<T> {
    /**
     * 构造一个对组件的基础操作支持的对象
     *
     * @param resourceService
     */
    protected AbstractScriptServiceImpl(IResourceService resourceService) {
        super(resourceService);
    }

    @Override
    public String getScriptContent(T scriptResource) {
        return getScriptContent(scriptResource.getPid());
    }

    @Override
    public String getScriptContent(LemsunResource resource) {
        return getScriptContent(resource.getPid());
    }

    @Override
    public String getScriptContent(String pid) {
        if (StringUtils.isEmpty(pid))
            throw new LemsunException("空组件不能获取脚本内容!");
        return getResourceService().getContent(pid);
    }

    @Override
    public void updateScriptContent(LemsunResource resource, String content) {
        if (resource==null)
            throw new   LemsunException("空组件不能更新脚本内容!");

        getResourceService().updateContent(resource.getPid(),content);
    }
}
