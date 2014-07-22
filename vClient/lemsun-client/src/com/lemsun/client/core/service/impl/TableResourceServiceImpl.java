package com.lemsun.client.core.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lemsun.client.core.Host;
import com.lemsun.client.core.data.TableData;
import com.lemsun.client.core.lmstable.Table5Resource;
import com.lemsun.client.core.lmstable.TableFace;
import com.lemsun.client.core.model.LemsunResource;
import com.lemsun.client.core.model.ResponseEntity;
import com.lemsun.client.core.service.IAccountService;
import com.lemsun.client.core.service.IRemoteService;
import com.lemsun.client.core.service.IResourceService;
import com.lemsun.client.core.service.ITableResourceService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * 表格组件服务实现类
 * User: 宗旭东
 * Date: 13-10-28
 * Time: 上午10:10
 */
@Service
public class TableResourceServiceImpl implements ITableResourceService {

    private Host host;
    private IAccountService accountService;
    private IRemoteService remoteService;
    private IResourceService resourceService;


    @Autowired
    public TableResourceServiceImpl(@Qualifier("host") Host host,
                                    IAccountService accountService,
                                    IRemoteService remoteService,
                                    IResourceService resourceService) {
        this.host = host;
        this.accountService = accountService;
        this.remoteService = remoteService;
        this.resourceService = resourceService;
    }

    @Override
    public Table5Resource get(String pid) throws IOException {

        LemsunResource temp = resourceService.getCurrentResource(pid);

        if(temp == null)
            return null;

        String url = "resource/get?type=table5&pid=" + temp.getPid();

       ResponseEntity<Table5Resource> entity = remoteService.getAddressContext(url,
               new TypeReference<ResponseEntity<Table5Resource>>() {
        });


        Table5Resource resource = entity.getEntity();

        if(resource != null) {
            url = "resource/content/attach/get?resourcePid=" + temp.getPid() + "&fileName=face";
            try
            {
                TableFace face = remoteService.getAddressContext(url, new TypeReference<TableFace>() {
                });

                if(face != null) {
                    resource.setFace(face);
                }
            }
            catch (Exception ex)
            {

            }
        }


        return resource;
    }

    @Override
    public TableData getTableData(Table5Resource resource) throws IOException {

        Date adate = accountService.getCurrentAccount().getAdate();

        String url = "resource/data/get?pid=" + resource.getId() + "&adate=" + DateFormatUtils.format(adate, "yyyyMMdd");

        ResponseEntity<TableData> data = remoteService.getAddressContext(url, new TypeReference<ResponseEntity<TableData>>() {
        });

        return data.getEntity();
    }

    @Override
    public long getTableDataCount(Table5Resource resource) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
