package com.lemsun.core.service.impl;

import com.lemsun.core.*;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.core.service.IResourceOperaterService;
import com.lemsun.core.service.IResourceService;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 提供对组件服务的基本操作支持,
 * 比如组件的 增 删 改 查, 一般来说一个组件的服务都可以通过继承这个组件来封装基本操作
 *
 */
public abstract class AbstractResourceSupportService<T extends IResource> implements IResourceOperaterService<T> {

    private Class<T> t;
    private IResourceService resourceService;

    /**
     * 构造一个对组件的基础操作支持的对象
     */
    protected AbstractResourceSupportService(IResourceService resourceService) {
        this.resourceService = resourceService;
        this.t = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    @Override
    public IResourceService getResourceService() {
        return resourceService;
    }


    /**
     * 获取完整的组件对象
     * @param pid 组件PID
     * @return
     */
    @Override
    public T get(String pid)
    {
        return resourceService.get(pid, t);
    }

    /**
     * 获取全部的组件对象
     * @return
     */
    @Override
    public List<T> getAll()
    {
        return resourceService.getAll(t);
    }

    /**
     * 分页获取组件列表
     * @param query
     * @return
     */
    @Override
    public Page<T> getPageing(AbstractPageRequest query) {
        return resourceService.getPageing(query, t);
    }

    /**
     * 保存当前的组件
     * @param resource
     * @throws Exception
     */
    @Override
    public void save(T resource)  {
        resourceService.create(resource);
    }

    /**
     * 更新组件
     * @param resource
     */
    @Override
    public void update(T resource) {
        resourceService.update(resource);
    }

    /**
     * 删除组件
     * @param resource
     */
    @Override
    public void delete(T resource) {
        resourceService.remove(resource.getPid());
    }

    @Override
    public void delete(String pid) {
        Assert.notNull(pid);
        resourceService.remove(pid);
    }
}
