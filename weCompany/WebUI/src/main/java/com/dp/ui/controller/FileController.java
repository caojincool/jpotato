package com.dp.ui.controller;

import com.dp.baobao.util.JsonObjectMapper;
import com.dp.ui.model.FolderInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpyang on 2014/11/18.
 */
@Controller
@RequestMapping("file")
public class FileController {

    String rootPath="";


    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "node",required = false)String node){
        ModelAndView view=new ModelAndView("/manager/file");
        if(node==""){
            node="/";
        }

        File file=new File(rootPath+node);
        String[] fs=file.list();


        view.addObject("picList",fs);
        return view;
    }

    @RequestMapping("/list")
    @ResponseBody
    public String root(HttpServletRequest request
            ,@RequestParam(value = "node",required = false)String node) throws IOException {
         rootPath=request.getServletContext().getRealPath("/upload");


        File file=new File(rootPath);
        if (!file.exists()) file.mkdir();

        FolderInfo[] fileInfo=new FolderInfo[1];
        fileInfo[0]=getFloder(file);

        ObjectMapper mapper=new JsonObjectMapper();

        return mapper.writeValueAsString(fileInfo);
    }


    public String mkdir(){
return "";
    }

    private FolderInfo getFloder(File file){
        FolderInfo folderInfo=new FolderInfo();
        if(file.isDirectory()){
            folderInfo.setId(file.getAbsolutePath().substring(rootPath.length()).replace("/","\\"));
            folderInfo.setText(file.getName());
        }

        File[] files=file.listFiles();

        if (files==null)
            return folderInfo;
        List<FolderInfo> folderInfos=new ArrayList<FolderInfo>();
        for(File f:files){
            if (f.isDirectory()){
                FolderInfo info=getFloder(f);
                folderInfos.add(info);
            }
        }
        folderInfo.setChildren(folderInfos);
        return folderInfo;
    }
}
