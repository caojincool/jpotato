package com.lemsun.client.core.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lemsun.client.core.Host;
import com.lemsun.client.core.IAccount;
import com.lemsun.client.core.IResponseEntity;
import com.lemsun.client.core.LemsunException;
import com.lemsun.client.core.model.LemsunResource;
import com.lemsun.client.core.model.PreviewImageSize;
import com.lemsun.client.core.model.ResourceAttach;
import com.lemsun.client.core.service.IAccountService;
import com.lemsun.client.core.service.IRemoteService;
import com.lemsun.client.core.service.IResourceService;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

/**
 * User: 宗旭东
 * Date: 13-3-9
 * Time: 下午12:04
 */
@Service
public class ResourceServiceImpl implements IResourceService {

    private IRemoteService remoteService;
    private IAccountService accountService;
    private Map<String,LemsunResource> cacheResource; //从服务器中拿取的组件缓存起来比较快
    private Host host;


    private static final String ResourceAddress = "resource/get";
    private static final String ResourceContextAddress = "resource/content/get";
    private static final String ResourceContextAttachAddress = "resource/content/attach/get";
    private static final String ScriptResourceAddress = "script/get";
    private static final String CurrentResourceAddress = "resource/current/get";
    private static final String ResourceAttachInfoAddress = "resource/attachinfo/get?pid=%s&filename=%s&filetype=%s";
    private static final String FilenameAttachInfoAddress = "resource/attachinfo/get?fullname=%s";
    private static final String ResourceAttachStreamAddress = "resource/attach/get?";

    private static final Logger log= LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    public ResourceServiceImpl(IRemoteService remoteService,
                               IAccountService accountService,
                               @Qualifier("host") Host host) {
        this.remoteService = remoteService;
        this.host = host;
        this.accountService = accountService;
    }

    @Override
    public String getPreviewImageName(String pid, PreviewImageSize size) {
        return pid + "preview." + size.toString();
    }

    @Override
    public String getDbFileName(String pid, String filename, String filetype) {
        return pid + ".atatch." + filename + (StringUtils.isEmpty(filename) ? "" : ("." + filetype));
    }

    @Override
    public String getDbContentName(String pid) {
        return pid + ".content";
    }

    @Override
    public String getDbResourceDescriptionName(String pid) {
        return pid + ".details";
    }

    @Override
    public LemsunResource getCurrentResource(String pid) {
        IAccount account = accountService.getCurrentAccount();

        return getCurrentResource(pid, account != null ? account.getAdate() : new Date());
    }

    @Override
    public LemsunResource getCurrentResource(String pid, Date adate) {
        String url = CurrentResourceAddress + "?pid=" + pid + "&adate=" + DateFormatUtils.format(adate, "yyyyMMdd");

        IResponseEntity<LemsunResource> entity = remoteService.getAddressContext(url, new TypeReference<IResponseEntity<LemsunResource>>() {});

        return entity.getEntity();

    }

    /**
     * 获取账户和组件编码的组件信息
     * 这里目前还没有实现组件的缓存如果要实现的话,先根据pid获得该组件的更新时间,根据更新时间与缓存的更新时间比较
     * @param account 账号对象
     * @param pid 资源主键
     * @return
     * @throws IOException
     */
    @Override
    public LemsunResource getLemsunResource(IAccount account, String pid) {

        String url = ResourceAddress
                + String.format("?type=resource&pid=%s&token=%s", pid, account == null ? "" : account.getToken());

        IResponseEntity<LemsunResource> entity =
                remoteService.getAddressContext(
                        url,
                        new TypeReference<IResponseEntity<LemsunResource>>() {}
                );

        if(!entity.isSuccess()) {
            throw new LemsunException("获取组件对象异常");
        }

        return entity.getEntity();
    }

    /**
     * @param pid 组件id
     */
    @Override
    public LemsunResource getLemsunResource(String pid) {
        return getLemsunResource(accountService.getCurrentAccount(), pid);
    }

    @Override
    public String getResourceContext(LemsunResource resource){
        String url = ResourceContextAddress + String.format("?type=resource&pid=%s&token=%s", resource.getPid(), "");

        String context = remoteService.getAddressContext(url);
        return context;
    }

    @Override
    public byte[] getResourceAttachContext(String resourcePid, String fileName){
        String url = ResourceContextAttachAddress + String.format("?resourcePid=%s&fileName=%s", resourcePid, fileName);
        return remoteService.getAddressData(url);
    }

    @Override
    public ResourceAttach getAttachInfo(String pid, String filename) {

        try {
            if(StringUtils.isEmpty(filename))
            {
                return getAttachInfo(getDbContentName(pid));
            }


            String url = String.format(ResourceAttachInfoAddress, URLEncoder.encode(pid, "UTF-8"), URLEncoder.encode(filename, "UTF-8"), "");
            IResponseEntity<ResourceAttach> re = remoteService.getAddressContext(url, new TypeReference<IResponseEntity<ResourceAttach>>() {});
            ResourceAttach attach = re.getEntity();
            return attach;
        } catch (Exception e) {

        }

        return null;
    }

    @Override
    public ResourceAttach getAttachInfo(String fullname) {
        try {
            String url = String.format(FilenameAttachInfoAddress, URLEncoder.encode(fullname, "UTF-8"));
            IResponseEntity<ResourceAttach> re = remoteService.getAddressContext(url, new TypeReference<IResponseEntity<ResourceAttach>>() {});
            ResourceAttach attach = re.getEntity();
            return attach;
        } catch (Exception e) {

        }

        return null;
    }

    @Override
    public InputStream getAttachStream(String fileid) {

        String url = ResourceAttachStreamAddress + "fileid=" + fileid;

        try {
            return remoteService.getAddressStream(url);
        } catch (IOException e) {
            throw new LemsunException("获取文件异常");
        }

    }

    @Override
    public InputStream getAttachFileString(String fullname) {

        try {
            String url = ResourceAttachStreamAddress + "filename=" + URLEncoder.encode(fullname, "UTF-8");
            return remoteService.getAddressStream(url);
        } catch (Exception e) {
            throw new LemsunException("获取文件异常");
        }

    }


}
