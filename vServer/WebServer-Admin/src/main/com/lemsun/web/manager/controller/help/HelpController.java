package com.lemsun.web.manager.controller.help;


import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.*;
import com.lemsun.core.service.ICategoryService;
import com.lemsun.core.service.IResourceService;
import com.lemsun.helper.HelpItem;
import com.lemsun.helper.HelpQuery;
import com.lemsun.helper.service.IHelperService;
import com.lemsun.web.manager.controller.model.component.CutImage;
import com.lemsun.web.manager.controller.util.ImageCut;
import com.lemsun.web.manager.controller.util.WebConstant;
import com.lemsun.web.manager.controller.util.WebUtils;
import com.lemsun.web.model.ResponseEntity;
import com.lemsun.web.model.UeditorUploadInfo;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * User: 刘晓宝
 * Date: 13-12-3
 * Time: 上午9:12
 */
@Controller
@RequestMapping("help/document")
public class HelpController {

    private final static Logger log = LoggerFactory.getLogger(HelpController.class);

    @Autowired
    private IHelperService helperService;

    @Autowired
    private IResourceService service;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    protected IAccountService accountService;
    /**
     * 进入帮助页面
     * @param pid 编码
     * @return
     */
    @RequestMapping(value = {"", "/", "view"})
    public ModelAndView help(String pid){
        ModelAndView view=new ModelAndView("help/help");
        if(StringUtils.isNotEmpty(pid)){
            if(pid.startsWith("C")||pid.startsWith("S")){
                LemsunResource resource=service.getBaseResource(pid);
                if(resource!=null){
                    view.addObject("curName",resource.getName());
                }
            }
        }
        view.addObject("pid",pid);
        return view;
    }

    /**
     * 查询页面
     * @param query
     * @return
     */
    @RequestMapping("getData")
    public ResponseEntity<Page<HelpItem>> getData(HelpQuery query){
        ResponseEntity<Page<HelpItem>> responseEntity = new ResponseEntity<>();
        try{
            Page<HelpItem> datas= helperService.query(query);
            responseEntity.setSuccess(true);
            responseEntity.setEntity(datas);
        }catch (Exception e){
            if(log.isErrorEnabled())

                log.error("帮助文档查询组件出错！"+e.getMessage());
            responseEntity.setSuccess(false);
            responseEntity.setMessage(e.getMessage());

        }
        return responseEntity;
    }

    /**
     * 查询组件类别
     * @return
     */
    @ResponseBody
    @RequestMapping("getCategorys")
    public List<ICategory> getCategorys(){

        Collection<ICategory> categorys = categoryService.getAll();


        List<ICategory> list=new ArrayList<>();
        BaseCategory cate=new BaseCategory("查询全部","");
        list.add(cate);
        for (ICategory c : categorys) {
            if(c.getCategory()!="ROOT"){
                list.add(c);
            }
        }
        return list;
    }
    /**
     * 进入更新组件预览图
     * @param request
     * @param pid
     * @return
     */
    @RequestMapping(value = "{pid}/imageAndDetails/update", method = RequestMethod.GET)
    public ModelAndView updateImageAndDetails(HttpServletRequest request,@PathVariable String pid){
        ModelAndView view= new ModelAndView("component/editer/imageAndDetails");
        view.addObject("pid",pid);
        String details= helperService.getHelpContext(pid);
        view.addObject("details",details);
        return view;
    }
    /**
     * 异步上传预览图片
     * @param request
     * @param pid
     * @return
     */
    @RequestMapping(value="{pid}/uploadImage", method = RequestMethod.POST)
    public ResponseEntity<String> uploadImage(MultipartHttpServletRequest request,@PathVariable String pid){
        ResponseEntity<String> responseEntity=new ResponseEntity<>();

        Map<String,MultipartFile> map=request.getFileMap();
        MultipartFile multipartFile=map.get("Filedata");

        String originalFilename=multipartFile.getOriginalFilename();

        String suffix = WebUtils.getSuffix(originalFilename);

        String sessionId=WebUtils.getSessionId(request);

        String userTemp=WebConstant.IMGROOT+ sessionId +File.separator;

        String path = FilenameUtils.concat(WebUtils.getWebAppPath(request),userTemp);

        WebUtils.checkImageDir(path);

        String imgFileId =DateFormatUtils.format(new Date(),WebConstant.YYYYMMDDHHMMSS);

        String fileName=pid+"_src_"+imgFileId+"."+suffix;

        try {

            InputStream inputStream=multipartFile.getInputStream();

             ImageIO.setUseCache(false);
            //ImageIO.setCacheDirectory(new File(request.getServletContext().getRealPath("/"), userTemp));

            BufferedImage srcImage = ImageIO.read(inputStream); // 读入文件
            if(srcImage.getHeight()<WebConstant.IMAGE_150X150||srcImage.getWidth()<WebConstant.IMAGE_150X150){
                responseEntity.setSuccess(false);
                responseEntity.setMessage("您上次图片尺寸太小长宽都不得小于150！");
            }else {
                ImageCut.scale(srcImage,path+fileName,WebConstant.IMAGE_W_1000);
                responseEntity.setSuccess(true);
                responseEntity.setMessage(userTemp+fileName);
            }
        } catch (IOException e) {
            responseEntity.setMessage("上传失败");
            log.error("上传组件：{}预览图出错：{}", pid, e.getMessage());
        }

        return responseEntity;
    }



