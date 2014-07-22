package com.lemsun.web.manager.controller.model.system;


import com.lemsun.core.PlateformInstance;
import com.lemsun.core.Register;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 新增系统实例模型
 * User: gm
 * Date: 12-12-4
 * Time: 下午1:48
 */
public class InstanceModel {

    private String id;
    private String name;
    private String pid;
    private String categorytype;
    private String remark;
    private String address;
    private String qyDate;
    private String jsDate;
    private String ip;
    private String txKey;
    private String user;
    private String jiqicode;
    private String regRemark;
    private Register regInfo;
    private String startPage;
    private String logon;
    private String error;
    private String logout;

    /**
     * 获取系统实例ID
     *
     * @return 系统实例ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置系统实例ID
     *
     * @param id 系统实例ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取系统实例名称
     *
     * @return 返回系统实例名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置系统实例名称
     *
     * @param name 系统实例名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取系统实例Pid
     *
     * @return 返回系统实例Pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置系统实例pid
     *
     * @param pid 系统实例pid
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取系统实例系统类型
     *
     * @return 系统实例系统类型
     */
    public String getCategorytype() {
        return categorytype;
    }

    /**
     * 设置系统实例系统类型
     *
     * @param categorytype 系统实例系统类型
     */
    public void setCategorytype(String categorytype) {
        this.categorytype = categorytype;
    }

    /**
     * 获取系统实例备注信息
     *
     * @return 返回系统实例备注信息
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置系统实例备注信息
     *
     * @param remark 系统实例备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取系统实例地址信息
     *
     * @return 返回系统实例地址信息
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置系统实例地址信息
     *
     * @param address 系统实例地址信息
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取系统实例启用日期
     *
     * @return 返回系统实例启用日期
     */
    public String getQyDate() {
        return qyDate;
    }

    /**
     * 设置系统实例启用日期
     *
     * @param qyDate 系统实例启用日期
     */
    public void setQyDate(String qyDate) {
        this.qyDate = qyDate;
    }

    /**
     * 获取系统实例结束日期
     *
     * @return 返回系统实例结束日期
     */
    public String getJsDate() {
        return jsDate;
    }

    /**
     * 设置系统实例结束日期
     *
     * @param jsDate 系统实例结束日期
     */
    public void setJsDate(String jsDate) {
        this.jsDate = jsDate;
    }

    /**
     * 获取系统实例IP信息
     *
     * @return 返回系统实例IP信息
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置系统实例IP信息
     *
     * @param ip 系统实例IP信息
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取系统实例通讯密钥信息
     *
     * @return 返回系统实例通讯密钥信息
     */
    public String getTxKey() {
        return txKey;
    }

    /**
     * 设置系统实例通讯密钥信息
     *
     * @param txKey 系统实例通讯密钥信息
     */
    public void setTxKey(String txKey) {
        this.txKey = txKey;
    }

    /**
     * 获取系统实例使用人信息
     *
     * @return 返回系统实例使用人信息
     */
    public String getUser() {
        return user;
    }

    /**
     * 设置系统实例使用人信息
     *
     * @param user 系统实例使用人信息
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * 获取系统实例机器码信息
     *
     * @return 返回系统实例机器码信息
     */
    public String getJiqicode() {
        return jiqicode;
    }

    /**
     * 设置系统实例机器码信息
     *
     * @param jiqicode 系统实例机器码信息
     */
    public void setJiqicode(String jiqicode) {
        this.jiqicode = jiqicode;
    }

    /**
     * 获取系统实例注册相关信息
     *
     * @return 返回系统实例注册相关信息
     */
    public String getRegRemark() {
        return regRemark;
    }

    /**
     * 设置系统实例注册相关信息
     *
     * @param regRemark 系统实例注册相关信息
     */
    public void setRegRemark(String regRemark) {
        this.regRemark = regRemark;
    }

    /**
     * 获取系统实例注册相关信息
     *
     * @return 返回系统实例注册相关信息
     */
    public Register getRegInfo() {
        return regInfo;
    }

    /**
     * 设置系统实例注册相关信息
     *
     * @param regInfo 系统实例注册相关信息
     */
    public void setRegInfo(Register regInfo) {
        this.regInfo = regInfo;
    }

    /**
     * 获取客户端实例起始页面地址(应该是一个组件ID)
     *
     * @return
     */
    public String getStartPage() {
        return startPage;
    }

    /**
     * 设置客户端实例起始页面地址(应该是一个组件ID)
     *
     * @param startPage 组件ID
     */
    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    /**
     * 默认登录组件编码
     */
    public String getLogon() {
        return logon;
    }

    /**
     * 默认登录组件编码
     */
    public void setLogon(String logon) {
        this.logon = logon;
    }

    /**
     * 注销
     *
     * @return
     */
    public String getLogout() {
        return logout;
    }

    /**
     * 注销字符串
     *
     * @param logout
     */
    public void setLogout(String logout) {
        this.logout = logout;
    }

    /**
     * 默认错误组件编码
     */
    public String getError() {
        return error;
    }

    /**
     * 默认错误组件编码
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * 封装
     */
    public PlateformInstance encapseModel() throws ParseException {
        PlateformInstance plateformInstance = new PlateformInstance();
        return encapseModel(plateformInstance);

    }

    public PlateformInstance encapseModel(PlateformInstance plateformInstance) throws ParseException {

        plateformInstance.setName(getName());
        plateformInstance.setRemark(getRemark());
        plateformInstance.setStartPage(getStartPage());
        plateformInstance.setAddress(getAddress());
        plateformInstance.setQyDate(new SimpleDateFormat("yyyy-MM-dd").parse(getQyDate()));
        plateformInstance.setJsDate(new SimpleDateFormat("yyyy-MM-dd").parse(getJsDate()));
        plateformInstance.setIp(getIp());
        plateformInstance.setTxKey(getTxKey());
        plateformInstance.setUser(getUser());
        plateformInstance.setJiqicode(getJiqicode());
        plateformInstance.setRemark(getRemark());
        plateformInstance.setLogon(getLogon());
        plateformInstance.setLogout(getLogout());
        plateformInstance.setError(getError());

        return plateformInstance;
    }
}

