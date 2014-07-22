package com.lemsun.core.repository;

import com.lemsun.core.LemsunException;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.PreviewImageSize;
import com.lemsun.core.ResourceAttach;
import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;

/**
 * 组件的附件标准操作
 */
@Repository
public final class ResourceAttachFileRepository extends AbstractLocalFsRepository {

    @Autowired
    public ResourceAttachFileRepository(MongoTemplate template,
                                        GridFsOperations gridFsOperations) {
        super(template, gridFsOperations);
    }


    /**
     * 设置获取组件的皮肤设置信息
     * @param pid
     * @return
     */
    public static String getSettingName(String pid) {
        return pid + ".face.setting";
    }

    /**
     * 获取组件的预览图片在数据库中的文件名称
     * @param pid
     * @param size
     * @return
     */
    public static String getPreviewImageName(String pid, PreviewImageSize size)
    {
        return pid + ".preview." + size.toString();
    }


    /**
     * 返回保存在DB数据库中的文件完整的名称
     * @param pid 组件ID
     * @param filename 文件名
     * @param filetype 文件类型
     * @return 数据库的文件完整名称
     */
    public static String getAttachName(String pid, String filename, String filetype)
    {
        return pid + ".attach." + filename + (StringUtils.isEmpty(filetype) ? "" : ("." + filetype));
    }

    /**
     * 返回保存在DB数据库中的组件说明文档的附件名称
     * @param pid 组件ID
     * @param filename 文件名
     * @param filetype 文件类型, 可以为空
     * @return 数据库的文件完整名称
     */
    public static String getDocAttachName(String pid,String filename,String filetype){
        return pid + ".doc.attach." + filename + (StringUtils.isEmpty(filetype) ? "" : ("." + filetype));
    }

    /**
     * 获取组件在数据库中的文件名
     * @param pid
     * @return
     */
    public static String getDbContentName(String pid)
    {
        return pid + ".content";
    }

    /**
     * 获取组件的描述信息, 在数据库的文件名
     * @param pid
     * @return
     */
    public static String getDbResourceDescriptionName(String pid)
    {
        return pid + ".details";
    }

    /**
     * 提供了获取组件附件的详细模型信息
     *
     * @param resource
     * @param filename
     * @param filetype 一般来说是文件的后缀名称, 如果文件名已经具备了后缀名称. 那么可以为空
     * @return
     */
    public ResourceAttach getAttachDetail(LemsunResource resource, String filename, String filetype) {

       return getAttachDetail(resource.getPid(), filename, filetype);
    }

    /**
     * 提供了获取组件附件的详细模型信息
     *
     * @param pid
     * @param filename
     * @param filetype 一般来说是文件的后缀名称, 如果文件名已经具备了后缀名称. 那么可以为空
     * @return
     */
    public ResourceAttach getAttachDetail(String pid, String filename, String filetype) {

        Assert.notNull(filename);
        Assert.notNull(pid);
        GridFSDBFile file = getAttach(pid, filename, filetype);

        return new ResourceAttach(pid, null, file);
    }

    /**
     * 获取组件的全部附件列表
     * @param pid
     * @return
     */
    public List<ResourceAttach> getAttachDetailAll(String pid) {
        return getAttachDetailAll(pid, ".*");
    }

    /**
     * 使用正则获取附件列表
     * @param pid
     * @param pattern
     * @return
     */
    public List<ResourceAttach> getAttachDetailAll(String pid, String pattern) {
        Assert.notNull(pid);
        Assert.notNull(pattern);

        List<GridFSDBFile> files = getGridFsOperations().find(query(whereFilename().regex(pid + "\\.attach\\." + pattern)));

        List<ResourceAttach> details = new ArrayList<>();

        for(GridFSDBFile f : files)
        {
            ResourceAttach d = new ResourceAttach(pid, null, f);
            details.add(d);
        }

        return details;
    }

    /**
     * 获取组件的描述信息
     * @param pid
     * @return
     */
    public String getDescription(String pid)
    {
        GridFSDBFile file = getAttach(getDbResourceDescriptionName(pid));
        return getGridFsFileContent(file);
    }


    /**
     * 获取预览平台的文件
     * @param pid
     * @param size
     * @return
     */
    public GridFSDBFile getPreviewImage(String pid, PreviewImageSize size)
    {
        return getAttach(getPreviewImageName(pid, size));
    }

    /**
     * 获取给出的组件内容并按照字符串的方式返回内容
     * @param pid
     * @return
     */
    public String getContent(String pid)
    {
        return getGridFsFileContent(getContentFile(pid));
    }

    /**
     * 获取设置信息的内容
     * @param pid
     * @return
     */
    public String getSetting(String pid) {
        return getGridFsFileContent(getSettingFile(pid));
    }

    /**
     * 获取组件内容文件
     * @param pid
     * @return
     */
    public GridFSDBFile getContentFile(String pid)
    {
        return getAttach(getDbContentName(pid));
    }


    /**
     * 获取组件的设置文件
     * @param pid
     * @return
     */
    public GridFSDBFile getSettingFile(String pid) {
        return getAttach(getSettingName(pid));
    }

    /**
     * 使用 ID 获取一个附件
     * @param id
     * @return
     */
    public GridFSDBFile getAttach(ObjectId id)
    {
        return getGridFsOperations().findOne(query(where("_id").is(id)));
    }


    /**
     * 使用 完整的附件名称 获取一个附件
     * @return
     */
    public GridFSDBFile getAttach(String fullname)
    {
        return getGridFsOperations().findOne(query(whereFilename().is(fullname)));
    }

