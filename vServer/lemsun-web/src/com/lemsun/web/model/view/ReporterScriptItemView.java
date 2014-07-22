package com.lemsun.web.model.view;

import com.lemsun.core.IAccount;
import com.lemsun.core.IResource;
import com.lemsun.reporter.ReporterScriptResource;

/**
 * 填报脚本组件显示项
 * Created by dpyang on 2014/5/21.
 */
public class ReporterScriptItemView extends ResourceBase {
    private String rScriptName;
    private long rScriptSize;
    private ReporterScriptResource resource=(ReporterScriptResource)super.getResource();

    public ReporterScriptItemView(IResource resource, IAccount account) {
        super(resource, account);
    }

    public String getrScriptName() {
        return rScriptName;
    }

    public void setrScriptName(String rScriptName) {
        this.rScriptName = rScriptName;
    }
}
