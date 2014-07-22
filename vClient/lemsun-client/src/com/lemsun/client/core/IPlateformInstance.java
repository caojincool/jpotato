package com.lemsun.client.core;

import com.lemsun.client.core.model.Register;

import java.util.Date;

/**
 * User: 宗旭东
 * Date: 13-3-9
 * Time: 上午9:35
 */
public interface IPlateformInstance {
    /**
     * 获取系统编号
     */
    String getId();

    /**
     * 获取注册实例的开始组件
     */
    String getStartPage();

    /**
     * 获取系统名称
     * @return 系统名称
     */
    String getName();

    /**
     * 获取此系统实例的系统类型
     * @return 返回系统类型对象
     */
    IPlateform getOwner();

    /**
     * 获取系统实例备注信息
     * @return 返回系统实例备注信息
     */
    String getRemark();


    /**
     * 获取系统实例地址信息
     * @return 返回系统实例地址信息
     */
    String getAddress();

    /**
     * 获取系统实例启用日期
     * @return 返回系统实例启用日期
     */
    Date getQyDate();

    /**
     * 获取系统实例结束日期
     * @return 返回系统实例结束日期
     */
    Date getJsDate();

    /**
     * 获取系统实例IP信息
     * @return 返回系统实例IP信息
     */
    String getIp();

    /**
     * 获取系统实例通讯密钥信息
     * @return 返回系统实例通讯密钥信息
     */
    String getTxKey();

    /**
     * 获取系统实例使用人信息
     * @return 返回系统实例使用人信息
     */
    String getUser();

    /**
     * 获取系统实例机器码信息
     * @return 返回系统实例机器码信息
     */
    String getJiqicode();

    /**
     * 获取系统实例注册相关信息
     * @return 返回系统实例注册相关信息
     */
    Register getRegInfo();

    /**
     * 获取实例注册的 Token <br/>
     * 实例的 Token 是使用在系统登录平台的值. 每次登录的时候. Token 会改变一次
     * @return 票据信息
     */
    String getToken();

    /**
     * 登录组件编码
     */
    String getLogon();

    /**
     * 获取注销组件编码
     */
    String getLogout();


    /**
     * 异常处理组件编码
     */
    String getError();
}
