package com.lemsun.ldbc;

/**
 * User: 刘晓宝
 * Date: 14-3-15
 * Time: 上午9:25
 */
public class ColumnInfo {
    /**
     * 普通列没有任何特殊情况
     */
    public static final int COMMON_COLUMN = 0;

    /**
     * 后台数据处理, 用户没有明确获取的列, 而由后台统一添加的一些必须传输的列值
     */
    public static final int BACK_COLUMN = 1;
    /**
     * 只读
     */
    public static final int READ_ONLY = 2;

    /**
     * 计算合并
     */
    public static final int MERGE_DATA = 4;
    /**
     * 不由真实的
     */
    public static final int VIRTUAL_DATA = 8;
    /**
     * 所属表pid
     */
    private String ref;
    /**
     * 列
     */
    private String name;

    public ColumnInfo(String ref, String name, int colType) {
        this.ref = ref;
        this.name = name;
        this.colType = colType;
    }

    /**
     * 类型
     */
    private int colType;
    /**
     * 列说明
     */
    private int cateType;

    /**
     * 列说明
     */
    public int getCateType() {
        return cateType;
    }

    /**
     * 列说明
     */
    public void setCateType(int cateType) {
        this.cateType = cateType;
    }

    /**
     * 所有表pid
     */
    public String getRef() {
        return ref;
    }

    /**
     * 设置表Pid
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     * 列名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取列类型
     */
    public int getColType() {
        return colType;
    }

    /**
     * 设置列类型
     */
    public void setColType(int colType) {
        this.colType = colType;
    }
}
