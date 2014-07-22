package com.lemsun.web.model.view;

import com.lemsun.core.IAccount;
import com.lemsun.core.IResource;
import com.lemsun.data.ImageResource;


/**
 * User: 刘晓宝
 * Date: 13-11-20
 * Time: 上午11:43
 */
public class ImageItemView  extends ResourceBase{
    private String imageName;
    private long imageSize;
    private ImageResource resource=(ImageResource)super.getResource();
    public ImageItemView(IResource resource,IAccount account) {
        super(resource,account);
    }

    public String getImageName() {
        return resource.getImageName();
    }

    public long getImageSize() {
        return resource.getImageSize();
    }
}
