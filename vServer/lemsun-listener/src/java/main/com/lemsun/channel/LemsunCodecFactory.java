package com.lemsun.channel;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

/**
 * Lemsun 编码格式
 * User: Xudong
 * Date: 12-9-11
 * Time: 上午8:51
 */
public class LemsunCodecFactory implements ProtocolCodecFactory {


	private final LemsunEncoder encoder;
	private final LemsunDecoder decoder;
	/** An IoBuffer containing the delimiter */


	public LemsunCodecFactory() {
		this(Charset.defaultCharset());
	}

	public LemsunCodecFactory(Charset charset) {
		encoder = new LemsunEncoder();
		decoder = new LemsunDecoder(charset);
	}


	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}
}
