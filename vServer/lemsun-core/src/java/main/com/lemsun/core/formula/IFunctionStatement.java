package com.lemsun.core.formula;

import com.lemsun.core.FunctionParam;
import org.bson.types.ObjectId;

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
     * 获取更新日期
     * @return 更新日期
     */
    Date getUpdateTime();
    /**
     * 函数名称
     * @return
     */
    public String getName();

    /**
     * 获取函数的中文名称
     * @return
     */
    public String getNameCH();


    /**
     * 获取函数的参数列表
     * @return
     */
    public List<FunctionParam> getFunParams();


    /**
     * 获取函数体
     * @return
     */
    public String getContext();

    /**
     * 获取说明
     * @return
     */
    public String getRemark();

    /**
     * 初始化脚本
     * @return
     */
    public String getInitScript();

    /**
     * 结束脚本
     * @return
     */
    public String getEndScript();
}
