package com.lemsun.data.viproject.importmodel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-2-2
 * Time: 上午11:29
 * To change this template use File | Settings | File Templates.
 */
public class ImportTableGroupModel {

    /**
     * 5代中的ID
     */
    private String pid;


    private String code;


    /**
     * 名字
     */
    private String name;




    /**
     * 5代中的ID
     */
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 名字
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * code
     * @return
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
