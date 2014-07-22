package com.lemsun.web.manager.controller.component.editer;


import com.lemsun.auth.BaseAccount;
import com.lemsun.core.*;
import com.lemsun.core.util.ResourceUtil;

import com.lemsun.form.WebPageParam;
import com.lemsun.form.WebPageResource;
import com.lemsun.form.service.IWebPageResourceService;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.component.WebPageResourceModel;
import com.lemsun.web.manager.controller.model.query.WebSkinQuery;
import com.lemsun.web.model.ResponseEntity;
import com.lemsun.web.model.UeditorUploadInfo;
import com.lemsun.web.model.view.WebskinItemView;
import org.apache.commons.io.FilenameUtils;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * User: Lucklim
 * Date: 13-1-23
 * Time: 上午11:38
 * webpage组件控制器
 */
@Controller
@RequestMapping("component/webskin")
public class WebskinController extends BaseController {

    @Autowired
    private IWebPageResourceService webPageResourceService;

    private final Logger logger = LoggerFactory.getLogger(WebskinController.class);

    private  Host host;// =Host.getInstance();
    /**
     * 创建WebPage组件
     * 并跳转至组件信息扩展视图
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView viewAdd(HttpServletRequest request) throws Exception {
        HttpSession session=request.getSession();
        String type=(String)session.getAttribute("type");
        LemsunResource resource = ((LemsunResource)session.
                getAttribute("resource"));

        ModelAndView view = new ModelAndView();

        if (resource == null){
            String url=null;
            if (type==null||type.equals("")){
                url="redirect:"
                        + PrepareModelInteceptor.getRootPath()
                        + "component/main/view";
            } else{
                url="redirect:"
                        + PrepareModelInteceptor.getRootPath()
                        + "component/"+type.toLowerCase()+"/view";
            }
            view.setViewName(url);
        }else{
            view.setViewName("component/add/Webskin");
            view.addObject("lemsunResource", mapper.writeValueAsString(resource));
            view.addObject("pid", resource.getPid());
            view.addObject("category", resource.getCategory());
        }
        return view;
    }

    /**
     * 处理创建web组件
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<String> doAdd(HttpServletRequest request) throws Exception {

        //获取基本组件
        LemsunResource lr = ((LemsunResource) request.getSession().
                getAttribute("resource"));

        lr=resourceService.getBaseResource(lr.getPid());
        WebPageResourceModel model = mapper.readValue(request.getReader(), WebPageResourceModel.class);
        savaResource(lr, model);
        return new ResponseEntity<>(mapper.writeValueAsString(lr));
    }

    /**
     * 保存组件
     * @param lr
     * @param model
     * @throws Exception
     */
    private void savaResource(LemsunResource lr, WebPageResourceModel model) throws Exception {
        WebPageResource webPageResource = new WebPageResource(lr);
        webPageResource = model.encapsulationWebPageResource(webPageResource);
        webPageResource.setState(ResourceState.RELEASE);
        webPageResourceService.update(webPageResource);
        webPageResourceService.update(webPageResource, model.getContext());
    }

    /**
     * 这里是给webpage组件的的附件专用
     * 获取附件文件视图
     * 预览请求这里
     */
    @RequestMapping(value = "{pid}/{filename}.{ftype}")
    public void getAttachFile(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable String pid,
            @PathVariable String filename,
            @PathVariable String ftype
    ) throws IOException {

        //亲,如果这里你发现了乱码中文,不要着急哦.一般情况下请检查你的tomcat的server.xml的
        //<Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000"redirectPort="8443" URIEncoding="UTF-8"/>
        // 是不是少了最后一个单词,我是说utf-8. 或者 url 有没有encode方法
        getAttachFile(response, pid, filename, ftype);
    }

    @RequestMapping(value = "add/{filename}.{ftype}")
    public void getAttachFile(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable String filename,
            @PathVariable String ftype
    ) throws IOException {

        //亲,如果这里你发现了乱码中文,不要着急哦.一般情况下请检查你的tomcat的server.xml的
        //<Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000"redirectPort="8443" URIEncoding="UTF-8"/>
        // 是不是少了最后一个单词,我是说utf-8. 或者 url 有没有encode方法
        IResource resource = (LemsunResource) request.getSession().getAttribute("resource");
        getAttachFile(response, resource.getPid(), filename, ftype);
    }

    /**
     * 这里是给webpage组件的的附件专用
     * 获取附件文件视图
     * 预览请求这里
     */
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

