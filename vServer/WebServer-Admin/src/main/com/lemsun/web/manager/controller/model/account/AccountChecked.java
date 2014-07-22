package com.lemsun.web.manager.controller.model.account;

import com.lemsun.auth.BaseAccount;

/**
 * 账户是否被选
 * User: dp
 * Date: 13-8-30
 * Time: 上午9:08
 */
public class AccountChecked extends BaseAccount{
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void encapsulation(BaseAccount baseAccount){
        setAccount(baseAccount.getAccount());
        setId(baseAccount.getId());
        setCreateTime(baseAccount.getCreateTime());
        setShowName(baseAccount.getShowName());
    }
}
