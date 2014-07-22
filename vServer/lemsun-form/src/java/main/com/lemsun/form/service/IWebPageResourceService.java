package com.lemsun.form.service;

import com.lemsun.core.*;
import com.lemsun.core.util.Pid;
import com.lemsun.form.FormResource;

import com.lemsun.form.WebPageParam;
import com.lemsun.form.WebPageResource;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

/**
 * 页面组件服务
 * User: Xudong
 * Date: 12-10-27
 * Time: 下午9:16
 */
public interface IWebPageResourceService extends IFormService<WebPageResource> {

    /**
     * 获取组件的附件信息
     *
     * @param resource 组件
     * @return 附件信息
     */
    List<ResourceAttach> getResourceAttaches(IResource resource);


    /**
     * 获取所有模板组件
     *
     * @return 所有模板组件
     */
    List<LemsunResource> getTempResourse(String pid);


    /**
     * 获取一个给定的组件对象的页面启动参数. 并且递归查找父组件的启动参数. 如果有需要覆盖的会进行覆盖. <br/>
     * 例如子组件的启动参数, 重复定义了父组件的启动参数. 子组件将会覆盖父组件的启动参数
     *
     * @param pid
     * @return
     */
    List<WebPageParam> getResourceParams(String pid);


}
