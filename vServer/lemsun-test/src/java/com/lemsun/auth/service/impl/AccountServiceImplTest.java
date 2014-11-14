package com.lemsun.auth.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.auth.*;
import com.lemsun.web.manager.controller.model.query.AccountQuery;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.IAccount;
import com.lemsun.core.jackson.JsonObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.io.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: Xudong
 * Date: 13-1-7
 * Time: 下午3:41
 */
public class AccountServiceImplTest extends TestSupport {

    @Autowired
    private IAccountService service;

    @Autowired
    private JsonObjectMapper objectMapper;

    private Logger log = LoggerFactory.getLogger(AccountServiceImplTest.class);

    @Test
    public void testCreate() throws Exception {
        log.debug("测试创建人员");

        BaseAccount account = new BaseAccount("zongxudong");

        account.setShowName("dpyang");
        account.setAccount("123698");
        account.setPassword("0");
        account.setAdministrator(true);
        account.setLoginIp("192.168.2.32");
        account.setLastLoginTime(new Date());
        service.save(account);
        log.debug("测试完成");
    }

    @Test
    public void testUpdateAvatar() {
//        BaseAccount account=service.getByAccount("admin");
//        File file=new File("D:\\Documents\\IdeaProjects\\Project_LemsunV\\out\\artifacts\\WebServer_Admin_war_exploded\\resource\\images\\accountface\\zong002dong7.jpg");
//        try {
//            InputStream inputStream=new FileInputStream(file);
//            service.updateAvatar(account,inputStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        //设置默认图标
        File file = new File("D:\\Documents\\IdeaProjects\\Project_LemsunV\\out\\artifacts\\WebServer_Admin_war_exploded\\resource\\images\\accountface\\default.jpg");
        try {
            InputStream inputStream = new FileInputStream(file);
            service.updateAvatar("default", inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (AccountException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAvatar() throws IOException, AccountException {
        BaseAccount account = (BaseAccount)service.getByAccount("admin");
        OutputStream fs = new FileOutputStream("D:\\Documents\\IdeaProjects\\Project_LemsunV\\out\\artifacts\\WebServer_Admin_war_exploded\\resource\\images\\accountface\\" + account.getId() + ".ico");
        InputStream inputStream = service.getAvatar("sdfsdf",1);
        byte[] b = new byte[20480];
        int x = 0, y = 0;
        while ((x = inputStream.read(b)) != -1) {
            y += x;
            fs.write(b, 0, y);
        }

        inputStream.close();
        fs.close();
    }

    @Test
    public void testChangePassword() throws AccountException {
        BaseAccount account = (BaseAccount)service.getByAccount("admin");

        service.changePassword(account, "0", "123123");
        account.getAccount();
    }

    @Test
    public void testQuery() throws IOException {

        AccountQuery query = new AccountQuery();
        query.setPageSize(5);

        Page<IAccount> list = service.getPageData(query);

        for (IAccount baseAccount : list) {
            log.debug(baseAccount.getAccount());
        }

    }

    @Test
    public void testUpdateAccount() throws Exception {
        //已经存在的账户更新其信息
        BaseAccount testAccount = (BaseAccount)service.getByAccount("admin");


        testAccount.setPassword("0");
        //不存在的账户更新其信
        service.updateAccount(testAccount);

        getLog().debug("更新完成");
    }

    @Test
    public void testUpdateExpandAccount() {

//        ExpandAccount expandAccount= (ExpandAccount) service.getByAccount("admin");
//        expandAccount.setEmail("123132@326.com");
//        expandAccount.setMobile("18602315663");

//        try {
//            //service.updateExpandAccount(expandAccount);
//        } catch (AccountException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
    }

    @Test
    public void testDeleteAccountById() {
        //存在的账�
        String id = "zong002dong10";
//        service.delectAccountById(id);

        //不存在的账户
        id = "zong";
        try {
            service.deleteAccountById(id);
        } catch (AccountException e) {
            log.debug(e.getMessage());
        }
    }

    @Test
    public void testGetAccountByAccount() {
        //不存在的登录账户
        String account = "2.32.3";
        BaseAccount baseAccount = null;
        try {
            baseAccount = (BaseAccount)service.getByAccount(account);
        } catch (AccountException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        log.debug("wjegtest:" + baseAccount.getShowName());
    }

    @Test
    public void testUpdateAccountPermissions() {
        IAccount account = null;
        try {
            account = service.getByAccount("dpyang");
        } catch (AccountException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        PermissionKey pk = new PermissionKey(Permission.getPermission(1), "com.lems.danju");
        Set<PermissionKey> pks = new HashSet<>();
        pks.add(pk);
        pks.add(new PermissionKey(Permission.Unkonw, "com.lms.test"));

        service.updateAccountPermission(account, pks);
    }

    @Test
    public void testGetAccountPermissions() {
        IAccount account = null;
        try {
            account = service.getByAccount("dpyang");
        } catch (AccountException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        List<PermissionKey> pks = service.getAccountPermissions(account);

        log.debug("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for (PermissionKey p : pks) {
            log.debug("++++++++++++++++++" + p.getKey() + ":" + p.getPermission() + "++++++++++++++++++");
        }
        log.debug("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Test
    public void testGetExpandAccount() throws AccountException {
        String accountId = "A00000220";
        BaseAccount baseAccount = service.getAccountByAccountId(accountId);

       // List<ExpandAccount> expandAccounts = service.getAccountExpandByAccount(baseAccount);
        //ExpandAccount expandAccounts=service.getExpandAccountByAccountId(accountId);

        //log.debug(String.valueOf(expandAccounts.getEmail()));
    }
}
