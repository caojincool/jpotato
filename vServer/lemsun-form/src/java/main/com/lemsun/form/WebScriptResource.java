package com.lemsun.form;

import com.lemsun.core.BaseCategory;
import com.lemsun.core.LemsunResource;

/**
 * 网页组件函数
 * User: Xudong
 * Date: 12-10-29
 * Time: 上午11:37
 */
public class WebScriptResource extends ScriptResource {

    public WebScriptResource(){
        super(null, BaseCategory.WEB_SCRIPT.getCategory());
        setPid(getPid());
    }

    public WebScriptResource(String name) {
        super(name, BaseCategory.WEB_SCRIPT.getCategory());
    }

    public WebScriptResource(LemsunResource resource){
        super(resource.getName());
        setCategory(resource.getCategory());
        setId(resource.getId());
        setPid(resource.getPid());
        setRemark(resource.getRemark());
        setStrParams(resource.getStrParams());
        setCreateUser(resource.getCreateUser());
        setUpdateTime(resource.getUpdateTime());
        setSystem(resource.isSystem());
        setBusinessCode(resource.getBusinessCode());
    }
}
