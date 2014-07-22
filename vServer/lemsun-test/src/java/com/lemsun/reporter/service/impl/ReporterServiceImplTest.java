package com.lemsun.reporter.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.reporter.ReporterParam;
import com.lemsun.reporter.ReporterResource;
import com.lemsun.reporter.service.IReporterResourceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by dpyang on 2014/5/20.
 */
public class ReporterServiceImplTest extends TestSupport{

    @Autowired
    private IReporterResourceService reporterResourceService;

    @Test
    public void createTest(){
        ReporterResource resource=new ReporterResource("abc");
        try {
            //reporterResourceService.create(resource);
            getLog().debug("ok------------------------------------------");
        } catch (Exception e) {
            getLog().error(e.getMessage()+"----------------------------");
        }
    }

    @Test
    public void updateParams(){



    }


}
