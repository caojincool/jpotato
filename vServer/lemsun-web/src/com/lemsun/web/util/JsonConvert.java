package com.lemsun.web.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/**
 * User: Lucklim
 * Date: 12-11-30
 * Time: 上午11:18
 */
public class JsonConvert {
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 序列化对象为JSON
     * @param object
     * @return
     */
    public static String Serialization(Object object) throws Exception {
        StringWriter sw = new StringWriter();
        try {
            mapper.writeValue(sw, object);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } catch (JsonMappingException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        if(sw != null) {
            try {
                sw.flush();
                sw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sw.toString();
    }
}

