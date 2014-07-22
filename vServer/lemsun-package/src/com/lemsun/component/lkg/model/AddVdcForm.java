package com.lemsun.component.lkg.model;

/**
 * Created with IntelliJ IDEA.
 * User: lmy
 * Date: 13-8-27
 * Time: 上午10:02
 * To change this template use File | Settings | File Templates.
 */
public class AddVdcForm {
    /**
     * 组件包ID
     */
    private String lid;
    /**
     * 需要添加组件pid集合
     */
    private String[] pids;
    /**
     * 组件包下虚拟文件ID
     */
    private String fid;

    public String getLid() {
        return lid;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String[] getPids() {
        return pids;
    }

    public void setPids(String[] pids) {
        this.pids = pids;
    }
}
