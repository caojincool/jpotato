package com.lemsun.web.manager.controller.component.editer;

import com.lemsun.auth.BaseAccount;
import com.lemsun.core.*;

import com.lemsun.core.service.IResourceService;
import com.lemsun.form.FormResource;

import com.lemsun.form.WpfPageResource;
import com.lemsun.form.service.IWpfPageResourceService;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.component.WpfPageResourceModel;
import com.lemsun.web.manager.controller.model.query.WpfSkinQuery;
import com.lemsun.web.model.ResponseEntity;
import com.lemsun.web.model.view.WpfskinItemView;
import com.lemsun.web.util.JsonConvert;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-23
 * Time: 上午11:38
 */
@Controller
@RequestMapping("component/wpfskin")
public class WpfskinController extends BaseController{

    @Autowired
    IWpfPageResourceService wpfPageResourceService;

    @Autowired
    IResourceService resourceService;


    private Logger logger = LoggerFactory.getLogger(WpfskinController.class);

    /**
     * 创建wpf组件视图
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView viewAdd(HttpServletRequest request) throws Exception {

        HttpSession session=request.getSession();
        LemsunResource lResource = (LemsunResource) session.getAttribute("resource");
        ModelAndView view = new ModelAndView();
        if (lResource == null)
           view.setViewName("redirect:" + PrepareModelInteceptor.getRootPath() + "component/main/create");
        else{
           view.setViewName("component/add/Wpfskin");
           view.addObject("lemsunResource", JsonConvert.Serialization(lResource));
           view.addObject("pid", lResource.getPid());
           view.addObject("category", lResource.getCategory());
        }

        return view;
    }

    /**
     * 处理创建wpf组件视图
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<String> doAdd(HttpServletRequest request) throws Exception {


        LemsunResource resource = ((LemsunResource) request.getSession().getAttribute("resource"));

        WpfPageResource wpfPageResource = new WpfPageResource(resource);

        save(request, ResourceState.RELEASE, wpfPageResource);

        return new ResponseEntity<>(mapper.writeValueAsString(resource));
    }

    /**
     * 上传组件附件
     */
    @ResponseBody
    @RequestMapping(value = "{pid}/attaches/upload", method = RequestMethod.POST)
    public String doUploadFiles(MultipartHttpServletRequest request,@PathVariable String pid) throws IOException {

        Map<String, MultipartFile> msp = request.getFileMap();
        MultipartFile file = msp.values().iterator().next();

        try {
            IResource resource = resourceService.getBaseResource(pid);
            //wpfPageResourceService.uploadAttachFile(resource, file.getInputStream(), file.getOriginalFilename(), file.getContentType());
            resourceService.uploadAttachFile(resource.getPid(), file.getInputStream(), file.getOriginalFilename(), null);
        } catch (Exception ex) {
            logger.error("上传附件失败："+ex.getMessage());
        }
        return "success";
    }

    /**
     * 移除组件的一个附件
     */
    @RequestMapping(value = "{pid}/attaches/remove", method = RequestMethod.POST)
    public ResponseEntity<String> doRemoveAttache(HttpServletRequest request,@PathVariable String pid) {

        String filename = request.getParameter("filename");
        wpfPageResourceService.removeAttach(pid, filename, null);
        return new ResponseEntity("success");
    }

    /**
     * 获取组件附件
     */
    @RequestMapping(value = "{pid}/attaches/all", method = RequestMethod.GET)
    public ResponseEntity<List<ResourceAttach>> getWpfAttaches(HttpServletRequest request,
                                                               @PathVariable String pid) throws Exception {
        IResource resource = resourceService.getBaseResource(pid);

        List<ResourceAttach> resourceAttaches = wpfPageResourceService.loadResouceAttachFiles(resource);

        return new ResponseEntity<>(resourceAttaches);
    }

