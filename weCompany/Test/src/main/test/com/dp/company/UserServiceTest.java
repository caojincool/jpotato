package com.dp.company;

import com.dp.company.domain.User;
import com.dp.company.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dpyang on 2014/10/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private Log log;

    @Before
    public void before() {
        log = LogFactory.getLog(UserServiceTest.class);
    }

    public Log getLog() {
        return log;
    }

    @Test
    public void testHasMatchUser() {
        boolean b1 = userService.hasMatchUser("admin", "123456");
        Assert.assertTrue(b1);
    }

    @Test
    public void testFindUserByUserName(){
        User user=userService.findUserByUserName("admin");
        Assert.assertNotNull(user);
    }

    @Test
    public void testLoginSuccess(){
        Date date=new Date(new java.util.Date().getTime());
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        log.info("时间是" + format.format(date));
    }
}
