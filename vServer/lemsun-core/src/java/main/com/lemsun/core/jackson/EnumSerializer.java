package com.lemsun.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-21
 * Time: 上午10:15
 */
public class EnumSerializer  extends JsonSerializer<Enum> {
	@Override
	public void serialize(Enum value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {

	}
}
