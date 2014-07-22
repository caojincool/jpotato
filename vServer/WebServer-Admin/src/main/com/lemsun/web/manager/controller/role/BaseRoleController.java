package com.lemsun.web.manager.controller.role;

import com.lemsun.auth.BaseAccount;
import com.lemsun.auth.BaseAccountRole;
import com.lemsun.auth.BaseRole;
import com.lemsun.auth.RoleException;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.auth.service.IBaseRoleService;
import com.lemsun.auth.service.IPermissionNodeService;
import com.lemsun.core.IAccount;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.query.RoleQuery;
import com.lemsun.web.manager.controller.model.role.BaseRoleAccounts;
import com.lemsun.web.manager.controller.model.role.BaseRoleCreateModel;
import com.lemsun.web.model.HeaderTitle;
import com.lemsun.web.model.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色管理控制器
 * User: dpyang
 * Date: 13-1-19
 * Time: 上午9:34
 */
@Service
@RequestMapping("role/*")
public class BaseRoleController {

    @Autowired
    IBaseRoleService service;

    @Autowired
    IPermissionNodeService pnService;

    @Autowired
    IAccountService accountService;

    @Autowired
    JsonObjectMapper mapper;

    private Logger log = LoggerFactory.getLogger(BaseRoleController.class);

    /**
     * 角色列表视图
     */
    @RequestMapping("view")
    public String getView(HttpServletRequest request) {
        request.getSession().setAttribute("head",7);
        request.getSession().setAttribute("left",3);
        return "role/main";
    }

    /**
     * 从数据库中获取角色列表
     */
    @RequestMapping(value = "list/get", method = RequestMethod.GET)
    public ResponseEntity getList(RoleQuery roleQuery) {
        Page<BaseRole> roles = service.getPageData(roleQuery);
        ResponseEntity<Page<BaseRole>> entity = new ResponseEntity<>(roles);
        entity.setTotalCount(roles.getTotalElements());
        return entity;
    }

    /**
     * 创建角色视图(第一步)
     *
     * @return 基本信息(角色名称, 角色说明, 是否内置角色)
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "role/create";
    }

    /**
     * 基本信息提交,接收基本信息放置在权限选择的隐藏表单
     */
    @RequestMapping(value = "premission/create", method = RequestMethod.POST)
    public ModelAndView create(BaseRoleCreateModel model, HttpServletRequest request) {
        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);

        BaseRole role = model.createBaseRole();
        ModelAndView view = new ModelAndView("/role/create_premission");
        view.addObject("model", role);
        return view;
    }

    /**
     * 接收权限键值对信息.
     */
    @RequestMapping(value = "select/permissions/create", method = RequestMethod.POST)
    public ResponseEntity<String> createSelectPermissions(HttpServletRequest request) throws Exception {

        if ((BaseRole) request.getSession().getAttribute("role") != null)
            request.getSession().removeAttribute("role");

        BaseRoleCreateModel model = mapper.readValue(request.getReader(), BaseRoleCreateModel.class);
        BaseRole baseRole = model.createRole();

        request.getSession().setAttribute("role", baseRole);

        return new ResponseEntity<>("success");
    }

    /**
     * 创建角色之选择用户
     */
    @RequestMapping(value = "select/account/create", method = RequestMethod.GET)
    public ModelAndView createSelectAccount(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("role/create_account");
        mav.addObject("roleid", ((BaseRole) request.getSession().getAttribute("role")).getName());

        return mav;
    }

    /**
     * 创建角色之处理选择的帐号选择 select/account/create
     */
    @RequestMapping(value = "select/account/create", method = RequestMethod.POST)
    public ResponseEntity<String> createSelectAccountAction(HttpServletRequest request) throws IOException {
        //清除原有session
        if ((BaseRoleAccounts) request.getSession().getAttribute("bras") != null)
            request.getSession().removeAttribute("bras");

        //获取选择的账户信息
        BaseRoleAccounts bras = mapper.readValue(request.getReader(), BaseRoleAccounts.class);
        request.getSession().setAttribute("bras", bras);

        return new ResponseEntity<>("success");
    }

    /**
     * 显示结束页面
     */
    @RequestMapping(value = "finash/create")
    public ModelAndView createFinash() {
        ModelAndView mav = new ModelAndView("role/create_finish");
        return mav;
    }
