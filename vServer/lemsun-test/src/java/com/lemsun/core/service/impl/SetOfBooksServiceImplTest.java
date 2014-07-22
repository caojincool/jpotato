package com.lemsun.core.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.SetOfBooksResource;
import com.lemsun.core.query.LemsunResourceQuery;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.service.ISetOfBooksService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-7-24
 * Time: 上午10:21
 */
public class SetOfBooksServiceImplTest extends TestSupport {

    @Autowired
    private ISetOfBooksService service;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IAccountService accountService;

    /**
     * 保存帐套
     *
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
    }


    /**
     * 添加帐套组件
     */
    @Test
    public void testaddResource() {
    }

    /**
     * 添加帐套账户
     */
    @Test
    public void testAddAccount() {

    }

    @Test
    public void testLoadPageSetOfBooksAccouns(){
    }

    @Test
    public void testLoadAllSetOfBooksAccounts(){
    }

    @Test
    public void testRemoveSetOfBooksAccount() throws Exception {
    }


    @Test
    public void testRemoveSetOfBooks() {

    }

    @Test
    public void testGetSetOfBooksByAccount() {
    }

    @Test
    public void testRemoveResource() {
        SetOfBooksResource sobr = new SetOfBooksResource();
        service.removeSobResource(sobr);
    }

    @Test
    public void testGetSetofBooksByResouce() {
    }

    @Test
    public void getSetOfBooksResourceBySid() {

    }

    @Test
    public void getSetOfBooksAccountBySid() {
        LemsunResourceQuery query = new LemsunResourceQuery();
        query.setPageSize(10);
        query.setOffset(1);

    }

}
