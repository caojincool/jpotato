package com.dp.baobao.service.impl;

import com.dp.baobao.domain.Company;
import com.dp.baobao.domain.IQuery;
import com.dp.baobao.mapper.IBaobaoMapper;
import com.dp.baobao.service.IBaobaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by dpyang on 2015/1/23.
 */
@Service
public class BaobaoServiceImpl implements IBaobaoService{

    @Autowired
    private IBaobaoMapper iBaobaoMapper;

    @Override
    public void editCompany(Company company) {
        if (StringUtils.isEmpty(company.getId())){
            iBaobaoMapper.insertCompany(company);
        }else{
            iBaobaoMapper.updateCompany(company);
        }
    }

    @Override
    public Company getCompany(IQuery query) {
        return iBaobaoMapper.getCompany(query);
    }

    @Override
    public List<Company> loadCampanies() {
        return iBaobaoMapper.loadCompanies();
    }
}
