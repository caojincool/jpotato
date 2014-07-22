package com.lemsun.com.lemsun.web.script.init;

import com.lemsun.TestSupport;
import com.lemsun.core.service.IResourceService;
import com.lemsun.form.WebScriptResource;
import com.lemsun.form.service.IWebScriptResourceService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * User: dpyang
 * Date: 13-5-6
 * Time: 上午11:
 */
public class WebScriptResourceServiceImplTest extends TestSupport {


    @Autowired
    private IWebScriptResourceService resourceService;

    @Autowired
    private IResourceService service;

    private static final Logger logger = LoggerFactory.getLogger(WebScriptResourceServiceImplTest.class);

    /**
     * 读取初始化脚本文件内容
     *
     * @param fileName 初始化的脚本内容
     * @return 脚本内容
     * @throws IOException
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
        WebScriptResource webScriptResource = resourceService.get("C000003165");
        String context = getResourceContext("InitFunction.js");

        logger.debug("读取到NavComponent.js的内容:" + context);

        if (webScriptResource == null) {
            WebScriptResource wsr = new WebScriptResource();
            wsr.setSystem(true);
            wsr.setCreateUser("lms");
            wsr.setContext(context);
            //resourceService.save(wsr);
        } else {
            webScriptResource.setSystem(true);
            //resourceService.update(webScriptResource, context);
        }

        logger.debug("更新成功!");
    }


    //读取脚本组件
    @Test
    public void testReadWebScriptContext() {
        //如果不存在就创建一个组件
        WebScriptResource webScriptResource = resourceService.get("1253");

        String context = null;
       // context = resourceService.getContext(webScriptResource);

        logger.debug(context);
    }

    @Test
    public void testDeleteWebScrptResource() {
        //service.delete("S000001063");
    }
}
