package com.lemsun.channel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemsun.core.SpringContextUtil;
import com.lemsun.core.jackson.JsonObjectMapper;
import org.apache.mina.core.buffer.IoBuffer;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-9
 * Time: 上午11:43
 */
public class RequestContext {

	private IoBuffer buffer;
	private MessageRequest request;

	public RequestContext(MessageRequest request, IoBuffer buffer) {
		this.buffer = buffer;
		this.request = request;
	}


	public IoBuffer getBuffer() {
		return buffer;
	}

	/**
	 * 获取字符内容
	 * @param charsetDecoder 编码
	 * @return 内容字符
	 * @throws CharacterCodingException 字符异常
	 */
	public String getAsString(CharsetDecoder charsetDecoder) throws CharacterCodingException {
		buffer.position(0);
		return buffer.getString(charsetDecoder);
	}

	/**
	 * 获取内容字符
	 * @return 字符
	 * @throws CharacterCodingException
	 */
	public String getAsString() throws CharacterCodingException {
		return getAsString(Charset.forName("UTF-8").newDecoder());
	}


	/**
	 * 将数据内容转换成一个对象. 取决于数据内容的格式. 一般有JSON 格式
	 * @param targetClass
	 * @param <T>
	 * @return
	 */
	public <T> T getObject(Class<T> targetClass) throws ChannelFormatException {
		try
		{
			ContextFormat f = request.getHeader().getDataFormat();

			if(f == ContextFormat.JSON) {

				String json = getAsString();

                JsonObjectMapper mapper = SpringContextUtil.getBean(JsonObjectMapper.class);
				return mapper.readValue(json, targetClass);
			}

			throw new ChannelFormatException("没有对应的解析格式 (" + f + ")");

		}
		catch (Exception ex) {
			throw new ChannelFormatException("数据内容解析出错. 不能转换成所要的对象 :" + ex.getMessage());
		}
	}

}
