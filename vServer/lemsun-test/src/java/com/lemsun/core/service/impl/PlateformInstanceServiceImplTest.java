package com.lemsun.core.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.core.PlateformException;
import com.lemsun.core.PlateformInstance;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.core.service.IPlateformInstanceService;
import com.lemsun.core.service.IPlateformService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;

/**
 * User: 宗旭东
 * Date: 13-3-1
 * Time: 下午1:56
 */
public class PlateformInstanceServiceImplTest extends TestSupport {

    @Autowired
    private IPlateformInstanceService plateformInstanceService;

    @Autowired
    private JsonObjectMapper mapper;

    @Autowired
    private IPlateformService plateformService;

    @Test
    public void testPlateformInstanceGet() throws IOException {
        String pid = "P000001002";


        PlateformInstance pi = plateformInstanceService.get(pid);

        getLog().debug(mapper.writeValueAsString(pi));

    }

    @Test
    public void testCreatePlant() throws PlateformException {
        for (int i = 0; i < 15; i++) {
            PlateformInstance plateformInstance = new PlateformInstance();

            plateformInstance.setName("name" + i);
            plateformInstance.setRemark("remark" + i);
            plateformInstance.setAddress("address" + i);

            plateformInstance.setIp("192.156.2.1" + i);
            plateformInstance.setTxKey("sdf");

            plateformInstance.setUser("admin");
            plateformInstance.setJiqicode("jiqi");
            plateformInstance.setQyDate(new Date());
            plateformInstance.setJsDate(new Date());
            //plateformInstance.setOwner(plateformService.get("PLATEFORM00000001"));
            plateformInstanceService.create(plateformInstance);
        }
    }


    //public void
}
