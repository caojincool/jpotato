package com.lemsun.web.manager.controller.component;

import com.lemsun.core.SpringContextUtil;
import com.lemsun.core.service.IResourceService;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.resource.FileResource;
import com.lemsun.data.resource.ImageResource;
import com.lemsun.data.service.IDbService;
import com.lemsun.data.service.IFileService;
import com.lemsun.data.tables.TableGroupResource;
import com.lemsun.form.*;
import com.lemsun.form.service.*;
import com.lemsun.web.manager.model.component.*;
import com.lemsun.web.model.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 组件创建
 * User: Lucklim
 * Date: 12-12-13
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("component/componentmain")
public class ComponentCreateController {
    @Autowired
    IDbService dbService;

    @Autowired
    IResourceService resourceService;

    @Autowired
    IScriptResourceService scriptResourceService;

    @Autowired
    IWebScriptResourceService webScriptResourceService;

    @Autowired
    IWpfScriptResourceService wpfScriptResourceService;

    @Autowired
    IWpfPageResourceService wpfPageResourceService;

    @Autowired
    IWebPageResourceService webPageResourceService;

    @Autowired
    IFileService fileService;

    private static final Logger log = LoggerFactory.getLogger(ComponentCreateController.class);


    @ResponseBody
    @RequestMapping("db/update")
    public ResponseEntity updateDb(DBModel dbModel)
    {
        ResponseEntity responseEntity=new ResponseEntity();
        try{
            dbService.updateConfig(dbModel.getUpdateDateModel(dbService));
            responseEntity.setSuccess(true);
        }catch (Exception ex)
        {
            responseEntity.setSuccess(false);
            responseEntity.setMessage(ex.getMessage());
        }
        return responseEntity;
    }

    @ResponseBody
    @RequestMapping("tablegroup/update")
    public ResponseEntity updateTableGroup(TableGroupModel tableGroupModel)
    {
        ResponseEntity responseEntity=new ResponseEntity();
        try
        {
            dbService.updateTableGroupResource(tableGroupModel.getUpdateDateModel(dbService));
            responseEntity.setSuccess(true);
        }catch (Exception ex)
        {
            responseEntity.setSuccess(false);
            responseEntity.setMessage(ex.getMessage());
        }
        return responseEntity;
    }

