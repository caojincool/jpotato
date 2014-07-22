/**
 * 5代网页端模型包
 */
package com.lemsun.client.core.model;

import java.util.Map;

/**
 * web页面调用wpf组件的协议格式
 * User: dpyang
 * Date: 13-5-7
 * Time: 下午6:52
 */
public class WpfMassageCommand {
    public static final String header="ilemsun://command/";
    private String sender;
    private String command;
    private String token;
    private String target;
    private String pid;
    private String action;
    private Map<String,String> parms;

    /**
     * 获取发送者
     * @return 发送者
     */
    public String getSender() {
        return sender;
    }

    /**
     * 设置发送者
     * @param sender 发送者
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * 获取执行命令
     * @return 执行命令
     */
    public String getCommand() {
        return command;
    }

    /**
     * 设置执行命令
     * @param command 执行命令
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * 获取标识令牌
     * @return 标识令牌
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置标识令牌
     * @param token 标识令牌
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取目标ID
     * @return 目标ID
     */
    public String getTarget() {
        return target;
    }

    /**
     * 设置目标ID
     * @param target 目标ID
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * 获取组件编码
     * @return 组件编码
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置组件编码
     * @param pid 组件编码
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取操作名称
     * @return 操作名称
     */
    public String getAction() {
        return action;
    }

    /**
     * 设置操作名称
     * @param action 操作名称
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 获取参数
     * @return 参数
     */
    public Map<String, String> getParms() {
        return parms;
    }

    /**
     * 设置参数
     * @param parms 参数
     */
    public void setParms(Map<String, String> parms) {
        this.parms = parms;
    }
}
