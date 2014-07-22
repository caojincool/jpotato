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
public class FileResource extends AbstractLemsunResource {

    private String fileName;
    private long fileSize;

    public FileResource() {
        super(null,BaseCategory.RESOURCE.getCategory());
    }

    public FileResource(LemsunResource resource) {
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

    public FileResource(String name) {
        super(name, BaseCategory.RESOURCE.getCategory());
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