    @ResponseBody
    @RequestMapping("table/update")
    public ResponseEntity updateTable(TableModel tableModel)
    {
        ResponseEntity responseEntity=new ResponseEntity();
        try
        {
//            TableGroupResource tableGroupResource=dbService.getTableGroupResourceByPid(tableModel.getParentid());
//            dbService.addTableResource(tableModel.getDataModel(tableGroupResource.getDbConfig()));
            responseEntity.setSuccess(true);
        }catch (Exception ex)
        {
            responseEntity.setSuccess(false);
            responseEntity.setMessage(ex.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping("script/update")
    public ResponseEntity updateScript(String pid,String context,String remark)
    {
        ResponseEntity responseEntity=new ResponseEntity();
        try
        {

            ScriptResource scriptResource= resourceService.get(pid, ScriptResource.class);
            if(scriptResource==null)
                throw new Exception("找不到相应Script组件");
            scriptResource.setContext(context);
            scriptResource.setRemark(remark);
            scriptResourceService.updateScriptResource(scriptResource);
            responseEntity.setSuccess(true);
        }catch (Exception ex)
        {
            responseEntity.setSuccess(false);
            responseEntity.setMessage(ex.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping("webscript/update")
    public ResponseEntity updateWebScript(String pid,String context,String remark)
    {
        ResponseEntity responseEntity=new ResponseEntity();
        try
        {

            WebScriptResource scriptResource= resourceService.get(pid, WebScriptResource.class);
            if(scriptResource==null)
                throw new Exception("找不到相应Script组件");
            scriptResource.setContext(context);
            scriptResource.setRemark(remark);
            webScriptResourceService.updateWebScriptResource(scriptResource);
            responseEntity.setSuccess(true);
        }catch (Exception ex)
        {
            responseEntity.setSuccess(false);
            responseEntity.setMessage(ex.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping("wpfscript/update")
    public ResponseEntity updateWpfScript(String pid,String context,String remark)
    {
        ResponseEntity responseEntity=new ResponseEntity();
        try
        {

            WpfScriptResource scriptResource= resourceService.get(pid, WpfScriptResource.class);
            if(scriptResource==null)
                throw new Exception("找不到相应Script组件");
            scriptResource.setContext(context);
            scriptResource.setRemark(remark);
            wpfScriptResourceService.updateWpfScriptResource(scriptResource);
            responseEntity.setSuccess(true);
        }catch (Exception ex)
        {
            responseEntity.setSuccess(false);
            responseEntity.setMessage(ex.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping("webskin/getcontext")
    public String getWebSkin(String pid) throws Exception {
        WebPageResource webPageResource = webPageResourceService.get(pid);
        if(webPageResource==null)throw new Exception("找不到该WEB界面资源");
        return webPageResourceService.getResourceContext(webPageResource);
    }

    @RequestMapping("webskin/update")
    public ResponseEntity updateWebSkin(String pid,String context)
    {
        ResponseEntity responseEntity=new ResponseEntity();
        try
        {
            WebPageResource webPageResource = webPageResourceService.get(pid);
            if(webPageResource==null)throw new Exception("找不到该WEB界面资源");
            webPageResourceService.updateContext(webPageResource,context);
            responseEntity.setSuccess(true);
        }catch (Exception ex)
        {
            responseEntity.setSuccess(false);
            responseEntity.setMessage(ex.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping("wpfskin/getcontext")
    public String getWpfSkin(String pid) throws Exception {


        WpfPageResource wpfPageResource= wpfPageResourceService.get(pid);
        if(wpfPageResource==null)throw new Exception("找不到该WPF界面资源");
        return wpfPageResourceService.getResourceContext(wpfPageResource);
    }

    @RequestMapping("wpfskin/update")
    public ResponseEntity updateWpfSkin(String pid,String context)
    {
        ResponseEntity responseEntity=new ResponseEntity();
        try
        {
            WpfPageResource wpfPageResource= wpfPageResourceService.get(pid);
            if(wpfPageResource==null)throw new Exception("找不到该WPF界面资源");
            wpfPageResourceService.updateContext(wpfPageResource,context);
            responseEntity.setSuccess(true);
        }catch (Exception ex)
        {
            responseEntity.setSuccess(false);
            responseEntity.setMessage(ex.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping(value = "file/update",method = RequestMethod.POST)
    public ModelAndView updateFileResouce(String pid,String remark ,@RequestParam("file") MultipartFile  file) throws Exception {
        if(file==null)
            throw new Exception("没有上传的文件");
        FileResource fileResource= resourceService.get(pid, FileResource.class);
        fileResource.setRemark(remark);
        if(fileResource==null)
            throw new Exception("找不到要修改的文件资源");
        fileService.updateFile(fileResource,file);
        // return new ModelAndView("redirect:/component/componentmain/update?category="+fileResource.getCategory()+"&pid="+fileResource.getPid());
		return new ModelAndView("redirect:/component/componentmain/quanxian?category="+fileResource.getCategory()+"&pid="+fileResource.getPid());
    }

    @RequestMapping(value = "image/update",method = RequestMethod.POST)
    public ModelAndView updateImageResouce(String pid,String remark ,@RequestParam("file") MultipartFile  file) throws Exception {
        if(file==null)
            throw new Exception("没有上传的文件");
        ImageResource imageResource= resourceService.get(pid, ImageResource.class);
        imageResource.setRemark(remark);
        if(imageResource==null)
            throw new Exception("找不到要修改的文件资源");
        fileService.updateImage(imageResource, file);

		// 2013-1-4 gm update
        // return new ModelAndView("redirect:/component/componentmain/update?category="+imageResource.getCategory()+"&pid="+imageResource.getPid());
		return new ModelAndView("redirect:/component/componentmain/quanxian?category="+imageResource.getCategory()+"&pid="+imageResource.getPid());
    }
}
