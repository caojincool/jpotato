package com.dp.baobao.service;

import com.dp.baobao.domain.Company;
import com.dp.baobao.domain.IQuery;

import java.util.List;

/**
 * 服务接口
 * Created by dpyang on 2015/1/23.
 */
public interface IBaobaoService {

     void editCompany(Company company);

     Company getCompany(IQuery query);

     List<Company> loadCampanies();
}
