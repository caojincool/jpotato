package com.lemsun.data.viproject;

import com.lemsun.core.ILoadChild;
import com.lemsun.data.viproject.util.ReportType;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Xudong
 * Date: 12-12-26
 * Time: 下午1:46
 * 四代的导航模型
 */
public class ViNavigate implements ILoadChild<ViNavigate> {
    private ViNavigate parent;
    private List<ViNavigate> child;
    //A1
    private String code;
    //A4
    private String accountSymbol;
    //A9
    private int category;
    //A10
    private String nameFormat;
    //A13
    private String aliasName;
    //A14
    private String name;
    //A15
    private String formulaRelation;
    //A16
    private String documentsRules;
    //A17
    private String helpMessage;
    //A18
    private String carryoverFormula;
    //A19
    private String combinedFormula;
    //A20
    private String transferFormula;
    //A21
    private String financialAccount;
    //A22
    private int financialCategory;
    //A23
    private int storageCondition;
    //A24
    private String archiveStaff;
    //A25
    private int forwardNotSynchronization;
    //A26
    private String hangingSheet;
    //A27
    private String accountSettings;
    //A28
    private String uploadIssuedState;
    //A29
    private int issued;
    //A30
    private String directorySettings;
    //A31
    private String formulaAbb;
    //A32
    private String standardAccountSettings;
    //A33
    private String modiAccumulation;
    //A34
    private String pfcRelationship;
    //A35
    private String programVersion;
    //A36
    private String formulaVersion;
    //A37
    private String processRelated;
    //A38
    private String version;
    //A39
    private String transmissionFormula;
    //A40
    private String skinSolution;
    //A41
    private String formulaVersionMsg;
    //A42
    private String queryIinterface;
    //B2
    private String booksGuid;
    //B3
    private String reportGuid;



    private Date updateTime;
    private boolean carry;


    public String getNameFormat() {
        return nameFormat;
    }

    public void setNameFormat(String nameFormat) {
        this.nameFormat = nameFormat;
    }

    public String getAccountSymbol() {
        return accountSymbol;
    }

    public void setAccountSymbol(String accountSymbol) {
        this.accountSymbol = accountSymbol;
    }

    public String getBooksGuid() {
        return booksGuid;
    }

    public void setBooksGuid(String booksGuid) {
        this.booksGuid = booksGuid;
    }

    public String getReportGuid() {
        return reportGuid;
    }

    public void setReportGuid(String reportGuid) {
        this.reportGuid = reportGuid;
    }

    public String getSkinSolution() {
        return skinSolution;
    }

    public void setSkinSolution(String skinSolution) {
        this.skinSolution = skinSolution;
    }

    public String getHangingSheet() {
        return hangingSheet;
    }

    public void setHangingSheet(String hangingSheet) {
        this.hangingSheet = hangingSheet;
    }

    /**
     *
     * @return
     */
    public ViNavigate getParent() {
        return parent;
    }

    public void setParent(ViNavigate parent) {
        this.parent = parent;
    }


    @Override
    public List<ViNavigate> getChild() {
        return child;
    }

    @Override
    public void setChild(List<ViNavigate> child) {
        this.child = child;
    }

    @Override
    public void loadChild(Collection collection) {

    }

    /**
     * 获取表格名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     */
    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getCategoryUTF()
    {
        return ReportType.getReportTypeName(getCategory());
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isCarry() {
        return carry;
    }

    public void setCarry(boolean carry) {
        this.carry = carry;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
