package com.lemsun.client.core.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lemsun.client.core.IAccount;
import com.lemsun.client.core.IResponseEntity;
import com.lemsun.client.core.LemsunException;
import com.lemsun.client.core.model.ImageResource;
import com.lemsun.client.core.service.IAccountService;
import com.lemsun.client.core.service.IImageResourceService;
import com.lemsun.client.core.service.IRemoteService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-5-31
 * Time: 上午9:34
 */
@Service
public class ImageResourceImpl implements IImageResourceService {

    private IRemoteService remoteService;
    private IAccountService accountService;
    private static final String ResourceAddress = "resource/get";
    private static final String ResourceContextAddress = "resource/content/get";
    private static final Logger log= LoggerFactory.getLogger(ResourceServiceImpl.class);


    @Autowired
    public ImageResourceImpl(IRemoteService remoteService,
                             IAccountService accountService){
        this.remoteService=remoteService;
        this.accountService=accountService;
    }

    /**
     * 根据组件编码获取图片组件
     *
     * @param pid 组件
     * @return 图片类型组件
     */
    @Override
    public ImageResource getImageResource(IAccount account,String pid) {
        String url = ResourceAddress
                + String.format("?type=image&pid=%s&token=%s", pid, account == null ? "" : account.getToken());

        IResponseEntity<ImageResource> entity =
                remoteService.getAddressContext(
                        url,
                        new TypeReference<IResponseEntity<ImageResource>>() {}
                );

        if(!entity.isSuccess()) {
            throw new LemsunException(entity.getMessage());
        }

        return entity.getEntity();
    }

    @Override
    public ImageResource getImageResource(String pid) {
        return getImageResource(accountService.getCurrentAccount(), pid);
    }

    /**
     * 根据图片组件获取图片
     *
     * @param imageResource 图片组件
     * @return 图片
     */
    @Override
    public byte[] getImage(IAccount account,ImageResource imageResource)  {
        return getImage(account,imageResource.getPid());
    }

    @Override
    public byte[] getImage(ImageResource resource) {
        return getImage(resource.getPid());
    }

    /**
     * 根据图片组件编码获取图片
     *
     * @param pid 图片组件编码
     * @return 图片
     */
    @Override
    public byte[] getImage(IAccount account,String pid)  {
        String url = ResourceContextAddress
                + String.format("?type=resource&pid=%s&token=%s", pid, account == null ? "" : account.getToken());

        return Base64.decodeBase64(remoteService.getAddressData(url));
    }

    @Override
    public byte[] getImage(String pid) {
        return getImage(accountService.getCurrentAccount(), pid);
    }
}
