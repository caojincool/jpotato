package com.lemsun.auth.service;

import com.lemsun.auth.PermissionNode;
import com.lemsun.auth.PermissionNodeExcetion;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * 权限节点服务接口.
 * User: dpyang
 * Date: 13-1-21
 * Time: 下午4:48
 */
public interface IPermissionNodeService {

    /**
     * 创建新的权限
     * @param permissionNode 权限节点
     */
    void create(PermissionNode permissionNode) throws PermissionNodeExcetion;

    /**
     * 保存已经存在的权限节点
     * @param permissionNode 权限节点
     */
    void save(PermissionNode permissionNode) throws PermissionNodeExcetion;

    /**
     * 删除一个权限节点及其子节点信息
     * @param permissionNode 权限节点
     */
    void delete(PermissionNode permissionNode) throws PermissionNodeExcetion;

    /**
     * 获取权限根节点
     * @return  权限根节点
     */
    PermissionNode getRoot();

    /**
     * 获取根节点下的子节点
     * @return 权限根目录下的二级权限节点
     */
    List<PermissionNode> getRootChildPermissions();

    /**
     * 根据某个节点ID返回该节点下的子节点
     * @param parentId 节点ID
     * @return 该节点下的所有子节点
     */
    List<PermissionNode>  getChild(ObjectId parentId);

    /**
     * 获取节点信息
     * @param name 节点名称
     * @return 节点信息
     */
    PermissionNode getPermissionNode(ObjectId id);

    /**
     * 根据权限节点获取该节点的详细信息
     * @param key 权限节点
     * @return 一条权限节点的详细信息
     */
    PermissionNode getPermissionNodeByKey(String key) throws PermissionNodeExcetion;
}