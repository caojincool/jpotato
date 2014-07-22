package com.lemsun.form;

import com.lemsun.core.BaseCategory;
import com.lemsun.core.LemsunResource;

/**
 * WPF 函数脚本资源
 * User: Xudong
 * Date: 12-11-5
 * Time: 下午1:45
 */
public class TaskScriptResource extends ScriptResource {

    public TaskScriptResource() {
        super(null,BaseCategory.WPF_SCRIPT.getCategory());
    }

	public TaskScriptResource(String name) {
		super(name,BaseCategory.WPF_SCRIPT.getCategory());
	}

    public TaskScriptResource(LemsunResource resource){
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


    private String context;


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
