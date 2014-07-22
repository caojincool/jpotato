package com.lemsun.web.manager.controller.model.query;

import com.lemsun.web.model.ExtPageRequest;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created with IntelliJ IDEA.
 * User: lmy
 * Date: 13-8-27
 * Time: 上午10:27
 * To change this template use File | Settings | File Templates.
 */
public class VirtualDirectoryComponentQuery  extends ExtPageRequest {
    /**
     * 虚拟文件ID
     */
    private String fid;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
    @Override
    public Query createQuery() {

        Query query = super.createQuery();
        if(StringUtils.isNotEmpty(getFid())) {
            query.addCriteria(Criteria.where("navigation.$id").is(new ObjectId(getFid())));
        }

        return query;
    }
}
