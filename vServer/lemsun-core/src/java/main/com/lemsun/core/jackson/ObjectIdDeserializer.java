package com.lemsun.core.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.bson.types.ObjectId;

import java.io.IOException;

/**
 * User: 宗旭东
 * Date: 13-5-18
 * Time: 下午12:51
 */
public class ObjectIdDeserializer extends JsonDeserializer<ObjectId> {
    @Override
    public ObjectId deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String id = jsonParser.getText();
        return new ObjectId(id);
    }
}
