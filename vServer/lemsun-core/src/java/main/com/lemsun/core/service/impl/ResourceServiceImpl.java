package com.lemsun.core.service.impl;

import com.lemsun.core.*;
import com.lemsun.core.PreviewImageSize;
import com.lemsun.core.repository.ResourceAttachFileRepository;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.core.service.IAccountCoreService;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.util.Pid;
import com.lemsun.core.util.ResourceUtil;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现对组件的服务.
 * User: Xudong
 * Date: 12-10-25
 * Time: 下午4:03
 */
@Service
public final class ResourceServiceImpl implements IResourceService {

    private ResourceRepository repository;
    private IAccountCoreService accountCoreService;

    private final Logger logger= LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    public ResourceServiceImpl(IAccountCoreService accountCoreService,
                               ResourceRepository repository) {

        this.repository = repository;
        this.accountCoreService = accountCoreService;
    }


    /**
     * 重新加载内存组件
     */
    public void reloadGlobleTree() {
        repository.getTypedResourceList(null);
    }

    @Override
    public String getPreviewImageName(String pid, PreviewImageSize size) {
        return ResourceAttachFileRepository.getPreviewImageName(pid, size);
    }

    @Override
    public String getDbFileName(String pid, String filename, String filetype) {
        return ResourceAttachFileRepository.getAttachName(pid, filename, filetype);
    }

    @Override
    public String getDbContentName(String pid) {
        return ResourceAttachFileRepository.getDbContentName(pid);
    }

    @Override
    public String getDbResourceDescriptionName(String pid) {
        return ResourceAttachFileRepository.getDbResourceDescriptionName(pid);
    }

    @Override
    public LemsunResource getByBusinessCode(String code) {
        //TODO 宗旭东 业务编码查找实现


        return null;
    }

    /**
     * 创建组件
     *
     * @param resource
     */
    @Override
    public void create(IResource resource) {

        if (StringUtils.isEmpty(resource.getCreateUser()))
            resource.setCreateUser(accountCoreService.getCurrentAccountManager().getAccount().getAccount());
            resource.setState(16);
        repository.create(resource);
    }


    @Override
    public <T extends IResource> Page<T> getPageing(AbstractPageRequest query, Class<T> clazz) {
        return repository.getPagging(query, clazz);
    }


