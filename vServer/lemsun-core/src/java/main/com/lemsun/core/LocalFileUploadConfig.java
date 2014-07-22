package com.lemsun.core;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.web.util.WebUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * User: 刘晓宝
 * Date: 13-11-1
 * Time: 下午4:28
 */
public class LocalFileUploadConfig {
    private int limitSize;
    private String[] fileTypes;
    private Resource tempDir;

    public LocalFileUploadConfig(int limitSize, String fileTypes, Resource tempDir) throws Exception {
        this.limitSize = limitSize;
        if(!StringUtils.isEmpty(fileTypes)){
            this.fileTypes = fileTypes.split(",");
        }

        this.tempDir = tempDir;

        if(this.tempDir != null && !this.tempDir.getFile().isDirectory())
        {
            throw new LemsunException("指定的上传临时不是一个文件夹, 必须指定一个文件夹 :" + tempDir.getFile().getName());
        }

    }

    public int getLimitSize() {
        return limitSize;
    }


    public String[] getFileTypes() {
        return fileTypes;
    }


    /**
     * 获取临时文件地址的资源对象
     */
    public Resource getTempDir() { return tempDir; }
}
