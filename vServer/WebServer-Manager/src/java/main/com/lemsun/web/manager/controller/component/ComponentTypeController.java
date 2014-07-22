package com.lemsun.web.manager.controller.component;

import com.lemsun.core.BaseCategory;
import com.lemsun.core.ICategory;
import com.lemsun.core.service.ICategoryService;
import com.lemsun.web.manager.model.component.BaseCategoryTreeStore;
import com.lemsun.web.model.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 组件类别管理
 * User: Lucklim
 * Date: 12-11-19
 * Time: 下午12:42
 */
@Controller
@RequestMapping("component/componenttype")
public class ComponentTypeController {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = {"","/","view"})
    public String view()
    {
        return "admin/component/componentType";
    }

    @ResponseBody
    @RequestMapping("list/get")
    public List<BaseCategoryTreeStore> getData()
    {
        ICategory baseCategory= categoryService.getRootCategoryWithChild();

        return (new BaseCategoryTreeStore()).convertBaseCategory(baseCategory, true);
    }

    @ResponseBody
    @RequestMapping("list/getnotrootcategory")
    public List<BaseCategoryTreeStore> getNotRootCategory(String parentid,String category)
    {
        List<BaseCategory> baseCategories = null;
        if(StringUtils.isEmpty(parentid))
            baseCategories=categoryService.getRootsonCategory();
        else
        {
            baseCategories=categoryService.getCategoryByResourceCategory(category);
        }
        return (new BaseCategoryTreeStore()).convertBaseCategory(baseCategories);
    }


    @RequestMapping(value = "add",method =RequestMethod.POST)
    public ModelAndView createBaseCategory(HttpServletRequest request, String typename,String componenttype,String componentremark,@RequestParam("comicon") MultipartFile file,String pid) throws Exception {

//		ResponseEntity responseEntity = new ResponseEntity();
        BaseCategory parentBaseCategory= categoryService.getBaseCategoryByPid(pid);
        if(parentBaseCategory!=null)
        {
            BaseCategory baseCategory=new BaseCategory(typename,componenttype,parentBaseCategory);
			//2012-12-20 add gm
			baseCategory.setRemark(componentremark);

			//2012-12-28 add gm
			String[] exnames=file.getOriginalFilename().split("\\.");
			String ext=exnames[exnames.length-1].toLowerCase();
			if ("jpg".equals(ext)||"jpeg".equals(ext)||"png".equals(ext)||"gif".equals(ext)||"bmp".equals(ext)){
				if(file.getSize()>10000000)
					throw new Exception("上传失败：图片大小不能超过10M");
				try{
					baseCategory.setImgiconsize(file.getSize());
//					categoryService.createCategoryAndIcon(baseCategory, file,request.getSession().getServletContext().getRealPath("/"));
//					responseEntity.setSuccess(true);
					return new ModelAndView("redirect:/component/componenttype");
				}catch (Exception ex)
				{
//					responseEntity.setSuccess(false);
					throw new Exception("保存数据出现异常！");
				}
			}else {
//				responseEntity.setSuccess(false);
				throw new Exception("不是图片类型");
			}
        }
        return new ModelAndView("redirect:/component/componenttype");
//		return responseEntity;
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity updateBaseCategory(String typename,String componenttype,String pid)
    {
        ResponseEntity responseEntity=new ResponseEntity();
        BaseCategory baseCategory= categoryService.getBaseCategoryByPid(pid);
        if(baseCategory==null)
        {
            responseEntity.setSuccess(false);
            return responseEntity;
        }
        baseCategory.setName(typename);
        categoryService.updateBaseCategory(baseCategory);
        responseEntity.setSuccess(true);
        return responseEntity;
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public void deleteBaseCategory(String pid) throws Exception {
        categoryService.deleteBaseCategoryByPid(pid);
    }
}
