package com.lemsun.auth.service.impl;

import com.lemsun.auth.*;
import com.lemsun.auth.repository.BaseRoleRepository;
import com.lemsun.auth.service.IBaseRoleService;
import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.IRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 角色接口实现
 * User: Xudong
 * Date: 13-1-14
 * Time: 下午3:07
 */
@Service
public class BaseRoleServiceImpl implements IBaseRoleService {

    private BaseRoleRepository roleRepository;

    @Autowired
    public BaseRoleServiceImpl(BaseRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * 创建用户角色信息
     *
     * @param role 角色信息
     * @throws com.lemsun.core.auth.AuthenticationException 判断角色是否存在
     */
    @Override
    public void create(BaseRole role) throws RoleException {
        //判断角色名称不为空
        if (StringUtils.isEmpty(role.getName()))
            throw RoleException.RoleisNull;

        //判断角色名称是否已经存在
        if (roleRepository.getRole(role.getName()) != null)
            throw RoleException.ExistRole;


        role.setCreateAccount(AccountManager.getCurrentManager().getAccount().getAccount());

        //创建用户
        roleRepository.insert(role);
    }

    /**
     * 更新角色信息
     *
     * @param role 角色信息
     */
    @Override
    public void update(BaseRole role) throws RoleException {
        //判断角色名称不为空
        if (role.getId()==null||StringUtils.isEmpty(role.getName()))
            throw RoleException.RoleisNull;

        roleRepository.save(role);
    }

    /**
     * 根据角色名称删除角色信息
     * @param name 角色名称
     * @throws com.lemsun.core.auth.AuthenticationException
     */
    @Override
    public void delete(String name) throws RoleException {
        //判断角色名称不为空
        if (StringUtils.isEmpty(name))
            throw RoleException.RoleisNull;

        roleRepository.delete(name);
    }

    /**
     * 根据角色名称获取角色信息
     * @param name 角色名称
     * @return角色信息
     * @throws com.lemsun.core.auth.AuthenticationException
     */
    @Override
    public BaseRole getRole(String name) throws RoleException {
        //判断角色名称不为空
        if (StringUtils.isEmpty(name))
            throw RoleException.RoleisNull;

        return roleRepository.getRole(name);
    }

    /**
     * 返回所有角色信息
     * @return 所有角色信息
     */
    @Override
    public List<BaseRole> getAll() {
        return roleRepository.loadAll();
    }


    public Set<PermissionKey> getRolePermissions(IRole role) {
        return null;
    }

    @Override
    public Page<BaseRole> getPageData(AbstractPageRequest query) {
        return roleRepository.getPageData(query);
    }
}
