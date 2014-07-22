package com.lemsun.data.service.impl;

import com.lemsun.core.LemsunException;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.service.impl.AbstractResourceSupportService;
import com.lemsun.data.ImageResource;
import com.lemsun.data.service.ImageResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图像组件服务
 */
@Service
public class ImageResourceServiceImpl extends AbstractResourceSupportService<ImageResource> implements ImageResourceService {
    /**
     * 构造一个对组件的基础操作支持的对象
     *
     * @param resourceService
     */
    @Autowired
    public ImageResourceServiceImpl(IResourceService resourceService) {
        super(resourceService);
    }


    @Override
    public void updateImage(ImageResource resource, InputStream stream) {
        getResourceService().updateContent(resource.getPid(),stream);
    }

    @Override
    public byte[] getImage(ImageResource resource) {
        if (resource==null){
            throw new LemsunException("空组件,不能获取图片内容");
        }
        InputStream stream=getResourceService().getContentFile(resource.getPid()).getInputStream();
        byte[] imgdata=null;
        try {
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
            int ch;
            while ((ch = stream.read()) != -1)
            {
                bytestream.write(ch);
            }
            imgdata=bytestream.toByteArray();
            bytestream.close();
        } catch (IOException e) {

        }


        return imgdata;
    }
}
