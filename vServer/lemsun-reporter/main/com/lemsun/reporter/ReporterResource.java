package com.lemsun.reporter;

import com.lemsun.core.AbstractLemsunResource;
import com.lemsun.core.BaseCategory;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.repository.ResourceRepository;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * 填报组件对象
 * Created by 宗 on 2014/5/19 0019.
 */
@Document(collection = ResourceRepository.ResourceCollectionName)
public class ReporterResource extends AbstractLemsunResource {


    public final static int EXCEL_TYPE = 1;
    public final static int EXCEL_2010_TYPE = 2;
    public final static int WPS_CELL = 3;

    public static String GetTypeName(int filetype) {
        if(filetype == EXCEL_TYPE) return "xls";
        if(filetype == EXCEL_2010_TYPE) return "xlsx";
        return "xls";
    }


    /**
     * 类型常量
     */
    public final static String TYPE = "reporter";

    private String beforeScript;
    //修改函数
    private String updateScript;
    //勾稽关系
    private String articulationScript;
    //审核函数
    private String checkScript;
    //提交函数
    private String commitScript;
    //上报函数
    private String reportedScript;
    //关闭函数
    private String endScript;

    private String formula;

    private Set<ReporterParam> startParams;

    //填报文件前缀
    private String prefix;
    //填报文件后缀
    private String suffix;
    //文件类型
    private int fileType;

    public ReporterResource() {
        super(null, BaseCategory.REPORTER.getCategory());
    }

    public ReporterResource(String name){
        super(name,BaseCategory.REPORTER.getCategory());
    }

    public ReporterResource(LemsunResource resource){
        super(resource.getName(),resource.getCategory());
        setId(resource.getId());
        setPid(resource.getPid());
        setRemark(resource.getRemark());
        setStrParams(resource.getStrParams());
        setCreateUser(resource.getCreateUser());
        setUpdateTime(resource.getUpdateTime());
        setSystem(resource.isSystem());
        setBusinessCode(resource.getBusinessCode());
    }

    /**
     * 填报类型
     */
    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getBeforeScript() {
        return beforeScript;
    }

    public void setBeforeScript(String beforeScript) {
        this.beforeScript = beforeScript;
    }

    public String getEndScript() {
        return endScript;
    }

    public void setEndScript(String endScript) {
        this.endScript = endScript;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Set<ReporterParam> getStartParams() {
        return startParams;
    }

    public void setStartParams(Set<ReporterParam> startParams) {
        this.startParams = startParams;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getReportedScript() {
        return reportedScript;
    }

    public void setReportedScript(String reportedScript) {
        this.reportedScript = reportedScript;
    }

    public String getUpdateScript() {
        return updateScript;
    }

    public void setUpdateScript(String updateScript) {
        this.updateScript = updateScript;
    }

    public String getArticulationScript() {
        return articulationScript;
    }

    public void setArticulationScript(String articulationScript) {
        this.articulationScript = articulationScript;
    }

    public String getCheckScript() {
        return checkScript;
    }

    public void setCheckScript(String checkScript) {
        this.checkScript = checkScript;
    }

    public String getCommitScript() {
        return commitScript;
    }

    public void setCommitScript(String commitScript) {
        this.commitScript = commitScript;
    }
}
