package com.lemsun.auth.service;

import com.lemsun.auth.BaseRole;
import com.lemsun.auth.RoleException;
import com.lemsun.core.AbstractPageRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 角色操作接口
 * User: Xudong
 * Date: 13-1-14
 * Time: 下午3:04
 */
public interface IBaseRoleService {

    /**
     * 将创建的角色保存到数据库中去
     */
    void create(BaseRole role) throws RoleException;

    /**
     * 更新一个角色信息
     * @param role 角色信息
     */
    void update(BaseRole role) throws RoleException;

    /**
     * 根据角色名称删除角色信息
     * @param name 角色名称
     * @throws com.lemsun.core.auth.AuthenticationException 角色名称为空
     */
    void delete(String name) throws RoleException;

    /**
     * 根据角色名称查询角色信息
     * @param name 角色名称
     * @return 角色信息
     * @throws com.lemsun.core.auth.AuthenticationException
     */
    BaseRole getRole(String name) throws  RoleException;
    /**
     * 获取全部的角色对象
     */
    List<BaseRole> getAll();
    /**
     * 获取分页的账号数据列�
     */
    Page<BaseRole> getPageData(AbstractPageRequest query);
}
