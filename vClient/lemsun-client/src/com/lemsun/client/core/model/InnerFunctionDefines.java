package com.lemsun.client.core.model;

import com.lemsun.client.core.IFunctionStatement;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 一个在组件内部定义的函数体
 * User: xudong
 * Date: 13-11-11
 * Time: 下午2:35
 */
public class InnerFunctionDefines implements IFunctionStatement {

//    private ObjectId _id;
    private String parentPid;
    private String name;
    private String nameCH;
    private String context;
    private String remark;
    private Date createTime;
    private Date updateTime;
    private  List<FunctionParam> funParams=new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getNameCH() {
        return  nameCH;
    }

    @Override
    public List<FunctionParam> getFunParams() {
        return funParams;
    }


    @Override
    public String getContext() {
        return context;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameCH(String nameCH) {
        this.nameCH = nameCH;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setFunParams(List<FunctionParam> funParams) {
        this.funParams = funParams;
    }
    /**
     * 获取数据库的组件编码
     *
     * @return
     */
//    @JsonSerialize(using = ObjectIdSerializer.class)
//    public ObjectId getId() {
//        return _id;
//    }
//
//    @JsonDeserialize(using = ObjectIdDeserializer.class)
//    protected void setId(ObjectId id) {
//        _id = id;
//    }
    /**
     * 创建日期
     * @return
     */
    @Override
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * 修改日期
     * @return
     */
    @Override
    public Date getUpdateTime() {
        return updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    @Override
    public String getParentPid() {
        return parentPid;
    }


    public void setParentPid(String parentPid) {
        this.parentPid = parentPid;
    }


    public String convertToScript() {
        return toString();
    }


    public String toScript() {
        StringBuilder sb=new StringBuilder();
        sb.append("/**"+"\n");
        for(FunctionParam param:this.getFunParams()){
            sb.append("* @param "+param.getName()+" 参数说明： "+param.getRemark()+"\n");
        }
        sb.append("* 别名："+this.getNameCH()+"\n");
        sb.append("* 说明："+this.getRemark()+"\n");
        sb.append("**/\n");
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
        sb.append(this.getContext()+"\n");
        sb.append("}\n");

        if(StringUtils.isNotEmpty(nameCH)){
            sb.append("var "+nameCH+" = "+name+";\n");
        }

        return sb.toString();
    }

    @Override
    public String getInitScript() {
        return null;
    }

    @Override
    public String getEndScript() {
        return null;
    }
}
