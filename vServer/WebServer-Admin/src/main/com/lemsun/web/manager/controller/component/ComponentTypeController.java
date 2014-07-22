package com.lemsun.web.manager.controller.component;

import com.lemsun.auth.AccountManager;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.BaseCategory;
import com.lemsun.core.ICategory;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.core.service.ICategoryService;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.component.BaseCategoryTreeStore;
import com.lemsun.web.model.HeaderTitle;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

/**
 * User: Xudong
 * Date: 13-1-6
 * Time: 下午8:28
 * 组件类别管理
 */
@Controller
@RequestMapping("component/componenttype")
public class ComponentTypeController {

    private final static Logger log = LoggerFactory.getLogger(ComponentTypeController.class);
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private JsonObjectMapper objectMapper;

    @Autowired
    private IAccountService accountService;

    /**
     * 类型界面展示
     */
    @RequestMapping(value = {"", "/", "view"})
    public ModelAndView view(HttpServletRequest request) throws IOException {
        request.getSession().setAttribute("head", 5);
        request.getSession().setAttribute("left", 4);
        return new ModelAndView("component/componentTypeView");
    }

    @RequestMapping(value = {"", "/", "viewiframe"})
    public ModelAndView viewiframe(HttpServletRequest request) throws IOException {
        return new ModelAndView("component/componentType");
    }

    /**
     * 类型数据获取
     */
    @ResponseBody
    @RequestMapping("list/get")
    public List<BaseCategoryTreeStore> getData() {
        ICategory baseCategory = categoryService.getRoot();
        return (new BaseCategoryTreeStore()).convertBaseCategory(baseCategory, true);
    }

    @RequestMapping("icon/{cateogry}")
    public void getIcon(@PathVariable("cateogry") String cateogry,
                        HttpServletRequest request,
                        HttpServletResponse response) throws IOException {

        if (StringUtils.isEmpty(cateogry)) {
            cateogry = "";
        }

        String fileName = FilenameUtils.concat(request.getSession().getServletContext().getRealPath("/"),
                 "resource/Typeimage/"
                + cateogry.toUpperCase().trim() + ".png");

        File file = new File(fileName);

        if (!(file.exists() && file.canRead())) {
            file = new File(FilenameUtils.concat(request.getSession().getServletContext().getRealPath("/"),
                    "resource/Typeimage/components.png"));
        }

        FileInputStream inputStream = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        int length = inputStream.read(data);
        inputStream.close();

        response.setContentType("image/png");

        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
    }

    /**
     * 修改类型界面
     */
    @RequestMapping(value = "modification", method = RequestMethod.GET)
    public ModelAndView modification(String id, HttpServletRequest request) {
        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);

        ModelAndView view = new ModelAndView("component/modification/modification_start");
        view.addObject("category", categoryService.getByPid(id));

        Collection<ICategory> categories = categoryService.getAll();
        view.addObject("categorys", categories);
        return view;
    }

    /**
     * 修改类型界面下一步
     */
    @RequestMapping(value = "modification", method = RequestMethod.POST)
    public ModelAndView modification(BaseCategoryTreeStore baseCategoryTreeStore,
                                     HttpServletRequest request,
                                     @RequestParam("file") MultipartFile file) throws Exception {
        baseCategoryTreeStore.setText(request.getParameter("name"));
        baseCategoryTreeStore.setRemark(request.getParameter("remark"));
        baseCategoryTreeStore.setIconCls(request.getParameter("iconCls"));

        /*********保存数据库***********/
        BaseCategory baseCategory = categoryService.getByPid(request.getParameter("parentpid"));
        baseCategory.setName(request.getParameter("name"));
        baseCategory.setRemark(request.getParameter("remark"));
        categoryService.edit(baseCategory);

        ModelAndView view = new ModelAndView("redirect:" + PrepareModelInteceptor.getRootPath() + "index?current=component/componenttype");

        if (file.getSize() < 10) {
            return view;
        }

        String[] exnames = file.getOriginalFilename().split("\\.");

        String ext = exnames[exnames.length - 1].toLowerCase();

        accountService.updateAvatar(AccountManager.getCurrentManager().getAccount(), file.getInputStream());

        if ("jpg".equals(ext) || "jpeg".equals(ext) || "png".equals(ext) || "gif".equals(ext) || "bmp".equals(ext)) {
            if (file.getSize() > 10000000)
                throw new Exception("上传失败：图片大小不能超过10M");
            file.transferTo(
                    new File(
                            FilenameUtils.concat( request.getSession().getServletContext().getRealPath("/"),
                                     "resource\\Typeimage\\"
                                    + baseCategory.getCategory() + ".png"))
            );
        } else {
            throw new Exception("不是图片类型");
        }

        /*********页面跳转***********/

        //view.addObject("category", baseCategoryTreeStore);
        return view;
    }

    /**
     * 添加类型
     */
    @RequestMapping(value = "addtype", method = RequestMethod.GET)
    public ModelAndView addtype(String id, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("component/addtype/addtype");
        view.addObject("addtype", categoryService.getByPid(id));
        return view;
    }

    @RequestMapping(value = "addtype", method = RequestMethod.POST)
    public ModelAndView addtype(HttpServletRequest request, @RequestParam("file") MultipartFile file, String parentpid) throws Exception {

//		BaseCategory baseCategory=new BaseCategory("","",categoryService.getBaseCategoryByPid(request.getParameter("parentpid")));
//        baseCategory.setName(request.getParameter("name"));
//		baseCategory.setCategory(request.getParameter("type"));
//		baseCategory.setRemark(request.getParameter("remark"));

        ModelAndView view = new ModelAndView("component/addtype/addtype_finish");
//		view.addObject("category", baseCategory);

//		String[] exnames=file.getOriginalFilename().split("\\.");
//		String ext=exnames[exnames.length-1].toLowerCase();
//		if ("jpg".equals(ext)||"jpeg".equals(ext)||"png".equals(ext)||"gif".equals(ext)||"bmp".equals(ext)){
//			if(file.getSize()>10000000)
//				throw new Exception("上传失败：图片大小不能超过10M");
//				categoryService.createCategory(baseCategory);
//                file.transferTo(
//                        new File(
//                                request.getSession().getServletContext().getRealPath("/")
//                                        + "resource\\Typeimage\\"
//                                        + baseCategory.getCategory() + ".png")
//                );
//				//return "";
//		}else {
//			throw new Exception("不是图片类型");
//		}

        return view;
    }
}
