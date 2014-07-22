package com.lemsun.form.service.impl;

import com.lemsun.core.*;
import com.lemsun.core.events.ResourceEvent;
import com.lemsun.core.formula.IFunctionStatement;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.service.impl.AbstractResourceSupportService;
import com.lemsun.core.util.Pid;
import com.lemsun.core.util.ResourceUtil;
import com.lemsun.form.*;
import com.lemsun.form.repository.FormResourceRepository;
import com.lemsun.form.repository.FunctionStatementRepository;
import com.lemsun.form.repository.WebPageResourceRepository;
import com.lemsun.form.service.IFormResourceService;
import com.lemsun.form.service.IWebPageResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-27
 * Time: 下午9:18
 */
@Service
public class WebPageResourceServiceImpl extends AbstractFormServiceImpl<WebPageResource> implements IWebPageResourceService {

    private static final Logger log = LoggerFactory.getLogger(WebPageResourceServiceImpl.class);

    private WebPageResourceRepository webPageResourceRepository;

    @Autowired
    public WebPageResourceServiceImpl(IResourceService resourceService,
                                      WebPageResourceRepository webPageResourceRepository,
                                      IFormResourceService formResourceService) {
        super(resourceService, formResourceService);
        this.webPageResourceRepository = webPageResourceRepository;
    }


    /**
     * 获取组件的附件信息
     *
     * @param resource 组件
     * @return 附件信息
     */
    @Override
    public List<ResourceAttach> getResourceAttaches(IResource resource) {
        return getResourceService().getAttachDetails(resource.getPid());

    }


    /**
     * 获取所有模板组件
     *
     * @return 所有模板组件
     */
    @Override
    public List<LemsunResource> getTempResourse(String pid) {
        return webPageResourceRepository.getTempleResource(pid);
    }

    @Override
    public List<WebPageParam> getResourceParams(String pid) {
        return webPageResourceRepository.getStartParams(pid);
    }
}
