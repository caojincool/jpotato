package com.lemsun.web.manager.controller.iservice;

import com.lemsun.auth.AccountException;
import com.lemsun.auth.AccountManager;
import com.lemsun.auth.BaseAccount;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.IAccount;
import com.lemsun.core.LemsunException;
import com.lemsun.core.PlateformException;
import com.lemsun.core.PlateformInstance;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.core.service.IPlateformInstanceService;
import com.lemsun.web.WebSession;
import com.lemsun.web.manager.controller.model.account.InterfaceAccountLogin;
import com.lemsun.web.model.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * 针对接口通道的接口
 * User: Xudong
 * Date: 13-1-22
 * Time: 上午9:58
 */
@Controller("interfaceAccountController")
@RequestMapping("/interface/{plateform}/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IPlateformInstanceService plateformInstanceService;

    @Autowired
    private JsonObjectMapper mapper;


    /**
     * 创建新的账号
     *
     * @param plateform
     * @param request
     * @return
     */
    @RequestMapping("create")
    public ResponseEntity<IAccount> create(@PathVariable("plateform") String plateform,
                                           HttpServletRequest request,
                                           String account) throws Exception {

        IAccount acc = mapper.readValue(account, BaseAccount.class);

        if (acc == null || StringUtils.isEmpty(acc.getAccount())) throw new LemsunException("创建账号必须要传入用户名");

        accountService.save(acc);

        IAccount saveAcc = accountService.get(acc.getId());

        return new ResponseEntity<>(saveAcc);
    }


    /**
     * 通过账号ID删除账号
     *
     * @param plateform
     * @param account
     * @return
     */
    @RequestMapping("delete")
    public ResponseEntity<String> delete(@PathVariable("plateform") String plateform, String account) {
        IAccount acc = accountService.get(account);

        if (acc == null) throw new LemsunException("不存在的账号");

        accountService.deleteAccountById(account);

        return new ResponseEntity<>("OK");
    }

    /**
     * 更新账号对象
     *
     * @param plateform
     * @param account
     * @return
     * @throws IOException
     */
    @RequestMapping("update")
    public ResponseEntity<String> update(@PathVariable("plateform") String plateform, String account) throws IOException {
        IAccount acc = mapper.readValue(account, BaseAccount.class);
        accountService.updateAccount(acc);

        return new ResponseEntity<>("OK");
    }


    @RequestMapping("resetPwd")
    public ResponseEntity<String> resetPassword(String account){
        IAccount account1=accountService.getByAccount(account);
        accountService.resetPassword(account1.getId());

        return new ResponseEntity<>("OK");
    }

    @RequestMapping("addAccount")
    public ResponseEntity<String> addAccount(BaseAccount account){
        ResponseEntity<String> responseEntity=new ResponseEntity<>();
        try {
            accountService.save(account);
        }catch (AccountException e){
            responseEntity.setSuccess(false);
            responseEntity.setMessage("新增账户出现以下异常:"+e.getMessage());
        }
        responseEntity.setSuccess(true);
        responseEntity.setMessage("OK");
        return responseEntity;
    }


    @RequestMapping("changepwd")
    public ResponseEntity<String> changepwd(String account,String op,String np){

        accountService.changePassword(account,op,np);
        return new ResponseEntity<>("OK");
    }
    /**
     * 使用账号获取账号对象
     *
     * @param account
     * @return
     */
    @RequestMapping("get")
    public ResponseEntity<IAccount> get(String account) {
        IAccount acc = accountService.getAccountByAccount(account);
        return new ResponseEntity<>(acc);
    }

    @RequestMapping("login")
    public ResponseEntity<IAccount> login(
            InterfaceAccountLogin login,
            HttpServletRequest request,
            HttpSession httpSession,
            @PathVariable("plateform") String plateform) throws Exception {


        PlateformInstance instance = plateformInstanceService.getPlatefrmByToken(plateform);

        if (instance == null) {
            throw PlateformException.NotPlateformException;
        }

        AccountManager manager = null;
        if (StringUtils.isNotEmpty(login.getActoken())) {
            //使用用户票据登录
            IAccount account = accountService.getByToken(login.getActoken());
            if (account == null) {
                throw AccountException.UnAccount;
            }
            WebSession session = new WebSession(request, account, instance);
            manager = accountService.doLogin(session, login.getActoken());
        } else {
            IAccount account = accountService.getByAccount(login.getUsername());

            if (account == null) {
                throw AccountException.UnAccount;
            }

            WebSession session = new WebSession(request, account, instance);

            manager = accountService.doLogin(session, login.getUsername(), login.getPassword());
        }


        httpSession.setAttribute(AccountManager.class.getName(), manager);
        httpSession.setAttribute("account", manager.getAccount());

        return new ResponseEntity<>(manager.getAccount());
    }

    /**
     * 设置用户默认的操作日期
     *
     * @param plateform
     * @param adate
     * @return
     */
    @RequestMapping("adate")
    public ResponseEntity<Date> setCurrentAdate(@PathVariable("plateform") String plateform, String adate) throws ParseException {
        AccountManager manager = (AccountManager) accountService.getCurrentAccountManager();
        manager.getConfig().setDefaultActionDate(DateUtils.parseDate(adate, "yyyyMMdd"));
        return new ResponseEntity<>(accountService.getCurrentAdate());
    }

}
