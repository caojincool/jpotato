package com.lemsun.client.core;

import com.lemsun.client.core.model.LemsunResource;

/**
 * 权限异常
 * User: dp
 * Date: 13-6-7
 * Time: 上午10:30
 */
public class PermissionException extends RuntimeException {

    private IAccount account;
    private LemsunResource resource;
    private Permission permission;


    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public PermissionException(String message, IAccount account, LemsunResource resource, Permission permission) {
        super(message);
        this.account = account;
        this.resource = resource;
        this.permission = permission;
    }


    public IAccount getAccount() {
        return account;
    }

    public LemsunResource getResource() {
        return resource;
    }

    public Permission getPermission() {
        return permission;
    }
}
