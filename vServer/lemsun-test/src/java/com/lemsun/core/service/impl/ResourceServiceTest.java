package com.lemsun.core.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.Permission;
import com.lemsun.core.ResourcePermission;
import com.lemsun.core.query.LemsunResourceQuery;
import com.lemsun.core.service.IResourceService;
import com.lemsun.form.WebScriptResource;
import com.mongodb.gridfs.GridFSDBFile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-23
 * Time: 下午9:11
 */
public class ResourceServiceTest extends TestSupport {

    @Autowired
    private IResourceService service;

    @Test
    public void testGlobleReload() {

    }

    @Test
    public void testGetPagging() {
        LemsunResourceQuery query = new LemsunResourceQuery();

        query.setCategory("DB");
        query.setName("阳泉");
        //Page<?> data = service.getPageing(query);

        //getLog().debug(toJson(data));
    }

    @Test
    public void testGetLemsunResource() {
        LemsunResource resource = service.getBaseResource("c000000766");
        if (resource != null)
            getLog().debug(resource.getPid());
    }

    @Test
    public void testGetResourcePermission(){
        List<ResourcePermission> list=service.listPermissions("C000000927");
        for (ResourcePermission rp:list){
            getLog().info(rp.toString());
        }
    }

    @Test
    public void testUpdateResourcePermission() throws Exception {
        ResourcePermission rp=new ResourcePermission();
        rp.setName("admin");
        rp.setType(1);
        rp.setPermission(Permission.Allow);

        List<ResourcePermission> list=new ArrayList<>();
        list.add(rp);

        service.updatePermissions("C000000927",list);
    }

    @Test
    public void testGetAllFs(){
        //List<GridFSDBFile> fils=service.getDbFiles("S|C\\d{9}.context");
        //getLog().debug("文件总数:"+fils.size());
    }
}
