package com.lemsun.data;

import com.lemsun.core.AbstractLemsunResource;
import com.lemsun.core.BaseCategory;
import com.lemsun.core.LemsunResource;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-12-18
 * Time: 下午4:12
 */
public class ImageResource extends AbstractLemsunResource {

    public static final String TYPE = "image";

    public ImageResource() {
        super(null, BaseCategory.IMAGE.getCategory());
    }

    public ImageResource(String name) {
        super(name, BaseCategory.IMAGE.getCategory());
    }

    public ImageResource(LemsunResource resource) {
        super(resource.getName(), resource.getCategory());
        setId(resource.getId());
        setPid(resource.getPid());
        setRemark(resource.getRemark());
        setStrParams(resource.getStrParams());
        setCreateUser(resource.getCreateUser());
        setUpdateTime(resource.getUpdateTime());
        setSystem(resource.isSystem());
        setBusinessCode(resource.getBusinessCode());
    }

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
