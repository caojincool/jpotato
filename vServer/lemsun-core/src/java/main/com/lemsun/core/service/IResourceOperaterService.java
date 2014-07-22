package com.lemsun.core.service;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.IPageQuery;
import com.lemsun.core.IResource;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 实现组件的基本操作接口. 比如增删改查等
 */
public interface IResourceOperaterService<T extends IResource> {


    /**
     * 获取一个不针对具体类型的组件, 操作服务对象
     */
    IResourceService getResourceService();


    /**
     * 获取组件对象
     * @param pid 组件PID
     * @return 返回组件对象
     */
    T get(String pid);

    /**
     * 获取全部的组件对象
     * @return 组件列表
     */
    List<T> getAll();

    /**
     * 使用分页的方式获取对象列表
     * @param query
     * @return
     */
    Page<T> getPageing(AbstractPageRequest query);


    /**
     * 将组件保存到数据库
     * @param resource
     */
    void save(T resource) throws Exception;


    /**
     * 将组件更新为当前传入的组件
     * @param resource
     */
    void update(T resource);


    /**
     * 删除组件
     *
     */
    void delete(T resource);

    /**
     * 删除组件
     * @param pid
     */
    void delete(String pid);

}
