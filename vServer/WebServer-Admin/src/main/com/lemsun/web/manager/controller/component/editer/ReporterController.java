package com.lemsun.web.manager.controller.component.editer;

import com.lemsun.auth.BaseAccount;
import com.lemsun.core.*;
import com.lemsun.core.repository.ResourceAttachFileRepository;
import com.lemsun.reporter.ReporterResource;
import com.lemsun.reporter.service.IReporterResourceService;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.component.ReporterResourceModel;
import com.lemsun.web.manager.controller.model.query.ReporterQuery;
import com.lemsun.web.model.ResponseEntity;
import com.lemsun.web.model.view.ReporterItemView;
import com.lemsun.web.util.JsonConvert;
import com.mongodb.gridfs.GridFSDBFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 填报组件
 * todo:只是能创建基本信息,缺少编辑内容,权限
 * Created by 宗 on 2014/5/19 0019.
 */
@Controller
@RequestMapping("component/reporter")
public class ReporterController extends BaseController {

    @Autowired
    IReporterResourceService reporterResourceService;


    /**
     * 填报组件列表显示
     *
     * @return
     */
    @RequestMapping("view")
    public ModelAndView view(ReporterQuery query) throws IOException {
        return super.view(query);
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView viewAdd(HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        LemsunResource lResource = (LemsunResource) session.getAttribute("resource");
        ModelAndView view = new ModelAndView();
        if (lResource == null)
            view.setViewName("redirect:" + PrepareModelInteceptor.getRootPath() + "component/main/create");
        else {
            view.setViewName("component/add/reporter");
            view.addObject("lemsunResource", JsonConvert.Serialization(lResource));
            view.addObject("pid", lResource.getPid());
            view.addObject("category", lResource.getCategory());
        }

        return view;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<String> doCreate(MultipartHttpServletRequest request) throws Exception {
        //获取基本信息
        ReporterResourceModel model = mapper.readValue(request.getParameter("data"), ReporterResourceModel.class);
        ReporterResource resource = reporterResourceService.get(model.getPid());
        model.convert(resource);

        //上传文件
        MultipartFile file = request.getFile("content");
        boolean success = uploadReporter(resource, file);

        String message = success ? "ok" : "创建失败，请联系管理员！";

        return new ResponseEntity<>(success, message);
    }

    @RequestMapping(value = "{pid}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String pid) throws Exception {
        ReporterResource resource = reporterResourceService.get(pid);

        ModelAndView view = new ModelAndView("component/editer/reporter");
        view.addObject("lemsunResource", mapper.writeValueAsString(resource));
        return view;
    }

    @RequestMapping(value = "{pid}/edit", method = RequestMethod.POST)
    public ResponseEntity<String> doEdit(MultipartHttpServletRequest request) throws IOException {
        //获取基本信息
        ReporterResourceModel model = mapper.readValue(request.getParameter("data"), ReporterResourceModel.class);
        ReporterResource resource = reporterResourceService.get(model.getPid());
        model.convert(resource);

        //上传文件
        boolean success = false;
        MultipartFile file = request.getFile("content");
        if (file.getSize() <= 0) {
            reporterResourceService.update(resource);
            success = true;
        } else {
            success = uploadReporter(resource, file);
        }
        String message = success ? "ok" : "创建失败，请联系管理员！";

        return new ResponseEntity<>(success, message);
    }

    private boolean uploadReporter(ReporterResource resource, MultipartFile file) {
        if (file.getSize() <= 0) {
            logger.error("填报文件内容是空，禁止上传！");
            return false;
        }
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

        LocalFileUploadConfig config = Host.getInstance().getReporterUploadConfig();
        long ssize = config.getLimitSize() * 1024 * 1024;
        if (ssize < file.getSize()) {
            logger.error("填报文件超过" + config.getLimitSize() + "M,禁止上传！");
            return false;
        }
        String[] types = config.getFileTypes();

        boolean istype = false;
        for (String t : types) {
            if (t.toLowerCase().equals(suffix.toLowerCase())) {
                istype = true;
                break;
            }
        }
        if (!istype)
            return istype;

        try {

            resource.setPrefix(fileName.substring(0, fileName.lastIndexOf(".")));
            resource.setSuffix(suffix);
            reporterResourceService.update(resource);
            reporterResourceService.uploadReporter(resource, file.getInputStream());
        } catch (Exception e) {
            logger.error("上传填报文件错误：" + e.getMessage());
        }
        return true;
    }

    @RequestMapping(value = "getReporterfile")
    public void getReporterFile(String pid, HttpServletResponse response) {
        LemsunResource resource = resourceService.getBaseResource(pid);

        if (resource == null) {
            throw new LemsunException(pid + "不是一个合法的填报组建,不能获取填报组件", "100", null);
        }

        response.setContentType("application/x-xls");

        ResourceAttach fileAttach = resourceService.getAttachDetail(ResourceAttachFileRepository.getDbContentName(resource.getPid()));

        GridFSDBFile file = resourceService.getAttachFile(new ObjectId(fileAttach.getFileid()));
        if (file != null) {
            try {
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileAttach.getName(), "UTF-8"));
                OutputStream stream = response.getOutputStream();
                file.writeTo(stream);
                stream.flush();
                stream.close();
            } catch (IOException e) {
                throw new LemsunException("填报文件读写异常");
            }
        }

    }

    @Override
    void deleteResource(String pid) throws Exception {
        reporterResourceService.delete(pid);
    }

    @Override
    protected ComponentQueryHandler createComponentQueryHandler() throws IOException {
        ComponentQueryHandler handler = new ComponentQueryHandler() {
            @Override
            protected Map<String, Integer> createMap() {
                Map<String, Integer> map = new HashMap<>();
                map.put("head", 1);
                map.put("left", 3);
                return map;
            }

            @Override
            protected Page<ReporterResource> executeQueryComponent() {
                return reporterResourceService.getPageing(query);
            }

            @Override
            protected Page<ReporterItemView> afterRelatedQueryHandler() {
                List<ReporterItemView> reporterResources = new ArrayList<>(data.getSize());
                for (IResource reporter : data.getContent()) {
                    BaseAccount account = null;
                    try {
                        account = accountService.getAccountByAccount(reporter.getCreateUser());
                    } catch (Exception e) {
                        logger.info("异常数据", e.getMessage());
                    }
                    if (account == null) {
                        account = new BaseAccount();
                    }

                    ReporterItemView reporterItemView = new ReporterItemView(reporter, account);
                    reporterResources.add(reporterItemView);
                }

                return new PageImpl<>(reporterResources, query, data.getTotalElements());
            }
        };
        return handler;
    }
}
