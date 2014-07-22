package com.lemsun.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lemsun.core.jackson.CustomJsonDateSerializer;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 平台实例
 * 修改说明:原user属性已经set方法没有怎么this关键字,以至set模型以后没有user
 * User: Xudong
 * Date: 12-11-22
 * Time: 下午3:54
 */
@Document(collection = "PlateformInstance")
public class PlateformInstance {


    /**
     * 系统类型
     */
    @DBRef
    private Plateform owner;

    /**
     * 系统实例说明
     */
    private String remark;
    /**
     * 系统实例地址
     */
    private String address;
    /**
     * 系统实例启用日期
     */
    private Date qyDate;
    /**
     * 系统实例结束日期
     */
    private Date jsDate;

    /**
     * 系统实例IP信息
     */
    private String ip;
    /**
     * 系统实例通讯密钥
     */
    private String txKey;

    /**
     * 系统实例使用人
     */
    private String user;
    /**
     * 机器码
     */
    private String jiqicode;

    /**
     * 注册信息  --  暂未使用
     */
    private Register regInfo;

    private String id;

    private String name;

    private String token;

    private String logon;

    private String logout;

    private String error;

    /**
     * 系统实例启动组件编码
     */
    private String startPage;

    /**
     * 获取系统编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置系统编码
     *
     * @param id 编码
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取系统名称
     *
     * @return 系统名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置系统名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取此系统实例的系统类型
     *
     * @return 返回系统类型对象
     */
    public Plateform getOwner() {
        return owner;
    }

    /**
     * 设置此系统实例的系统类型
     *
     * @param owner 系统类型
     */
    public void setOwner(Plateform owner) {
        this.owner = owner;
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
     * 设置系统实例的备注信息
     *
     * @param remark 系统实例的备注信息
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
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    public Date getQyDate() {
        return qyDate;
    }

    /**
     * 设置系统实例启用日期
     *
     * @param qyDate 系统实例启用日期
     */
    public void setQyDate(Date qyDate) {
        this.qyDate = qyDate;
    }

    /**
     * 获取系统实例结束日期
     *
     * @return 返回系统实例结束日期
     */
    public Date getJsDate() {
        return jsDate;
    }

    /**
     * 设置系统实例结束日期
     *
     * @param jsDate 系统实例结束日期
     */
    public void setJsDate(Date jsDate) {
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
     * 获取实例注册的 Token <br/>
     * 实例的 Token 是使用在系统登录平台的值. 每次登录的时候. Token 会改变一次
     *
     * @return 票据信息
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置实例的 Token
     *
     * @param token 票据信息
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取客户端实例开始页面地址
     */
    public String getStartPage() {
        return startPage;
    }

    /**
     * 设置客户端实例开始页面地址
     */
    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    /**
     * 获取默认登录组件编码
     *
     * @return
     */
    public String getLogon() {
        return logon;
    }

    /**
     * 设置默认登录组件编码
     *
     * @param logon 登录组件编码
     */
    public void setLogon(String logon) {
        this.logon = logon;
    }

    /**
     * 设置注销组件
     *
     * @return
     */
    public String getLogout() {
        return logout;
    }

    /**
     * 设置默认注销组件
     *
     * @param logout
     */
    public void setLogout(String logout) {
        this.logout = logout;
    }

    /**
     * 获取错误组件编码
     *
     * @return 错误处理组件编码
     */
    public String getError() {
        return error;
    }

    /**
     * 设置错误页面组件编码
     *
     * @param error 错误处理组件编码
     */
    public void setError(String error) {
        this.error = error;
    }
}
