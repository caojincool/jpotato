package com.lemsun.data.service.impl;

import com.lemsun.core.LemsunException;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.service.impl.AbstractResourceSupportService;
import com.lemsun.data.FileResource;
import com.lemsun.data.service.IFileResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件资源操作服务类
 * User: Lucklim
 * Date: 12-12-21
 * Time: 上午9:29
 */
@Service
public class FileResourceServiceImpl extends AbstractResourceSupportService<FileResource> implements IFileResourceService {




    @Autowired
    public FileResourceServiceImpl(
                                   IResourceService resourceService) {
        super(resourceService);
    }

    @Override
    public void updateFile(FileResource resource, InputStream stream)  {
        getResourceService().updateContent(resource.getPid(),stream);
    }

    @Override
    public byte[] getFile(FileResource resource) {
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
