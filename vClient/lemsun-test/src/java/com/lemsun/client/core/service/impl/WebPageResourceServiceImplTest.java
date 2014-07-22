package com.lemsun.client.core.service.impl;

import com.lemsun.client.TestSupport;
import com.lemsun.client.core.Utils;
import com.lemsun.client.core.model.WebPageResource;
import com.lemsun.client.core.service.IWebPageResourceService;
import org.apache.commons.codec.Charsets;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: dpyang
 * Date: 13-5-7
 * Time: 下午3:04
 * To change this template use File | Settings | File Templates.
 */
public class WebPageResourceServiceImplTest extends TestSupport {

    @Autowired
    IWebPageResourceService webPageResourceServrce;

    private Logger logger= LoggerFactory.getLogger(WebPageResourceServiceImplTest.class);

    //获取页面组件
    @Test
    public void testGetWebPageResource() throws IOException {

            WebPageResource webPageResource =webPageResourceServrce.getWebPageResource("C000001052");
            logger.debug(String.valueOf(webPageResource.getStartParam().size()));

    }

    //获取页面组件附件
    @Test
    public void testGetWebPageResourceAttach() throws IOException {


            String pid=Utils.parsePid("1044",false);

            byte[] data=webPageResourceServrce.getWebPageResourceAttachContext(pid,"cms.js");

            String context = Charsets.UTF_8.newDecoder().decode(ByteBuffer.wrap(data)).toString();
            logger.debug(context);


    }

    //测试组件内容
    @Test
    public void testGetWebpageResourceContext() throws IOException {
            WebPageResource webPageResource =webPageResourceServrce.getWebPageResource("C000001052");

            String context=webPageResourceServrce.getWebPageResourceContext(webPageResource);
            logger.debug(context);
    }
}

