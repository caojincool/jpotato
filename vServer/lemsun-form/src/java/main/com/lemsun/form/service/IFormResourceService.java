package com.lemsun.form.service;


import com.lemsun.core.formula.IFunctionStatement;
import com.lemsun.form.InnerFunctionDefines;

import java.util.List;

/**
 * 定义一个针对可以显示的组件公共服务
 */
public interface IFormResourceService {
    /**
     * 保存函数对象
     * @param funtion
     */
    void insertStatement(IFunctionStatement funtion);

    /**
     * 更新函数对象
     * @param funtion
     */
    void updateStatement(IFunctionStatement funtion);

    /**
     * 删除
     * @param name
     */
    void dateteStatement(String parentpid,String name);
    /**
     * 移除组件下函数
     * @param parentpid
     */
    void removeStatement(String parentpid);


    /**
     * 获取组件下所有函数
     * @param parentpid
     * @return
     */
    List<InnerFunctionDefines> getStatementsByParentpid(String parentpid);

    /**
     * 获取函数
     * @param name 函数名
     * @return
     */
    InnerFunctionDefines getStatement(String parentpid,String  name);

    /**
     * 返回组件下所有格式化后函数
     * @param parentpid
     * @return
     */
    String formatFunciton(String  parentpid);
}
