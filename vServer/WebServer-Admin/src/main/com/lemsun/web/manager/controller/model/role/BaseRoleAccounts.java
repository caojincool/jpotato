package com.lemsun.web.manager.controller.model.role;

import com.lemsun.auth.BaseAccountRole;

import java.util.List;

/**
 * 创建用户信息
 * User: dpyang
 * Date: 13-1-31
 * Time: 上午11:36
 */
public class BaseRoleAccounts {
    private List<BaseAccountRole> ras;

    /**
     * 获取一组帐号角色信息
     */
    public List<BaseAccountRole> getRas() {
        return ras;
    }

    /**
     * 设置一组帐号角色信息
     */
    public void setRas(List<BaseAccountRole> ras) {
        this.ras = ras;
    }
}
