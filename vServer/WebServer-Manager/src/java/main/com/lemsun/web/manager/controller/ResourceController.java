package com.lemsun.web.manager.controller;

import com.lemsun.core.*;
import com.lemsun.core.service.ICategoryService;
import com.lemsun.core.service.INavigationService;
import com.lemsun.core.service.IResourceService;
import com.lemsun.form.WebPageResource;
import com.lemsun.form.service.IFormService;
import com.lemsun.form.service.IWebPageResourceService;
import com.lemsun.web.manager.model.CategoryTreeNode;
import com.lemsun.web.manager.model.ResourceCreate;
import com.lemsun.web.manager.model.ResourceListQuery;
import com.lemsun.web.model.ExtTreeNode;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-19
 * Time: 下午6:23
 */
@Controller
@RequestMapping("/resource/*")
public class ResourceController {

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private INavigationService navigationService;


	@Autowired
	private IResourceService resourceService;

	@Autowired
	private IFormService formService;

	@Autowired
	private ApplicationContext context;


	@RequestMapping("/list")
	public ModelAndView List()
	{
		ModelAndView view = new ModelAndView("resource/list");

		//类型数据
		ICategory root = categoryService.getRootCategory();
		CategoryTreeNode tree = new CategoryTreeNode();
		//loadData(root, tree);
		tree.loadTreeNode(root);

		view.addObject("category", tree);

		//导航数据
		Navigation nav = navigationService.getRoot();
		ExtTreeNode navTree = new ExtTreeNode();

		//loadData(nav, navTree);
		navTree.loadTreeNode(nav);

		view.addObject("navigate", navTree);
		return view;
	}

	@RequestMapping("/{pid}/page")
	public ModelAndView show(@PathVariable("pid") String id)
	{
		id = "form:" + id.toUpperCase();

		ModelAndView view = new ModelAndView(id);
		return view;
	}



	@ResponseBody
	@RequestMapping("/list/get")
	public Page<LemsunResource> getResources(ResourceListQuery query)
	{
		return resourceService.getPagging(query, query.getCategory(), query.getName());
	}


	/**
	 * 创建一个新的资源组件
	 * @param category
	 * @return
	 */
	@RequestMapping("/{category}/create")
	public ModelAndView createResource(@PathVariable String category)
	{

		ICategory cate = categoryService.getCategory(category);

		ModelAndView view = new ModelAndView("resource/webpage_resource_creater");


		view.addObject("category", cate);

		return view;
	}

	@RequestMapping(value = "/{category}/create", method = RequestMethod.POST)
	public ModelAndView categoryResource(ResourceCreate create, HttpServletRequest request)
	{

		ModelAndView view = new ModelAndView("resource/webpage_resource_creater");

		WebPageResource resource = new WebPageResource(create.getName(), create.getCategory());
		resource.setRemark(create.getRemark());
		List<String> names = create.getNames();
		if(!CollectionUtils.isEmpty(names))
		{
			List<String> values = create.getValues();
			for(int i=0; i<names.size(); i++)
			{
				resource.setAttribute(names.get(i),values.get(i));
			}
		}


		IWebPageResourceService formService = context.getBean(IWebPageResourceService.class);

		try {
			formService.save(resource);
			//创建成功. 跳转到更新内容
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			//return "redirect:" + basePath + resource.getPid() + "/context";
			view.setViewName("redirect:" + basePath + "resource/" + resource.getPid() + "/context");
		} catch (Exception e) {
			//TODO 退回重新提交, 设置异常信息



		}

		return view;
	}


	/**
	 *
	 * 创建资源内容页面
	 */
	@RequestMapping("/{pid}/context")
	public ModelAndView createResource(@PathVariable("pid") String id, HttpServletResponse response) {

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);


		ModelAndView view = new ModelAndView("resource/webpage_resource_context");

		WebPageResource resource = formService.getResource(id, WebPageResource.class);

		//IWebPageResourceService formService = context.getBean(IWebPageResourceService.class);



		if(resource == null)
		{
			view.setViewName("resource/webpage_resource_create");
		}
		else
		{
			String context = formService.getResourceContext(resource);
			view.addObject("resource", resource);
			view.addObject("context", context);
		}

		return view;
	}


	/**
	 * 更新表单内容
	 * @return 成功或者失败地址
	 */
	@RequestMapping(value = "/{pid}/context/update", method = RequestMethod.POST)
	public ModelAndView updateResourceContext(@PathVariable("pid") String id, String context) {
		ModelAndView view = new ModelAndView("resource/webpage_resource_context_update");

		WebPageResource resource = formService.getResource(id, WebPageResource.class);


		formService.updateResourceContext(resource, context);

		return view;
	}

}
