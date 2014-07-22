package com.lemsun.auth.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.auth.*;
import com.lemsun.auth.service.IBaseRoleService;
import com.lemsun.auth.service.IPermissionNodeService;
import com.lemsun.auth.service.impl.model.RoleQuery;
import com.lemsun.core.jackson.JsonObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Xudong
 * Date: 13-1-14
 * Time: 下午3:15
 */
public class BaseRoleServiceImplTest extends TestSupport {

    @Autowired
    private IBaseRoleService service;

    @Autowired
    private IPermissionNodeService permissionNodeService;

    @Autowired
    private JsonObjectMapper jsonObjectMapper;

    private static Logger logger = LoggerFactory.getLogger(BaseRoleServiceImpl.class);


    //获取系统角色拥有权限即所有菜单都有允许操作
    private List<PermissionKey> getPermission(Permission permission) {
        List<PermissionKey> permissionKeys = new ArrayList<>(12);

        //获取所有权限节点信息
        PermissionNode node = permissionNodeService.getRoot();
        PermissionKey p = new PermissionKey(permission, node.getKey());
        permissionKeys.add(p);
        PermissionKey twoPermissionKey;
        for (PermissionNode tp : node.getChild()) {
            twoPermissionKey = new PermissionKey(permission, tp.getKey());
            permissionKeys.add(twoPermissionKey);
            twoPermissionKey = null;

            for (PermissionNode trp : tp.getChild()) {
                twoPermissionKey = new PermissionKey(permission, trp.getKey());
                permissionKeys.add(twoPermissionKey);
                twoPermissionKey = null;
            }
        }
        return permissionKeys;
    }

    @Test
    public void testCreate() {
        getLog().debug("角色测试开始:");

        //设置系统角色
        BaseRole administrators = new BaseRole();
        administrators.setName("administrators");
        administrators.setCreateAccount("administrator");
        administrators.setPermissions(getPermission(Permission.Allow));
        administrators.setDescribe("管理员对系统不受限制的完全访问权");
        administrators.setisSystem(true);

        try {
            service.create(administrators);
        } catch (RoleException e) {
            logger.debug(e.getMessage());
        }

        BaseRole guest = new BaseRole();
        guest.setName("Guests");
        guest.setPermissions(getPermission(Permission.Unkonw));
        guest.setCreateAccount("administrator");
        guest.setDescribe("按默认值，来宾跟用户组的成员有同等访问权，但来宾帐户的限制更多");
        guest.setisSystem(true);

        try {
            service.create(guest);
        } catch (RoleException e) {
            logger.debug(e.getMessage());
        }
        logger.debug("角色测试结束");
    }

    @Test
    public void testDelete() {

        getLog().debug("开始测试删除角色信息");

        String name = "dtscal";
        try {
            service.delete(name);
        } catch (RoleException e) {
            getLog().debug(e.getMessage());
        }

        getLog().debug("结束测试删除角色信息");
    }

    @Test
    public void testUpdate() {
        getLog().debug("开始更新角色信息");

        String s = null;

        try {
            BaseRole role = service.getRole("运维部");
            role.setName("dtscal");
            //s = jsonObjectMapper.writeValueAsString(roles);
            service.update(role);
        } catch (RoleException e) {
            getLog().debug(e.getMessage());
        }
        getLog().debug("更新完成:" + s);
    }

    @Test
    public void testLoadAll() {
        getLog().debug("开始测试返回角色列表");

        List<BaseRole> roles = service.getAll();
        String s = null;
        try {
            s = jsonObjectMapper.writeValueAsString(roles);
        } catch (IOException e) {
            getLog().debug(e.getMessage());
        }
        getLog().debug("测试得到的json字符串是:" + s);
    }

    @Test
    public void testFilter(){
        List<String> names=new ArrayList<>(3);

        String[] ab={"Guests","administrators","test","tests"};

        names.add("Guests");
        names.add("administrators");
        names.add("test");

        RoleQuery roleQuery=new RoleQuery();
        roleQuery.setAb(ab);

        Page<BaseRole> roles=service.getPageData(roleQuery);

        for (BaseRole role:roles){
            getLog().info("过滤后的角色名称："+role.getName()+"\r\n");
        }
    }
}
