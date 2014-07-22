package com.lemsun.client.core.service;

import com.lemsun.client.core.data.TableData;
import com.lemsun.client.core.lmstable.Table5Resource;
import com.lemsun.client.core.model.LemsunResource;

import java.io.IOException;

/**
 * 表格组件服务接口
 * User: 宗旭东
 * Date: 13-10-25
 * Time: 下午4:17
 */
public interface ITableResourceService {

    /**
     * 获取表格组件对象, 但是不会包含组件的附加信息, 比如界面设置, 数据设置等
     * @param pid 表格组件编码
     * @return 表格组件对象
     */
    Table5Resource get(String pid) throws IOException;


    /**
     * 使用组件获取组件的数据信息
     * @param resource 表格组件
     * @return 获取组件的数据信息
     */
    TableData getTableData(Table5Resource resource) throws IOException;


    /**
     * 获取当前表格的数据量
     * @param resource 表格组件
     * @return 当前表格的数据量
     */
    long getTableDataCount(Table5Resource resource);
}
