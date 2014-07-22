package com.lemsun.client.web.controller.iserver;

import com.lemsun.client.core.service.IResourceService;
import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 提供对外的组件操作服务
 */
@Controller
@RequestMapping("/component")
public class ResourceController {

    @Autowired
    IResourceService resourceService;


    /**
     * 提供下载附件的地址, fileid 为附件的唯一id, filename 为设置下载的文件名称, 如果没有文件名称, 那么下载的文件将按照唯一的id设置
     * @param fileid 附件在数据库中的唯一ID
     * @param filename
     * @param response
     */
    @RequestMapping("attach/get")
    public void getAttachFile(String fileid, String filename, HttpServletResponse response) throws Exception {

        response.reset();

        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "0");
        response.setContentType("application/octet-stream;charset=GBK");

        String name = StringUtils.isEmpty(filename) ? fileid : filename;

        //response.addHeader("Content-Disposition", "attachement;filename=" + new String(name.getBytes(Charsets.UTF_8), "ISO8859-1"));
        String fname = URLEncoder.encode(name, "utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fname + "\"");

        InputStream input = resourceService.getAttachStream(fileid);



        OutputStream out = response.getOutputStream();
        IOUtils.copy(input, out);

        input.close();

        out.flush();
        out.close();
    }

}
