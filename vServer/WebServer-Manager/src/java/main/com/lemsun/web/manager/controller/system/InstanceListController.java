package com.lemsun.web.manager.controller.system;

import com.lemsun.core.Plateform;
import com.lemsun.core.PlateformInstance;
import com.lemsun.core.service.IPlateformInstanceService;
import com.lemsun.core.service.IPlateformService;
import com.lemsun.web.manager.model.system.CategoryModel;
import com.lemsun.web.manager.model.system.InstanceModel;
import com.lemsun.web.manager.model.system.InstanceBean;
import com.lemsun.web.model.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-11-19
 * Time: 下午12:49
 */
@Controller
@RequestMapping("system/instance")
public class InstanceListController {

	@Autowired
	private IPlateformService plateformService;

	@Autowired
	private IPlateformInstanceService plateformInstanceService;

	private final static Logger log= LoggerFactory.getLogger(InstanceListController.class);

	/**
	 * 默认系统实例路由
	 * @return 系统实例集合界面
	 */
	@RequestMapping(value = {"","/","view","/list"})
	public String view()
	{
		return "admin/system/instanceList";
	}


	/**
	 * 分页获取系统实例路由
	 * @param query 查询条件
	 * @return 系统实例分页数据
	 */
	@ResponseBody
	@RequestMapping("/list/get")
	public ResponseEntity<ArrayList<InstanceBean>> getData(InstanceBean query)
	{
		ResponseEntity<ArrayList<InstanceBean>> responseEntity=new ResponseEntity<ArrayList<InstanceBean>>();

        Page<PlateformInstance> result = plateformInstanceService.getPagging(query);

		ArrayList<InstanceBean> list=new ArrayList<InstanceBean>();
		for(int i=0;i<result.getContent().size();i++)
		{
			InstanceBean dt=new InstanceBean();
			dt.setId(result.getContent().get(i).getPid());
			dt.setName(result.getContent().get(i).getName());
			dt.setIp(result.getContent().get(i).getIp());
			dt.setSysName(result.getContent().get(i).getOwner().getName());
			dt.setAddress(result.getContent().get(i).getAddress());
			dt.setTime(result.getContent().get(i).getQyDate());
			list.add(dt);
		}
        //setSuccess设置为false时，不会刷新数据
		responseEntity.setSuccess(true);
		responseEntity.setEntity(list);
		responseEntity.setTotalCount(result.getTotalElements());
		return responseEntity;
	}

	/**
	 * 获取所有系统类型
	 * @param response  标识
	 * @return 所有系统类型集合
	 */
	@ResponseBody
	@RequestMapping("/list/getcategory")
	public ResponseEntity<ArrayList<CategoryModel>> getAllCategoryData(HttpServletResponse response)
	{
		ResponseEntity<ArrayList<CategoryModel>> responseEntity=new ResponseEntity<ArrayList<CategoryModel>>();

		List<Plateform> result = plateformService.getAllCategory();
		ArrayList<CategoryModel> list=new ArrayList<CategoryModel>();
		for (Plateform aResult : result) {
			CategoryModel dt = new CategoryModel();
			dt.setCpid(aResult.getPid());
			dt.setCname(aResult.getName());
			list.add(dt);
		}

		if(list.size()>0)responseEntity.setSuccess(true);
		responseEntity.setEntity(list);
		return responseEntity;
	}

	/**
	 * 新增实例界面路由
	 * @return 跳转到新增实例界面
	 */
	@RequestMapping("/create")
	public ModelAndView createView()
	{
		List<Plateform> pf = plateformService.getAllCategory();
		ModelAndView view=new ModelAndView("admin/system/instanceOperate");
		view.addObject("categorylist",pf);
		return view;
	}

