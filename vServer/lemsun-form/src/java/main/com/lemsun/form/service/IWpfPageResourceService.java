package com.lemsun.form.service;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.IResource;

import com.lemsun.core.ResourceAttach;
import com.lemsun.core.util.Pid;
import com.lemsun.form.WpfPageResource;
import org.springframework.data.domain.Page;

import java.io.InputStream;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-6
 * Time: 下午3:04
 */
public interface IWpfPageResourceService extends IFormService<WpfPageResource> {



    /**
     * 获取某个wfp组件的的所有附件
     *
     * @param resource 某个组件
     * @return 组件附件列表
     */
    List<ResourceAttach> loadResouceAttachFiles(IResource resource) throws Exception;


}
