package com.lemsun.form.service.impl;

import com.lemsun.core.LemsunException;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.service.impl.AbstractResourceSupportService;
import com.lemsun.form.ScriptResource;
import com.lemsun.form.service.IScriptResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-12-20
 * Time: 下午4:09
 */
@Service
public  class ScriptResourceServiceImpl extends AbstractScriptServiceImpl<ScriptResource>  implements IScriptResourceService {


    /**
     * 构造一个对组件的基础操作支持的对象
     *
     * @param resourceService
     */
    @Autowired
    public ScriptResourceServiceImpl(IResourceService resourceService) {
        super(resourceService);
    }
}