//
//    /**
//     * 角色创建完成返回首页
//     */
//    @RequestMapping(value = "ok/create", method = RequestMethod.POST)
//    public String createReturnIndex(HttpServletRequest request) throws Exception {
//
//        BaseRole model = (BaseRole) request.getSession().getAttribute("role");
//        BaseRoleAccounts bras = (BaseRoleAccounts) request.getSession().getAttribute("bras");
//
//        if (bras.getRas().size() > 0)
//            barService.saveListBaseAccountRole(bras.getRas());
//        service.create(model);
//
//        return "redirect:/role/view";
//    }
//
//    /**
//     * 角色详细信息
//     */
//    @RequestMapping(value = "detailed", method = RequestMethod.GET)
//    public ModelAndView roleDetailed(String rolename) throws Exception {
//
//        ModelAndView mav = new ModelAndView();
//
//        if (StringUtils.isEmpty(rolename)) {
//            mav.setViewName("redirect:/role/view");
//            return mav;
//        }
//
//        BaseRole role = service.getRole(rolename);
//
//        if (role == null) {
//            mav.setViewName("redirect:/role/view");
//            return mav;
//        }
//
//        mav.addObject("name", role.getName());
//        mav.addObject("describe", role.getDescribe());
//        mav.addObject("permissionKeys", mapper.writeValueAsString(role.getPermissions()));
//        mav.addObject("accounts", getAccountsByRole(role.getName()));
//
//        return mav;
//    }
//
//    /**
//     * 根据角色名称查询对应的帐号信息列表
//     */
//    private String getAccountsByRole(String e) throws Exception {
//        if (StringUtils.isEmpty(e))
//            return "";
//
//        List<BaseAccountRole> bars = barService.getAccountRoleByRole(e);
//        List<IAccount> accounts = new ArrayList<>(bars.size());
//
//        for (BaseAccountRole b : bars) {
//            accounts.add(accountService.getByAccount(b.getAccountId()));
//        }
//
//        return mapper.writeValueAsString(accounts);
//    }
//
//    /**
//     * 确认删除角色信息
//     */
//    @RequestMapping(value = "delete/{name}", method = RequestMethod.GET)
//    public ModelAndView delete(@PathVariable String name, HttpServletRequest request) throws Exception {
//        ModelAndView mav = new ModelAndView("role/deleteById");
//
//        if (StringUtils.isEmpty(name)) {
//            mav.setViewName("redirect:/role/view");
//            return mav;
//        }
//
//        BaseRole role = service.getRole(name);
//
//        if (role == null) {
//            mav.setViewName("redirect:/role/view");
//            return mav;
//        }
//
//        mav.addObject("name", role.getName());
//        mav.addObject("describe", role.getDescribe());
//        mav.addObject("permissionKeys", mapper.writeValueAsString(role.getPermissions()));
//        mav.addObject("accounts", getAccountsByRole(role.getName()));
//
//        return mav;
//    }
//
//    /**
//     * 删除信息
//     */
//    @RequestMapping(value = "delete",method = RequestMethod.POST)
//    public ResponseEntity<String> deleteok(String name,HttpServletRequest request) throws RoleException {
//        //根据角色名称删除角色
//        if (StringUtils.isEmpty(name))
//            throw RoleException.RoleisNull;
//
//        service.delete(name);
//        barService.delByRoleName(name);
//
//        return new ResponseEntity<>("success");
//    }
//
//    /**
//     * 编辑角色信息
//     */
//    @RequestMapping(value = "edit/{name}", method = RequestMethod.GET)
//    public ModelAndView edit(@PathVariable String name, HttpServletRequest request) throws Exception {
//        ModelAndView mav = new ModelAndView("role/edit");
//
//        if (StringUtils.isEmpty(name)) {
//            mav.setViewName("redirect:/role/view");
//            return mav;
//        }
//
//        BaseRole role = service.getRole(name);
//
//        if (role == null) {
//            mav.setViewName("redirect:/role/view");
//            return mav;
//        }
//
//        mav.addObject("name", role.getName());
//        mav.addObject("describe", role.getDescribe());
//        mav.addObject("permissionKeys", mapper.writeValueAsString(role.getPermissions()));
//        mav.addObject("accounts", getAccountsByRole(role.getName()));
//
//        return mav;
//    }

    /**
     * 处理编辑
     */
    @RequestMapping(value = "edited/{oldname}",method = RequestMethod.POST)
    public ResponseEntity<String> edited(@PathVariable String oldname, String name,String describe, HttpServletRequest request) throws IOException, RoleException {

        //BaseRoleCreateModel role=mapper.readValue(request.getReader(),BaseRoleCreateModel.class);

        //这里只是临时修改
        BaseRole temp=service.getRole(oldname);
        temp.setName(name);
        temp.setDescribe(describe);

        service.update(temp);
        return new ResponseEntity<>("success");
    }

//    /**
//     * 根据角色信息查询该角色的详细信息
//     */
//    @RequestMapping(value = "detailedByExt/{rolename}")
//    private ModelAndView roleDetailedByExt(@PathVariable String rolename) throws Exception {
//        ModelAndView mav = new ModelAndView("role/detailedByExt");
//
//        if (StringUtils.isEmpty(rolename)) {
//            mav.setViewName("redirect:/role/view");
//            return mav;
//        }
//
//        BaseRole role = service.getRole(rolename);
//
//        if (role == null) {
//            mav.setViewName("redirect:/role/view");
//            return mav;
//        }
//
//        mav.addObject("name", role.getName());
//        mav.addObject("describe", role.getDescribe());
//        mav.addObject("permissionKeys", mapper.writeValueAsString(role.getPermissions()));
//        mav.addObject("accounts", getAccountsByRole(role.getName()));
//
//        return mav;
//    }

}
