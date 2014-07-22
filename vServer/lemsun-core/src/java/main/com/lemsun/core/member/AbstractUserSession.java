package com.lemsun.core.member;

import com.lemsun.core.IAccount;
import com.lemsun.core.IResponseCommand;
import com.lemsun.core.PlateformInstance;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

/**
 * 提供基本的用户会话实现的方法
 * User: 宗旭东
 * Date: 13-3-4
 * Time: 下午5:11
 */
public abstract class AbstractUserSession implements IUserSession {

    private Date startTime = new Date();
    private Date lastUpdateTime = new Date();
    private String plateformId;
    private IAccount account;
    private Charset charset;
    private String id;
    private String ip;
    private String plateformToken;

    protected AbstractUserSession(IAccount account, PlateformInstance plateform, String ip) {
        this.account = account;
        plateformId = plateform.getId();
        plateformToken = plateform.getToken();
        id = UUID.randomUUID().toString().replaceAll("-", "");
        this.ip = ip;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public abstract String getSessionId();

    @Override
    public Date getStartTime() {
        return startTime;
    }

    @Override
    public Date getLastAction() {
        return lastUpdateTime;
    }

    @Override
    public void updateLastAction() {
        lastUpdateTime = new Date();
    }

    @Override
    public IAccount getAccount() {
        return this.account;
    }

    @Override
    public abstract Object getAttribute(String key);

    @Override
    public abstract void setAttribute(String key, Object value);

    @Override
    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    @Override
    public String getClientIp() {
        return ip;
    }

    /**
     * Web 用户的Session 不能发送消息给客户端. 这是一个空实现的方法. 不能进行真实的消息发送
     * @param command 命令
     */
    @Override
    public void SendMessage(IResponseCommand command) {

    }

    @Override
    public String getPlateformId() {
        return plateformId;
    }

    @Override
    public String getPlateformToken() {
        return plateformToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractUserSession that = (AbstractUserSession) o;

        if (StringUtils.equals(getPlateformId(), that.getPlateformId())) return true;

        return false;
    }

    @Override
    public int hashCode() {

        return (getClass().getName() + getPlateformId()).hashCode();
    }
}
