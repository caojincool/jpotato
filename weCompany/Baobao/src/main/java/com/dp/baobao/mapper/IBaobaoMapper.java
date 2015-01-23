package com.dp.baobao.mapper;

import com.dp.baobao.domain.Company;
import com.dp.baobao.domain.IQuery;

import java.util.List;

/**
 * 持久对象
 * Created by dpyang on 2015/1/23.
 */
public interface IBaobaoMapper {

    public void insertCompany(Company company);

    public void updateCompany(Company company);

    public Company getCompany(IQuery query);

    public List<Company> loadCompanies();
}