    @Override
    public IResource findParentResource(IResource resource, String cate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public LemsunResource getBaseResource(String pid) {
        return repository.getBaseResource(pid);
    }

    @Override
    public String getContent(String pid) {
        return repository.getAttachFileRepository().getContent(pid);
    }

    @Override
    public String getSetting(String pid) {
        return repository.getAttachFileRepository().getSetting(pid);
    }

    @Override
    public List<ResourceAttach> getAttachDetails(String pid) {
        return repository.getAttachFileRepository().getAttachDetailAll(pid);
    }

    @Override
    public ResourceAttach getAttachDetail(String pid, String filename, String filetype) {
        return repository.getAttachFileRepository().getAttachDetail(pid, filename, filetype);
    }

    @Override
    public ResourceAttach getAttachDetail(String fullname) {
        GridFSDBFile file = repository.getAttachFileRepository().getAttach(fullname);
        if(file == null) return null;
        return new ResourceAttach("", "", file);
    }

    @Override
    public GridFSDBFile getPreviewImage(String pid, PreviewImageSize size) {
        return repository.getAttachFileRepository().getPreviewImage(pid, size);
    }

    @Override
    public GridFSDBFile getContentFile(String pid) {
        return repository.getAttachFileRepository().getContentFile(pid);
    }


    @Override
    public void saveResourceFace(String pid, InputStream stream) {

        try {
            if(StringUtils.isEmpty(pid) || stream.available() == 0)
            {
                throw new LemsunException("保存的皮肤信息不能为空");
            }
            repository.getAttachFileRepository().updateSetting(pid, stream);
        } catch (IOException e) {
            throw new LemsunException("皮肤信息不能进行保存");
        }


    }



    @Override
    @Pid
    public <T> T get(String pid, Class<T> type) {
         T t = repository.get(pid, type);

        if(t == null) throw ResourceException.ResourceIsNull;

        return t;

    }

    @Override
    public IResource get(String pid) {
        IResource rs = repository.get(pid);

        if(rs == null) throw ResourceException.ResourceIsNull;

        return rs;
    }

    @Override
    public <T extends IResource> List<T> getAll(Class<T> type) {
        return repository.getAll(type);
    }


    @Override
    public List<LemsunResource> getByCategory(String category) {
        List<LemsunResource> lemsunResources = repository.getResourceByCategory(category);
        if (lemsunResources == null) lemsunResources = new ArrayList<>();
        return lemsunResources;
    }

    /**
     * 保存一个组件的权限信息
     *
     * @param pid 组件pid
     * @param rp  组件的权限集�
     */
    @Override
    public void updatePermissions(String pid, List<ResourcePermission> rp) throws Exception {
        if (!ResourceUtil.isRecourcePid(pid))
            throw new Exception("组件编码不合符规�不能保存");

        IResource resource=get(pid);

        if (rp.size() <= 0) return;

        resource.setRpList(rp);
        repository.update(resource);
    }

    /**
     * 获取一个组件的权限列表
     *
     * @param pid  组件编码
     * @return 权限列表
     *         如果组件不存在返回null
     */
    @Override
    public List<ResourcePermission> listPermissions(String pid) {
        if (!ResourceUtil.isRecourcePid(pid))
            return null;

        IResource resource=get(pid);
        List<ResourcePermission> resourcePermissions=resource.getRpList();

        return resourcePermissions;
    }

    @Override
    public LemsunResource loadTreeResourceByPid(String pid) {
        LemsunResource resource=repository.getBaseResource(pid);
        repository.loadTreeResource(resource);
        return resource;
    }

    @Override
    @Pid
    public List<LemsunResource> getChildResourceByParentId(String pid) {
        return repository.getResourceByParentId(pid);
    }

    @Override
    public List<String> distinctScriptTypebyCategory(String category) {
        return repository.distinctScriptTypebyCategory(category);
    }

    @Override
    public List<LemsunResource> queryByCategoryAndScriptType(String category, String scriptType) {
        return repository.queryByCategoryAndScriptType(category, scriptType);
    }

    @Override
    public void update(IResource resource) {
        repository.update(resource);
    }

    @Override
    public void uploadAttachFile(String pid, InputStream stream, String fileName, String fileType) {
        repository.getAttachFileRepository().updateAttach(pid, fileName, fileType, stream);
    }

    @Override
    public void uploadAttachFile(String pid, InputStream stream, String fileName) {
        uploadAttachFile(pid, stream, fileName, null);
    }

    @Override
    public void updateContent(String pid, String content) {
        repository.getAttachFileRepository().updateContent(pid, content);
    }

    @Override
    public void updateContent(String pid, InputStream stream) {
        repository.getAttachFileRepository().updateContent(pid, stream);
    }

    @Override
    public void updateContent(String pid, InputStream stream, String filename)
    {
        repository.getAttachFileRepository().updateContent(pid, stream, filename);
    }

    @Override
    public void updatePreviewImage(String pid, PreviewImageSize size, InputStream stream) {
        repository.getAttachFileRepository().updatePreviewImage(pid, size, stream);
    }

    @Override
    public void remove(String pid) {
        repository.remove(pid);
    }


    @Override
    public void removeAttach(String pid, String filename, String filetype) {
        repository.getAttachFileRepository().deleteAttach(pid, filename, filetype);
    }

    @Override
    public GridFSDBFile getAttachFile(String pid, String filename, String fileType) {

        return repository.getAttachFileRepository().getAttach(pid, filename, fileType);
    }

    @Override
    public GridFSDBFile getAttachFile(String pid, String filename) {
        return getAttachFile(pid, filename, null);
    }

    @Override
    public GridFSDBFile getAttachFile(ObjectId id) {
        return repository.getAttachFileRepository().getAttach(id);
    }

    @Override
    public GridFSDBFile getAttachFile(String fullname) {
        return repository.getAttachFileRepository().getAttach(fullname);
    }


}
