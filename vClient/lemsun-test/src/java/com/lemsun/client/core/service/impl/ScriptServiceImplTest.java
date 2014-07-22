package com.lemsun.client.core.service.impl;

import com.lemsun.client.TestSupport;
import com.lemsun.client.core.model.InnerFunctionDefines;
import com.lemsun.client.core.model.ScriptResource;
import com.lemsun.client.core.service.IScriptService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dpyang
 * Date: 13-11-25
 * Time: 下午3:08
 * To change this template use File | Settings | File Templates.
 */
public class ScriptServiceImplTest extends TestSupport {

    @Autowired
    IScriptService service;

    @Test
    public void testGetGlobelScriptResources(){
        getLog().info("开始获取组件");

        try {
            List<ScriptResource> resources=service.getGlobelScriptResources();

            for (ScriptResource resource:resources){
                getLog().info(resource.getContext());
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testGetInnerScriptsByPid() throws IOException {
        List<InnerFunctionDefines> s=service.getInnerScripts("C000004453");

        if (s!=null){
            for (InnerFunctionDefines resource:s){
                getLog().info(resource.getContext());
            }
        }
    }
}
