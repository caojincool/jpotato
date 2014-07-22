package com.lemsun.client.core.service;

import com.lemsun.client.core.IAccount;
import com.lemsun.client.core.model.ImageResource;

import java.io.IOException;

/**
 * 处理图片资源组件的服务类
 * User: dp
 * Date: 13-5-30
 * Time: 下午4:05
 */
public interface IImageResourceService {
    /**
     * 根据组件编码获取图片组件
     * @param pid 组件
     * @return 图片类型组件
     */
    ImageResource getImageResource(IAccount account,String pid);

    ImageResource getImageResource(String pid);


    /**
     * 根据图片组件获取图片
     * @param imageResource 图片组件
     * @return 图片
     */
    byte[] getImage(IAccount acount,ImageResource imageResource);

    byte[] getImage(ImageResource resource);

    /**
     * 根据图片组件编码获取图片
     * @param pid 图片组件编码
     * @return 图片
     */
    byte[] getImage(IAccount account,String pid);

    byte[] getImage(String pid);
}
