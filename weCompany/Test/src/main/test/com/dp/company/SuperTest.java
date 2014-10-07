package com.dp.company;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    private Log log;

    @Before
    public void before() {
        log = LogFactory.getLog(UserServiceTest.class);
    }

    public Log getLog() {
        return log;
    }

}
