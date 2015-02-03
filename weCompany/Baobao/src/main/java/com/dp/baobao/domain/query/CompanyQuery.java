package com.dp.baobao.domain.query;

import com.dp.baobao.domain.AbstractQuery;
import com.dp.baobao.domain.Company;
import com.dp.baobao.domain.IQuery;
import org.apache.ibatis.type.Alias;

/**
 * Created by dpyang on 2015/1/23.
 */
public class CompanyQuery extends AbstractQuery {
    private String code;

    public CompanyQuery(){}

    public CompanyQuery(String id){
        setId(id);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
