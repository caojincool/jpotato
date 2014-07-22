package com.lemsun.reporter.service.impl;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.IResource;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.core.service.IResourceService;
import com.lemsun.reporter.ReporterScriptResource;
import com.lemsun.reporter.service.IReporterScriptResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 填报脚本服务实现对象
 * Created by dpyang on 2014/5/21.
 */
@Service
public class ReporterScriptResourceServiceImpl implements IReporterScriptResourceService {

    private ResourceRepository repository;
    private IResourceService resourceService;


    @Autowired
    public ReporterScriptResourceServiceImpl(IResourceService resourceService, ResourceRepository repository) {
        this.resourceService = resourceService;
        this.repository = repository;
    }

    /**
     * 更新填报脚本组件
     *
     * @param reporterScriptResource 脚本组件
     */
    @Override
    public void update(ReporterScriptResource reporterScriptResource) {
        resourceService.update(reporterScriptResource);
    }

    /**
     * 更新脚本组件内容
     *
     * @param reporterScriptResource 脚本组件
     * @param context                内容
     */
    @Override
    public void updateContent(ReporterScriptResource reporterScriptResource, String context) {
        resourceService.updateContent(reporterScriptResource.getPid(), context);
    }

    /**
     * 更新脚本组件内容
     *
     * @param pid     脚本组件pid
     * @param context 内容
     */
    @Override
    public void updateContent(String pid, String context) throws Exception {
        ReporterScriptResource reporterScriptResource=get(pid);
        if (reporterScriptResource==null)
            throw new Exception("没有查询到"+pid+"组件");

        updateContent(reporterScriptResource,context);
    }

    /**
     * 获取脚本组件内容
     *
     * @param pid 脚本组件pid
     * @return 内容
     */
    @Override
    public String getContent(String pid) {
        return resourceService.getContent(pid);
    }

    /**
     * 获取填报脚本组件内容
     *
     * @param reporterScriptResource 脚本组件
     * @return 脚本内容
     */
    @Override
    public String getContent(ReporterScriptResource reporterScriptResource) {
        return getContent(reporterScriptResource.getPid());
    }

    /**
     * 获取填报脚本组件
     *
     * @param pid 填报组件PID
     * @return 填报脚本组件
     */
    @Override
    public ReporterScriptResource get(String pid) {
        return resourceService.get(pid, ReporterScriptResource.class);
    }

    /**
     * 分页获取脚本组件内容
     *
     * @param query 查询条件
     * @param clazz
     * @return
     */
    @Override
    public <T extends IResource> Page<T> getPageing(AbstractPageRequest query, Class<T> clazz) {
       return repository.getPagging(query,clazz);
    }

    public void remove(String pid) throws Exception {
        ReporterScriptResource resource=get(pid);
        if (resource!=null)
            repository.remove(pid);
        else
            throw new Exception(pid+"不是一个有效的填报组件编码");
    }

    @Override
    public List<ReporterScriptResource> getAll() {
        return repository.getAll(ReporterScriptResource.class);
    }
}
