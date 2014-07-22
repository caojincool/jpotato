package com.lemsun.auth.events;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * 用户事件对象. 当用户发生登录, 退出. 等相关操作后. 在容器中就发出这个事件
 * User: 宗旭东
 * Date: 13-2-25
 * Time: 下午8:26
 */
public class AccountEvent extends ApplicationContextEvent {

    public AccountEvent(ApplicationContext source, AccountEventType eventType) {
        super(source);
        this.eventType = eventType;
    }

    private AccountEventType eventType;

    public AccountEventType getEventType() {
        return eventType;
    }

    /**
     * User: 宗旭东
     * Date: 13-2-25
     * Time: 下午8:26
     */
    public static enum AccountEventType {
        /**
         * 登录
         */
        Logon,
        /**
         * 退出
         */
        LogOut,
        /**
         * 超时退出
         */
        Timeout

    }
}
