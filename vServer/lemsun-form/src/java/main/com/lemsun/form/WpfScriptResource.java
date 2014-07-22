package com.lemsun.form;

import com.lemsun.core.BaseCategory;
import com.lemsun.core.LemsunResource;

/**
 * WPF 函数脚本资源
 * User: Xudong
 * Date: 12-11-5
 * Time: 下午1:45
 */
public class WpfScriptResource extends ScriptResource {

    public WpfScriptResource() {
        super(null, BaseCategory.WPF_SCRIPT.getCategory());
    }

	public WpfScriptResource(String name) {
		super(name, BaseCategory.WPF_SCRIPT.getCategory());
	}

    public WpfScriptResource(LemsunResource resource){
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