    /**
     * 保存切割预览图及组件详细说明
     * 需要参数
     * a、预览图原始地址srcPath
     * b、需要切割起始坐标x、y及长宽winth、height
     * c、组件pid
     * d、说明详情details
     * 操作步骤
     * 1、判断是否需要切割图片 如果不需要切割跳过 2、3、4步对预览图片处理
     * 2、如果需要 进行切割图片
     * 3、保存切割图片到mongodb文件存储系统
     * 4、删除原始图片
     * 5、保存更新组件详情到文件存储系统
     * @param request
     * @param pid
     * @return
     */
    @RequestMapping(value = "{pid}/cutPreviewImage", method = RequestMethod.POST)
    public ResponseEntity<String> cutPreviewImage(CutImage image, HttpServletRequest request,@PathVariable String pid)  {

        log.info("开始为组件s%切割图片",pid);
        ResponseEntity<String> responseEntity=new ResponseEntity<>();

        try {
            if(image!=null&&image.getSrcPath()!=null&&!image.getSrcPath().isEmpty()){
                //进行图片处理
                String path = WebUtils.getWebAppPath(request);
                String imgFileId =DateFormatUtils.format(new Date(),WebConstant.YYYYMMDDHHMMSS);
                //得到切割图片
                String srcPath=FilenameUtils.concat(path,image.getSrcPath());
                String suffix=WebUtils.getSuffix(srcPath);
                String sessionId=WebUtils.getSessionId(request);
                String userTemp=WebConstant.IMGROOT+ sessionId +File.separator;
                //ImageIO.setCacheDirectory(new File(path, userTemp));
                ImageIO.setUseCache(false);
                String temps=FilenameUtils.concat(path,userTemp);
                String cutPath=temps+pid+"_"+image.getWidth()+"X"+image.getHeight()+"_"+imgFileId+suffix;
                image.setSrcPath(srcPath);
                image.setDescPath(cutPath);
                ImageCut.abscut(image.getSrcPath(),image.getDescPath(),image.getX(),image.getY(),image.getWidth(),image.getHeight());//切割图片


                String middlePath=temps+pid+"_"+ WebConstant.IMAGE_150X150+"X"+WebConstant.IMAGE_150X150+"_"+imgFileId+suffix;
                String minPath=temps+pid+"_"+WebConstant.IMAGE_80X80+"X"+WebConstant.IMAGE_80X80+"_"+imgFileId+suffix;
                CutImage image150=new CutImage(WebConstant.IMAGE_150X150,WebConstant.IMAGE_150X150,middlePath);
                CutImage image80=new CutImage(WebConstant.IMAGE_80X80,WebConstant.IMAGE_80X80,minPath);
                ImageCut.scale(cutPath, image150,image80);

                File cutFile=new File(cutPath);
                cutFile.delete();


                String[] paths={srcPath,middlePath,minPath};

                //持久化到数据库
                 saveImageToDB(pid, paths, suffix);
            }
            responseEntity.setSuccess(true);
        } catch ( IOException e) {
            responseEntity.setSuccess(false);
            responseEntity.setMessage("组件{}切割图片失败！");
            if(log.isErrorEnabled())

                log.error("更新组件：{}切割图片失败！错误信息为："+e.getMessage(),pid);
        }

        return responseEntity;
    }

    /**
     * 保存图片到数据库
     * @param pid
     * @param paths
     * @param suffix
     * @throws IOException
     */
    private void saveImageToDB(String pid, String[] paths,   String suffix) throws IOException {
        int i=-1;
        for(String srcPath:paths){
            File scrFile=new File(srcPath);
            FileInputStream scrStream=new FileInputStream(scrFile);
            helperService.updateImage(pid, i,scrStream, suffix);
            scrStream.close();
            scrFile.delete();
            i++;
        }

    }
    /**
     * 进入组件预览大图预览页面
     * @param pid
     * @return
     */
    @RequestMapping("{pid}/effectPicture/view")
    public  ModelAndView effectPictureView(  @PathVariable String pid){
        ModelAndView view=new ModelAndView("component/effectPictureView");
        if(pid.startsWith("C")||pid.startsWith("S")){
            LemsunResource resource=service.getBaseResource(pid);
            view.addObject("pid",resource.getPid());
            view.addObject("name",resource.getName());
        }
        return view;
    }

