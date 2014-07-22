package com.lemsun.auth.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.auth.PermissionNode;
import com.lemsun.auth.PermissionNodeExcetion;
import com.lemsun.auth.service.IPermissionNodeService;
import com.lemsun.core.jackson.JsonObjectMapper;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * 权限结点测试类
 * User: dpyang
 * Date: 13-1-21
 * Time: 下午5:00
 */
public class PermissionNodeServiceImplTest extends TestSupport {
    @Autowired
    private IPermissionNodeService service;

    @Autowired
    private JsonObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(PermissionNodeServiceImplTest.class);

    /**
     * 插入权限
     */
    @Test
    public void testCreate() {
        //获取根节点
        PermissionNode pn = service.getRoot();

        //创建一级权限目录
        PermissionNode resource = new PermissionNode();
        resource.setKey("Resource");
        resource.setName("组件管理");
        resource.setRemark("组件管理的说明");
        resource.setParent(pn);
        try {
            service.create(resource);
        } catch (PermissionNodeExcetion permissionNodeExcetion) {
            logger.debug(permissionNodeExcetion.getMessage());
        }

        PermissionNode system = new PermissionNode();
        system.setKey("System");
        system.setName("系统管理");
        system.setRemark("系统管理的说明");
        system.setParent(pn);
        try {
            service.create(system);
        } catch (PermissionNodeExcetion permissionNodeExcetion) {
            logger.debug(permissionNodeExcetion.getMessage());
        }

        PermissionNode personnel = new PermissionNode();
        personnel.setKey("Personner");
        personnel.setName("人员管理");
        personnel.setRemark("人员管理的说明");
        personnel.setParent(pn);

        try {
            service.create(personnel);
        } catch (PermissionNodeExcetion permissionNodeExcetion) {
            logger.debug(permissionNodeExcetion.getMessage());
        }

        //创建二级权限目录
        PermissionNode[] twoNode = new PermissionNode[8];

        twoNode[0] = new PermissionNode();
        twoNode[0].setKey("Nav");
        twoNode[0].setName("导航管理");
        twoNode[0].setRemark("导航管理说明");
        twoNode[0].setParent(resource);

        twoNode[1] = new PermissionNode();
        twoNode[1].setKey("Category");
        twoNode[1].setName("类别管理");
        twoNode[1].setRemark("类别管理说明");
        twoNode[1].setParent(resource);

        twoNode[2] = new PermissionNode();
        twoNode[2].setKey("Resource");
        twoNode[2].setName("组件管理");
        twoNode[2].setRemark("组件管理说明");
        twoNode[2].setParent(resource);

        twoNode[3] = new PermissionNode();
        twoNode[3].setKey("Category");
        twoNode[3].setName("系统类别管理");
        twoNode[3].setRemark("系统类别管理说明");
        twoNode[3].setParent(system);

        twoNode[4] = new PermissionNode();
        twoNode[4].setKey("Instance");
        twoNode[4].setName("系统实例管理");
        twoNode[4].setRemark("系统实例管理说明");
        twoNode[4].setParent(system);

        twoNode[5] = new PermissionNode();
        twoNode[5].setKey("Account");
        twoNode[5].setName("帐号管理");
        twoNode[5].setRemark("帐号管理说明");
        twoNode[5].setParent(personnel);

        twoNode[6] = new PermissionNode();
        twoNode[6].setKey("Role");
        twoNode[6].setName("角色管理");
        twoNode[6].setRemark("角色管理说明");
        twoNode[6].setParent(personnel);

        twoNode[7] = new PermissionNode();
        twoNode[7].setKey("Permission");
        twoNode[7].setName("权限浏览");
        twoNode[7].setRemark("权限浏览的说明");
        twoNode[7].setParent(personnel);

        for (PermissionNode p : twoNode) {
            try {
                service.create(p);
            } catch (PermissionNodeExcetion permissionNodeExcetion) {
                logger.debug(permissionNodeExcetion.getMessage());
            }
        }
    }

    /**
     * 更新数据测试
     */
    @Test
    public void testUpdate() {
        /*
        logger.debug("开始测试修改某个权限节点信息");
        logger.debug("查询根节点信息");
        PermissionNode permissionNode = service.getRoot();

        String s = null;
        try {
            s = objectMapper.writeValueAsString(permissionNode);
            logger.debug(s);
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }

        logger.debug("查询完成\r\n开始更新");
        permissionNode.setKey("Root");
        try {
            service.save(permissionNode);
            logger.debug("更新完毕!");
        } catch (PermissionNodeExcetion permissionNodeExcetion) {
            logger.debug(permissionNodeExcetion.getMessage());
        }
        */

        logger.debug("开始测试修改某个权限节点信息");
        logger.debug("查询根节点信息");

        PermissionNode root = service.getRoot();
        ObjectId key1id = new ObjectId("50ff54661800598052da80c8");
        PermissionNode key1 = service.getPermissionNode(key1id);

        String s = null;
        try {
            s = objectMapper.writeValueAsString(key1);
            logger.debug(s);
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }

        logger.debug("查询完成\r\n开始更新");
        key1.setParent(root);
        try {
            service.save(key1);
            logger.debug("更新完毕!");
        } catch (PermissionNodeExcetion permissionNodeExcetion) {
            logger.debug(permissionNodeExcetion.getMessage());
        }
    }

    /**
     * 返回根节点
     */
    @Test
    public void testGetRoot() {
        try {
            logger.debug("开始获取根节点");
            PermissionNode permissionNode = service.getRoot();
            logger.debug("根节点信息如下:\r\n");
            String s = objectMapper.writeValueAsString(permissionNode);
            logger.debug(s);
            logger.debug("测试完毕");
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
    }

    @Test
    public void testGetChild() {
        ObjectId id = new ObjectId("50ff57db1800830b05163f4a");
        List<PermissionNode> permissionNodes = service.getChild(id);

        try {
            logger.debug("开始输出");
            String s = objectMapper.writeValueAsString(permissionNodes.size());
            logger.debug(s);
            logger.debug("输出完毕");
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
    }

    /**
     * 删除测试
     */
    @Test
    public void testDelete() {
        //删除末级节点
        /*ObjectId id=new ObjectId("50ff734f7cdec8d04e9c5389");
        PermissionNode permissionNode=service.getPermissionNode(id);
        try {
            service.delete(permissionNode);
        } catch (PermissionNodeExcetion permissionNodeExcetion) {
            logger.debug(permissionNodeExcetion.getMessage());
        }*/

        //删除有末级就节点的节点
        ObjectId id = new ObjectId("50ff35f21800ca5318bd6fc6");
        PermissionNode permissionNode = service.getPermissionNode(id);

        try {
            service.delete(permissionNode);
        } catch (PermissionNodeExcetion permissionNodeExcetion) {
            logger.debug(permissionNodeExcetion.getMessage());
        }
    }
}
