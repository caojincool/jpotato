package com.lemsun.web.manager.controller.iservice;

import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.IResource;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.ResourceAttach;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.core.service.IResourceService;
import com.lemsun.data.lmstable.DataModel;
import com.lemsun.data.lmstable.Table5Resource;
import com.lemsun.data.lmstable.TableFace;
import com.lemsun.data.ImageResource;
import com.lemsun.data.service.ILmsTableService;
import com.lemsun.data.service.ITableService;
import com.lemsun.data.service.ImageResourceService;
import com.lemsun.data.tables.TableDataQuery;
import com.lemsun.data.tables.TableResource;
import com.lemsun.form.WebPageResource;
import com.lemsun.form.WpfPageResource;
import com.lemsun.form.service.IWebPageResourceService;
import com.lemsun.form.service.IWpfPageResourceService;
import com.lemsun.ldbc.TableData;
import com.lemsun.reporter.ReporterResource;
import com.lemsun.reporter.service.IReporterResourceService;
import com.lemsun.web.manager.controller.model.entity.ResourceRequest;
import com.lemsun.web.model.ResponseEntity;
import com.mongodb.gridfs.GridFSDBFile;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.StubNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 组件资源接口服务
 * User: Xudong
 * Date: 13-1-22
 * Time: 上午11:09
 */