    /**
     * 处理图像上传
     * 单张上传
     */
    @ResponseBody
    @RequestMapping(value = "{pid}/attach/images/upload", method = RequestMethod.POST)
    public UeditorUploadInfo uploadImages(@PathVariable String pid,
                                             MultipartHttpServletRequest request) throws Exception {
        if (StringUtils.isEmpty(pid))
            throw new Exception("未获取组件编码,不能上传附件!");

        LemsunResource resource = resourceService.getBaseResource(pid);

        return upload(request, resource);
    }

    /**
     * 处理文上传
     */
    @ResponseBody
    @RequestMapping(value = "{pid}/attach/files/upload", method = RequestMethod.POST)
    public UeditorUploadInfo uploadFiles(@PathVariable String pid,
                                            MultipartHttpServletRequest request) throws Exception {
        if (StringUtils.isEmpty(pid))
            throw new Exception("未获取组件编码,不能上传附件!");

        LemsunResource resource = resourceService.getBaseResource(pid);

        return upload(request, resource);
    }

    /**
     * 检查并上传文件
     */
    private UeditorUploadInfo upload(MultipartHttpServletRequest request,
                                     LemsunResource resource) {
        UeditorUploadInfo info = new UeditorUploadInfo();
        Map<String, MultipartFile> msp = request.getFileMap();
        MultipartFile file = msp.values().iterator().next();
        LocalFileUploadConfig fileUploadConfig=Host.getInstance().getFileUploadConfig();
        if (file.getSize() > fileUploadConfig.getLimitSize()*1024*1024) {
            info.setError("附件不能超过10M");
        } else if ( fileUploadConfig.getFileTypes()!=null&&!FilenameUtils.isExtension(file.getOriginalFilename(),fileUploadConfig.getFileTypes())) {
            info.setError("文件类型不符合");
        } else {
            try {

                webPageResourceService.updateAttach(resource.getPid(), file.getOriginalFilename(), null, file.getInputStream());

                //webPageResourceService.updateWebPageResourceAttachFile(resource, file.getInputStream(), file.getOriginalFilename(), file.getContentType());
                info.setOriginal(file.getOriginalFilename());
                info.setUrl("/component/webskin/"+resource.getPid()+"/"+file.getOriginalFilename());
            } catch (Exception ex) {
                info.setError(ex.getMessage());
            }
        }
        return info;
    }

    /**
     * 一个组件下的所有图片
     * 对应图片上传控件的在线管理
     */
    @ResponseBody
    @RequestMapping(value = "{pid}/image/manager")
    public String imageManager(@PathVariable String pid,HttpServletRequest request) throws UnsupportedEncodingException {
        String images = "";

        LemsunResource resource = resourceService.getBaseResource(pid);


        //查询出所有组件附件
        List<ResourceAttach> resourceAttaches = webPageResourceService.getResourceAttaches(resource);

        LocalFileUploadConfig fileUploadConfig=host.getFileUploadConfig();
        //判断出图片内容{filetype}/{filename}/{pid}
        for (ResourceAttach ra : resourceAttaches) {
            if (fileUploadConfig.getFileTypes()!=null&&!FilenameUtils.isExtension(ra.getName(),fileUploadConfig.getFileTypes())) {
                String temp = URLEncoder.encode(ra.getName().substring(0, ra.getName().lastIndexOf(".")), "UTF-8");
                images += "component/webskin/"+pid+"/" + temp + "." + ra.getType() + "ue_separate_ue";
            }
        }
        return images;
    }

    /**
     * 获取资源所有附件
     */
    @RequestMapping(value = "attaches/all", method = RequestMethod.GET)
    public ResponseEntity<List<ResourceAttach>> getWprAttaches(HttpServletRequest request, String pid) {

        IResource resource = resourceService.getBaseResource(pid);

        List<ResourceAttach> resourceAttaches = webPageResourceService.getResourceAttaches(resource);

        ResponseEntity<List<ResourceAttach>> responseEntity = new ResponseEntity<>(resourceAttaches);
        return responseEntity;
    }

    /**
     * 处理删除附件
     */
    @RequestMapping(value = "{pid}/attach/remove", method = RequestMethod.POST)
    public ResponseEntity<String> doRemoveWprAttaches(@PathVariable String pid,
                                              HttpServletRequest request) {
        IResource resource = resourceService.getBaseResource(pid);

        //获取文件名
        String filename = request.getParameter("filename");

        //webPageResourceService.removeWebPageResourceAttachFile(resource, filename);
        webPageResourceService.removeAttach(resource.getPid(), filename, null);

        return new ResponseEntity<>("success");
    }

