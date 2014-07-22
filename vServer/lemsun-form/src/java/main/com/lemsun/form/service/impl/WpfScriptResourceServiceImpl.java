package com.lemsun.form.service.impl;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.Host;
import com.lemsun.core.LemsunException;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.events.ResourceEvent;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.service.impl.AbstractResourceSupportService;
import com.lemsun.core.util.Pid;
import com.lemsun.core.util.ResourceUtil;
import com.lemsun.form.WpfScriptResource;
import com.lemsun.form.repository.FormResourceRepository;
import com.lemsun.form.repository.ScriptResourceRepository;
import com.lemsun.form.repository.WpfScriptResourceRepository;
import com.lemsun.form.service.IWpfScriptResourceService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-5
 * Time: 下午1:49
 */
@Service
public class WpfScriptResourceServiceImpl extends AbstractScriptServiceImpl<WpfScriptResource> implements IWpfScriptResourceService {

    /**
     * 构造一个对组件的基础操作支持的对象
     *
     * @param resourceService
     */
    @Autowired
    protected WpfScriptResourceServiceImpl(IResourceService resourceService) {
        super(resourceService);
    }



//    @Override
//    public String getScriptContent(LemsunResource resource) {
//        return getScriptContent(resource.getPid());
//    }
//
//    @Override
//    public String getScriptContent(String pid) {
//        return getResourceService().getContent(pid);
//    }
//
//    /**
//     * 根据基本组件更新组件内容
//     * @param resource 基本组件
//     * @param content 组件内容
//     */
//    public void updateContent(LemsunResource resource,String content){
//        if (resource==null)
//            throw new LemsunException("空组件,不能更新内容");
//        getResourceService().updateContent(resource.getPid(),content);
//    }
}
