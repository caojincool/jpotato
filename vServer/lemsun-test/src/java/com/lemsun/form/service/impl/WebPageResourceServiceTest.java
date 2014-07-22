package com.lemsun.form.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.core.*;
import com.lemsun.core.service.IResourceService;

import com.lemsun.form.WebPageParam;
import com.lemsun.form.WebPageResource;
import com.lemsun.form.service.IWebPageResourceService;
import com.lemsun.web.manager.controller.model.query.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * web页面组件简单测试类
 * User: dpyang
 * Date: 13-3-21
 * Time: 上午10:42
 */
public class WebPageResourceServiceTest extends TestSupport {

    @Autowired
    IWebPageResourceService webPageResourceService;

    @Autowired
    IResourceService resourceService;

    private Logger logger = LoggerFactory.getLogger(WebPageResourceServiceTest.class);

    //创建基础信息
    @Test
    public void testCreate() throws Exception {

    }

    /**
     * 更新web页面信息
     */
    @Test
    public void testUpdateWebPageResource() throws Exception {
        WebPageResource webPageResource = webPageResourceService.get("C000002385");
        webPageResource.setAttribute("testupdateAttribute", "我是附加的信息");
        webPageResource.setContextType("html/text");
        webPageResource.setCache(true);
        webPageResource.setCacheTime(12);
        webPageResource.setInitScript("test initScripte");
        webPageResource.setEndScript("test endScript");
        logger.info("开始更新刚刚插入的web页面组件C000002385");
        webPageResourceService.update(webPageResource);
        logger.info("更新完毕!");
    }

    /**
     * 更新web组件内容
     */
    @Test
    public void testUpdateWebPageContext() throws Exception {
        WebPageResource webPageResource = webPageResourceService.get("C000002385");
        logger.info("开始web页面组件C000002385内容");
        webPageResourceService.update(webPageResource, "<a>sdfsdfasd</a>");
        logger.info("更新完毕!");
    }

    @Test
    public void testGetWebPageResourceAttaches() {

    }

    @Test
    public void testGetTempleResource() {
        //List<LemsunResource> list=webPageResourceService.getTempResourse();

        //logger.debug("父组件的个数是:\t"+list.size());
    }

    @Test
    public void testStartParam() {
        List<WebPageParam> params = webPageResourceService.getResourceParams("C000000974");
    }

    @Test
    public void testUpsetAllWebResoruceContext() {
        //h获取所有的web组件
        ComponentQuery query = new ComponentQuery();
        query.setPage(1);
        query.setStart(0);
        query.setLimit(22);
        query.setCategory(BaseCategory.WEB_SKIN.getCategory());
        Page<LemsunResource> data = resourceService.getPageing(query,LemsunResource.class);

        for (IResource l : data) {

            logger.debug(l.getPid() + ":" + resourceService.getContent(l.getPid()).substring(0, 20));
        }
    }

    @Test
    public void removelAll(){

    }
}
