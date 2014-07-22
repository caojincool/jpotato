package com.lemsun.web.manager.controller.account;

//import com.lemsun.auth.Account;
//import com.lemsun.auth.Role;
import com.lemsun.web.model.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lemsun.auth.service.IAccountService;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-21
 * Time: 下午5:11
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("account/")
public class AccountController {

    private final static Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private IAccountService accountService;

//    /**
//     * 控制页面跳转
//     * @return
//     */
//    @RequestMapping(value = {"accounts"})
//    public String account()
//    {
//        return "admin/account/accounts";
//    }
//
//    @RequestMapping(value = {"accountCreate"})
//    public String create(String id){
//        return "admin/account/accountCreate";
//    }
//    /**
//     * 新增方法调用
//     * @return
//     */
//    @RequestMapping(value = "save")
//    public ResponseEntity save(Account account) throws Exception {
//
//        //accountService.save(account);
//
//        return new ResponseEntity(true);
//    }
//
//    /**
//     * 查询方法调用
//     * @return
//     */
//    @RequestMapping(value = "getData")
//    public Page<Account> getData(Account account)
//    {
//        return accountService.getData(account);
//    }
//
//    /**
//     * 删除记录方法调用
//     * @return
//     */
//    @RequestMapping(value = "delete")
//    public ResponseEntity delete(String id)
//    {
//
//        accountService.delete(id);
//
//        return new ResponseEntity(true);
//    }
//
//    /**
//     * 获取一条记录方法调用
//     * @return
//     */
//    @RequestMapping(value = "getOne")
//    public HashMap getOne(String id)
//    {
//        HashMap map = new HashMap();
//        map.put("data",accountService.getAccountById(id));
//        map.put("success",true);
//        return map;
//    }
//
//    /**
//     * 获取账号角色
//     * @return List
//     */
//    @RequestMapping(value = "getRole")
//    public List<Role> getRole(String id)
//    {
//        return accountService.getRole(id);
//    }
//
//    /**
//     * 设置账号角色
//     * @return ResponseEntity
//     */
//    @RequestMapping(value = "setRole")
//    public ResponseEntity setRole(String id,String name) throws Exception {
//        accountService.setRole(id,name);
//        return new ResponseEntity(true);
//    }
//
//    /**
//     * 移除账号角色
//     * @return ResponseEntity
//     */
//    @RequestMapping(value = "removeRole")
//    public ResponseEntity removeRole(String id,String name) throws Exception {
//        accountService.removeRole(id,name);
//        return new ResponseEntity(true);
//    }
}
