package com.lemsun.sql.builder;

/**
 * 定义一个列的接口
 */
public interface ISQLCol extends ISQLSegment {

    /**
     * 获取别名
     */
    String getAlias();

    /**
     * 返回列最终的名称, 如果有别名返回别名. 如果没有就返回 名称
     */
    String getTargetName();

}
