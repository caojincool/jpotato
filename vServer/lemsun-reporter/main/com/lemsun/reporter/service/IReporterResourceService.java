package com.lemsun.reporter.service;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.IResource;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.ResourceAttach;
import com.lemsun.core.service.IResourceOperaterService;
import com.lemsun.reporter.ReporterResource;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.data.domain.Page;

import java.io.InputStream;

/**
 * 填报组件服务对象
 * Created by dpyang on 2014/5/20.
 */
public interface IReporterResourceService extends IResourceOperaterService<ReporterResource> {

    /**
     * 获取内容附件的信息
     * @param pid
     * @return
     */
    ResourceAttach getContentInfo(String pid);


    /**
     * 获取填报的文件
     * @param reporterResource 填报组件
     * @return 填报组件内容
     */
    GridFSDBFile getReporterFile(ReporterResource reporterResource);

    /**
     * 获取填报的文件
     * @param resource 填报组件的基本对象
     * @return 填报组件内容
     */
    GridFSDBFile getReporterFile(LemsunResource resource);

    /**
     * 上传填报
     * @param reporterResource
     * @param stream
     */
    void uploadReporter(ReporterResource reporterResource, InputStream stream);

    /**
     * 上传填报
     * @param resource 基本的组件
     * @param reportertype 填报文件的类型
     * @param stream 填报内容
     */
    void uploadReporter(LemsunResource resource, int reportertype, InputStream stream);

}
