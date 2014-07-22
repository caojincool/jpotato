package com.lemsun.client.core.service;

import com.lemsun.client.core.IAccount;
import com.lemsun.client.core.model.LemsunResource;
import com.lemsun.client.core.model.PreviewImageSize;
import com.lemsun.client.core.model.ResourceAttach;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * 定义组件服务接口
 * User: 宗旭东
 * Date: 13-3-9
 * Time: 下午12:03
 */
public interface IResourceService {


    /**
     * 获取组件的预览图片在数据库中的文件名称
     * @param pid
     * @param size
     * @return
     */
    String getPreviewImageName(String pid, PreviewImageSize size);

    /**
     * 返回保存在DB数据库中的文件完整的名称
     * @param pid 组件ID
     * @param filename 文件名
     * @param filetype 文件类型
     * @return 数据库的文件完整名称
     */
    String getDbFileName(String pid, String filename, String filetype);

    /**
     * 获取组件在数据库中的文件名
     * @param pid
     * @return
     */
    String getDbContentName(String pid);

    /**
     * 获取组件的描述信息, 在数据库的文件名
     * @param pid
     * @return
     */
    String getDbResourceDescriptionName(String pid);

    /**
     * 使用当前账号的操作日期, 获取组件
     * @param pid 组件编码
     * @return 基本组件
     */
    public LemsunResource getCurrentResource(String pid);

    /**
     * 使用一个PID 和操作日期 获取一个当前用户的组件
     * @param pid 组件pid
     * @param adate 创建日期
     * @return 基本组件
     */
    public LemsunResource getCurrentResource(String pid, Date adate);

    /**
     * 使用一个账号获取组件的基本信息
     * @param account 账号对象
     * @param pid 资源主键
     * @return 资源对象
     */
    public LemsunResource getLemsunResource(IAccount account, String pid) ;

    /**
     * 使用操作用户的账号获取组件的基本信息
     * @param pid 组件id
     * @return 组件对象
     */
    public LemsunResource getLemsunResource(String pid);

    /**
     * 获取组件的内容
     * @param resource 组件对象
     * @return 内容 ( 如果是二进制的以 base64 进行编码)
     * @throws IOException 读写异常
     */
    public String getResourceContext(LemsunResource resource);

    /**
     * 获取资源组件的附加文件
     * @param resourcePid 组件
     * @param fileName 文件名称
     * @return 文件内容
     * @throws IOException 读写异常
     */
    byte[] getResourceAttachContext(String resourcePid, String fileName);


    /**
     * 通过附件名称获取附件信息
     * @param pid
     * @param filename 如果文件名为空, 返回组件内容信息
     * @return
     */
    ResourceAttach getAttachInfo(String pid, String filename);

    /**
     * 通过完整的附件名称获取附件信息
     * @param fullname
     * @return
     */
    ResourceAttach getAttachInfo(String fullname);


    /**
     * 使用文件的ID 获取组件的附件流
     * @param fileid
     * @return
     */
    InputStream getAttachStream(String fileid);


    /**
     * 使用完整的附件名称获取附件流
     * @param fullname
     * @return
     */
    InputStream getAttachFileString(String fullname);

}
