package com.lemsun.client.core.model;

/**
 * 图片资源组件
 * User: dp
 * Date: 13-5-30
 * Time: 下午3:58
 */
public class ImageResource extends LemsunResource{

    private String imageName;
    private long imageSize;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public long getImageSize() {
        return imageSize;
    }

    public void setImageSize(long imageSize) {
        this.imageSize = imageSize;
    }
}
