package com.lemsun.core.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.data.service.IFileResourceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: 刘晓宝
 * Date: 13-10-28
 * Time: 下午4:09
 */
public class FileServiceImplTest extends TestSupport {
    @Autowired
    private IFileResourceService service;
    @Test
    public void testDeleteFileResource(){
        try{
        service.delete("C000003799");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
