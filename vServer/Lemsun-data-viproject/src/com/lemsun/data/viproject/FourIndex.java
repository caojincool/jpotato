package com.lemsun.data.viproject;

import com.lemsun.core.ILoadChild;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 4代目录模型类
 * User: Lucklim
 * Date: 13-1-19
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
public class FourIndex implements ILoadChild<FourIndex> {

    private FourIndex parent;
    private List<FourIndex> child;

    //A1
    private String code;
    //A4
    private String accountSymbol;
    //A9
    private int category;
    //A10
    private String nameFormat;
    //A13
    // private String aliasName;
    //A14
    private String name;

    //A26
    private String hangingSheet;

    //A40
    private String skinSolution;

    //B2
    private String booksGuid;
    //B3
    private String reportGuid;

    private Date updateTime;


    /**
     * 得到父级目录
     */
    public FourIndex getParent() {
        return parent;
    }

    /**
     * 设置父级目录
     * @param parent
     */
    public void setParent(FourIndex parent) {
        this.parent = parent;
    }
    /**
     * 设置更新时间
     * @return
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 得到更新时间
     * @return
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * A1 得到编码
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * A1 设置编码
     * @return
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * A4 得到界面配置
     * @return
     */
    public String getAccountSymbol() {
        return accountSymbol;
    }

    /**
     * A4 设置界面配置
     * @return
     */
    public void setAccountSymbol(String accountSymbol) {
        this.accountSymbol = accountSymbol;
    }

    /**
     * A9 设置报表类型
     * @return
     */
    public int getCategory() {
        return category;
    }

    /**
     * A9 得到报表类型
     * @return
     */
    public void setCategory(int category) {
        this.category = category;
    }

    /**
     * A10 设置本表命名规则
     * @return
     */
    public String getNameFormat() {
        return nameFormat;
    }

    /**
     * A10 得到本表命名规则
     * @return
     */
    public void setNameFormat(String nameFormat) {
        this.nameFormat = nameFormat;
    }

    /**
     * A14 得到报表名
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * A14 设置报表名
     * @return
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A26 得到对应挂接的表
     * @return
     */
    public String getHangingSheet() {
        return hangingSheet;
    }

    /**
     * A26 设置对应挂接的表
     * @return
     */
    public void setHangingSheet(String hangingSheet) {
        this.hangingSheet = hangingSheet;
    }

    /**
     *  A40 得到皮肤方案
     * @return
     */
    public String getSkinSolution() {
        return skinSolution;
    }

    /**
     * A40 设置皮肤方案
     * @return
     */
    public void setSkinSolution(String skinSolution) {
        this.skinSolution = skinSolution;
    }

    /**
     * B2 得到帐套Guid
     * @return
     */
    public String getBooksGuid() {
        return booksGuid;
    }

    /**
     * B2 设置帐套Guid
     * @return
     */
    public void setBooksGuid(String booksGuid) {
        this.booksGuid = booksGuid;
    }

    /**
     * B3 得到报表Guid
     * @return
     */
    public String getReportGuid() {
        return reportGuid;
    }

    /**
     * B3 设置报表Guid
     * @return
     */
    public void setReportGuid(String reportGuid) {
        this.reportGuid = reportGuid;
    }

    @Override
    public List<FourIndex> getChild() {
        return child;
    }

    @Override
    public void setChild(List<FourIndex> child) {
        this.child = child;
    }

    @Override
    public void loadChild(Collection collection) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
