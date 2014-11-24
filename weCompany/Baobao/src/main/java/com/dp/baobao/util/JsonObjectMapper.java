package com.dp.baobao.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

/**
 * Created by dpyang on 2014/11/14.
 */
public class JsonObjectMapper extends ObjectMapper {
    public JsonObjectMapper(){
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);

        configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));

    }

}
