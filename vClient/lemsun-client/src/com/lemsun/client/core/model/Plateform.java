package com.lemsun.client.core.model;

import com.lemsun.client.core.IPlateform;

import java.util.Hashtable;

/**
 * 当前的平台对象
 * User: 宗旭东
 * Date: 13-2-20
 * Time: 下午4:38
 */
public class Plateform implements IPlateform {

    /**
     * 系统类型key
     */
    private String key;
    /**
     * 开始资源
     */
    private String startResource;
    /**
     * 开始脚本
     */
    private String startScript;
    /**
     * 结束脚本
     */
    private String endScript;

    /**
     * 附加集合
     */
    private Hashtable<String, Object> param;

    private String pid;

    @Override
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取附加集合
     * @return 返回附加集合
     */
    @Override
    public Hashtable<String, Object> getParam()
    {
        return param;
    }

    /**
     * 设置附加集合
     * @param param
     */
    public void setParam(Hashtable<String, Object> param) {
        this.param = param;
    }

    /**
     * 获取附加集合去掉{}后的值
     * @return 返回附加集合
     */
    @Override
    public String getParamstring() {

        return param.toString().substring(1,param.toString().length()-1);
    }

    /**
     * 获取系统类型Key
     * @return 返回Key
     */
    @Override
    public String getKey() {
        return key;
    }

    /**
     * 设置系统类型Key
     * @param key 系统key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取开始资源
     * @return 返回开始资源
     */
    @Override
    public String getStartResource() {
        return startResource;
    }

    /**
     * 设置开始资源
     * @param startResource 开始资源
     */
    public void setStartResource(String startResource) {
        this.startResource = startResource;
    }

    /**
     * 获取开始脚本
     * @return 开始脚本
     */
    @Override
    public String getStartScript() {
        return startScript;
    }

    /**
     * 设置开始脚本
     * @param startScript 开始脚本
     */
    public void setStartScript(String startScript) {
        this.startScript = startScript;
    }

    /**
     * 获取结束脚本
     * @return 返回结束脚本
     */
    @Override
    public String getEndScript() {
        return endScript;
    }

    /**
     * 设置结束脚本
     * @param endScript 结束脚本
     */
    public void setEndScript(String endScript) {
        this.endScript = endScript;
    }

    /**
     * 获取附加集合
     * @param key 集合key
     * @return 返回此集合
     */
    @Override
    public Object getAttribute(String key) {
        if(param.containsKey(key)) return param.get(key);
        return null;
    }

    /**
     * 设置附加集合
     * @param key 集合key
     * @param value 集合值
     */
    public void setAttribute(String key, Object value) {
        if(param.containsKey(key)) param.remove(key);

        param.put(key, value);
    }

}
