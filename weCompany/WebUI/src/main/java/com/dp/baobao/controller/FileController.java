package com.dp.baobao.controller;

import com.dp.baobao.model.FolderInfo;
import com.dp.baobao.model.ResponseEntity;
import javafx.beans.property.adapter.ReadOnlyJavaBeanBooleanProperty;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dpyang on 2015/1/28.
 */
@Controller
@RequestMapping("/fileMg/*")
public class FileController {

    private String root="/upload";

    @RequestMapping("/folder")
    @ResponseBody
    public List<FolderInfo> folder(HttpServletRequest request){
        String id=request.getParameter("id");

        if(StringUtils.isEmpty(id)) {
            id = "/";
        }

        String trs=request.getServletContext().getRealPath(root+id);
        File file=new File(trs);
        File[] files=file.listFiles();
        List<FolderInfo> infos=new ArrayList<>();
        for(File f:files){
            if(f.isDirectory()){
                FolderInfo info=new FolderInfo(id+f.getName()+"/",f.getName(),hasChild(f)?"closed":"open");
                Map<String,String> att=new HashMap<>();
                att.put("parent",id);
                info.setAttributes(att);
                infos.add(info);
            }
        }
        return infos;
    }

    @RequestMapping(value = "editFolder",method = RequestMethod.POST)
    public ResponseEntity<String> editFolder(HttpServletRequest request){

        String id=request.getParameter("id");
        String parent=request.getParameter("parent");
        String nName=request.getParameter("text");

        String parentPath=request.getServletContext().getRealPath(root+parent);
        String ofolder=request.getServletContext().getRealPath(root+id);

        File resFile = new File(ofolder);
        File newFile = new File(parentPath+File.separator+nName);

        if(newFile.exists()){
            return new ResponseEntity<>(false,nName+"目录已经存在！");
        }else{
            boolean success=resFile.renameTo(newFile);
            return new ResponseEntity<>(success,success?"更新成功":"更新失败！");
        }
    }

    @RequestMapping(value = "addFolder",method = RequestMethod.POST)
    public ResponseEntity<String> addFolder(HttpServletRequest request){
        String id=request.getParameter("id");
        String nName=request.getParameter("text");

        String path=request.getServletContext().getRealPath(root+id);
        File f=new File(path);

        if(f.exists()){
            return new ResponseEntity<>(false,nName+"已经存在！<br>请双击该目录进行重命名！");
        }else{
            f.mkdir();
            return new ResponseEntity<>(true,"ok");
        }
    }

    @RequestMapping(value = "rmDir",method = RequestMethod.POST)
    public ResponseEntity<String> rmFolder(HttpServletRequest request){
        String id=request.getParameter("id");
        String path=request.getServletContext().getRealPath(root+id);

        File f=new File(path);

        boolean success=FileUtils.deleteQuietly(f);

        return new ResponseEntity<>(success,success?"ok":"删除错误！");
    }

    @RequestMapping(value = "fileShow")
    public ModelAndView fileShow(HttpServletRequest request){
        ModelAndView view=new ModelAndView();
        view.setViewName("picture/index");

        String id=request.getParameter("id");
        String temp=request.getServletContext().getRealPath(root+id);
        File f=new File(temp);
        File[] files=f.listFiles();
        Map<String,String> ff=new HashMap<>();

        for (File f1:files){
            if(f1.isFile()){
                ff.put(f1.getName(),id+f1.getName());
            }
        }
        view.addObject("files",ff);
        return view;
    }

    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public ResponseEntity<String> upload(@RequestParam("Filedata") MultipartFile uploadify, HttpServletRequest request) throws IOException {
        if(uploadify.isEmpty()){
            System.out.println("文件未上传");
        }else{
            String id=request.getParameter("id");
            String realPath = request.getSession().getServletContext().getRealPath(root+id);
            FileUtils.copyInputStreamToFile(uploadify.getInputStream(), new File(realPath, uploadify.getOriginalFilename()));
        }
        return new ResponseEntity<>(true,"1");
    }

    @RequestMapping(value = "fileRM",method = RequestMethod.POST)
    public ResponseEntity<String> fileRM(HttpServletRequest request){
        String id=request.getParameter("id");
        String[] o=request.getParameterValues("fs[]");

        String path=request.getServletContext().getRealPath(root+id);
        for(String s:o){
            File f=new File(path+File.separator+s);
            if(f.isFile()){
                f.delete();
            }
        }
        return new ResponseEntity<>(true,"ok");
    }

    private boolean hasChild(File file){
        if(file==null) return false;

        File[] files=file.listFiles();
        boolean hasChaild=false;
        for(File f:files){
            if(f.isDirectory()){ hasChaild=true;break;}
        }
        return hasChaild;
    }
}