    /**
     * 组件预览图 预览
     * @param pid 组件编码
     * @param size 尺寸
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("{pid}/preView")
    public void getImage(
            @PathVariable String pid,
            int size,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        GridFSDBFile file = helperService.getImage(pid,size);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("image/jpeg");
        if(file!=null){
            OutputStream stream = response.getOutputStream();
            file.writeTo(stream);
            stream.flush();
        }else{
            String path = WebUtils.getWebAppPath(request);
            File defaults=new File(FilenameUtils.concat(path,"resource/images/default/default"+size+".jpg"));
            InputStream in=new FileInputStream(defaults);
            OutputStream stream = response.getOutputStream();
            int b;
            while ((b = in.read()) != -1) {
                stream.write(b);
            }
            stream.flush();
        }
    }
    /**
     * 处理组件详情附件上传
     */
    @ResponseBody
    @RequestMapping(value = "{pid}/attach/files/upload", method = RequestMethod.POST)
    public UeditorUploadInfo uploadFiles(@PathVariable String pid,
                                         MultipartHttpServletRequest request) throws Exception {
        if (StringUtils.isEmpty(pid))
            throw new Exception("未获取组件编码,不能上传附件!");

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
                String fileName=file.getOriginalFilename();
                String prefix=fileName.substring(0,fileName.indexOf("."));
                String suffix=fileName.substring(fileName.indexOf(".")+1,fileName.length());
                helperService.updateHelpContextAttachFile(pid, file.getInputStream(), prefix,suffix);
                info.setOriginal(file.getOriginalFilename());
                info.setUrl("/help/document/"+pid+"/"+file.getOriginalFilename());
            } catch (Exception ex) {
                info.setError(ex.getMessage());
            }
        }

        return info;
    }


    /**
     * 这里是给帮助文件的附件预览
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

        String fileName = URLDecoder.decode(filename, "UTF-8") + "." + ftype;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("json/text");

        GridFSDBFile file = helperService.getHelpContextAttach(pid, fileName);

        if(file != null) {
            ServletOutputStream stream = response.getOutputStream();
            file.writeTo(stream);
            stream.flush();
            stream.close();
        }
        else {
            Writer writer = response.getWriter();
            writer.write("");
            writer.flush();
            writer.close();
        }

    }

    /**
     * 一个帮助文档下的所有图片
     * 对应图片上传控件的在线管理
     */
    @ResponseBody
    @RequestMapping(value = "{pid}/image/manager")
    public String imageManager(@PathVariable String pid,HttpServletRequest request) throws UnsupportedEncodingException {
        String images = "";
        //查询出所有组件附件
        List<ResourceAttach> resourceAttaches = helperService.getHelpContextAttachList(pid);

        LocalFileUploadConfig fileUploadConfig=Host.getInstance().getFileUploadConfig();
        //判断出图片内容{filetype}/{filename}/{pid}
        for (ResourceAttach ra : resourceAttaches) {
            if (fileUploadConfig.getFileTypes()!=null&&!FilenameUtils.isExtension(ra.getName(),fileUploadConfig.getFileTypes())) {
                String temp = URLEncoder.encode(ra.getName().substring(0, ra.getName().lastIndexOf(".")), "UTF-8");
                images += "/help/document/"+pid+"/" + temp + "." + ra.getType() + "ue_separate_ue";
            }
        }
        return images;
    }
    /**
     * 更新详情
     * @param pid
     * @param details
     * @return
     */
    @RequestMapping("{pid}/updateDetails")
    public  ResponseEntity<String> updateDetails(  @PathVariable String pid,String name, String details){
        ResponseEntity<String> responseEntity=new ResponseEntity<>();
        try{
            helperService.updateHelpContext(pid, details);
            responseEntity.setSuccess(true);
        }catch (Exception e){
            log.error("组件详情更新失败!错误信息"+e.getMessage(),e);
            responseEntity.setMessage("组件详情更新失败！");
        }
        return responseEntity;
    }

    /**
     * 帮助文档内容
     * @param pid
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("{pid}/detailsContext")
    public void getDetails(@PathVariable String pid,  HttpServletResponse response)  {
        String details=helperService.getHelpContextHandler(pid);
        response.setContentType("text/html;charset=UTF-8");
        try{
            Writer writer = response.getWriter();
            writer.write(details);
            writer.flush();
            writer.close();
          }catch (Exception e){
              log.error("获取文档内容出错！"+e.getMessage());
          }

    }

}
