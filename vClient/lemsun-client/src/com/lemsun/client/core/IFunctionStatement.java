package com.lemsun.client.core;

import com.lemsun.client.core.model.FunctionParam;

import java.util.Date;
import java.util.List;

/**
 * 描述一个函数的执行内容
 * User: xudong
 * Date: 13-11-11
 * Time: 下午2:30
 */
public interface IFunctionStatement {
    /**
     * 获取数据库主键
     */
//    ObjectId getId();

    /**
     * 父Pid
     */
    String getParentPid();

    /**
     * 获取创建日期
     * @return 更新日期
     */
    Date getCreateTime();

    /**
     * 获取更新日期
     * @return 更新日期
     */
    Date getUpdateTime();

    /**
     * 函数名称
     */
    public String getName();

    /**
     * 获取函数的中文名称
     */
    public String getNameCH();

    /**
     * 获取函数的参数列表
     */
    public List<FunctionParam> getFunParams();

    /**
     * 获取函数体
     */
    public String getContext();

    /**
     * 获取说明
     */
    public String getRemark();

    /**
     * 初始化脚本
     */
    public String getInitScript();

    /**
     * 结束脚本
     */
    public String getEndScript();
}
