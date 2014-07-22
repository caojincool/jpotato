package com.lemsun.web.manager.controller.component.editer;

import com.lemsun.auth.BaseAccount;
import com.lemsun.core.*;
import com.lemsun.data.FileResource;
import com.lemsun.data.service.IFileResourceService;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.query.ResourceQuery;
import com.lemsun.web.model.view.FileItemView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.terracotta.quartz.wrappers.FiredTrigger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-23
 * Time: 上午11:01
 */
@Controller
@RequestMapping("component/resource")
public class ResourceController extends BaseController{

    @Autowired
   private IFileResourceService fileService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView viewadd(HttpServletRequest request, String pid) throws Exception {
        LemsunResource resource = ((LemsunResource) request.getSession().
                getAttribute("resource"));

        ModelAndView view = new ModelAndView();

        if (resource == null)
            view.setViewName("redirect:" + PrepareModelInteceptor.getRootPath() + "component/main/create");
        else
            view.setViewName("component/add/Resource");

        view.addObject("fileresource", resource);

        return view;
    }

    @RequestMapping(value = "doAdd", method = RequestMethod.POST)
    public ModelAndView doadd(MultipartHttpServletRequest request) throws Exception {

        LemsunResource resource = ((LemsunResource) request.getSession().
                getAttribute("resource"));

        resource=resourceService.getBaseResource(resource.getPid());
        ModelAndView view = upLoad(request,resource,"component/add/Resource");

        if (view==null)
            view=getModelAndView(resource.getPid(),  request);

        return view;
    }

    private ModelAndView upLoad(MultipartHttpServletRequest request,LemsunResource resource,String url) throws IOException {
        MultipartFile file=request.getFile("resourcefile");
        String fileName=file.getOriginalFilename();
        String suffix=fileName.substring(fileName.lastIndexOf("."),fileName.length());
        LocalFileUploadConfig fileUploadConfig= Host.getInstance().getFileUploadConfig();

        if (file.getSize()<=0){
            return errorView(resource,url,"您尚未选择上传的文件资源.");
        }else if(file.getSize()>fileUploadConfig.getLimitSize()*1024*1024){
            return errorView(resource,url,"您上传的文件资源超过了"+fileUploadConfig.getLimitSize()+"M");
        }else if(fileUploadConfig.getFileTypes()!=null){
            for(String t:fileUploadConfig.getFileTypes()){
                if(suffix.equalsIgnoreCase(t)){
                    return errorView(resource,url,"您不能上传"+suffix+"类型的文件!");
                }
            }
            return null;
        }else{
            FileResource fileResource=new FileResource(resource);
            fileResource.setFileName(fileName);
            fileResource.setFileSize(file.getSize());

            //todo:在更新组件内容(上传组件)的时候设置状态我觉得不妥.不过现在也没有多大影响
            fileResource.setState(ResourceState.RELEASE);
            fileService.update(fileResource);
            fileService.updateFile(fileResource,file.getInputStream());
            return null;
        }
    }

    private ModelAndView errorView(LemsunResource resource,String url
                                   ,String errorMessage){

        ModelAndView view=new ModelAndView(url);
        view.addObject("fileresource", resource);
        view.addObject("error",errorMessage);

        return view;
    }

    @RequestMapping(value = "{pid}/edit", method = RequestMethod.GET)
    public ModelAndView view(String type,@PathVariable String pid) throws Exception {

        FileResource fileResource=fileService.get(pid);

        ModelAndView view=new ModelAndView("component/editer/Resource");
        view.addObject("fileresource", fileResource);
        view.addObject("type",type);
        return view;
    }

    @RequestMapping(value = "{pid}/edit", method = RequestMethod.POST)
    public ModelAndView doEdit(MultipartHttpServletRequest request,FileResource fr) throws IOException {

        LemsunResource resource=resourceService.getBaseResource(fr.getPid());
        ModelAndView view=null;

        if(request.getFile("resourcefile").getSize()>0) {
            view = upLoad(request, resource, "component/editer/Resource");
        }else{
            FileResource fileResource=resourceService.get(resource.getPid(),FileResource.class);
            fileResource.setFileName(fr.getFileName());
            fileResource.setFileSize(fr.getFileSize());
            fileResource.setRemark(fr.getRemark());
            fileResource.setStrParams(fr.getStrParams());
            fileService.update(fileResource);
        }


        if(view==null){
            ModelMap mmap = new ModelMap();
            mmap.put("success",true);
            view=new ModelAndView("redirect:"
                    + PrepareModelInteceptor.getRootPath()+"component/main/operatingResults",mmap);
        }
        return view;
    }

    @RequestMapping(value = "{pid}/down")
    public void down(@PathVariable String pid,HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        ServletOutputStream stream = response.getOutputStream();

        FileResource resource=fileService.get(pid);
        response.setHeader("Content-Disposition", "attachment; filename=" + resource.getFileName());
        stream.write(fileService.getFile(resource));
        stream.flush();
    }

    /**
     * 显示组件视图
     */
    @RequestMapping("view")
    public ModelAndView view(HttpServletRequest request, ResourceQuery query) throws IOException {
        return super.view(query);
    }
    @Override
    protected ComponentQueryHandler createComponentQueryHandler() throws IOException {
        ComponentQueryHandler handler= new ComponentQueryHandler(){

            @Override
            protected Map<String, Integer> createMap() {
                Map<String,Integer> map=new HashMap<>();
                map.put("head",1);
                map.put("left",22);
                return map;
            }
            @Override
            protected  Page<FileResource> executeQueryComponent() {
                return   fileService.getPageing(query);
            }

            @Override
            protected  Page<FileItemView> afterRelatedQueryHandler() {
                List<FileItemView> webskinItemViewList=new ArrayList<>(data.getSize());
                for(IResource webskin: data.getContent()){
                    BaseAccount account=null;
                    try{
                        account=accountService.getAccountByAccount(webskin.getCreateUser());
                    }catch (Exception e){
                        logger.info("异常数据",e.getMessage());
                    }
                    if(account==null){
                        account=new BaseAccount();
                    }
                    FileItemView webskinItemView=new FileItemView(webskin,account);
                    webskinItemViewList.add(webskinItemView);

                }

                return  new PageImpl<>(webskinItemViewList, query, data.getTotalElements());
            }
        };
        return handler;
    }
    @Override
    protected IResource getIResource(String pid) throws Exception {
        return fileService.get(pid);
    }

    @Override
    void deleteResource(String pid) throws Exception {
        fileService.delete(pid);
    }
}
