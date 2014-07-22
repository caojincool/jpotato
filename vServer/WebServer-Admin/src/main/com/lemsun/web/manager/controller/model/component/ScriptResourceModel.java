package com.lemsun.web.manager.controller.model.component;

import com.lemsun.core.FunctionParam;
import com.lemsun.form.ScriptResource;
import com.lemsun.reporter.ReporterScriptResource;

import java.util.ArrayList;
import java.util.List;

/**
 * User: 刘晓宝
 * Date: 13-11-22
 * Time: 下午1:53
 */
public class ScriptResourceModel  extends LemsunResourceModel{
    private List<FunctionParam> funParams=new ArrayList<>();
    private String nameCH;
    private String scriptType;
    private String initScript;
    private String endScript;
    private String context;
    public List<FunctionParam> getFunParams() {
        return funParams;
    }

    public void setFunParams(List<FunctionParam> funParams) {
        this.funParams = funParams;
    }

    public String getEndScript() {
        return endScript;
    }

    public void setEndScript(String endScript) {
        this.endScript = endScript;
    }

    public String getInitScript() {
        return initScript;
    }

    public void setInitScript(String initScript) {
        this.initScript = initScript;
    }

    public String getScriptType() {
        return scriptType;
    }

    public void setScriptType(String scriptType) {
        this.scriptType = scriptType;
    }

    public String getNameCH() {
        return nameCH;
    }

    public void setNameCH(String nameCH) {
        this.nameCH = nameCH;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    /**
     * 封装一个已经存在的脚本组件
     */
    public void encapseResource(ScriptResource resource) {
        resource.setName(getName());
        resource.setNameCH(getNameCH());
        resource.setScriptType(getScriptType());
        resource.setStrParams(getStrParams());
        resource.setRemark(getRemark());
        resource.setInitScript(getInitScript());
        resource.setEndScript(getEndScript());
        resource.setFunParams(getFunParams());
    }

    public void encapseResource(ReporterScriptResource resource){
        resource.setName(getName());
        resource.setNameCH(getNameCH());
        resource.setScriptType(getScriptType());
        resource.setStrParams(getStrParams());
        resource.setRemark(getRemark());
        resource.setInitScript(getInitScript());
        resource.setEndScript(getEndScript());
        resource.setFunParams(getFunParams());
    }
}
