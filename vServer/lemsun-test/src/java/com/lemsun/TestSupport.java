package com.lemsun;

import com.lemsun.core.IAccount;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.data.connection.DbManager;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;


/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-14
 * Time: 下午1:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext-test.xml", "/applicationContext-data.xml"})
public class TestSupport {

    private org.slf4j.Logger log;

    private IAccount account;

    @Autowired
    private DbManager dbManager;

    @Autowired
    private JsonObjectMapper mapper;

    @Before
    public void testSetup() {
        log = LoggerFactory.getLogger(this.getClass());
    }


    public Logger getLog() {
        return log;
    }


    public IAccount getAccount() {
        return account;
    }


    public DbManager getDbManager() {
        return dbManager;
    }

    public String toJson(Object bean) {
        try {
            return mapper.writeValueAsString(bean);
        } catch (IOException e) {
            return "erro";
        }
    }
}