    /**
     * 编辑某个webpage组件常规信息
     */
    @RequestMapping(value = "{pid}/edit", method = RequestMethod.GET)
    public ModelAndView viewEdit(String type,HttpServletRequest request, @PathVariable String pid) throws Exception {
        if (!ResourceUtil.isRecourcePid(pid))
            throw ResourceException.ResourcePidisNull;

        WebPageResource webPageResource = webPageResourceService.get(pid);
        //webPageResource.setContent(webPageResourceService.getContext(webPageResource));

        ModelAndView view = new ModelAndView("component/editer/Webskin");
        view.addObject("lemsunResource", mapper.writeValueAsString(webPageResource));
        view.addObject("type",type);
        return view;
    }

    /**
     * 处理编辑
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseEntity<String> doEdit(HttpServletRequest request) throws Exception {
        WebPageResourceModel model = mapper.readValue(request.getReader(), WebPageResourceModel.class);

        WebPageResource webPageResource = webPageResourceService.get(model.getPid());
        webPageResource = model.encapsulationWebPageResource(webPageResource);
        webPageResource.setState(ResourceState.RELEASE);
        webPageResourceService.update(webPageResource);
        webPageResourceService.update(webPageResource, model.getContext());
        return new ResponseEntity<>("success");
    }

    /**
     * 获取组件内容
     */
    @ResponseBody
    @RequestMapping(value = "{pid}/getContext", method = RequestMethod.GET)
    public void getContext(HttpServletRequest request,
                           HttpServletResponse response, @PathVariable String pid) throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        //WebPageResource webPageResource = webPageResourceService.get(pid);
        //String context = webPageResourceService.getContext(webPageResource);
        String context = resourceService.getContent(pid);
        if(context!=null){
            response.getWriter().write(context);
        }else{
            response.getWriter().write("");
        }
    }
    /**
     * 预览开始参数数据
     * 暂未实现
     */
    @RequestMapping(value = "doPreViewStartParam", method = RequestMethod.POST)
    public ResponseEntity<String> doPreViewStartParam(HttpServletRequest request) throws Exception {

        WebPageResourceModel model = mapper.readValue(request.getReader(), WebPageResourceModel.class);


        return new ResponseEntity<String>(mapper.writeValueAsString("成功"));
    }



    /**
     * 查询查询所有模板组件
     *
     * @return 模板组件
     */
    @RequestMapping(value = "getAllTemplateWebPageResource")
    public ResponseEntity<List<LemsunResource>> getAllTemplateWebPageResource(HttpServletRequest request) {
        LemsunResource lemsunResource = (LemsunResource) request.getSession().getAttribute("resource");
        List<LemsunResource> list = webPageResourceService.getTempResourse(lemsunResource.getPid());
        return new ResponseEntity<>(list);
    }

    /**
     * 获取某个组件的开始参数
     */
    @RequestMapping(value = "{pid}/startParams/all")
    public ResponseEntity<Set<WebPageParam>> getStartParamsByPid(@PathVariable String pid) {

        WebPageResource lemsunResource = webPageResourceService.get(pid);

        return  new ResponseEntity<>(lemsunResource.getStartParam());
    }
    @RequestMapping("view")
    public ModelAndView view(HttpServletRequest request, WebSkinQuery query) throws IOException {
         return super.view(query);
    }

    @Override
    protected ComponentQueryHandler createComponentQueryHandler() throws IOException {
        ComponentQueryHandler handler= new ComponentQueryHandler(){

            @Override
            protected Map<String, Integer> createMap() {
                Map<String,Integer> map=new HashMap<>();
                map.put("head",1);
                map.put("left",1);
                return map;
            }
            @Override
            protected Page<WebPageResource> executeQueryComponent() {
                return   webPageResourceService.getPageing(query);
            }

            @Override
            protected  Page<WebskinItemView> afterRelatedQueryHandler() {
                List<WebskinItemView> webskinItemViewList=new ArrayList<>(data.getSize());
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
                    WebskinItemView webskinItemView=new WebskinItemView(webskin,account);
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
        List<ResourceAttach> resourceAttaches = webPageResourceService.getResourceAttaches(resource);
        view.addObject("resourceAttaches", resourceAttaches);
        return view;
    }

    @Override
    public ModelAndView delete(String type, HttpServletRequest request, @PathVariable String pid) throws Exception {
        ModelAndView view=super.delete(type, request, pid);
        IResource resource=(IResource)view.getModel().get("resource");
        List<ResourceAttach> resourceAttaches = webPageResourceService.getResourceAttaches(resource);
        view.addObject("resourceAttaches", resourceAttaches);
        return view;
    }

    @Override
    void deleteResource(String pid) throws Exception {
        webPageResourceService.delete(pid);
    }
}
