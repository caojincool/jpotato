package com.lemsun.data.service;

import com.lemsun.core.service.IResourceOperaterService;
import com.lemsun.data.FileResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件资源操作服务类接口
 * User: Lucklim
 * Date: 12-12-21
 * Time: 上午9:29
 * To change this template use File | Settings | File Templates.
 */
public interface IFileResourceService extends  IResourceOperaterService<FileResource>{

    /**
     * 更新文件资源,没有就创建
     *
     * @param resource 文件资源
     * @param file     文件内容
     * @throws Exception
     */
    void updateFile(FileResource resource, InputStream file) throws IOException;

    /**
     * 获取文件资源
     *
     * @param resource 图片组件编码
     * @return 组件不存在返回
     * @throws Exception
     */
    byte[]  getFile(FileResource resource);
}
