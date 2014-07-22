package com.lemsun.client.core.jackson;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lemsun.client.core.*;
import com.lemsun.client.core.model.*;
import org.springframework.stereotype.Service;

/**
 * Json 数据, 序列化或者反序列化映射模型
 * User: Xudong
 * Date: 12-12-22
 * Time: 下午6:12
 *
 */
@Service
public class JsonObjectMapper extends ObjectMapper {

    /**
     *
     */
    public JsonObjectMapper()
    {
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        setSerializationInclusion(JsonInclude.Include.NON_NULL);


        //configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        SimpleModule module = new SimpleModule("lemsun", Version.unknownVersion());
        //module.add
        module.addAbstractTypeMapping(IPlateform.class, Plateform.class);
        module.addAbstractTypeMapping(IPlateformInstance.class, PlateformInstance.class);
        module.addAbstractTypeMapping(IResponseEntity.class, ResponseEntity.class);

        this.registerModule(module);
    }

}