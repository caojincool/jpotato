package com.lemsun.channel;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 服务器端监听通道. 接收用户请求数据等
 * User: Xudong
 * Date: 12-10-9
 * Time: 上午11:11
 */
public class ZxdChannel {

	private SessionManager manager;
	private int port = 9000;
	private int bufferSize = 2048;
	private int idleTime = 10;
	private String chartset = "UTF-8";
	private IoHandler handler;


	public ZxdChannel() {
		//manager = new SessionManager(this);
	}

	/**
	 * 获取监听端口
	 * @return 端口
	 */
	public int getPort() {
		return port;
	}

	/**
	 * 设置监听端口
	 * @param port 端口号
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * 获取处理器
	 * @return 处理对象
	 */
	public IoHandler getHandler() {
		return handler;
	}

	/**
	 * 设置处理对象
	 * @param handler 处理器
	 */
	public void setHandler(IoHandler handler) {
		this.handler = handler;
	}

	/**
	 * 获取缓存大小
	 * @return 缓存大小
	 */
	public int getBufferSize() {
		return bufferSize;
	}

	/**
	 * 设置缓存大小
	 * @param bufferSize 缓存大小
	 */
	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	/**
	 * 获取等待时间
	 * @return 等待时间
	 */
	public int getIdleTime() {
		return idleTime;
	}

	/**
	 * 设置等待时间
	 * @param idleTime 等待时间
	 */
	public void setIdleTime(int idleTime) {
		this.idleTime = idleTime;
	}


	public String getChartset() {
		return chartset;
	}

	public void setChartset(String chartset) {
		this.chartset = chartset;
	}


	public SessionManager getManager() {
		return manager;
	}


	public void setManager(SessionManager manager) {
		this.manager = manager;
	}

	private IoAcceptor acceptor;

	/**
	 * 启动监听
	 * @throws IOException
	 */
	public void start() throws IOException {

		acceptor = new NioSocketAcceptor();

		acceptor.getFilterChain().addLast( "logger", new LoggingFilter());
		acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter(new LemsunCodecFactory(Charset.forName(getChartset()))));
		acceptor.setHandler(getHandler());
		acceptor.getSessionConfig().setReadBufferSize(getBufferSize());
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, getIdleTime());
		acceptor.bind(new InetSocketAddress(getPort()) );

	}


	/**
	 * 停止监听服务
	 */
	public void stop() {
		if(acceptor != null) {
			acceptor.unbind();
			acceptor.dispose();
			acceptor = null;
		}
	}


	/**
	 * 重启监听
	 * @throws IOException 通信异常
	 */
	public void restart() throws IOException {
		stop();
		start();
	}
}
