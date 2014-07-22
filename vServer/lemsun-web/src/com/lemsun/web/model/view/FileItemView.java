package com.lemsun.web.model.view;

import com.lemsun.core.IAccount;
import com.lemsun.core.IResource;
import com.lemsun.data.FileResource;

/**
 * User: 刘晓宝
 * Date: 13-11-20
 * Time: 上午11:43
 */
public class FileItemView extends ResourceBase{
    private FileResource resource=(FileResource)super.getResource();
    public FileItemView(IResource resource,IAccount account) {
        super(resource,account);
    }

    public String getFileName() {
        return resource.getFileName();
    }

    public long getFileSize() {
        return resource.getFileSize();
    }
}
