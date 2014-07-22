package com.lemsun.data.viproject;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-15
 * Time: 上午11:59
 * To change this template use File | Settings | File Templates.
 */
public class TableImportInfo {

    private String code;
    private boolean success;
    private String msg;
    private Date time;


    /**
     * 得到编码
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置编码
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *  是否成功
     * @return
     */
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 信息
     * @return
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 信息
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 操作时间
     * @return
     */
    public Date getTime() {
        return time;
    }

    /**
     * 操作时间
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }
}
