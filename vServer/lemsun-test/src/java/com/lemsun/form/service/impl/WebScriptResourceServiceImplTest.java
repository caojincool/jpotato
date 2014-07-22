package com.lemsun.form.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.form.WebScriptResource;
import com.lemsun.form.service.IWebScriptResourceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * User: 宗旭东
 * Date: 13-3-13
 * Time: 上午11:56
 */
public class WebScriptResourceServiceImplTest extends TestSupport {

    @Autowired
    private IWebScriptResourceService resourceService;

    @Test
    public void testGlobel() {
        List<WebScriptResource> resources = resourceService.getAll();

        getLog().debug(resources.toString());
    }

    @Test
    public void testGet() throws Exception {
        WebScriptResource wsr=resourceService.get("C000002268");
        getLog().debug("获取到"+wsr.toString());
    }


    //读取脚本组件
    @Test
    public void testgetContext() throws Exception {
        //如果不存在就创建一个组件
        WebScriptResource webScriptResource = resourceService.get("C000002268");

        String context = null;
        context = webScriptResource.getContext();

        getLog().debug(context);
    }

    /**
     * 读取初始化脚本文件内容
     *
     * @param fileName 初始化的脚本内容
     * @return 脚本内容
     * @throws java.io.IOException
     */
    public String getResourceContext(String fileName) throws IOException {

        File file = ResourceUtils.getFile("classpath:com/lemsun/init/web/" + fileName);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        StringBuffer sb = new StringBuffer();

        while (br.ready()) {
            sb.append(br.readLine() + "\r\n");
        }
        return new String(sb);
    }

    /**
     * 创建web导航脚本组件
     */
    @Test
    public void testCreateWebScriptByNavgation() throws Exception {
        //如果不存在就创建一个组件
        WebScriptResource webScriptResource = resourceService.get("C000000739");
        String context = getResourceContext("InitFunction.js");

        getLog().debug("读取到NavComponent.js的内容:" + context);

        if (webScriptResource == null) {
            WebScriptResource wsr = new WebScriptResource("工具包");
            wsr.setSystem(true);
            wsr.setCreateUser("lms");
            wsr.setContext(context);
            //resourceService.update(wsr);
           // resourceService.update(wsr,wsr.getContext());
        } else {
            webScriptResource.setName("工具包");
            webScriptResource.setSystem(true);
            webScriptResource.setCreateUser("lms");
           // resourceService.update(webScriptResource);
            //resourceService.update(webScriptResource,context);
        }

        getLog().debug("更新成功!");
    }
}
