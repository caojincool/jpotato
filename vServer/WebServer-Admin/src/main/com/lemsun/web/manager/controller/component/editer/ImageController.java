package com.lemsun.web.manager.controller.component.editer;

import com.lemsun.auth.BaseAccount;
import com.lemsun.core.*;
import com.lemsun.data.ImageResource;
import com.lemsun.data.service.ImageResourceService;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.entity.ResourceRequest;
import com.lemsun.web.manager.controller.model.query.ImageQuery;
import com.lemsun.web.model.view.ImageItemView;
import org.apache.commons.codec.binary.Base64;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-21
 * Time: 下午1:26
 * 图片资源操作类
 */
@Controller
@RequestMapping("component/image")
public class ImageController extends BaseController{


    @Autowired
    private ImageResourceService imageResourceService;


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView viewadd(HttpServletRequest request, String pid) throws Exception {
        LemsunResource resource = ((LemsunResource) request.getSession().
                getAttribute("resource"));

        ModelAndView view = new ModelAndView();

        if (resource == null)
            view.setViewName("redirect:" + PrepareModelInteceptor.getRootPath() + "component/main/create");
        else
            view.setViewName("component/add/Image");

        view.addObject("imageresource", resource);

        return view;
    }

    private ModelAndView upLoad(MultipartHttpServletRequest request,LemsunResource resource,String url) throws IOException {
        MultipartFile file=request.getFile("imagefile");
        String fileName=file.getOriginalFilename();
        String suffix=fileName.substring(fileName.lastIndexOf("."),fileName.length());
        LocalFileUploadConfig fileUploadConfig= Host.getInstance().getImageUploadConfig();

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
            ImageResource imageResource=new ImageResource(resource);
            imageResource.setImageName(fileName);
            imageResource.setImageSize(file.getSize());

            //todo:在更新组件内容(上传组件)的时候设置状态我觉得不妥.不过现在也没有多大影响
            imageResource.setState(ResourceState.RELEASE);
            imageResourceService.update(imageResource);
            imageResourceService.updateImage(imageResource,file.getInputStream());
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

    @RequestMapping(value = "doAdd", method = RequestMethod.POST)
    public ModelAndView doAdd(MultipartHttpServletRequest request,ImageResource ir) throws IOException {

        LemsunResource resource = ((LemsunResource) request.getSession().
                getAttribute("resource"));

        resource=resourceService.getBaseResource(resource.getPid());

        ModelAndView view=upLoad(request,resource,"component/add/Image");
        if(view==null)
            view=getModelAndView(resource.getPid(),  request);

        return view;
    }

    @RequestMapping(value = "{pid}/edit", method = RequestMethod.GET)
    public ModelAndView viewEdit(String type,HttpServletRequest request, @PathVariable String pid) throws Exception {
        ImageResource imageResource=imageResourceService.get(pid);

        ModelAndView view=new ModelAndView("component/editer/Image");
        view.addObject("imageresource", imageResource);
        view.addObject("type",type);
        return view;
    }

    @RequestMapping(value = "{pid}/edit", method = RequestMethod.POST)
    public ModelAndView doEdit(MultipartHttpServletRequest request,ImageResource ir) throws IOException {

        LemsunResource resource=resourceService.getBaseResource(ir.getPid());
        ModelAndView view=null;
        if(request.getFile("imagefile").getSize()>0)
            view = upLoad(request,resource,"component/editer/Image");
        else {
            ImageResource fileResource=resourceService.get(resource.getPid(),ImageResource.class);
            fileResource.setImageName(ir.getImageName());
            fileResource.setImageSize(ir.getImageSize());
            fileResource.setRemark(ir.getRemark());
            fileResource.setStrParams(ir.getStrParams());
            imageResourceService.update(fileResource);
        }

        if(view==null){
            ModelMap mmap = new ModelMap("success",true);
            view=new ModelAndView("redirect:"
                    + PrepareModelInteceptor.getRootPath()+"component/main/operatingResults",mmap);
        }
        return view;

    }

    @RequestMapping(value = "{pid}/getview")
    public void getImage(@PathVariable String pid,HttpServletResponse response) throws Exception {
        response.setContentType("image/JPEG");
        ServletOutputStream stream = response.getOutputStream();

        ImageResource resource=imageResourceService.get(pid);



        stream.write(imageResourceService.getImage(resource));

        stream.flush();
        stream.close();
    }

    /**
     * 显示组件视图
     */
    @RequestMapping("view")
    public ModelAndView view(HttpServletRequest request, ImageQuery query) throws IOException {
        return super.view(query);
    }

    @Override
    protected ComponentQueryHandler createComponentQueryHandler() throws IOException {
        ComponentQueryHandler handler= new ComponentQueryHandler(){

            @Override
            protected Map<String, Integer> createMap() {
                Map<String,Integer> map=new HashMap<>();
                map.put("head",1);
                map.put("left",21);
                return map;
            }
            @Override
            protected  Page<ImageResource> executeQueryComponent() {
                return   imageResourceService.getPageing(query);
            }

            @Override
            protected  Page<ImageItemView> afterRelatedQueryHandler() {
                List<ImageItemView> webskinItemViewList=new ArrayList<>(data.getSize());
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
                    ImageItemView webskinItemView=new ImageItemView(webskin,account);
                    webskinItemViewList.add(webskinItemView);

                }

                return  new PageImpl<>(webskinItemViewList, query, data.getTotalElements());
            }
        };
        return handler;
    }
    @RequestMapping("content/get")
    public void getImage(
            ResourceRequest request,
            HttpServletResponse response) throws IOException {
        String context = resourceService.getContent(request.getPid());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("image/jpeg");
        byte[] bytes= Base64.decodeBase64(context);
        OutputStream stream = response.getOutputStream();
        stream.write(bytes);
        stream.flush();
    }
    @Override
    void deleteResource(String pid) throws Exception {
        imageResourceService.delete(pid);
    }
}
