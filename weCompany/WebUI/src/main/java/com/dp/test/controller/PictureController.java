package com.dp.test.controller;

import com.dp.test.model.FolderInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpyang on 2014/11/24.
 */
@Controller
@RequestMapping("/picture")
public class PictureController {

    private String root;

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "node")String node){
        ModelAndView view = new ModelAndView("/picture/index");
        File file=new File(root+node);

        List<String> fs=new ArrayList<String>();
        for(File f:file.listFiles()){
            if(f.isFile() && f.getName().endsWith(".jpg") || f.getName().endsWith(".png") || f.getName().endsWith(".gif"))
                fs.add(f.getName());
        }
        view.addObject("p",node);
        view.addObject("picList",fs);
        return view;
    }

    @RequestMapping("/folder")
    @ResponseBody
    public List<FolderInfo> folder(HttpServletRequest request){
        root=request.getServletContext().getRealPath("/upload");
        File file=new File(root);
        if (!file.exists()) file.mkdir();

        List<FolderInfo> folderInfos=new ArrayList<FolderInfo>();
        folderInfos.add(getFolder(file));

        return folderInfos;
    }

    private FolderInfo getFolder(File file){
        FolderInfo folderInfo=new FolderInfo();
        if (file.isDirectory()){
            folderInfo.setId(file.getPath().substring(root.length()).replace("\\","/"));
            folderInfo.setText(file.getName());
            folderInfo.setState(false);
        }

        File[] files=file.listFiles();
        if (files==null) return folderInfo;
        List<FolderInfo> folderInfos=new ArrayList<FolderInfo>();
        for(File f:files){
            if(f.isDirectory()){
                FolderInfo info=getFolder(f);
                folderInfos.add(info);
            }
        }
        folderInfo.setChildren(folderInfos);
        return folderInfo;
    }

    @RequestMapping("folderView")
    public ModelAndView folderView(){
        ModelAndView view=new ModelAndView("");

        return view;
    }
}
