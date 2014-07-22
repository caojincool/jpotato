package com.lemsun.auth.service.impl;

import com.lemsun.auth.PermissionNode;
import com.lemsun.auth.PermissionNodeExcetion;
import com.lemsun.auth.repository.PermissionNodeRepository;
import com.lemsun.auth.service.IPermissionNodeService;
import com.lemsun.core.events.ResourceEvent;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限服务对象
 * User: dpyang
 * Date: 13-1-21
 * Time: 下午4:52
 */
@Service
public class PermissionNodeServiceImpl implements IPermissionNodeService {

    private PermissionNodeRepository repository;

    @Autowired
    public PermissionNodeServiceImpl(PermissionNodeRepository repository) {
        this.repository = repository;
    }

    /**
     * 创建新的权限
     *
     * @param permissionNode 权限节点
     * @throws PermissionNodeExcetion
     */
    @Override
    public void create(PermissionNode permissionNode) throws PermissionNodeExcetion {
        repository.insert(permissionNode);
    }

    /**
     * 更新已存在的权限
     *
     * @param permissionNode 权限节点
     * @throws PermissionNodeExcetion
     */
    @Override
    public void save(PermissionNode permissionNode) throws PermissionNodeExcetion {
        repository.save(permissionNode);
    }

    /**
     * 删除节点及其节点下的子节点
     *
     * @param permissionNode 权限节点
     * @throws PermissionNodeExcetion
     */
    @Override
    public void delete(PermissionNode permissionNode) throws PermissionNodeExcetion {
        repository.delete(permissionNode);
    }

    /**
     * 根据权限数据库ID查询该权限信息
     *
     * @param id 权限节点数据库ID
     * @return 权限节点信息
     */
    @Override
    public PermissionNode getPermissionNode(ObjectId id) {
        return repository.getPermissionNode(id);
    }

    /**
     * 根据权限节点获取该节点的详细信息
     *
     * @param key 权限节点
     * @return 一条权限节点的详细信息
     */
    @Override
    public PermissionNode getPermissionNodeByKey(String key) throws PermissionNodeExcetion {
        if (StringUtils.isEmpty(key))
            throw PermissionNodeExcetion.PKeyIsNull;

        return repository.getPermissionByKey(key);
    }

    /**
     * 获取权限根节点
     *
     * @return 权限根节点
     */
    @Override
    public PermissionNode getRoot() {
        return repository.getRoot();
    }

    /**
     * 获取权限根节点的的二级权限节点
     *
     * @return 二级权限节点
     */
    @Override
    public List<PermissionNode> getRootChildPermissions() {
        return repository.getRootChildPermissions();
    }

    /**
     * 根据权限ID获取其下级权限
     *
     * @param parentId 节点ID
     * @return 某个节点的下级权限
     */
    @Override
    public List<PermissionNode> getChild(ObjectId parentId) {
        return repository.getChild(parentId);
    }

}
