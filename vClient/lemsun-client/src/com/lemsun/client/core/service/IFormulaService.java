package com.lemsun.client.core.service;

import com.lemsun.client.core.data.ArrayData;
import com.lemsun.client.core.formula.IFormula;
import com.lemsun.client.core.formula.IStatement;
import java.util.Map;

/**
 * 公式服务定义接口
 * User: 宗
 * Date: 13-4-23
 * Time: 下午7:18
 */
public interface IFormulaService {

    /**
     * 获取公式数据, 首先从 scope 中查找有没有对应的变量. 如果没有, 就到服务器获取对象的数据
     * @param formula 公式
     * @return 公式执行的数据, 如果是单个对象, 可以使用 getValue() 获取
     */
    ArrayData getFormulaData(Map scope, String formula);

    /**
     * 获取公式数据, 首先从 scope 中查找有没有对应的变量. 如果没有, 就到服务器获取对象的数据
     * @param scope 公式的环境
     * @param formula 公式对象
     * @return 公式执行的数据, 如果是单个对象, 可以使用 getValue() 获取
     */
    ArrayData getFormulaData(Map scope, IFormula formula);

    /**
     * 判断是否是一个公式表达式
     * @param formula 公式
     * @return 公式
     */
    boolean isFormula(String formula);

    /**
     * 转换当前的字符串, 返回公式对象
     * @param formula 公式字符串
     * @return 公式对象
     */
    IFormula parse(String formula);

    /**
     * 解析输入的文本转换成公式片段
     */
    IStatement parseStatement(String statement);
}
