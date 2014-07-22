package com.lemsun.ldbc;

/**
 * Created with IntelliJ IDEA.
 * User: Xudong
 * Date: 12-12-25
 * Time: 上午9:34
 * 描述数据行的状态
 */
public final class RowStatus {

    /**
     * 标记数据列名
     */
    public final static String ColumnName = "_";

    /**
     * 分离的
     */
    public final  static int Detached = 1;
    /**
     * 没有更新
     */
    public final static int Unchanged = 2;
    /**
     * 新行
     */
    public final static int Added = 4;
    /**
     * 删除行
     */
    public final static int Deleted = 8;
    /**
     * 更新行
     */
    public final static int Modified = 16;
}
