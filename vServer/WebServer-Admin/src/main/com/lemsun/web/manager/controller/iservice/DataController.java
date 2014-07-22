package com.lemsun.web.manager.controller.iservice;

import com.lemsun.core.ArrayData;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.data.service.ILmsTableService;
import com.lemsun.ldbc.ColumnFileInfo;
import com.lemsun.ldbc.TableData;
import com.lemsun.web.model.ResponseEntity;
import com.lemsun.web.model.UeditorUploadInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 接口数据编辑, 对表格数据. 特殊的列进行数据提取, 修改等操作
 *
 * User: 宗旭东
 * Date: 13-7-11
 * Time: 上午10:35
 */
@Controller("interfaceDataController")
@RequestMapping("/interface/{plateform}/data")
public class DataController {

    @Autowired
    private ILmsTableService tableService;

    @Autowired
    private JsonObjectMapper jsonMapper;

    /**
     * 获取一个页面内容编辑网页. 这个网页支持对表格的 HTML 字段类型的编辑
     * @return
     */
    @RequestMapping("html/page")
    public ModelAndView getHtmlWebEditor(@PathVariable String plateform, String pid, String rowid, String col) throws Exception {
        ModelAndView view = new ModelAndView("iservice/data/htmlEditor");


        view.addObject("plateform", "interface/" + plateform + "/data/");
        view.addObject("resourcePid", pid);
        view.addObject("rowid", rowid);
        view.addObject("col", col);
        String context = tableService.getColumnContext(pid, col, rowid);
        view.addObject("context", context);

        return view;
    }

    /**
     * 更新一条数据的 HTML 内容
     * @param pid 组件ID
     * @param rowid 行 ID
     * @param request
     * @return
     */
    @RequestMapping("html/upload/content")
    public ResponseEntity<String> uploadHtmlContent(String pid, String col, String rowid, HttpServletRequest request) throws Exception {
        InputStream stream = request.getInputStream();

        tableService.uploadColumnString(pid, col, rowid, stream);

        return new ResponseEntity<>("OK");
    }


    /**
     * 接收上传的文件
     * @param pid
     * @param col
     * @param rowid
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "html/upload/file/{pid}/{col}/{rowid}", method = RequestMethod.POST)
    @ResponseBody
    public UeditorUploadInfo uploadFile(@PathVariable String pid,
                                        @PathVariable String col,
                                        @PathVariable String rowid, String remark, MultipartHttpServletRequest request) throws Exception
    {
        UeditorUploadInfo info = new UeditorUploadInfo();

        Map<String, MultipartFile> msp = request.getFileMap();

        if (msp.size() == 0) {
            info.setError("没有文件接收到文件.");
        }

        MultipartFile file = msp.values().iterator().next();
        if (file.getSize() > 1024 * 40000) {
            info.setError("图片文件不能超过40M");
        }

        String path = tableService.uploadColumnFile(pid, col, rowid, file.getOriginalFilename(), file.getContentType(), remark, file.getInputStream());

        String root = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";


        info.setOriginal(file.getOriginalFilename());
        info.setUrl(root + "html/get/file/" + path);

        return info;
    }


    /**
     * 接收上传的图片
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "html/upload/image")
    @ResponseBody
    public UeditorUploadInfo uploadImage(@RequestParam MultipartFile file,
                                         HttpServletRequest request) throws Exception
    {
        UeditorUploadInfo info = new UeditorUploadInfo();

        if (file == null) {
            info.setError("没有文件接收到文件.");
            return info;
        }

        if (file.getSize() > 1024 * 4000) {
            info.setError("图片文件不能超过4M");
            return info;
        }

        String pid = request.getParameter("pid");
        String col = request.getParameter("col");
        String rowid = request.getParameter("rowid");
        String title = request.getParameter("pictitle");

        String path = tableService.uploadColumnImage(pid, col, rowid, file.getOriginalFilename(), title, file.getInputStream());
        String root = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";

        info.setOriginal(file.getOriginalFilename());
        info.setOrgUrl(root + "interface/view/data/html/get/image/" + pid + "/" + col + "/" + path);
        info.setTitle(title);
        return info;
    }

    /**
     * 获取 HTML 字段的文件
     * @param pid
     * @param col
     * @param fileid
     * @param response
     */
    @RequestMapping("html/get/file/{pid}/{col}/{fileid}")
    public void getHtmlFile(@PathVariable("pid") String pid,
                            @PathVariable("col") String col,
                            @PathVariable("fileid") String fileid, HttpServletResponse response) throws Exception {

        OutputStream stream = response.getOutputStream();
        tableService.getColumnFile(pid, col, fileid, stream);
        stream.flush();
    }

    /**
     * 获取 HTML 字段的图片
     * @param pid
     * @param col
     * @param fileid
     * @param response
     */
    @RequestMapping("html/get/image/{pid}/{col}/{fileid}")
    public void getHtmlImage(@PathVariable("pid") String pid,
                             @PathVariable("col") String col,
                             @PathVariable("fileid") String fileid, HttpServletResponse response) throws Exception {

        OutputStream stream = response.getOutputStream();
        tableService.getColumnFile(pid, col, fileid, stream);
        stream.flush();
    }

    /**
     * 获取字段中的文件信息
     * @param pid
     * @param col
     * @return
     * @throws Exception
     */
    @RequestMapping("html/image/{pid}/{col}/{rowid}")
    @ResponseBody
    public String getHtmlImageInfo(@PathVariable String pid,
                                   @PathVariable String col,
                                   @PathVariable String rowid,
                                   HttpServletRequest request) throws Exception {
        List<ColumnFileInfo> infos = tableService.getColumnFileInfos(pid, col, rowid);

        String images = "";

        if(infos != null)
        {

            String root = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";

            for (ColumnFileInfo info : infos) {
                images += root + "interface/view/data/html/get/image/" + pid + "/" + col + "/" + info.getId() + "ue_separate_ue";
            }
        }

        return images;

    }


    /**
     * 将请求的数据数据提交
     * @param request
     * @return
     */
    @RequestMapping("save")
    public ResponseEntity<String> saveData(HttpServletRequest request, String pid) throws Exception {

        TableData data = jsonMapper.readValue(request.getInputStream(), TableData.class);
        tableService.saveData(data.getTablePid(), data);

        return new ResponseEntity<>("OK");
    }

}
