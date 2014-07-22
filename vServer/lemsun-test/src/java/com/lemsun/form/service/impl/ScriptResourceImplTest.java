package com.lemsun.form.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.core.service.IResourceService;
import com.lemsun.form.ScriptResource;
import com.lemsun.form.service.IScriptResourceService;
import com.lemsun.reporter.ReporterScriptResource;
import com.lemsun.reporter.service.IReporterScriptResourceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-9-3
 * Time: 下午3:59
 */
public class ScriptResourceImplTest extends TestSupport{


    @Autowired
    private IScriptResourceService scriptResourceServiceImpl;

    @Autowired
    private IReporterScriptResourceService reporterScriptResourceService;

    @Test
    public void testGetWebScript(){
        try {
            getLog().debug(scriptResourceServiceImpl.get("C000001806").toString());
        } catch (Exception e) {
            getLog().error("遇到错误:"+e.getMessage());
        }
    }

    @Test
    public void testGetAll(){
        List<ReporterScriptResource> resources=reporterScriptResourceService.getAll();
        for (ReporterScriptResource rs :resources)
        {
            getLog().debug(reporterScriptResourceService.getContent(rs));
        }

    }

    @Test
    public void testGetWpfScript(){
        try {
            getLog().debug(scriptResourceServiceImpl.get("C000001837").toString());
        } catch (Exception e) {
            getLog().error("遇到错误:"+e.getMessage());
        }
    }

    @Test
    public void testGetScript(){
        try {
            getLog().debug(scriptResourceServiceImpl.get("C000001833").toString());
        } catch (Exception e) {
            getLog().error("遇到错误:"+e.getMessage());
        }
    }

    @Test
    public void testUpdateScriptContent(){
        String content="abc";
        try {
            ScriptResource scriptResource=scriptResourceServiceImpl.get("C000001833");
            //scriptResourceServiceImpl.updateContent(scriptResource,content);
            testGetScript();
        } catch (Exception e) {
           getLog().error("遇到错误:"+e.getMessage());
        }

    }
}
