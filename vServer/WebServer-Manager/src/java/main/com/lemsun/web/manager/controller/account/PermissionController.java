package com.lemsun.web.manager.controller.account;

import com.lemsun.auth.Permission;
import com.lemsun.auth.service.IPermissionService;
import com.lemsun.web.manager.model.PermissionTreeStore;
import com.lemsun.web.manager.model.component.NavigationTreeStore;
import com.lemsun.web.model.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-22
 * Time: 下午5:14
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("permission/")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping(value = {"permissions"})
    public String root()
    {
        return "admin/account/permission";
    }

    @ResponseBody
    @RequestMapping("getData")
    public List<PermissionTreeStore> getData()
    {
        Permission permission= permissionService.getRoot();

        //return (new PermissionTreeStore()).convertPermission(permission, true);
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity addPermission(String name,String remark,String parentpid)
    {
        ResponseEntity responseEntity=new ResponseEntity();

        responseEntity.setSuccess(true);
        return responseEntity;
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity updatePermission(String name,String remark,String pid)
    {
        ResponseEntity responseEntity=new ResponseEntity();
        Permission permission= permissionService.getPermissionByPid(pid);
        if(permission==null)
        {
            responseEntity.setSuccess(false);
            return responseEntity;
        }

        permissionService.updatePermission(permission);
        responseEntity.setSuccess(true);
        return responseEntity;
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public void deletePermission(String pid)
    {
        permissionService.deletePermission(pid);
    }

    /**
     * 重置所有权限
     * @return
     */
    @RequestMapping(value = "resetPermission")
    public ModelAndView resetPermission()
    {
        permissionService.resetPermission();
        return new ModelAndView("redirect:/permission/permissions");
    }
}
