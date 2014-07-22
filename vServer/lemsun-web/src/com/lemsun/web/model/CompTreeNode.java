package com.lemsun.web.model;

/**
 * User: 刘晓宝
 * Date: 13-11-29
 * Time: 下午4:32
 */
public class CompTreeNode extends  ExtTreeNode {
    private boolean  isComp=false;

    public boolean isComp() {
        return isComp;
    }

    public void setComp(boolean comp) {
        isComp = comp;
    }
}
