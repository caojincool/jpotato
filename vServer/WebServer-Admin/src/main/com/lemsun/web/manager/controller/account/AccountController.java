package com.lemsun.web.manager.controller.account;

import com.lemsun.auth.*;
import com.lemsun.web.manager.controller.model.query.AccountQuery;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.IAccount;
import com.lemsun.core.auth.AuthenticationException;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.account.AccountCreateModel;
import com.lemsun.web.manager.controller.model.account.ExpandAccountModel;
import com.lemsun.web.manager.controller.model.role.BasePermission;
import com.lemsun.web.manager.controller.model.role.BaseRoleCreateModel;
import com.lemsun.web.model.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * User: Xudong
 * Date: 13-1-7
 * Time: 下午2:37
 * 账号角色管理
 */
@Controller
@RequestMapping("account/*")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    JsonObjectMapper mapper;

    private final static Logger log = LoggerFactory.getLogger(AccountController.class);
    private String idvalue = "";

    /**
     * 显示
     * 所有账户视图
     */
    @RequestMapping("view")
    public String view(HttpServletRequest request) {
        request.getSession().setAttribute("head",7);
        request.getSession().setAttribute("left",1);
        return "account/main";
    }

    /**
     * 获取账号数据
     */
    @RequestMapping("list/get")
    public ResponseEntity getAccountList(AccountQuery accountQuery) {
        Page<IAccount> data = accountService.getPageData(accountQuery);
        ResponseEntity<Page<IAccount>> entity = new ResponseEntity<>(data);
        entity.setTotalCount(data.getTotalElements());
        return entity;
    }

    /**
     * 创建账户
     * 之基本信息视图
     */
    @RequestMapping(value = "create/basicInfo", method = RequestMethod.GET)
    public String createBasicInfoView(HttpServletRequest request) {
        return "account/create/basicInfo";
    }

    /**
     * 处理
     * 创建账户
     * 之基本信息
     */
    @RequestMapping(value = "create/access", method = RequestMethod.POST)
    public ModelAndView doCreateBasicInfo(AccountCreateModel model, HttpServletRequest request) {

        BaseAccount account = model.createAccountModel();

        try {
            accountService.save(account);
        } catch (AccountException aex) {
            if (aex == AccountException.ExistAccount) {
                //对应异常处理
            }
        }

        ModelAndView view = new ModelAndView("account/create/access");
        view.addObject("baseAccount", account.getAccount());
        return view;
    }

    /**
     * 扩展账户信息
     * 之更新账户头像视图
     */
    @RequestMapping(value = "expand/avatar", method = RequestMethod.POST)
    public ModelAndView expandAvatarView(String account, HttpServletRequest request) throws UnsupportedEncodingException {

        IAccount iAccount = null;
        try {
            iAccount = accountService.getByAccount(account);
        } catch (AccountException e) {
            //异常处理

        }
        request.getSession().setAttribute("baseAccount", iAccount);

        //设置头像上传控件需要的基本属性
        String local = PrepareModelInteceptor.getRootPath();
        String localhost = local.substring(0, local.length() - 1);
        String EncodeLocalhost = URLEncoder.encode(localhost, "UTF-8");
        String avatarFlashParam = localhost + "/resource/images/common/camera.swf?nt=1&inajax=1&appid=1&input=" + iAccount.getAccount() + "&ucapi=" + EncodeLocalhost + "/account/expand/doUploadFace";

        ModelAndView view = new ModelAndView("account/expand/avatar");
        view.addObject("accountName", iAccount.getAccount());
        view.addObject("localhost", localhost);
        view.addObject("avatarFlashParam", avatarFlashParam);

        return view;
    }

    /**
     * 处理取消扩展信息
     */
    @RequestMapping(value = "cancelExpand")
    public String doCancelExpand(String account, HttpServletRequest requeste) {

        return "redirect:" + PrepareModelInteceptor.getRootPath() + "index?current=account";
    }

    /**
     * 扩展账户信息
     * 之上传头像处理
     */
    @RequestMapping(value = "expand/doExpandAvatar", method = RequestMethod.POST)
    public String doExpandAvatar(@RequestParam(value = "upload", required = false) MultipartFile imgFile,
                                 HttpServletRequest request) throws Exception {

        return "redirect:contact";
    }

    /**
     * 扩展账户信息
     * 之联系方式视图
     */
    @RequestMapping(value = "expand/contact", method = RequestMethod.GET)
    public String expandContactView(HttpServletRequest request) {

        return checkSessionAndReturnView(request, "account/expand/contact");
    }

    /**
     * 处理
     * 扩展账户之联系方式
     */
    @RequestMapping(value = "expand/doContact", method = RequestMethod.POST)
    public String doExpandContact(HttpServletRequest request, ExpandAccountModel createModel) throws Exception {

        BaseAccount account = (BaseAccount) request.getSession().getAttribute("baseAccount");

        createModel.setAccountId(account.getId());
        request.getSession().setAttribute("expandAccount", createModel);
        return "redirect:roles";
    }

    /**
     * 扩展账户
     * 之选择角色视图
     */
    @RequestMapping(value = "expand/roles", method = RequestMethod.GET)
    public String expandRoles(HttpServletRequest request) {
        return checkSessionAndReturnView(request, "account/expand/roles");
    }

    /**
     * 处理
     * 创建账户
     * 之选择角色
     */
    @RequestMapping(value = "expand/doRoles", method = RequestMethod.POST)
    public ResponseEntity<String> doExpandRoles(HttpServletRequest request) throws IOException {

        AccountCreateModel acm = mapper.readValue(request.getReader(), AccountCreateModel.class);
        //获取session值
        BaseAccount model = (BaseAccount) request.getSession().getAttribute("baseAccount");
        if (acm.getRoles().length > 0)
            model.setRoles(acm.getRoles());

        return new ResponseEntity<>("success");
    }

    /**
     * 扩展账户信息
     * 之选择权限视图
     */
    @RequestMapping(value = "expand/permissions", method = RequestMethod.GET)
    public String expandPermissionsView(HttpServletRequest request) {

        return checkSessionAndReturnView(request, "account/expand/permissions");
    }

    /**
     * 处理
     * 扩展账户
     * 之选择权限
     */
    @RequestMapping(value = "expand/doPermissions", method = RequestMethod.POST)
    public ResponseEntity<String> doExpandPermissions(HttpServletRequest request) throws IOException {

        //封装权限 Set<PermissionKey>
        Set<PermissionKey> pks = new HashSet<>();
        BaseRoleCreateModel model = mapper.readValue(request.getReader(), BaseRoleCreateModel.class);

        for (BasePermission pk : model.getPermissions()) {
            pks.add(pk.createPermissionKey());
        }

        request.getSession().setAttribute("pks", pks);

        return new ResponseEntity<>("success");
    }

    /**
     * 扩展账户信息完成视图
     */
    @RequestMapping(value = "expand/finish", method = RequestMethod.GET)
    public String expandFinish(HttpServletRequest request) {

        return checkSessionAndReturnView(request, "account/expand/finish");
    }

    /**
     * 处理(提交更新)
     * 完成扩展信息
     */
    @RequestMapping(value = "expand/doFinish", method = RequestMethod.POST)
    public String doExpandFinish(HttpServletRequest request) {

        //获取账户基本信息
        BaseAccount account = (BaseAccount) request.getSession().getAttribute("baseAccount");
        //获取账户扩展信息
        ExpandAccount expandAccount = ((ExpandAccountModel) request.getSession().getAttribute("expandAccount")).getExpandAccount();

        List<ExpandAccount> expandAccounts = new LinkedList<>();
        expandAccounts.add(expandAccount);

        //获取权限信息
        Set<PermissionKey> pks = (Set<PermissionKey>) request.getSession().getAttribute("pks");

        try {
            //accountService.updateAccount(account);
            accountService.updateExpand(account, expandAccounts, null, pks);
            //accountService.insertAccountPermissions(account, pks);
        } catch (AuthenticationException e) {
            if (e == AuthenticationException.AccountEx) {
                //异常处理
            }
        }

        request.getSession().removeAttribute("baseAccount");
        request.getSession().removeAttribute("pks");
        return "redirect:" + PrepareModelInteceptor.getRootPath() + "account/view";
    }

    /**
     * 检查session是否存在并根据session返回视图
     */
    private String checkSessionAndReturnView(HttpServletRequest request, String view) {
        if (request.getSession().getAttribute("baseAccount") == null)
            return "redirect:" + PrepareModelInteceptor.getRootPath() + "index?current=account/view";
        else
            return view;
    }

    /**
     * 显示
     * 账户详细信息视图
     */
    @RequestMapping("accountInfo")
    public ModelAndView accountView(String accountId) {
        BaseAccount baseAccount = null;
        ModelAndView view = null;

        try {
            baseAccount = accountService.getAccountByAccountId(accountId);
        } catch (AccountException e) {
            //To change body of catch statement use File | Settings | File Templates.
        }

        if (baseAccount != null) {
            view = new ModelAndView("account/accountInfo");
            //
            try {
                ExpandAccount expandAccounts = accountService.getAccountExpand(baseAccount);
                view.addObject("expandAccounts", expandAccounts);
            } catch (AccountException e) {
                //To change body of catch statement use File | Settings | File Templates.
            }
            view.addObject("baseAccount", baseAccount);
            view.addObject("isDelete", false);
        } else {
            view = new ModelAndView("redirect:" + PrepareModelInteceptor.getRootPath() + "index?current=account/view");
        }
        return view;
    }

    /**
     * 确认删除帐号视图
     */
    @RequestMapping("list/del")
    public ModelAndView delAccountByIdView(String accountId) {
        BaseAccount baseAccount = null;
        try {
            baseAccount = accountService.getAccountByAccountId(accountId);
        } catch (AccountException e) {
            //To change body of catch statement use File | Settings | File Templates.
        }
        idvalue = baseAccount.getId();
        ModelAndView view = new ModelAndView("account/delete");
        view.addObject("baseAccount", baseAccount);
        view.addObject("isDelete", true);
        return view;
    }

    /**
     * 处理
     * 确认删除帐号
     */
    @RequestMapping("list/enterDelete")
    public ModelAndView doDelAccountById() {
        try {
            accountService.deleteAccountById(idvalue);
        } catch (AccountException e) {
            //To change body of catch statement use File | Settings | File Templates.
        }
        ModelAndView view = new ModelAndView("redirect:" + PrepareModelInteceptor.getRootPath() + "account/view");
        view.addObject("baseAccount", idvalue);
        return view;
    }

    /**
     * 重置密码
     * @param accountId
     * @return
     * @throws Exception
     */
    @RequestMapping("list/resetPassword")
    public ModelAndView resetPassword(String accountId)  {
        try {
            accountService.resetPassword(accountId);
        } catch (AccountException e) {
            //return new ResponseEntity<>("fail");
        }
        return new ModelAndView("redirect:"+PrepareModelInteceptor.getRootPath()+"account/view");
    }

    @RequestMapping(value = "edit",method = RequestMethod.GET)
    public ModelAndView edit(String lastUrl,String account,HttpServletRequest request){
        BaseAccount baseAccount = null;
        ExpandAccount expandAccount=null;
        try {
            baseAccount = accountService.getAccountByAccountId(account);
            expandAccount=accountService.getAccountExpand(baseAccount);
        } catch (AccountException e) {
            //To change body of catch statement use File | Settings | File Templates.
        }
        idvalue = baseAccount.getId();
        ModelAndView view = new ModelAndView("account/edit");
        view.addObject("baseAccount", baseAccount);
        view.addObject("expandAccount", expandAccount);
        if(lastUrl==null||lastUrl.equals("")){
            lastUrl="/index";
        }
        view.addObject("lastUrl", lastUrl);
        return view;
    }

    @RequestMapping(value = "{account}/edit",method = RequestMethod.POST)
    public ModelAndView edit(HttpServletRequest request,@PathVariable String account){
        String lastUrl=request.getParameter("lastUrl");
        ModelAndView view=new ModelAndView("redirect:"+lastUrl);

        String showName=request.getParameter("showName");
        String email=request.getParameter("email");
        String mobile=request.getParameter("mobile");

        ExpandAccount expandAccount=null;

        try {
            BaseAccount baseAccount = (BaseAccount)accountService.getByAccount(account);

            if(accountService.getAccountExpand(baseAccount)==null)
                expandAccount=new ExpandAccount(baseAccount.getId());
            else
                expandAccount=accountService.getAccountExpand(baseAccount);

            baseAccount.setShowName(showName);
            expandAccount.setEmail(email);
            expandAccount.setMobile(mobile);

            accountService.updateAccount(baseAccount);
            accountService.updateExpand(expandAccount);
            HttpSession session= request.getSession();
            BaseAccount currAccount=(BaseAccount)session.getAttribute("account");
            if(currAccount.getId().equalsIgnoreCase(baseAccount.getId())){//判断修改是否当前用户
                session.setAttribute("account", baseAccount);
            }
        } catch (AccountException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (AuthenticationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return view;
    }

    /**
     * 获取用户头像
     * @param accountId
     * @param size
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("{accountId}/avatar/{size}")
    public void getAvatar(
            @PathVariable String accountId,
            @PathVariable  int size,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        InputStream in = accountService.getAvatar(accountId, size);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("image/jpeg");
        OutputStream stream = response.getOutputStream();
        int b;
        while ((b = in.read()) != -1) {
            stream.write(b);
        }
        stream.flush();
    }
}
