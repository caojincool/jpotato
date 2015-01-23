package com.dp.baobao.domain.query;

import com.dp.baobao.domain.Company;
import com.dp.baobao.domain.IQuery;
import org.apache.ibatis.type.Alias;

/**
 * Created by dpyang on 2015/1/23.
 */
@Alias("CompanyQuery")
public class CompanyQuery implements IQuery {
    private String id;
    private String code;

    public CompanyQuery(){}

    public CompanyQuery(String id){
        this.id=id;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
