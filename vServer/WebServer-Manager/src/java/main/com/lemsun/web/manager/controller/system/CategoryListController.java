package com.lemsun.web.manager.controller.system;

import com.lemsun.core.Plateform;
import com.lemsun.core.service.IPlateformService;
import com.lemsun.web.manager.model.system.CategoryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-11-19
 * Time: 下午12:51
 */
@Controller
@RequestMapping("system/category")
public class CategoryListController {

	@Autowired
	private IPlateformService plateformService;

	private final static Logger log = LoggerFactory.getLogger(CategoryListController.class);

	/**
	 * 获取 category为 PLATEFORM 平台视图
	 * @return 网页平台信息
	 */
	@RequestMapping(value = {"","/","view","/web"})
	public ModelAndView view()
	{
		ModelAndView view=new ModelAndView("admin/system/categoryList");
		Plateform pf=plateformService.getByCategory("PLATEFORM");
		view.addObject("listone",pf);
		return view;
	}

	/**
	 * 获取 category为 WPF 平台视图
	 * @return WPF 平台信息
	 */
	@RequestMapping("/wpf")
	public ModelAndView getWpfView()
	{
		ModelAndView view=new ModelAndView("admin/system/categoryWPFPage");
		Plateform pf=plateformService.getByCategory("WPF");
		view.addObject("listone",pf);
		return view;
	}

	/**
	 * 修改 网页 平台信息
	 * @param context 待修改的 网页 平台信息表单
	 * @return 返回修改后的 网页 平台信息表单
	 */
	@RequestMapping(value = "/web/update", method = RequestMethod.POST)
	public ModelAndView updateWebContext(CategoryModel context) throws Exception {
		if(log.isDebugEnabled()) log.debug("获取要修改的系统类型对象数据 : {}", context);

		//plateformService.get(String pid)方法是比较的数据库表中pid字段
		Plateform plateform=plateformService.get(context.getCpid());
		plateform.setStartScript(context.getCstartscript());
		plateform.setEndScript(context.getCendscript());
		plateform.setKey(context.getCkey());
		try{
			if(context.getCparam().length()>2)
			{
				String parm = context.getCparam();
//				String p1 = parm.substring(1,parm.length()-1);
				String[] par = parm.split(",|\n|&");

				if(par.length>0)
				{
					for (String aPar : par) {
						plateform.setAttribute(aPar.split("=")[0].trim(), aPar.split("=")[1].trim());
					}
				}
			}
		}
		catch (Exception e)
		{
			throw new Exception("附加集合输入不规范，系统无法处理！");
		}
		plateform.setUpdateTime(new Date());

		plateformService.update(plateform);

//		ModelAndView view=new ModelAndView(new RedirectView("../../../system/category/web"));
		ModelAndView view=new ModelAndView("redirect:/system/category/web");
		view.addObject("listone",plateformService.get(context.getCpid()));
		return view;
	}

	/**
	 * 修改 WPF 平台信息
	 * @param context 待修改的 WPF 平台信息表单
	 * @return 返回修改后的 WPF 平台信息表单
	 */
	@RequestMapping(value = "/wpf/update", method = RequestMethod.POST)
	public ModelAndView updateWpfContext(CategoryModel context) throws Exception {
		if(log.isDebugEnabled()) log.debug("获取要修改的系统类型对象数据 : {}", context);

		//plateformService.get(String pid)方法是比较的数据库表中pid字段
		Plateform plateform=plateformService.get(context.getCpid());
		plateform.setStartResource(context.getCstartResource());
		plateform.setStartScript(context.getCstartscript());
		plateform.setEndScript(context.getCendscript());
		try{
			if(context.getCparam().length()>0)
			{
				String parm = context.getCparam();
//				String p1 = parm.substring(1,parm.length()-1);
				String[] par = parm.split(",|\n|&");
				if(par.length>0)
				{
					for (String aPar : par) {
						plateform.setAttribute(aPar.split("=")[0].trim(), aPar.split("=")[1].trim());
					}
				}
			}
		}
		catch (Exception e){
			throw new Exception("附加集合输入不规范，系统无法处理！");
		}
		plateform.setUpdateTime(new Date());

		plateformService.update(plateform);

//		ModelAndView view=new ModelAndView(new RedirectView("../../../system/category/wpf"));
		ModelAndView view=new ModelAndView("redirect:/system/category/wpf");
		view.addObject("listone",plateformService.get(context.getCpid()));
		return view;
	}
}
