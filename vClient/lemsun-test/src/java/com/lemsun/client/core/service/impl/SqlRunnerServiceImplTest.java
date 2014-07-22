package com.lemsun.client.core.service.impl;

import com.lemsun.client.TestSupport;
import com.lemsun.client.core.data.ArrayData;
import com.lemsun.client.core.service.ISqlRunnerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-6-20
 * Time: 上午11:17
 */
public class SqlRunnerServiceImplTest extends TestSupport {

    @Autowired
    ISqlRunnerService sqlRunnerService;

    @Test
    public void testSelect(){
        String sql="select a,b,c,e,d from C000001226 where b='通知公告'";
        getLog().debug("开始从远程服务器执行sql语句:"+sql);


    }
}
