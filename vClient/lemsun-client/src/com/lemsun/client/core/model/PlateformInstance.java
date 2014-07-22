package com.lemsun.client.core.model;

import com.lemsun.client.core.IPlateform;
import com.lemsun.client.core.IPlateformInstance;

import java.util.Date;

/**
 * 表示当前平台的实例
 * User: 宗旭东
 * Date: 13-2-20
 * Time: 下午4:52
 */
public class PlateformInstance implements IPlateformInstance {



    private IPlateform owner;
    private String remark;
    private String address;
    private Date qyDate;
    private Date jsDate;
    private String ip;
    private String txKey;
    private String user;
    private String jiqicode;
    private Register regInfo;
    private String id;
    private String name;
    private String token;
    private String startPage;
    private String logon;
    private String logout;
    private String error;

    @Override
    public String getId() {
        return id;
    }

    /**
     * 设置系统编码
     * @param id 编码
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取系统名称
     * @return 系统名称
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 设置系统名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取此系统实例的系统类型
     * @return 返回系统类型对象
     */
    @Override
    public IPlateform getOwner() {
        return owner;
    }

    /**
     * 设置此系统实例的系统类型
     * @param owner 系统类型
     */
    public void setOwner(IPlateform owner) {
        this.owner = owner;
    }

    /**
     * 获取系统实例备注信息
     * @return 返回系统实例备注信息
     */
    @Override
    public String getRemark() {
        return remark;
    }

    /**
     * 设置系统实例的备注信息
     * @param remark 系统实例的备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取系统实例地址信息
     * @return 返回系统实例地址信息
     */
    @Override
    public String getAddress() {
        return address;
    }

    /**
     * 设置系统实例地址信息
     * @param address 系统实例地址信息
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取系统实例启用日期
     * @return 返回系统实例启用日期
     */
    @Override
    public Date getQyDate() {
        return qyDate;
    }

    /**
     * 设置系统实例启用日期
     * @param qyDate 系统实例启用日期
     */
    public void setQyDate(Date qyDate) {
        this.qyDate = qyDate;
    }

    /**
     * 获取系统实例结束日期
     * @return 返回系统实例结束日期
     */
    @Override
    public Date getJsDate() {
        return jsDate;
    }

    /**
     * 设置系统实例结束日期
     * @param jsDate 系统实例结束日期
     */
    public void setJsDate(Date jsDate) {
        this.jsDate = jsDate;
    }

    /**
     * 获取系统实例IP信息
     * @return 返回系统实例IP信息
     */
    @Override
    public String getIp() {
        return ip;
    }

    /**
     * 设置系统实例IP信息
     * @param ip 系统实例IP信息
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取系统实例通讯密钥信息
     * @return 返回系统实例通讯密钥信息
     */
    @Override
    public String getTxKey() {
        return txKey;
    }

    /**
     * 设置系统实例通讯密钥信息
     * @param txKey 系统实例通讯密钥信息
     */
    public void setTxKey(String txKey) {
        this.txKey = txKey;
    }

    /**
     * 获取系统实例使用人信息
     * @return 返回系统实例使用人信息
     */
    @Override
    public String getUser() {
        return user;
    }

    /**
     * 设置系统实例使用人信息
     * @param user 系统实例使用人信息
     */
    public void setUser(String user) {
        user = user;
    }

    /**
     * 获取系统实例机器码信息
     * @return 返回系统实例机器码信息
     */
    @Override
    public String getJiqicode() {
        return jiqicode;
    }

    /**
     * 设置系统实例机器码信息
     * @param jiqicode 系统实例机器码信息
     */
    public void setJiqicode(String jiqicode) {
        this.jiqicode = jiqicode;
    }

    /**
     * 获取系统实例注册相关信息
     * @return 返回系统实例注册相关信息
     */
    @Override
    public Register getRegInfo() {
        return regInfo;
    }

    /**
     * 设置系统实例注册相关信息
     * @param regInfo 系统实例注册相关信息
     */
    public void setRegInfo(Register regInfo) {
        this.regInfo = regInfo;
    }

    /**
     * 获取实例注册的 Token <br/>
     * 实例的 Token 是使用在系统登录平台的值. 每次登录的时候. Token 会改变一次
     * @return 票据信息
     */
    @Override
    public String getToken() {
        return token;
    }

    /**
     * 设置实例的 Token
     * @param token 票据信息
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取注册实例的开始组件
     */
    public String getStartPage() {
        return startPage;
    }

    /**
     * 设置注册实例的开始组件
     */
    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    /**
     * 默认登录组件
     */
    public String getLogon() {
        return logon;
    }

    /**
     * 注销组件
     * @param logout
     */
    public void setLogout(String logout) {
        this.logout = logout;
    }

    /**
     * 获取注销组件编码
     */
    @Override
    public String getLogout() {
        return logout;
    }

    /**
     * 默认登录组件
     */
    public void setLogon(String logon) {
        this.logon = logon;
    }

    /**
     * 默认错误组件
     */
    public String getError() {
        return error;
    }

    /**
     * 默认错误组件
     */
    public void setError(String error) {
        this.error = error;
    }
}
