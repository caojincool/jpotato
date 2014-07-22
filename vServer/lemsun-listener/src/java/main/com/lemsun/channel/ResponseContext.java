package com.lemsun.channel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemsun.core.SpringContextUtil;
import com.lemsun.core.jackson.JsonObjectMapper;
import org.apache.mina.core.buffer.IoBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-9
 * Time: 上午11:43
 */
public class ResponseContext implements IResponseWriter {

	private MessageResponse response;

	private static final Logger log = LoggerFactory.getLogger(ResponseContext.class);

	public ResponseContext(MessageResponse response) {
		this.response = response;
	}


	public byte[] getBytes() {
		return null;
	}

	@Override
	public void write(IoBuffer buffer) {


		Object re = response.getResponse();

		ResponseHeader header = response.getHeader();

		try
		{

			IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);

			if(re != null) {

				if(header.getDataFormat() == ContextFormat.JSON) {
					writeJson(buf, re);
				}

			}

			header.setContextLenght(buf.position());

			header.write(buffer);
			buf.flip();
			buffer.put(buf);
			buffer.putString("end;\n", response.getSession().getCharsetEncoder());
		}
		catch (Exception ex) {
			//TODO 处理程序返回代码出错
			ex.printStackTrace();

		}

	}

	private void writeJson(IoBuffer buf, Object re) throws IOException {

        JsonObjectMapper mapper = SpringContextUtil.getBean(JsonObjectMapper.class);
		String json = mapper.writeValueAsString(re);

		ISession session = response.getSession();

		if(log.isDebugEnabled())
		{
			log.debug("输出数据");
			log.debug(json);
		}
		buf.putString(json, session.getCharsetEncoder());
	}
}
