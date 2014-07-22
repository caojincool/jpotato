package com.lemsun.data.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.data.connection.DbManager;
import com.lemsun.ldbc.TableData;
import com.lemsun.ldbc.service.ISqlOperaterService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-6-20
 * Time: 上午9:28
 */
public class SqlOperaterServiceImplTest extends TestSupport {
    @Autowired
    ISqlOperaterService service;

    @Autowired
    private DbManager dbManager;


    @Test
    public void testSelect() {
        TableData data = null;
        getLog().debug("开始查询");
        try {
            data = service.select(dbManager.getDataSource("C000000656"), "select * from C000001226");
        } catch (Exception e) {
            getLog().error("查询失败:" + e.getMessage());
        }
        getLog().debug("查询到" + data.getColCount() + "列" + data.getRowCount() + "行数据");
        getLog().debug(data.toString());
    }

    @Test
    public void testExcute() {

    }
}
