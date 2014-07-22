package com.lemsun.helper.service;

import com.lemsun.core.ResourceAttach;
import com.lemsun.helper.HelpItem;
import com.lemsun.helper.HelpQuery;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * User: 宗旭东
 * Date: 13-12-2
 * Time: 上午11:39
 */
public interface IHelperService {

    /**
     * 查询帮助
     * @param query
     * @return
     */
    public Page<HelpItem> query(HelpQuery query);

    /**
     * 更新预览图片
     * @param pid
     * @param stream
     *  @param suffix 图片后缀
     */
    void updateImage(String pid, int size, InputStream stream,String suffix);

    /**
     * 获取预览图片指定尺寸
     * @param pid
     * @param size
     * @return
     */
    GridFSDBFile getImage(String pid, int size) ;

    /**
     * 获取板转未转化帮助
     * @param pid
     * @return
     */
    public String getHelpContext(String pid);

    /**
     * 由模板转化后帮助文档
     * @param pid
     * @return
     */
    public String getHelpContextHandler(String pid);
    /**
     * 更新帮助
     * @param pid 编码
     *
     * @param context 更新内容
     */
    void updateHelpContext(String pid, String context);

    /**
     * 更新帮助的附件
     *
     * @param pid 编码
     * @param stream   附件流
     * @param fileName 文件名称
     * @param fileType 文件类型
     */
    void updateHelpContextAttachFile(String pid, InputStream stream, String fileName, String fileType);

    /**
     * 获取帮助的附加文件 根据编码和文件名
     *
     *
     * @param pid 组件
     * @param fileName 文件�
     * @return 文件内容
     */
    GridFSDBFile getHelpContextAttach(String pid, String fileName) throws IOException;

    /**
     * 获取帮助的附加文件 根据编码
     *
     * @param pid 编码
     * @return 附件列表
     */
    List<ResourceAttach> getHelpContextAttachList(String pid);

    /**
     *删除组件时调用删除帮助内容
     * @param pid 编码
     */
    void deleteHelpContext(String pid);
}
