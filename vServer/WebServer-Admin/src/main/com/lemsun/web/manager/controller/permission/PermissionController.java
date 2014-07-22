package com.lemsun.web.manager.controller.permission;

import com.lemsun.auth.PermissionNode;
import com.lemsun.auth.PermissionNodeExcetion;
import com.lemsun.auth.service.IPermissionNodeService;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.permissionNode.PermissionNodeTreeStore;
import com.lemsun.web.model.HeaderTitle;
import com.lemsun.web.model.ResponseEntity;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 权限管理控制器
 * User: dpyang
 * Date: 13-1-22
 * Time: 上午8:57
 */
@Service
@RequestMapping("permission/*")
public class PermissionController {

    @Autowired
    private IPermissionNodeService service;

    /**
     * 默认权限浏览视图
     * @return 权限浏览视图
     */
    @RequestMapping(value = "view")
    public String view(HttpServletRequest request){
        request.getSession().setAttribute("head",7);
        request.getSession().setAttribute("left",5);
        return  "permission/main";
    }
    @RequestMapping(value = "/viewiframe")
    public String viewiframe() {
        return "permission/body";
    }
    /**
     * 获取根节点下的信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "list/get")
    public List<PermissionNodeTreeStore> getRoot(){
        PermissionNode permissionNode=service.getRoot();
        List<PermissionNodeTreeStore> list=(new PermissionNodeTreeStore()).convertPermissionNodeTreeStore(permissionNode,true);
        return list;
    }

    /**
     * 修改权限信息
     * @param id 权限ID
     * @param request
     */
    @RequestMapping(value = "modification",method= RequestMethod.GET)
    public ModelAndView startModification(String id,HttpServletRequest request) throws PermissionNodeExcetion {
        if (StringUtils.isEmpty(id)){
           throw PermissionNodeExcetion.PIdisNull;
        }

        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);

        ObjectId oid=new ObjectId(id);
        PermissionNode permissionNode=service.getPermissionNode(oid);

        ModelAndView mav=new ModelAndView("/permission/modification_start");
        mav.addObject("model",permissionNode);

        return  mav;
    }

    /**
     * 保存权限模型
     * @param model 权限模型
     * @param request
     * @return 完成页面
     * @throws PermissionNodeExcetion
     */
    @RequestMapping(value = "modification/finish",method= RequestMethod.POST)
    public ModelAndView startModification(PermissionNode model,HttpServletRequest request) throws PermissionNodeExcetion {
        if (model==null){
            throw PermissionNodeExcetion.PIdisNull;
        }

        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);

        //设置父级节点
        model.setParent(service.getPermissionNode(model.getId()).getParent());
        service.save(model);
        ModelAndView mav=new ModelAndView("/permission/modification_finish");
        return  mav;
    }

    /**
     * 修改完成
     * @return 返回主页
     */
    @RequestMapping(value = "modification/ok",method = RequestMethod.POST)
    public String finishModification(){
        return "redirect:/index";
    }

    /**
     * 通过url请求返回图像的字节流
     */
    @RequestMapping("icon/{cateogry}")
    public void getIcon(@PathVariable("cateogry") String cateogry,
                        HttpServletRequest request,
                        HttpServletResponse response) throws IOException {

        if(StringUtils.isEmpty(cateogry)) {
            cateogry = "";
        }

        String fileName = FilenameUtils.concat(request.getSession().getServletContext().getRealPath("/"),
                "resource\\icons\\auth\\"
                + cateogry.toUpperCase().trim() + ".png");

        File file = new File(fileName);

        //判断文件是否存在如果不存在就返回默认图标
        if(!(file.exists() && file.canRead())) {
            file = new File(FilenameUtils.concat(request.getSession().getServletContext().getRealPath("/"),
                     "resource/icons/auth/root.png"));
        }

        FileInputStream inputStream = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        int length = inputStream.read(data);
        inputStream.close();

        response.setContentType("image/png");

        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
    }
}