	/**
	 * 新增系统实例
	 * @param context 实例对象
	 * @return 系统列表界面
	 * @throws ParseException 日期转换异常
	 */
	@RequestMapping(value = "/create/save", method = RequestMethod.POST)
	public ModelAndView saveContext(InstanceModel context) throws ParseException {
		if(log.isDebugEnabled()) log.debug("获取要新增的系统类型对象数据 : {}", context);
        //实例化此类时，需传入两个参数，参数一：实例名称 参数二：类型
		PlateformInstance plateformInstance =new PlateformInstance(context.getName(),context.getCategorytype());
		plateformInstance.setName(context.getName());

		plateformInstance.setOwner(plateformService.get(context.getCategorytype()));
		plateformInstance.setRemark(context.getRemark());
		plateformInstance.setAddress(context.getAddress());
		try
		{
			plateformInstance.setQyDate(new SimpleDateFormat("yyyy-MM-dd").parse(context.getQyDate()));
			plateformInstance.setJsDate(new SimpleDateFormat("yyyy-MM-dd").parse(context.getJsDate()));
		}
		catch (ParseException ignored)
		{
			throw new ParseException("无法转换输入的日期，请确保输入日期格式无误！",0);
		}
		plateformInstance.setIp(context.getIp());
		plateformInstance.setTxKey(context.getTxKey());

		plateformInstance.setUser(context.getUser());
		plateformInstance.setJiqicode(context.getJiqicode());
		plateformInstance.setRemark(context.getRegRemark());

		plateformInstanceService.create(plateformInstance);

		return resetView();
	}

	/**
	 * 修改系统实例界面
	 * @param pid 系统实例pid
	 * @return 返回修改系统实例界面
	 */
	@RequestMapping("/{pid}/update")
	public ModelAndView getUpdateView(@PathVariable("pid") String pid)
	{
		if(StringUtils.isEmpty(pid))
			return resetView() ;

		if(log.isDebugEnabled())
			log.debug("修改ID为{}的实例信息",pid);

		PlateformInstance pfi=plateformInstanceService.get(pid);

		ModelAndView view=new ModelAndView("admin/system/instanceUpdate");
		view.addObject("listone",pfi);
		return view;
	}

	/**
	 * 修改系统实例
	 * @param context 系统实例模型
	 * @return 修改后，返回系统实例列表
	 * @throws Exception 修改实例异常
	 */
	@RequestMapping(value = "/update/save", method = RequestMethod.POST)
	public ModelAndView updateContext(InstanceModel context) throws Exception {

		if(log.isDebugEnabled()) log.debug("获取要修改的系统类型对象数据 : {}", context);

		PlateformInstance plateformInstance = plateformInstanceService.get(context.getPid());
		if(plateformInstance==null)
		{
			return resetView();
		}

		plateformInstance.setName(context.getName());
		plateformInstance.setRemark(context.getRemark());
		plateformInstance.setAddress(context.getAddress());

		plateformInstance.setIp(context.getIp());
		plateformInstance.setTxKey(context.getTxKey());

		plateformInstance.setUser(context.getUser());
		plateformInstance.setJiqicode(context.getJiqicode());
		plateformInstance.setRemark(context.getRegRemark());

		plateformInstanceService.update(plateformInstance);

		return resetView();
	}

	/**
	 * 系统实例预览界面路由
	 * @param pid 系统实例pid
	 * @return 系统实例预览界面
	 */
	@RequestMapping("/{pid}/preview")
	public ModelAndView getPreView(@PathVariable("pid") String pid)
	{
		if(StringUtils.isEmpty(pid))
			return resetView() ;

		if(log.isDebugEnabled())
			log.debug("预览ID为{}的实例信息",pid);

		PlateformInstance pfi= plateformInstanceService.get(pid);
		ModelAndView view=new ModelAndView("admin/system/instanceView");
		view.addObject("listone",pfi);
		return view;
	}

	/**
	 * 删除系统实例
	 * @param pid 系统实例pid
	 * @return 系统实例列表界面
	 * @throws Exception pid为空
	 */
	@RequestMapping("/{pid}/delete")
	public ModelAndView deleteByPid(@PathVariable("pid") String pid) throws Exception {

		if(StringUtils.isEmpty(pid))
			throw new Exception("pid为空，无法删除!");

		if(log.isDebugEnabled())
			log.debug("删除失败，ID为空{}",pid);

		plateformInstanceService.delete(pid);

		return resetView();
	}

	/**
	 * 返回系统实例界面方法
	 * @return 系统实例界面
	 */
	protected static ModelAndView resetView()
	{
		return new ModelAndView(new RedirectView("../../../system/instance"));
	}
}