    /**
     * 编辑wpf组件视图
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "{pid}/edit", method = RequestMethod.GET)
    public ModelAndView viewEdit(String type,HttpServletRequest request, @PathVariable String pid) throws Exception {




        WpfPageResource wpfPageResource=wpfPageResourceService.get(pid);


        ModelAndView view = new ModelAndView("component/editer/Wpfskin");
        view.addObject("lmsResource", mapper.writeValueAsString(wpfPageResource));
        view.addObject("type",type);
        return view;
    }

    /**
     * 处理编辑wpf组件视图
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseEntity<String> doEdit(HttpServletRequest request) throws Exception {

        WpfPageResourceModel model = mapper.readValue(request.getReader(), WpfPageResourceModel.class);
        WpfPageResource wpfPageResource=wpfPageResourceService.get(model.getPid());
        wpfPageResource=model.encapseWpfResource(wpfPageResource);
        wpfPageResource.setState(ResourceState.RELEASE);
        wpfPageResourceService.update(wpfPageResource);
        wpfPageResourceService.update(wpfPageResource, model.getContext());
        return new ResponseEntity<>("success");
    }

    /**
     * 根据组件编码获取对应组件内容
     *
     * @return 组件内容
     */
    @RequestMapping(value = "{pid}/context", method = RequestMethod.GET)
    public ResponseEntity<String> getContextByPid(@PathVariable String pid) {
        if(StringUtils.isEmpty(pid)){
            throw ResourceException.ResourcePidisNull;
        }

        String context = resourceService.getContent(pid);

        return new ResponseEntity(context);
    }



    private void save(HttpServletRequest request,
                      int state,
                      WpfPageResource wpfPageResource) throws Exception {



        WpfPageResourceModel model = mapper.readValue(request.getReader(), WpfPageResourceModel.class);
        wpfPageResource=model.encapseWpfResource(wpfPageResource);

        LemsunResource temp = resourceService.getBaseResource(wpfPageResource.getPid());
        wpfPageResource.copyResource(temp);

        wpfPageResource.setState(state);

        wpfPageResourceService.update(wpfPageResource);
        wpfPageResourceService.update(wpfPageResource, model.getContext());
    }
    /**
     * 显示组件视图
     */
    @RequestMapping("view")
    public ModelAndView view(HttpServletRequest request, WpfSkinQuery query) throws IOException {
        return super.view(query);
    }

    @Override
    protected ComponentQueryHandler createComponentQueryHandler() throws IOException {
        ComponentQueryHandler handler= new ComponentQueryHandler(){

            @Override
            protected Map<String, Integer> createMap() {
                Map<String,Integer> map=new HashMap<>();
                map.put("head",1);
                map.put("left",2);
                return map;
            }
            @Override
            protected  Page<WpfPageResource> executeQueryComponent() {
                return   wpfPageResourceService.getPageing(query);
            }

            @Override
            protected  Page<WpfskinItemView> afterRelatedQueryHandler() {
                List<WpfskinItemView> webskinItemViewList=new ArrayList<>(data.getSize());
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
                    WpfskinItemView webskinItemView=new WpfskinItemView(webskin,account);
                    webskinItemViewList.add(webskinItemView);

                }

                return  new PageImpl<>(webskinItemViewList, query, data.getTotalElements());
            }
        };
        return handler;
    }

    @Override
    public ModelAndView details(String type, HttpServletRequest request, @PathVariable String pid) throws Exception {
        ModelAndView view=super.details(type, request, pid);
        IResource resource=(IResource)view.getModel().get("resource");
        List<ResourceAttach> resourceAttaches = wpfPageResourceService.loadResouceAttachFiles(resource);
        view.addObject("resourceAttaches", resourceAttaches);
        return view;
    }

    @Override
    public ModelAndView delete(String type, HttpServletRequest request, @PathVariable String pid) throws Exception {
        ModelAndView view=super.delete(type, request, pid);
        IResource resource=(IResource)view.getModel().get("resource");
        List<ResourceAttach> resourceAttaches = wpfPageResourceService.loadResouceAttachFiles(resource);
        view.addObject("resourceAttaches", resourceAttaches);
        return view;
    }
    /**
     * 这里是给webpage组件的的附件专用
     * 获取附件文件视图
     * 预览请求这里
     */
    @RequestMapping(value = "{pid}/{filename}.{ftype}")
    public void getAttachFile(
            HttpServletResponse response,
            @PathVariable String pid,
            @PathVariable String filename,
            @PathVariable String ftype
    ) throws IOException {

        //亲,如果这里你发现了乱码中文,不要着急哦.一般情况下请检查你的tomcat的server.xml的
        //<Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000"redirectPort="8443" URIEncoding="UTF-8"/>
        // 是不是少了最后一个单词,我是说utf-8. 或者 url 有没有encode方法
        String temp = URLDecoder.decode(filename, "UTF-8") + "." + ftype;
        resourceController.getPlateformResourceAttachContent("system", pid, temp, response);
    }
    @Override
    void deleteResource(String pid) throws Exception {
        wpfPageResourceService.delete(pid);
    }
}
