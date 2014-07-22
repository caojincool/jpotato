package com.lemsun.channel;


import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客户端提供的数据发送编码
 * User: Xudong
 * Date: 12-9-14
 * Time: 下午2:45
 */
public class LemsunEncoder  extends ProtocolEncoderAdapter {

	private final Logger log = LoggerFactory.getLogger(LemsunEncoder.class);

	public LemsunEncoder() {

	}


	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {

		try
		{
			IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
			if(message instanceof MessageResponse) {


				MessageResponse response = (MessageResponse) message;

				response.getContext().write(buf);
				buf.flip();
				out.write(buf);
                out.flush();
			}
			else if(message instanceof ChannelException) {
				buf.clear();
				ExcuteResult result = ExcuteResult.create((ChannelException)message);
				result.write(buf);
				buf.flip();
				out.write(buf);
                out.flush();
			}
			else if(message instanceof Exception) {
				buf.clear();
				ExcuteResult result = ExcuteResult.create((Exception)message);
				result.write(buf);
				buf.flip();
				out.write(buf);
                out.flush();
			}
		}
		catch (Exception ex) {
			log.error("最终消息处理出错 : " + ex.getMessage());
		}


	}
}
