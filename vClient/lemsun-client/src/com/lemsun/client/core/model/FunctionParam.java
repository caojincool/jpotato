package com.lemsun.client.core.model;

/**
 * 函数的参数对象
 * User: xudong
 * Date: 13-11-11
 * Time: 下午2:32
 */
public class FunctionParam {

    private String name;
    private int cate;
    private String defaultValue;
    private String remark;
    /**
     * 参数名
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     * 参数名
     * @return
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 类型
     * @return
     */
    public int getCate() {
        return cate;
    }
    /**
     * 类型
     * @return
     */
    public void setCate(int cate) {
        this.cate = cate;
    }
    /**
     * 默认值
     * @return
     */
    public String getDefaultValue() {
        return defaultValue;
    }
    /**
     * 默认值
     * @return
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * 说明
     * @return
     */
    public String getRemark() {
        return remark;
    }
    /**
     * 说明
     * @return
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
