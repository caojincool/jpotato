package com.lemsun.form.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.form.WpfScriptResource;
import com.lemsun.form.service.IWpfScriptResourceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 测试
 * User: Xudong
 * Date: 13-1-29
 * Time: 下午2:55
 */
public class WpfScriptResourceServiceImplTest extends TestSupport {

    @Autowired
    private IWpfScriptResourceService resourceService;

    @Test
    public void testGetGlobel() {
        List<WpfScriptResource> resources = resourceService.getAll();

        getLog().debug(toJson(resources));
    }

    @Test
    public void testGet(){
        WpfScriptResource wpfScriptResource=resourceService.get("C000002280");
        getLog().info(wpfScriptResource.toString());
    }

}
