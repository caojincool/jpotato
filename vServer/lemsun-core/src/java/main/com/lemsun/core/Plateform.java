package com.lemsun.core;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Hashtable;

/**
 * 系统平台. 一个平台对应的模型. 记录对平台的配置信息
 * User: Xudong
 * Date: 12-10-24
 * Time: 下午3:27
 * 修改说明;因为平台只用了组件的name和category属性所以没有必要继承组件
 * 单独把两个属性提取出来即可
 * 修改人:dpyang
 */
@Document(collection = "Plateform")
public class Plateform {

    public static final Plateform WPF = new Plateform("WPF", "WPF");

    public static final Plateform WEB = new Plateform("WEB", "WEB");

    public static final Plateform PLATEFORM = new Plateform("PLATEFORM", "PLATEFORM");

    private Plateform(String name, String category) {
        this.name = name;
        this.category = category;
    }

    /**
     * 平台组件ID
     */
    @Id
    private ObjectId id;


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
    private Hashtable<String, Object> param = new Hashtable<>();

    /**
     * 平台名称
     */
    private String name;

    /**
     * 平台类型
     */
    private String category;

    private Date updateTime;

    private Date createTime;

    private String createUser;


    /**
     * 获取附加集合
     *
     * @return 返回附加集合
     */
    public Hashtable<String, Object> getParam() {
        return param;
    }

    /**
     * 获取附加集合去掉{}后的值
     *
     * @return 返回附加集合
     */
    public String getParamstring() {

        return param.toString().substring(1, param.toString().length() - 1);
    }

    /**
     * 获取系统类型Key
     *
     * @return 返回Key
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置系统类型Key
     *
     * @param key 系统key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取开始资源
     *
     * @return 返回开始资源
     */
    public String getStartResource() {
        return startResource;
    }

    /**
     * 设置开始资源
     *
     * @param startResource 开始资源
     */
    public void setStartResource(String startResource) {
        this.startResource = startResource;
    }

    /**
     * 获取开始脚本
     *
     * @return 开始脚本
     */
    public String getStartScript() {
        return startScript;
    }

    /**
     * 设置开始脚本
     *
     * @param startScript 开始脚本
     */
    public void setStartScript(String startScript) {
        this.startScript = startScript;
    }

    /**
     * 获取结束脚本
     *
     * @return 返回结束脚本
     */
    public String getEndScript() {
        return endScript;
    }

    /**
     * 设置结束脚本
     *
     * @param endScript 结束脚本
     */
    public void setEndScript(String endScript) {
        this.endScript = endScript;
    }

    /**
     * 获取附加集合
     *
     * @param key 集合key
     * @return 返回此集合
     */
    public Object getAttribute(String key) {
        if (param.containsKey(key)) return param.get(key);
        return null;
    }

    /**
     * 设置附加集合
     *
     * @param key   集合key
     * @param value 集合值
     */
    public void setAttribute(String key, Object value) {
        if (param.containsKey(key)) param.remove(key);

        param.put(key, value);
    }

    /**
     * 获取平台名称
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 获取平台类别
     *
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     * 获取平台数据库组件ID
     *
     * @return
     */
    public ObjectId getId() {
        return id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

}
