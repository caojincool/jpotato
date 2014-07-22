package com.lemsun.isserver;

import com.lemsun.ldbc.TableData;

/**
 * 数据提供服务接口, 提供对数据查询, 修改等操作命令
 * User: 宗
 * Date: 13-4-19
 * Time: 下午5:58
 */
public interface IDataInterface {

    /**
     * 执行公式, 并发回公式执行的数据内容.
     * @param formula
     * @return
     */
    TableData getFormulaData(String formula);
}
