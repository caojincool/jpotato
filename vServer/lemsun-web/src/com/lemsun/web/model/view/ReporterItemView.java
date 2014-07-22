package com.lemsun.web.model.view;

import com.lemsun.core.IAccount;
import com.lemsun.core.IResource;
import com.lemsun.reporter.ReporterResource;

/**
 * 填报项
 * Created by dpyang on 2014/5/21.
 */
public class ReporterItemView extends ResourceBase {

    private String imageName;
    private long imageSize;

    private ReporterResource resource=(ReporterResource)super.getResource();
    public ReporterItemView(IResource resource, IAccount account) {
        super(resource, account);
    }

    public String getImageName() {
        return imageName;
    }

    public long getImageSize() {
        return imageSize;
    }
}
