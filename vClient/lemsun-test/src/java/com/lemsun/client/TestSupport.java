package com.lemsun.client;


import com.lemsun.client.core.IAccount;
import com.lemsun.client.core.model.BaseAccount;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;


/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-14
 * Time: 下午1:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class TestSupport {

	private Logger log;
    private IAccount account;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;


	@Before
	public void testSetup()
	{
		log = LoggerFactory.getLogger(this.getClass());
        BaseAccount acc = new BaseAccount("admin");
        acc.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
        account = acc;
        request=  new MockHttpServletRequest();
        response= new MockHttpServletResponse();
	}

    public Logger getLog() {
        return log;
    }



    public IAccount getAccount() {
        return account;
    }

    public MockHttpServletRequest getRequest() {
        return request;
    }

    public MockHttpServletResponse getResponse() {
        return response;
    }
}
