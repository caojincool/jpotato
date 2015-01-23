package com.dp.test;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dpyang on 2014/10/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class SuperTest {

    private final Logger logger=Logger.getLogger(SuperTest.class);

    @Before
    public void before() {
        logger.info("开始测试");
    }

    @After
    public void after(){
        logger.info("测试结束");
    }
}
