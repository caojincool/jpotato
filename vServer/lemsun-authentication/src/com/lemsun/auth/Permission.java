package com.lemsun.auth;

/**
 * User: ssy
 * Date: 12-12-15
 * Time: 上午9:34
 * 权限浏览
 */
public enum  Permission {
    /**
     * 允许
     */
    Allow (1),
    /**
     * 未知
     */
    Unkonw(2),
    /**
     * 拒绝
     */
    Deny(3),
    /**
     * 隐藏
     */
    Hiddle(4);

    private int index;

    Permission(int index) {
        this.index = index;
    }

    /**
     *
     * @return
     */
    public int getIndex() {
        return index;
    }
    public  Permission getPermission() {
        return   index == 1 ? Allow
                : index == 2 ? Unkonw
                : index == 3 ? Deny
                : Hiddle;
    }

    public static Permission getPermission(int index) {
        return index == 1 ? Allow
                : index == 2 ? Unkonw
                : index == 3 ? Deny
                : Hiddle;
    }
}
