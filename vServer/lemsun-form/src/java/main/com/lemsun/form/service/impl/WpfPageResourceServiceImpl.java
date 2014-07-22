package com.lemsun.form.service.impl;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.Host;
import com.lemsun.core.IResource;
import com.lemsun.core.ResourceAttach;
import com.lemsun.core.events.ResourceEvent;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.util.Pid;
import com.lemsun.core.util.ResourceUtil;

import com.lemsun.form.WpfPageResource;
import com.lemsun.form.repository.FormResourceRepository;
import com.lemsun.form.repository.FunctionStatementRepository;
import com.lemsun.form.repository.WpfPageResourceRepository;
import com.lemsun.form.service.IFormResourceService;
import com.lemsun.form.service.IWpfPageResourceService;
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
 * Date: 12-11-6
 * Time: 下午3:04
 */
@Service
public class WpfPageResourceServiceImpl extends AbstractFormServiceImpl<WpfPageResource> implements IWpfPageResourceService {

    private static final Logger log = LoggerFactory.getLogger(WebPageResourceServiceImpl.class);
    private WpfPageResourceRepository wpfPageResourceRepository;


    @Autowired
    public WpfPageResourceServiceImpl(IResourceService resourceService,
                                      WpfPageResourceRepository wpfPageResourceRepository,
                                      IFormResourceService formResourceService) {
        super(resourceService, formResourceService);
        this.wpfPageResourceRepository = wpfPageResourceRepository;

    }

    /**
     * 获取某个wfp组件的的所有附件
     *
     * @param resource 某个组件
     * @return 组件附件列表
     */
    @Override
    public List<ResourceAttach> loadResouceAttachFiles(IResource resource) throws Exception {

        if (resource == null)
            throw new Exception("获取某个wfp组件的的所有附件方法失败:原因组件为空!");
        return getResourceService().getAttachDetails(resource.getPid());
    }

}
