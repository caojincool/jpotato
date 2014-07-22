package com.lemsun.client.core.model;

import com.lemsun.client.core.IFunctionStatement;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: 宗旭东
 * Date: 13-3-13
 * Time: 下午1:42
 */
public class ScriptResource extends LemsunResource implements IFunctionStatement{

    private List<FunctionParam> funParams=new ArrayList<>();
    private String nameCH;
    private String scriptType;
    private String initScript;
    private String endScript;
    private String context;

    @Override
    public Date getCreateTime() {
        return null;
    }

    /**
     * 函数别名
     * @return
     */
    @Override
    public String getNameCH() {

        return nameCH;

    }

    public void setNameCH(String nameCH) {
        this.nameCH = nameCH;
    }

    /**
     * 函数参数
     * @return
     */
    @Override
    public List<FunctionParam> getFunParams() {
        return funParams;
    }
    /**
     * 函数参数
     * @return
     */
    public void setFunParams(List<FunctionParam> funParams) {
        this.funParams = funParams;
    }
    /**
     * 函数主体
     * @return
     */
    public String getContext() {
        return context;
    }
    /**
     * 函数主体
     * @return
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * 函数类型
     * @return
     */
    public String getScriptType() {
        return scriptType;
    }

    public void setScriptType(String scriptType) {
        this.scriptType = scriptType;
    }

    /**
     * 初始化脚本
     * @return
     */
    public String getInitScript() {
        return initScript;
    }

    public void setInitScript(String initScript) {
        this.initScript = initScript;
    }
    /**
     * 结束脚本
     * @return
     */
    public String getEndScript() {
        return endScript;
    }

    public void setEndScript(String endScript) {
        this.endScript = endScript;
    }

    public String toScript(){

        StringBuilder sb=new StringBuilder();

        if (StringUtils.isNotEmpty(initScript)){
            sb.append(initScript);
        }
        sb.append("\nfunction ").append(this.getName()).append(" (");
        int i=0;
        for(FunctionParam param:this.getFunParams()){
            i++;
            sb.append(param.getName());
            if(i!=this.getFunParams().size()){
                sb.append(",");
            }
        }
        sb.append(") { \n").append(this.getContext()).append("\n").append("}\n");


        if (StringUtils.isNotEmpty(endScript)){
            sb.append(endScript);
        }

        //拼接别名
        if(StringUtils.isNotEmpty(nameCH)){
            sb.append("\nvar ").append(nameCH).append("=").append(getName()).append(";");
        }

        return sb.toString();
    }
}
