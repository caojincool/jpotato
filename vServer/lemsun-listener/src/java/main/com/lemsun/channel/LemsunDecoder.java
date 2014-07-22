package com.lemsun.channel;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderException;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-9-14
 * Time: 下午2:18
 */
public class LemsunDecoder implements ProtocolDecoder {

	private static Logger log = LoggerFactory.getLogger(LemsunDecoder.class);

	private final AttributeKey CONTEXT = new AttributeKey(getClass(), "lemsuncontext");

	private final Charset charset;




	public LemsunDecoder(Charset charset) {


		this.charset = charset;

	}


	/**
	 * Return the context for this session
	 */
	private StreamContext getContext(IoSession session) {
		StreamContext ctx;
		ctx = (StreamContext) session.getAttribute(CONTEXT);

		if (ctx == null) {
			ctx = new StreamContext();
			session.setAttribute(CONTEXT, ctx);
		}

		return ctx;
	}

	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		decodeAuto(getContext(session), session, in, out);
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {

	}

	private void decodeAuto(StreamContext ctx, IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws CharacterCodingException, ProtocolDecoderException {

		int oldPos = in.position();
		int oldLimit = in.limit();
		try{
			while (in.hasRemaining()) {
				ctx.put(in.get(), session);
				//TextLineDecoder
				if(ctx.matchRequest && ctx.request != null) {

					int pos = in.position();

					out.write(ctx.request);
					ctx.matchRequest = false;
					//ctx.request = null;
					//in.clear();
					ctx.reset();
				}
			}

		}
		catch (Exception ex) {
			ctx.reset();
			out.write(ex);
		}

		//in.position(oldPos);

		//in.limit(oldLimit);
	}


	/**
	 * {@inheritDoc}
	 */
	public void dispose(IoSession session) throws Exception {
		StreamContext ctx = (StreamContext) session.getAttribute(CONTEXT);

		if (ctx != null) {
			session.removeAttribute(CONTEXT);
		}
	}



	/**
	 * 用户缓存的用户数据内容
	 */
	private class StreamContext {

		private boolean matchHeader = false;
		private boolean matchContext = false;

		//命令头部信息
		private IoBuffer headBuf;

		//命令内容信息
		private IoBuffer contextBuf;


		private MessageRequest request;

		private ByteFIFO tempContextEnd;
		private ByteFIFO tempHeader;
		private final  byte[] _contextEnd;
		private final byte[] _commandStart;

		private boolean matchRequest;


		StreamContext() {
			_commandStart = charset.encode("begin ").array();
			headBuf = IoBuffer.allocate(100).setAutoExpand(true);
			contextBuf = IoBuffer.allocate(100).setAutoExpand(true);
			_contextEnd = charset.encode("end;").array();
			tempContextEnd = new ByteFIFO(_contextEnd.length);
			tempHeader = new ByteFIFO(_commandStart.length);
		}




		private int tempContextLenght;
		/**
		 * 放入字节
		 * @param b
		 */
		void put(byte b, IoSession session) throws ChannelFormatException {


			if(matchContext) {
				tempContextEnd.add(b);
				contextBuf.put(b);

				//内容结束
				if(tempContextLenght == contextBuf.position()
						|| (b == ';' && tempContextEnd.compare(_contextEnd))) {
					matchContext = false;
					matchRequest = true;
					contextBuf.position(contextBuf.position() - _contextEnd.length);
					contextBuf.flip();

					request.setContext(createContext(contextBuf));
					return;
				}
			}

			if(!matchContext) {
				//头部信息解析

				if(matchHeader) {
					headBuf.put(b);

					if(b == '\n') {
						matchHeader = false;
						matchContext = true;
						byte [] s = new byte[headBuf.position()];
						headBuf.flip();
						headBuf.get(s);
						request = createRequest(new String(s, charset), session);
					}
				}
				else {
					tempHeader.add(b);
					if(!matchHeader && tempHeader.compare(_commandStart)) {
						matchHeader = true;
						//headBuf.put(_commandStart);
					}
				}
			}

		}


		void reset() {
			headBuf.clear();
			contextBuf = IoBuffer.allocate(100).setAutoExpand(true);
			matchHeader = false;
			matchContext = false;
		}



		MessageRequest createRequest(String header, IoSession session) throws ChannelFormatException {
			RequestHeader h = new RequestHeader(header);
			ISession se = ClientSession.getSession(session);
			return new MessageRequest(h, se);
		}

		RequestContext createContext(IoBuffer buffer) {
			//IoBuffer b = IoBuffer.allocate(buffer.position()).put(buffer);

			return new RequestContext(request, buffer);
		}
	}
}
