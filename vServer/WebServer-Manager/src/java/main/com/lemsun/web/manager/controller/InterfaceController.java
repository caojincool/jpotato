package com.lemsun.web.manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemsun.core.IResource;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.Plateform;
import com.lemsun.core.service.IPlateformService;
import com.lemsun.core.service.IResourceService;
import com.lemsun.data.connection.TableSet;
import com.lemsun.data.service.ITableService;
import com.lemsun.data.tables.TableDataQuery;
import com.lemsun.data.tables.TableResource;
import com.lemsun.form.FormResource;
import com.lemsun.form.ScriptResource;
import com.lemsun.form.WpfPageResource;
import com.lemsun.form.service.IWpfPageResourceService;
import com.lemsun.form.service.IWpfScriptResourceService;
import com.lemsun.web.manager.model.entity.ResourceRequest;
import com.lemsun.web.manager.model.entity.ScriptRequest;
import com.lemsun.web.model.ResponseEntity;
import net.sf.cglib.core.CollectionUtils;
import net.sf.cglib.core.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 对外接口.
 * User: Xudong
 * Date: 12-11-2
 * Time: 下午4:32
 */
@Controller
@RequestMapping("/interface/*")
public class InterfaceController {
	@Autowired
	private ApplicationContext context;



    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

	/**
	 * 获取平台资源数据
	 */
	@RequestMapping("{plateform}")
	public ResponseEntity<Plateform> getPlateform(@PathVariable("plateform") String pf)
	{
		IPlateformService service = context.getBean(IPlateformService.class);

		Plateform plateform = service.getByCategory(pf.toUpperCase());

        return new ResponseEntity<>(plateform);
	}

	/**
	 * 获取平台的全局脚本对象
	 */
	@RequestMapping("{plateform}/script/get")
	public ResponseEntity<List> getPlateformScript(@PathVariable("plateform") String pf, ScriptRequest request)
	{
		List data = null;

		if(StringUtils.equalsIgnoreCase(pf, "wpf"))
		{
			data = context.getBean(IWpfScriptResourceService.class).getAll(request.getParentPid());
		}


        return new ResponseEntity<>(data);
	}


	/**
	 * 获取组件对象
	 */
	@RequestMapping("{plateform}/resource/get")
	public ResponseEntity<IResource> getPlateformResource(@PathVariable("plateform") String pf, ResourceRequest request)
	{
		if(StringUtils.equalsIgnoreCase(pf, "wpf"))
		{
			IResource resource = null;

			String type = request.getType();
			String pid = request.getPid();

			if(LemsunResource.TYPE.equalsIgnoreCase(type))
				resource = context.getBean(IResourceService.class).getBaseResource(pid);

			if(FormResource.TYPE.equalsIgnoreCase(type))
				resource = context.getBean(IWpfPageResourceService.class).get(pid);

			if(TableResource.TYPE.equalsIgnoreCase(type))
				resource = context.getBean(ITableService.class).get(pid);

            return new ResponseEntity<>(resource);
		}
		return null;
	}


	/**
	 * 获取组件内容信息
	 * @param pf 平台信息
	 * @param request      组件请求对象
	 * @param response 回复对象
	 * @throws IOException 执行异常
	 */
	@RequestMapping("{plateform}/resource/content/get")
	public void getPlateformResourceContent(@PathVariable("plateform") String pf, ResourceRequest request, HttpServletResponse response)
			throws IOException {
		if(StringUtils.equalsIgnoreCase(pf, "wpf"))
		{

		}

		IResourceService service = context.getBean(IResourceService.class);
		//WpfPageResource resource = service.

		String context = service.getResourceContext(request.getPid());
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(context);

	}

	/**
	 * 获取表格数据接口
	 * @param query 分页信息
	 * @return 数据内容
	 * @throws Exception 执行异常
	 */
	@RequestMapping("{plateform}/resource/data/get")
	public ResponseEntity<TableSet> getTableResourceData(@PathVariable("plateform") String pf, TableDataQuery query) throws Exception {
		return new ResponseEntity<>(context.getBean(ITableService.class).getTableData(query));
	}


    public void getTableResourceDataCount() {
        //context.getBean(ITableService.class).
    }

}
