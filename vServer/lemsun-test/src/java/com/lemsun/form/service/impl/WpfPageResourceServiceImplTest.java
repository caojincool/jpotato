package com.lemsun.form.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.core.IResource;
import com.lemsun.form.service.IWpfPageResourceService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * User: 宗旭东
 * Date: 13-3-29
 * Time: 上午11:27
 * 测试wpf组件的基本管理
 */
public class WpfPageResourceServiceImplTest extends TestSupport {

    @Autowired
    private IWpfPageResourceService resourceService;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(WpfPageResourceServiceImplTest.class);

    private IResource resource;

    @Before
    public void befor() {
        resource = resourceService.get("C000002249");
    }


    @Test
    public void testRemoveResouceAttachFile(){

    }
}
