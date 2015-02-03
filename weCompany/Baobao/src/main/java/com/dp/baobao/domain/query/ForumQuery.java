package com.dp.baobao.domain.query;

import com.dp.baobao.domain.AbstractQuery;
import com.dp.baobao.domain.Forum;

/**
 * Created by dpyang on 2015/1/24.
 */
public class ForumQuery extends AbstractQuery {
    private String code;

    public ForumQuery(){}

    public ForumQuery(String code){this.code=code;}

    public ForumQuery(String id,String code){
        setId(id);
        setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
