package com.lemsun.client.core.service.impl;

import com.lemsun.client.TestSupport;
import com.lemsun.client.core.Host;
import com.lemsun.client.core.Utils;
import com.lemsun.client.core.jackson.JsonObjectMapper;
import com.lemsun.client.core.lmstable.Table5Resource;
import com.lemsun.client.core.model.LemsunResource;
import com.lemsun.client.core.service.IResourceService;
import com.lemsun.client.core.service.ITableResourceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * User: 宗旭东
 * Date: 13-10-28
 * Time: 下午1:11
 */
public class TableResourceServiceImplTest extends TestSupport {

    @Autowired
    ITableResourceService service;

    @Autowired
    IResourceService resourceSerivce;

    @Test
    public void testGet() throws IOException {

          String pid = "C000003796";

        LemsunResource temp = resourceSerivce.getCurrentResource(pid);


        Table5Resource resource = service.get(temp.getPid());


        getLog().debug(Utils.toJson(resource));






    }

}
