package com.lemsun.core.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.core.Navigation;
import com.lemsun.core.NavigationComponent;
import com.lemsun.core.service.INavigationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航测试
 * User: Xudong
 * Date: 13-1-9
 * Time: 上午11:15
 * To change this template use File | Settings | File Templates.
 */
public class NavigationServiceImplTest extends TestSupport {
    @Autowired
    private INavigationService navigationService;

    @Test
    public void testGetRoot(){
        Navigation navigation=navigationService.getRoot();
        getLog().info(navigation.toString());
    }

    @Test
    public void testGet(){
        Navigation navigation=navigationService.get("C000002363");
        getLog().info(navigation.toString());
    }

    @Test
    public void testCreate() throws Exception {
        Navigation navigation=new Navigation("dpyang");

        navigation.setParent(navigationService.getRoot());
        navigation.setRemark("测试创建节点");

        navigationService.create(navigation);
    }

    @Test
    public void testAddComponent() throws Exception {
        NavigationComponent component=new NavigationComponent();
        component.setResourcePid("C000001255");
        component.setNavPid("C000003416");
        navigationService.addComponent(component);

        getLog().info("挂载成功!");
    }

    @Test
    public void testGetAllPidByNavid(){
        List<NavigationComponent> components=navigationService.getAllComponent("C000003415");

        for (NavigationComponent component:components){
            //component.setLemsunResource();
            getLog().info(component.getResourcePid()+"|"+component.getName());
        }
    }

    @Test
    public void testAddComponents(){
        List<NavigationComponent> components=new ArrayList<>(2);

        Navigation navigation=navigationService.get("C000003415");
        NavigationComponent component=new NavigationComponent();
        component.setResourcePid("C000003218");
        component.setNavPid("C000003416");

        NavigationComponent component1=new NavigationComponent();
        component1.setResourcePid("C000003222");
        component1.setNavPid("C000003416");

        components.add(component);
        components.add(component1);

        navigationService.addComponents(components);
    }



}
