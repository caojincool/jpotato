package com.lemsun.core.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.core.service.ICodecService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-14
 * Time: 下午3:28
 */
public class CodeServiceImplTest extends TestSupport {

    @Autowired
    private ICodecService service;

    @Test
    public void testCodec() {
        String code = service.encode("zong 宗旭东");
        getLog().debug(code);

        String context = service.decode(code);
        getLog().debug(context);

    }

}
