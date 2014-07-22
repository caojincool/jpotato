package com.lemsun.lkg;

import com.lemsun.TestSupport;

import com.lemsun.component.lkg.VirtualDirectory;
import com.lemsun.component.lkg.VirtualDirectoryComponent;
import com.lemsun.component.lkg.model.AddVdcForm;
import com.lemsun.component.lkg.service.IVirtualDirectoryService;
import com.lemsun.web.manager.controller.model.packages.VirtualDirectoryTreeStore;
import com.lemsun.web.manager.controller.model.query.VirtualDirectoryComponentQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lmy
 * Date: 13-8-26
 * Time: 下午4:00
 * To change this template use File | Settings | File Templates.
 */
public class VirtualDirectoryTest extends TestSupport {
    @Autowired
    IVirtualDirectoryService virtualDirectoryService;

    public void testCreate() {
        VirtualDirectory navigation = virtualDirectoryService.getVirtualDirectoryByPid("C000002075");
        VirtualDirectory newNav = new VirtualDirectory("测试3");
        //navigation 为 null 时，表示添加的是根节点
        newNav.setParent(navigation);
        newNav.setName("测试3");
        newNav.setRemark("ss");
        newNav.setCreateUser("system");
        newNav.setLid("LIDL000000012");
        virtualDirectoryService.create(newNav);
    }
    public void testGetRoot() {
        VirtualDirectory navigation = virtualDirectoryService.getRoot("L000000012");
        List<VirtualDirectoryTreeStore> list=(new VirtualDirectoryTreeStore()).convertNavigation(navigation, true);
        for(VirtualDirectoryTreeStore store:list){
            System.err.print(store.toString());
        }
    }

    public void testAddVirtualDirectoryComponent() {
        AddVdcForm ad=new AddVdcForm();
        ad.setLid("LIDL000000012");
        ad.setFid("521b09a2cb808069071a841c");
        String[] strs=new String[2];
        strs[0]="C000001226";
        strs[1]="C000001225";

        ad.setPids(strs);
        try{
         virtualDirectoryService.addVirtualDirectoryComponent(ad);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void testGetNComponentPagging() {
        VirtualDirectoryComponentQuery query=new VirtualDirectoryComponentQuery();
        query.setFid("521b0c76cb80976499b3a7ed");
        query.setPageSize(2);
        query.setLimit(2);
        query.setPage(3);
        try{
            Page<VirtualDirectoryComponent> virtualDirectoryComponents= virtualDirectoryService.getNComponentPagging(query);
            System.err.print(virtualDirectoryComponents.getSize());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testGetVirtualDirectoryComponentsByPackageId() {

        try{
            List<VirtualDirectoryComponent> virtualDirectoryComponents= virtualDirectoryService.getVirtualDirectoryComponentsByPackageId("LIDL000000012");
            System.err.print(virtualDirectoryComponents.size());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
