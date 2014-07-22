package com.lemsun.data.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.service.IDbService;
import com.lemsun.ldbc.TableData;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-14
 * Time: 下午2:11
 */
public class DbServiceImplTest extends TestSupport {

    @Autowired
    private IDbService service;

    private Logger log = LoggerFactory.getLogger(DbServiceImplTest.class);

    @Test
    public void testCreate() throws Exception {

        DbConfigResource configResource = new DbConfigResource("阳泉四代数据库");
        configResource.setUsername("sa");
        configResource.setPassword("1");

        configResource.setDefaultDb(true);

        service.addConfig(configResource);
    }

    @Test
    public void testSelect() {
        try {
            TableData data=service.select("C000001723","select * from C000005719_XX_09");
            assertNotNull(data);
            log.info("总行数"+data.getRowCount());
        } catch (Exception e) {
            log.error("错误信息:"+e.getMessage());
        }

    }


    @Test
    public void testGetDbNames() throws Exception {
        DbConfigResource resource = service.getDbconfigResource("DB00000160");
        String[] names = service.getDbNames(resource);

        getLog().debug(toJson(names));
    }

    @Test
    public void testExecute() {
        try {
            service.execute("C000000169", "INSERT INTO C000000444_XX (Code, D, E, F) VALUES ('102', 'dpyna', 'dpyang@gmail.com', '杨大平')");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
