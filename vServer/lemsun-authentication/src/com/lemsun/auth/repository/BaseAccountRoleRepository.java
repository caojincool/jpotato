package com.lemsun.auth.repository;

import com.lemsun.auth.BaseAccountRole;
import com.lemsun.auth.RoleException;
import com.lemsun.core.repository.AbstractLocalRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * 帐号角色数据库操作对象
 * User: dpyang
 * Date: 13-1-30
 * Time: 下午2:25
 */
@Repository
public class BaseAccountRoleRepository extends AbstractLocalRepository{

    @Autowired
    public BaseAccountRoleRepository(MongoTemplate template) {
        super(template);
    }

    /**
     * 保存帐号角色信息
     */
    public void save(BaseAccountRole bar) throws RoleException {
        if(StringUtils.isEmpty(bar.getRoleId()))
            throw RoleException.RoleisNull;
        if(StringUtils.isEmpty(bar.getAccountId()))
            throw RoleException.BaseAccountRoleAccountIsNull;

        getTemplate().save(bar);
    }

    /**
     * 根据帐号获取帐号角色信息
     */
    public List<BaseAccountRole> getBaseAccountRoleByAccount(String account) throws Exception{
        if (StringUtils.isEmpty(account))
            throw new Exception("查询条件不能为空");

        return getTemplate().find(query(where("accountId").is(account)),BaseAccountRole.class);
    }

    /**
     * 根据角色获取帐号角色信息
     */
    public List<BaseAccountRole> getBaseAccountRoleByRole(String role) throws Exception {
        if (StringUtils.isEmpty(role))
            throw new Exception("查询条件不能为空");

        return getTemplate().find(query(where("roleId").is(role)),BaseAccountRole.class);
    }

    /**
     * 根据角色名称删除信息
     */
    public void delByRoleName(String roleName){
        getTemplate().remove(query(where("roleId").is(roleName)),BaseAccountRole.class);
    }

    public void delByAccountName(String accountId){
        getTemplate().remove(query(where("accountId").is(accountId)),BaseAccountRole.class);
    }
}
