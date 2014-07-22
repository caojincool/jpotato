package com.lemsun.core.member;

import com.lemsun.core.LemsunResource;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-12-8
 * Time: 上午9:19
 * To change this template use File | Settings | File Templates.
 */
public class ResourceReturnData {
    public List<LemsunResource> lemsunResources;
    public long count;

    public List<LemsunResource> getLemsunResources() {
        return lemsunResources;
    }

    public void setLemsunResources(List<LemsunResource> lemsunResources) {
        this.lemsunResources = lemsunResources;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }


}
