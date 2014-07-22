package com.lemsun.client.core.service.impl;

import com.lemsun.client.TestSupport;
import com.lemsun.client.core.model.LemsunResource;
import com.lemsun.client.core.model.ResourceAttach;
import com.lemsun.client.core.service.IResourceService;
import org.apache.commons.codec.Charsets;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * User: 宗旭东
 * Date: 13-3-11
 * Time: 上午11:43
 */
public class ResourceServiceImplTest extends TestSupport {

    @Autowired
    private IResourceService resourceService;

    //获取基本的组件
    @Test
    public void testGetResource() throws IOException {

      LemsunResource resource = resourceService.getLemsunResource(getAccount(), "C000006980");

      getLog().debug(resource.getCategory().toString());

    }

    //获取基本组件的附件
    @Test
    public void testGetResourceAttach() throws IOException {
        ResourceAttach attach = resourceService.getAttachInfo(resourceService.getDbContentName("C000006980"));
        getLog().debug(attach.getFileid());
    }

    //获取基本组件内容
    @Test
    public void testGetResourceContext() throws IOException {

        LemsunResource resource = resourceService.getLemsunResource(getAccount(), "C000001052");
        String context=resourceService.getResourceContext(resource);

        getLog().debug(context);
    }
}
