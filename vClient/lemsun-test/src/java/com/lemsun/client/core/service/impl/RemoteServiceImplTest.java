package com.lemsun.client.core.service.impl;

import com.lemsun.client.TestSupport;
import com.lemsun.client.core.Host;
import com.lemsun.client.core.model.PlateformInstance;
import com.lemsun.client.core.model.ResponseEntity;
import com.lemsun.client.core.jackson.JsonObjectMapper;
import com.lemsun.client.core.service.IRemoteService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: 宗旭东
 * Date: 13-3-8
 * Time: 下午1:55
 */
public class RemoteServiceImplTest extends TestSupport {

    @Autowired
    private IRemoteService remoteService;

    @Autowired
    private Host host;

    @Autowired
    private JsonObjectMapper objectMapper;

    @Test
    public void testPlateformInstance() throws Exception {
        getLog().debug("测试开始");
        //IResponseEntity<PlateformInstance> re = remoteService.getAddressContext(host.getKey(), PlateformInstance.class);


//
//        PlateformInstance instance = re.getEntity();
//
//
//        getLog().debug(instance.getIp());

        String context = remoteService.getAddressContext(host.getKey());

        //IResponseEntity<PlateformInstance> re = objectMapper.readValue(context, new ResponseEntityTypeReference<PlateformInstance>());
        //IResponseEntity<PlateformInstance> re = objectMapper.readValue(context, new TypeReference<IResponseEntity<PlateformInstance>>(){});


    }

    @Test
    public void testVoid() {

        String name = PlateformInstance.class.getName();
        String cls = "com.lemsun.client.core.model.IResponseEntity<" + name + ">";

    }

}
