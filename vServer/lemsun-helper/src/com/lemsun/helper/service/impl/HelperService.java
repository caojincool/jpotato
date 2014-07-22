package com.lemsun.helper.service.impl;

import com.lemsun.auth.BaseAccount;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.ResourceAttach;
import com.lemsun.core.PreviewImageSize;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.service.impl.AbstractResourceSupportService;
import com.lemsun.helper.HelpItem;
import com.lemsun.helper.HelpQuery;
import com.lemsun.helper.VelocityUtils;
import com.lemsun.helper.service.IHelperService;

import com.mongodb.gridfs.GridFSDBFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * User: 刘晓宝
 * Date: 13-12-3
 * Time: 上午8:42
 */
@Service
public class HelperService implements IHelperService{
    private final static Logger log = LoggerFactory.getLogger(HelperService.class);

    private ResourceRepository resourceRepository;
    protected IAccountService accountService;

    @Autowired
    public HelperService(IAccountService accountService,ResourceRepository resourceRepository) {
        this.accountService=accountService;
        this.resourceRepository=resourceRepository;

    }

    @Override
    public Page<HelpItem> query(HelpQuery query) {

        Page<LemsunResource> dataResource = resourceRepository.getPagging(query, LemsunResource.class);
        List<LemsunResource> listData=dataResource.getContent();
        List<HelpItem> helpList=new ArrayList<>();
        if(listData!=null){
            for(LemsunResource resource:listData){
                HelpItem item=new HelpItem();
                item.setCategory(resource.getCategory());
                item.setPid(resource.getPid());
                item.setName(resource.getName());
                item.setUpdateTime(resource.getUpdateTime());
                item.setDescrption(resource.getRemark());
                item.setBusinessCode(resource.getBusinessCode());
                helpList.add(item);
            }
        }

        return new PageImpl<HelpItem>(helpList,query,dataResource.getTotalElements());
    }

    @Override
    public void updateImage(String pid, int size, InputStream stream, String suffix) {

        resourceRepository.getAttachFileRepository().updatePreviewImage(pid, convertToSize(size), stream);
    }

    @Override
    public GridFSDBFile getImage(String pid, int size)  {
        return resourceRepository.getAttachFileRepository().getPreviewImage(pid, convertToSize(size));

    }

    @Override
    public String getHelpContext(String pid) {
        return resourceRepository.getAttachFileRepository().getDescription(pid);
    }

    @Override
    public String getHelpContextHandler(String pid) {
        String details=getHelpContext(pid);
        if(details!=null){

            if(pid.startsWith("C")||pid.startsWith("S")){
                LemsunResource resource=resourceRepository.getBaseResource(pid);

                BaseAccount account=null;
                try{
                    account=accountService.getAccountByAccount(resource.getCreateUser());
                }catch (Exception e){
                    log.info("异常数据",e.getMessage());
                }
                if(account==null){
                    account=new BaseAccount();
                }
                resource.setCreateUser(account.getShowName());
                details= VelocityUtils.evaluate(details, resource);
            }
        }else{
            details=new String("请组件创建人员完善组件说明！");
        }
        return details;
    }

    @Override
    public void updateHelpContext(String pid,String context) {
        resourceRepository.getAttachFileRepository().updateDescription(pid, context);
    }

    @Override
    public void updateHelpContextAttachFile(String pid, InputStream stream, String fileName, String fileType) {
        resourceRepository.getAttachFileRepository().updateDocAttach(pid, fileName,fileType,stream);
    }

    @Override
    public GridFSDBFile getHelpContextAttach(String pid, String fileName) throws IOException {
        return resourceRepository.getAttachFileRepository().getDocAttach(pid,fileName,null);
    }

    @Override
    public List<ResourceAttach> getHelpContextAttachList(String pid) {
        //return helperRepository.getResourceDetailsAttaches(pid);
        //return resourceService
        return null;
    }

    @Override
    public void deleteHelpContext(String pid) {
        //暂时不做事情 因为在组件删除时候清理所有附件 以后可能要删除搜索引擎中产生数据
        if(log.isDebugEnabled())
        log.debug("执行了删除搜索引擎中产生数据");
    }

    private PreviewImageSize convertToSize(int size)
    {
        if(size == -1) return PreviewImageSize.Original;
        if(size == 1) return PreviewImageSize.Middle;
        return PreviewImageSize.Small;
    }

}
