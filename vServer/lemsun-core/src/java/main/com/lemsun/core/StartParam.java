package com.lemsun.core;

/**
 * 开始参数模型, 定义在组件中的开始参数
 */
public class StartParam {


    /**
     * 占位类型
     */
    public final static int AERY = 1;

    /**
     * 表达式
     */
    public final static int REF = 2;

    /**
     * 字符类型
     */
    public final static int CHARS = 3;

    /**
     * 公式
     */
    public final static int FORMULA = 4;

    private String name;
    private int cate;
    private String value;
    //    private int status;
    private boolean parent;
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


//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }

    /**
     * 父组件的开始参数
     *
     * @return
     */
    public boolean isParent() {
        return parent;
    }

    /**
     * 父组件的开始参数
     *
     * @param parent
     */
    public void setParent(boolean parent) {
        this.parent = parent;
    }



    /**
     * 开始参数的说明
     *
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 开始参数的说明
     *
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StartParam)) return false;

        StartParam that = (StartParam) o;

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
