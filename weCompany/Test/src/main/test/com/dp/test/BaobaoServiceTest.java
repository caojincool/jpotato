package com.dp.test;

import com.dp.baobao.domain.Company;
import com.dp.baobao.domain.IQuery;
import com.dp.baobao.service.IBaobaoService;
import com.dp.baobao.domain.query.CompanyQuery;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class BaobaoServiceTest extends SuperTest {

    @Autowired
    private IBaobaoService iBaobaoService;

    private IQuery query;

    @Before
    public void before(){
       query=new CompanyQuery("6d0972b4-a2da-11e4-9d68-8d15c28d7bc0");
    }

    @Test
    public void testEditCompany() throws Exception {
        Company company=new Company("","002","洋芋网","洋芋博客");

        iBaobaoService.editCompany(company);
    }

    @Test
    public void testGet() throws Exception {

        Company company=iBaobaoService.getCompany(query);

        assertNotNull(company);
    }

    @Test
    public void testUpdate(){
        Company company=iBaobaoService.getCompany(query);
        company.setRemark("test");
        iBaobaoService.editCompany(company);
    }

    @Test
    public void testLoadCampanies() throws Exception {
        List<Company> companies=iBaobaoService.loadCampanies();

        assertEquals(3,companies.size());
    }
}