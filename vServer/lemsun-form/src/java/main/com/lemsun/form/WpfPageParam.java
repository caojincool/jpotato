package com.lemsun.form;

/**
 * WFP 组件渲染前的初始化化参数对象
 * User: dp
 * Date: 13-5-11
 * Time: 上午9:39
 */
public class WpfPageParam {
    /**
     * 数据源
     */
    public final static int DBSOUR = 1;

    /**
     * 字符类型
     */
    public final static int CHAR = 2;

    /**
     * 公式类型
     */
    public final static int REF = 3;

    private String name;
    private int cate;
    private String value;
    private int status;
    private String remark;

    /**
     * 获取参数名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置参数名称, 参数名称不能重复
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取参数类型
     */
    public int getCate() {
        return cate;
    }

    /**
     * 设置参数类型
     */
    public void setCate(int cate) {
        this.cate = cate;
    }

    /**
     * 获取参数表达式
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置参数表达式
     */
    public void setValue(String value) {
        this.value = value;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 说明
     *
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 说明
     *
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WebPageParam)) return false;

        WpfPageParam that = (WpfPageParam) o;

        if (cate != that.cate) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + cate;
        return result;
    }
}
