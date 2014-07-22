package com.lemsun.form;

import com.lemsun.core.*;
import com.lemsun.core.formula.IFunctionStatement;
import com.lemsun.core.service.IResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-29
 * Time: 上午11:53
 */
public class ScriptResource extends AbstractLemsunResource implements IFunctionStatement {

    private  List<FunctionParam> funParams=new ArrayList<>();
    private String nameCH;
    private String scriptType;
    private String initScript;
    private String endScript;

    @Transient
    private String context;

    public ScriptResource() {
        super(null, BaseCategory.SCRIPT.getCategory());
    }

    public ScriptResource(String name) {
		super(name, BaseCategory.SCRIPT.getCategory());
	}

    public ScriptResource(String name, String category)
    {
        super(name, category);
    }

    public ScriptResource(LemsunResource resource) {
        super(resource.getName(), resource.getCategory());
        setId(resource.getId());
        setPid(resource.getPid());
        setRemark(resource.getRemark());
        setStrParams(resource.getStrParams());
        setCreateUser(resource.getCreateUser());
        setUpdateTime(resource.getUpdateTime());
        setSystem(resource.isSystem());
        setState(resource.getState());
        setBusinessCode(resource.getBusinessCode());
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


    @Override
    public String getContext() {
        if(getPid()!=null)
            return Host.getInstance().getContext().getBean(IResourceService.class).getContent(getPid());
        return null;
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

    public String convertToScript() {
        return toString();
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();


         sb.append("/**"+"\n");
         for(FunctionParam param:this.getFunParams()){
         sb.append("* @param "+param.getName()+" 参数说明： "+param.getRemark()+"\n");
         }
         sb.append("* 说明："+this.getRemark()+"\n");
         sb.append("*/\n");
        if(StringUtils.isNotEmpty(this.getInitScript())){
            sb.append(this.getInitScript()+"\n");
        }
        sb.append("function "+this.getName()+" (");

        int i=0;
        for(FunctionParam param:this.getFunParams()){
            i++;
            sb.append(param.getName());
            if(i!=this.getFunParams().size()){
                sb.append(",");
            }
        }
        sb.append(") { \n");
        for(FunctionParam param:this.getFunParams()){
            if(StringUtils.isNotEmpty(param.getDefaultValue())){
                switch (param.getCate()){
                    case FunctionParam.STRING:
                        sb.append(param.getName()+"="+param.getName()+"!=''?'"+param.getName()+"':'"+param.getDefaultValue()+"';\n");
                        break;
                    default:  sb.append(param.getName()+"="+param.getName()+"!=''?"+param.getName()+":"+param.getDefaultValue()+";\n");
                }
            }

        }
        sb.append(this.getContext());
        sb.append("\n");
        sb.append("}\n");
        if(!StringUtils.isEmpty(this.getNameCH())){
            sb.append("var "+this.getNameCH()+"= "+this.getName());
            sb.append(";");
            sb.append("\n");
        }
        if(StringUtils.isNotEmpty(this.getEndScript())){
            sb.append(this.getEndScript()+"\n");
        }
        return sb.toString();

    }
}