@Controller("interfaceResourceController")
@RequestMapping("/interface/{plateform}/resource")
public class ResourceController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private JsonObjectMapper objectMapper;

    @Autowired
    private IResourceService resourceService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    /**
     * 获取组件对象
     */
    @RequestMapping("get")
    public ResponseEntity<IResource> getPlateformResource(@PathVariable("plateform") String pf, ResourceRequest request) throws Exception {

        IResource resource = null;
        String type = request.getType();
        String pid = request.getPid();

        if (LemsunResource.TYPE.equalsIgnoreCase(type))
            resource = context.getBean(IResourceService.class).getBaseResource(pid);
        else if (WebPageResource.TYPE.equalsIgnoreCase(type))
            resource = context.getBean(IWebPageResourceService.class).get(pid);
        else if (TableResource.TYPE.equalsIgnoreCase(type))
            resource = context.getBean(ITableService.class).get(pid);
        else if (ImageResource.TYPE.equalsIgnoreCase(type))
            resource = context.getBean(ImageResourceService.class).get(pid);
        else if(Table5Resource.TYPE.equalsIgnoreCase(type))
            resource = context.getBean(ILmsTableService.class).getResource(pid);
        else if(WpfPageResource.TYPE.equalsIgnoreCase(type))
            resource = context.getBean(IWpfPageResourceService.class).get(pid);
        else if(ReporterResource.TYPE.equalsIgnoreCase(type))
            resource = context.getBean(IReporterResourceService.class).get(pid);

        return new ResponseEntity<>(resource);
    }

    /**
     * 获取组件内容信息
     *
     * @param pf       平台信息
     * @param request  组件请求对象
     * @param response 回复对象
     * @throws java.io.IOException 执行异常
     */
    @RequestMapping("content/get")
    public void getPlateformResourceContent(
            @PathVariable("plateform") String pf,
            ResourceRequest request,
            HttpServletResponse response) throws IOException {

        //获取基本组件类型
        IResourceService service = context.getBean(IResourceService.class);

        String context = service.getContent(request.getPid());

        response.setCharacterEncoding("UTF-8");
        response.setContentType("json/text");
        if (context != null)
            response.getWriter().write(context);

        response.flushBuffer();
    }

    /**
     * 获取附件
     * @param pf
     * @param resourcePid
     * @param fileName
     * @param response
     * @throws IOException
     */
    @RequestMapping("content/attach/get")
    public void getPlateformResourceAttachContent(@PathVariable("plateform") String pf,
                                                  String resourcePid,
                                                  String fileName,
                                                  HttpServletResponse response) throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("json/text");
        IResourceService service = context.getBean(IResourceService.class);
        GridFSDBFile file = service.getAttachFile(resourcePid, fileName);

        OutputStream out = response.getOutputStream();

        if(file != null) {
            file.writeTo(out);
        }

        out.flush();
        out.close();
    }

    /**
     * 获取表格数据接口
     *
     * @param query 分页信息
     * @return 数据内容
     * @throws Exception 执行异常
     */
    @RequestMapping("data/get")
    public ResponseEntity<TableData> getTableResourceData(@PathVariable("plateform") String pf, TableDataQuery query) throws Exception {
        TableData data = context.getBean(ILmsTableService.class).query(query);

        return new ResponseEntity<>(data);
    }

    /**
     * 获取表的数据总数
     * @param pf
     * @param query
     * @return
     */
    @RequestMapping("data/count")
    public ResponseEntity<Long> getTableResourceDataCount(@PathVariable("plateform") String pf, TableDataQuery query) {

        Long count = context.getBean(ILmsTableService.class).getDataCount(query);

        return new ResponseEntity<>(count);
    }


    /**
     * 获取表单的最后一次的单据号的值
     * @param pf
     * @param query
     * @return
     */
    @RequestMapping("form/last")
    public ResponseEntity<Long> getLastFormNo(@PathVariable("plateform") String pf, TableDataQuery query) {
        Long formNo = context.getBean(ILmsTableService.class).getLastFormNo(query.getPid(), query.getAdate());
        return new ResponseEntity<>(formNo);
    }

    /**
     * 获取组件组下的当前组件
     */
    @RequestMapping("current/get")
    public ResponseEntity<LemsunResource> getCurrentResource(@PathVariable("plateform") String pf, String pid, String adate) throws Exception {
        Date a;
        if(StringUtils.isEmpty(adate)) {
             a = context.getBean(IAccountService.class).getCurrentAdate();
        }
        else {
            a = DateUtils.parseDate(adate, new String[]{"yyyyMMdd"});
        }

        LemsunResource resource = context.getBean(ILmsTableService.class).getCurrentResource(pid, a);
        return new ResponseEntity<>(resource);
    }

    /**
     * 保存一个表格组件的设置信息
     * @param pid 表格组件
     * @param face 表格界面
     * @param datamodel 表格数据模型
     * @return
     */
    @RequestMapping("table/save")
    public ResponseEntity<String> saveTableResource(String pid, String face, String datamodel) throws Exception {

        ILmsTableService service = context.getBean(ILmsTableService.class);

        TableFace tableFace = objectMapper.readValue(face, TableFace.class);

        DataModel dataModel = objectMapper.readValue(datamodel, DataModel.class);

        service.updateResource(pid, tableFace, dataModel);
        return new ResponseEntity<>("OK");
    }


    /**
     * 保存组件提交对应的皮肤信息文件, 文件将不做修改的保存到本地. 有可能出现异常后会覆盖之前的信息
     * @param pid
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("face/save")
    public ResponseEntity<String> saveResourceFace(String pid, HttpServletRequest request) throws Exception {
        IResourceService service = resourceService;
        service.saveResourceFace(pid, request.getInputStream());
        return new ResponseEntity<>("OK");
    }


    /**
     * 获取组件的设置信息
     * @param pid
     * @param response
     * @throws IOException
     */
    @RequestMapping("face/get")
    public void getResourceFace(String pid, HttpServletResponse response) throws IOException {
        String setting = resourceService.getSetting(pid);
        //response.reset();
        ServletOutputStream out = response.getOutputStream();

        if(StringUtils.isNotEmpty(setting)) {
            out.write(setting.getBytes(Charsets.UTF_8));
        }

        out.flush();
        out.close();
    }


    /**
     * 获取组件附件的信息
     * @param pid 组件的pid
     * @param filename 附件名称
     * @param filetype 附件类型
     * @return 附件信息对象
     */
    @RequestMapping("attachinfo/get")
    public ResponseEntity<ResourceAttach> getAttachInfo(String pid, String filename, String filetype, String fullname) {
        ResourceAttach attach = null;
        if(StringUtils.isNotEmpty(fullname))
        {
            attach = resourceService.getAttachDetail(fullname);
        }
        else
        {
            attach = resourceService.getAttachDetail(pid, filename, filetype);
        }

        return new ResponseEntity<>(attach);
    }


    /**
     * 根据附件的唯一ID, 这个位于的ID是从 ResourceDetail 中的附件唯一ID 获取附件流
     * @param fileid
     */
    @RequestMapping("attach/get")
    public void getAttachStream(String fileid, String filename, HttpServletResponse response) throws IOException {

        GridFSDBFile file = null;

        if(StringUtils.isNotEmpty(filename))
        {
            file = resourceService.getAttachFile(filename);
        }
        else
        {
            file = resourceService.getAttachFile(new ObjectId(fileid));
        }

        if(file != null)
        {
            OutputStream out = response.getOutputStream();
            file.writeTo(out);
            out.flush();
            out.close();
        }
    }
}
