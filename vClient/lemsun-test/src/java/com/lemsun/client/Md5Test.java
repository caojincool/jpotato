package com.lemsun.client;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: 宗旭东
 * Date: 13-3-29
 * Time: 上午9:18
 */
public class Md5Test {

    private static Logger log = LoggerFactory.getLogger(Md5Test.class);

    @Test
    public void testMD5() {
        String md5 = DigestUtils.md5Hex("zong");
        log.debug(String.format("test:%s, %s", md5, "sdf"));
    }
}
