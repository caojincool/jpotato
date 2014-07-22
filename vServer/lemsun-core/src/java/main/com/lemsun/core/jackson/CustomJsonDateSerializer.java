package com.lemsun.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 序列化日期
 * User: Xudong
 * Date: 12-11-3
 * Time: 上午9:52
 */
public class CustomJsonDateSerializer  extends JsonSerializer<Date> {

	 private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	@Override
	public void serialize(Date aDate, JsonGenerator aJsonGenerator, SerializerProvider aSerializerProvider)
			throws IOException, JsonProcessingException {

		String dateString = dateFormat.format(aDate);
		aJsonGenerator.writeString(dateString);
	}
}
