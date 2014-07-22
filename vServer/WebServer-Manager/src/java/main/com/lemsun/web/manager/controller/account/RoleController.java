//package com.lemsun.web.manager.controller.account;
//
////import com.lemsun.auth.Role;
////import com.lemsun.auth.SimplePermission;
////import com.lemsun.auth.service.IRoleService;
//
//import com.lemsun.web.model.ResponseEntity;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//
//import java.util.List;
//
///**
// * Created with IntelliJ IDEA.
// * User: Administrator
// * Date: 12-11-21
// * Time: 下午5:12
// * To change this template use File | Settings | File Templates.
// */
//
//@Controller
//@RequestMapping("role/")
//public class RoleController {
//    private final static Logger log = LoggerFactory.getLogger(RoleController.class);
//
//    @Autowired
//    //private IRoleService roleService;
//
//    /**
//     * 控制页面跳转
//     * @return
//     */
//    @RequestMapping(value = {"roles"})
//    public String role()
//    {
//        log.debug("角色信息跳转");
//        return "admin/account/role";
//    }
//
//    /**
//     * 新增方法调用
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "save")
//    public ResponseEntity save(Role role) throws Exception {
//
//        roleService.save(role);
//
//        return new ResponseEntity(true);
//    }
//
//    /**
//     * 查询方法调用
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "getData")
//    public Page<Role> getData(Role role)
//    {
//        return roleService.getData(role);
//
//    }
//
//    /**
//     * 删除记录方法调用
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "delete")
//    public ResponseEntity delete(String name)
//    {
//
//        roleService.delete(name);
//
//        return new ResponseEntity(true);
//    }
//
//    /**
//     * 获取角色权限
//     * @return List
//     */
//    @ResponseBody
//    @RequestMapping(value = "getPermission")
//    public List<SimplePermission> getPermision(String name)
//    {
//        return roleService.getPermission(name);
//    }
//
//    /**
//     * 设置角色权限
//     * @return ResponseEntity
//     */
//    @ResponseBody
//    @RequestMapping(value = "setPermission")
//    public ResponseEntity getPermision(String roleName,SimplePermission sp) throws Exception {
//        roleService.setPermission(roleName,sp);
//        return new ResponseEntity(true);
//    }
//
//    /**
//     * 移除角色权限
//     * @return ResponseEntity
//     */
//    @ResponseBody
//    @RequestMapping(value = "removePermission")
//    public ResponseEntity removePermision(String roleName,String id) throws Exception {
//        roleService.removePermission(roleName,id);
//        return new ResponseEntity(true);
//    }
//
//    /**
//     * 获取账号未获取的角色权限
//     * @return ResponseEntity
//     */
//    @ResponseBody
//    @RequestMapping(value = "getUnassignRole")
//    public List<Role> getUnassignRole(String id){
//        return roleService.getUnassignRole(id);
//    }
//}
