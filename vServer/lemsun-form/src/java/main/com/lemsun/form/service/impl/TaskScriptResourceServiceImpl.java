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
import com.lemsun.form.ScriptResource;
import com.lemsun.form.TaskScriptResource;
import com.lemsun.form.repository.FormResourceRepository;
import com.lemsun.form.repository.ScriptResourceRepository;
import com.lemsun.form.repository.TaskScriptResourceRepository;
import com.lemsun.form.service.ITaskScriptResourceService;
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
public class TaskScriptResourceServiceImpl extends AbstractScriptServiceImpl<TaskScriptResource> implements ITaskScriptResourceService {


    /**
     * 构造一个对组件的基础操作支持的对象
     *
     * @param resourceService
     */
    @Autowired
    protected TaskScriptResourceServiceImpl(IResourceService resourceService) {
        super(resourceService);
    }
}
