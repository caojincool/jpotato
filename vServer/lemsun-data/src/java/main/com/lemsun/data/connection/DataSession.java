package com.lemsun.data.connection;

import com.lemsun.core.IAccount;
import com.lemsun.core.IResponseCommand;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户数据库会话对象. 支持数据库, 事务的操作
 * User: Xudong
 * Date: 12-11-16
 * Time: 下午1:40
 */
public class DataSession implements IDataSession {

	/**
	 * 当前 Session 中保存用户信息的建
	 */
	public static final String SESSION_KEY = "__context__";

	private IAccount account;
	private Date startTime = new Date();
	private Date lastAction;
	private String id;
	private TransactionManager transactionManager;
	private Charset charset;
	private Map<Object, Object> params = new HashMap<>();

	public DataSession(DbManager dbManager, IAccount account) {
		id = UUID.randomUUID().toString().replaceAll("-", "");
		//transactionManager = new TransactionManager(dbManager, this);
		this.account = account;
	}

	public String getId() {
		return id;
	}

    @Override
    public String getSessionId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	/**
	 * 获取用户的账号
	 * @return 账号对象
	 */
	public IAccount getAccount() {
		return account;
	}

	@Override
	public Object getAttribute(String key) {
		return params.get(key);
	}

	@Override
	public void setAttribute(String key, Object value) {

		if(params.containsKey(key))
			params.remove(key);

		params.put(key, value);
	}

	public Charset getCharset() {
		return charset;
	}

    @Override
    public String getClientIp() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void SendMessage(IResponseCommand command) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getPlateformId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getPlateformToken() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void close() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setCharset(Charset charset) {
		this.charset = charset;
	}
	/**
	 *
	 * @return 用户登录的时间
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 *
	 * @return 用户最后一次操作时间
	 */
	public Date getLastAction() {
		return lastAction;
	}

    @Override
    public void updateLastAction() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setLastAction(Date lastAction)
	{
		this.lastAction = lastAction;
	}

}
