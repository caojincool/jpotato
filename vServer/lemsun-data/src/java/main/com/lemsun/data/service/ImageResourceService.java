package com.lemsun.data.service;

import com.lemsun.core.service.IResourceOperaterService;
import com.lemsun.data.ImageResource;

import java.io.InputStream;

/**
 * Created by dpyang on 2014/6/17.
 * 图片组件服务接口
 */
public interface ImageResourceService extends IResourceOperaterService<ImageResource>{

    /**
     * 更新图片资源,没有就创建
     *
     * @param resource
     * @param stream
     * @throws Exception
     */
    void updateImage(ImageResource resource, InputStream stream);

    /**
     * 获取文件资源
     *
     * @param resource 图片组件编码
     * @return 组件不存在返回null
     * @throws Exception
     */
    byte[] getImage(ImageResource resource);
}
