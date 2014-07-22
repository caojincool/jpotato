package com.lemsun.auth.service;

import com.lemsun.auth.Permission;
import com.lemsun.auth.PermissionNode;
import com.lemsun.core.IResource;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ssy
 * Date: 12-12-15
 * Time: 上午9:13
 * 权限浏览
 */
public interface IPermissionService {

    /**
     *
     * @return 返回根节点
     */
    Permission getRoot();

    /**
     * 查询导航下的资源
     * @param permission 导航下的资源
     * @return 资源列表
     */
    List<IResource> getPermissionResource(Permission permission);

    /**
     * 创建保存一个导航节点
     * @param permission 节点
     */
    void create(Permission permission);

    /**
     * 根据pid获取Navigation
     * @param pid
     * @return
     */
    Permission getPermissionByPid(String pid);

    /**
     * 修改
     * @param permission
     */
    void updatePermission(Permission permission);

    /**
     * 根据pid删除Navigation
     * @param pid
     */
    void deletePermission(String pid);

    /**
     * 重置所有权限
     *
     */
    void resetPermission();

}
