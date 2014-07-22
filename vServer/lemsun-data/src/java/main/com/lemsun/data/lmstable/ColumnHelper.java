package com.lemsun.data.lmstable;

/**
 * 列帮助模型
 */
public class ColumnHelper {

    private int coontextType;
    private String context;
    private boolean open;


    public ColumnHelper(String context, int coontextType) {
        this.context = context;
        this.coontextType = coontextType;
    }

    public ColumnHelper() {

    }

    /**
     * 获取是否启用或者打开帮助
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * 设置获取是否打开帮助
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * 获取帮助类型
     */
    public int getContextType() {
        return coontextType;
    }

    public void setContextType(int cate) {
        this.coontextType = cate;
    }

    /**
     * 获取帮助内容
     */
    public String getContext() {
        return context;
    }

    /**
     * 设置帮助内容
     */
    public void setContext(String context) {
        this.context = context;
    }
}