    /**
     * 使用附件名称获取组件的附件
     * @param pid 组件的ID
     * @param filename 组件的附件名称
     * @param filetype 附件类型, 可以为空
     */
    public GridFSDBFile getAttach(String pid, String filename, String filetype)
    {
        String name = getAttachName(pid, filename, filetype);
        return getGridFsOperations().findOne(query(whereFilename().is(name)));
    }
    /**
     * 使用附件名称获取组件说明文档的附件
     * @param pid 组件的ID
     * @param filename 组件的附件名称
     * @param filetype 附件类型, 可以为空
     */
    public GridFSDBFile getDocAttach(String pid, String filename, String filetype)
    {
        String name = getDocAttachName(pid, filename, filetype);
        return getGridFsOperations().findOne(query(whereFilename().is(name)));
    }


    /**
     * 更新组件的描述信息
     * @param pid
     * @param description
     */
    public void updateDescription(String pid, String description)
    {
        try(InputStream stream = getInputStream(description))
        {
            updateAttach(getDbResourceDescriptionName(pid), stream, ".descript");
            stream.close();
        }
        catch (Exception ex)
        {
            throw new LemsunException("组件的描述信息保存失败");
        }
    }

    /**
     * 更新组件的设置信息
     * @param pid
     * @param input
     */
    public void updateSetting(String pid, InputStream input)
    {
        String filename = getSettingName(pid);
        updateAttach(filename, input, "setting");
    }


    /**
     * 更新组件的内容, 当前方法将在给出的组件PID中创建一个 {pid}.content 的组件附件
     * @param pid
     * @param content
     */
    public void updateContent(String pid, String content)
    {
        updateContent(pid, getInputStream(content));
    }


    public void updateContent(String pid, InputStream stream)
    {
        updateContent(pid, stream, "content");
    }

    public void updateContent(String pid, InputStream stream, String filename)
    {
        try
        {
            updateAttach(getDbContentName(pid), stream, filename);
            stream.close();
        }
        catch (Exception ex)
        {
            throw new LemsunException("内容保存失败");
        }
    }

    /**
     * 针对给出的组件 PID 更新属于组件的说明文档附件, 如果当前没有给出的附件那么就保存附件
     * @param pid 组件的ID
     * @param filename 文件的名称
     * @param filetype 文件类型, 可以为空
     * @param stream 内容流, 如果为空等同与删除
     */
    public void updateDocAttach(String pid, String filename, String filetype, InputStream stream)
    {
        Assert.notNull(pid);
        Assert.notNull(filename);
        String fullname = getDocAttachName(pid, filename, filetype);
        updateAttach(fullname, stream, filename + (StringUtils.isNotEmpty(filetype) ? "."+filetype : ""));

    }

    /**
     * 针对给出的组件 PID 更新属于组件的附件, 如果当前没有给出的附件那么就保存附件
     * @param pid 组件的ID
     * @param filename 文件的名称
     * @param filetype 文件类型, 可以为空
     * @param stream 内容流, 如果为空等同与删除
     */
    public void updateAttach(String pid, String filename, String filetype, InputStream stream)
    {
        Assert.notNull(pid);
        Assert.notNull(filename);
        String fullname = getAttachName(pid, filename, filetype);
        updateAttach(fullname, stream, filename + (StringUtils.isNotEmpty(filetype) ? "."+filetype : ""));

    }

    /**
     * 使用完整的文件名称更新组件的附件
     * @param fullname
     * @param stream
     * @param filename 标记在文件流中的文件名
     */
    public void updateAttach(String fullname, InputStream stream, String filename)
    {
        Assert.notNull(fullname);
        deleteAttach(fullname);
        String pid = fullname.substring(0, fullname.indexOf('.'));
        getGridFsOperations().store(stream, fullname, new BasicDBObject("filename", filename));

        getTemplate().updateFirst(query(where("pid").is(pid)), update("updateTime", new Date()), ResourceRepository.ResourceCollectionName);


    }

    /**
     * 使用完整的附件名称更新一个文本内容
     * @param fullname
     * @param content
     */
    public void updateAttach(String fullname, String content) {
        Assert.notNull(fullname);
        deleteAttach(fullname);

        if(StringUtils.isEmpty(content)) return;
        try {
            InputStream input = new ByteArrayInputStream(content.getBytes("utf-8"));
            updateAttach(fullname, input, null);
            input.close();
        } catch (Exception e) {

        }

    }

    /**
     * 更新组件的预览图片
     * @param pid
     * @param size
     * @param stream
     */
    public void updatePreviewImage(String pid, PreviewImageSize size, InputStream stream)
    {
        Assert.notNull(pid);
        Assert.notNull(size);
        Assert.notNull(stream);
        updateAttach(getPreviewImageName(pid, size), stream, null);
    }

    /**
     * 删除组件下的附件
     * @param pid
     * @param filename
     * @param filetype
     */
    public void deleteAttach(String pid, String filename, String filetype)
    {
        Assert.notNull(pid);
        Assert.notNull(filename);
        String fullname = getAttachName(pid, filename, filetype);
        deleteAttach(fullname);
    }

    /**
     * 使用我完整的组件名称删除组件附件
     * @param fullname
     */
    public void deleteAttach(String fullname)
    {
        Assert.notNull(fullname);
        String pid = fullname.substring(0, fullname.indexOf('.'));
        getGridFsOperations().delete(query(whereFilename().is(fullname)));
        getTemplate().updateFirst(query(where("pid").is(pid)), update("updateTime", new Date()), ResourceRepository.ResourceCollectionName);
    }

    /**
     * 将给出的组件ID下的附件全部移除.
     */
    public void deleteAll(String pid)
    {
        getGridFsOperations().delete(query(whereFilename().regex("^" + pid + "\\." + ".*")));
    }
}
