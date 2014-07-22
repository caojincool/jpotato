package com.lemsun.core.repository;

import com.lemsun.core.IResource;
import com.lemsun.core.LemsunException;
import com.lemsun.core.ResourceAttach;
import com.lemsun.core.util.ResourceUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.util.Assert;

import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereMetaData;

/**
 * 支持提供对组件对象需要操作特殊的内容文件保存. 读取. 修改等操作
 * User: Xudong
 * Date: 12-12-6
 * Time: 下午8:11
 * 修改说明:重载原来的获取资源附件方法
 */
public abstract class AbstractLocalFsRepository extends AbstractLocalRepository {
    private static final Logger log = LoggerFactory.getLogger(AbstractLocalFsRepository.class);

    private GridFsOperations gridFsOperations;
    protected static final String ATTACH="attach";//组件内容附件命名规
    protected static final String PRFIX=".";



    protected AbstractLocalFsRepository(MongoTemplate template, GridFsOperations gridFsOperations) {
        super(template);
        this.gridFsOperations = gridFsOperations;
    }


    protected GridFsOperations getGridFsOperations()
    {
        return gridFsOperations;
    }


    /**
     * 将附件的内容读取成字符
     * @param file
     * @return
     * @throws Exception
     */
    protected String getGridFsFileContent(GridFSDBFile file) {
        if(file == null) return null;

        try(InputStream stream = file.getInputStream())
        {
            char[] cs = IOUtils.toCharArray(stream, Charsets.UTF_8);
            return new String(cs);
        }
        catch (Exception ex)
        {
            throw new LemsunException("组件的内容读取失败:" + ex.getMessage());
        }
    }

    /**
     * 将给定的字符转换成流
     * @param content
     * @return
     */
    protected InputStream getInputStream(String content)
    {
        byte[] data = null;

        if(StringUtils.isEmpty(content))
        {
            data = new byte[0];
        }
        else
        {
            data = content.getBytes(Charsets.UTF_8);
        }

        ByteArrayInputStream stream = new ByteArrayInputStream(data);
        return stream;
    }



    /**
     * 根据组件编码获取组件内容
     * 例如:
     * wpf的xaml代码
     * web组件为html代码
     * 脚本组件为脚本代码
     * 图片组件或者资源组件则是Base64字符
     *
     * @param pid 组件编码
     * @return 组件内容
     */
    @Deprecated
    public String getResourceContext(String pid) {

        String context = null;
        try {
            GridFSDBFile file = getResourceContextFile(pid);

            if(file != null)
            context = fileConvert(context, file);


        } catch (IOException e) {
            if(log.isErrorEnabled())
            log.error("获取组件内容失败 :" + pid, e);
        }

        return context;
    }

    /**
     * 获取内容组件的文件流
     * @param pid
     * @return
     */
    @Deprecated
    public GridFSDBFile getResourceContextFile(String pid) {
        return gridFsOperations.findOne(query(whereFilename().is(pid + ".context")));
    }

    /**
     * GridFSDBFile转化为字符串
     * @param context
     * @param file
     * @return
     * @throws IOException
     */
    @Deprecated
    protected String fileConvert(String context, GridFSDBFile file) throws IOException {
        if (file == null)
            throw new IOException("没有发现内容的文");

        DBObject metadata = file.getMetaData();

        Object o = metadata == null ? null : metadata.get("byte");

        if (o != null && o == true) {
            int byteread;
            int length = (int) file.getLength();
            byte[] tempbytes = new byte[length];

            InputStream inputStream = file.getInputStream();
            int offset = 0;

            while ((byteread = inputStream.read(tempbytes, offset, tempbytes.length - offset)) != -1) {
                offset += byteread;
            }
            inputStream.close();
            context = Base64.encodeBase64String(tempbytes);
        } else {
            InputStreamReader reader = new InputStreamReader(file.getInputStream(), Charsets.UTF_8);

            CharBuffer buffer = CharBuffer.allocate((int) file.getLength());

            reader.read(buffer);
            buffer.flip();

            context = buffer.toString();

            reader.close();
        }
        return context;
    }


    /**
     * 更新字符内容的组件内容
     *
     * @param resource 组件对象
     * @param context  内容字符
     */
    @Deprecated
    public void updateResource(IResource resource, String context) {
        if (StringUtils.isEmpty(context)) context = "";
        byte[] bts = context.getBytes(Charsets.UTF_8);
        updateResource(resource, bts, "context", false);
    }


    /**
     * 更新组件内容
     *
     * @param resource 组件
     * @param bts      组件内容
     * @param filetype 组件类型
     * @param bytes    是否是以二进制流保存内容
     */
    @Deprecated
    protected void updateResource(IResource resource, byte[] bts, String filetype, boolean bytes) {

        String pid=resource.getPid();
        updateContext(bts, filetype, bytes, pid);
    }

    /**
     *
     * @param bts 是否字节流
     * @param filetype 后缀存储命名
     * @param bytes 二进制流
     *
     * @param pid 更新内容所属  编码
     */
    @Deprecated
    protected void updateContext(byte[] bts, String filetype, boolean bytes, String pid) {
        try (ByteArrayInputStream stream = new ByteArrayInputStream(bts)) {
            BasicDBObject mete = new BasicDBObject("contextType", filetype);
            mete.append("byte", bytes);
            gridFsOperations.delete(query(whereFilename().is(pid + "."+filetype)));
            gridFsOperations.store(stream, pid + "."+filetype, mete);

            stream.close();

            getTemplate().updateFirst(
                    query(where("pid").is(pid)),
                    update("updateTime", new Date()),
                    ResourceRepository.ResourceCollectionName
            );
        } catch (IOException e) {
            if (log.isErrorEnabled()) log.error("更新后缀名为s%为内容失败 :" , filetype,e);
        }

        if (log.isDebugEnabled()) {
            log.debug("编码：  {} 附件内容已经保存，字节长度为： {}byte", pid, bts.length);
        }
    }


    /**
     * 获取组件附件 根据pattern获取组件不同场景使用附件
     * @param pid 组件编码
     * @param pattern 要匹配字符串, 匹配的字符串将使用正则表达式 "\\."+pattern+"\\.."
     * @return
     */
    @Deprecated
    protected List<ResourceAttach> getResourceAttaches(String pid,String pattern){
        List<GridFSDBFile> gfs = gridFsOperations.find(query(whereFilename().regex(pid + "\\."+pattern+"\\..")));

        List<ResourceAttach> resoruceAttaches = new ArrayList<>(gfs.size());

        for (GridFSDBFile gf : gfs) {
            ResourceAttach resoruceAttach = new ResourceAttach();
            String[] strings = gf.getFilename().split("\\.");
            String s1 = "";

            for (int i = 2; i < strings.length - 1; i++) {
                s1 += strings[i] + ".";
            }

            resoruceAttach.setMd5(gf.getMetaData().get("md5").toString());
            resoruceAttach.setName(s1 + strings[strings.length - 1]);
            resoruceAttach.setType(strings[strings.length - 1]);
            resoruceAttach.setSize(gf.getLength());
            resoruceAttaches.add(resoruceAttach);
        }
        return resoruceAttaches;
    }
}
