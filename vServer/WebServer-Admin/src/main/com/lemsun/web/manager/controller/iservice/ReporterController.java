package com.lemsun.web.manager.controller.iservice;

import com.lemsun.reporter.ReporterResource;
import com.lemsun.reporter.service.IReporterResourceService;
import com.lemsun.web.manager.controller.model.entity.ResourceRequest;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 填报组件接口
 * Created by 宗 on 2014/5/20 0020.
 */
@Controller("interfaceReporterController")
@RequestMapping("/interface/{plateform}/reporter")
public class ReporterController {

    @Autowired
    private IReporterResourceService reporterResourceService;


    /**
     * 打开一个组件报表
     */
    @RequestMapping("content/get/{pid}")
    public void open(
            @PathVariable("plateform") String pf,
            @PathVariable("pid") String pid,
            HttpServletResponse response) throws IOException {

        ReporterResource resource = reporterResourceService.get(pid);


        //reporterResourceService.get


        String fileName = resource.getPrefix() + "." + resource.getSuffix();

        String urlname = new String(fileName.getBytes("UTF-8"), "iso-8859-1");

        response.addHeader("Content-Disposition", "inline; filename=" + urlname);
        if(resource.getFileType() == ReporterResource.EXCEL_2010_TYPE
                || resource.getFileType() == ReporterResource.EXCEL_TYPE)
            response.setContentType("application/vnd.ms-excel;charset=utf-8");

        GridFSDBFile file = reporterResourceService.getReporterFile(resource);

        OutputStream output = response.getOutputStream();
        file.writeTo(output);
        output.flush();
        output.close();
    }

    /**
     * 填报组件的页面视图
     * @return
     */
    @RequestMapping("view")
    public ModelAndView view()
    {
        return new ModelAndView("iservice/reporter/view");
    }

}
