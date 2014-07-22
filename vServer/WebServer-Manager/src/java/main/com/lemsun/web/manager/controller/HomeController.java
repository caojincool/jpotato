package com.lemsun.web.manager.controller;

import com.lemsun.core.Plateform;
import com.lemsun.core.service.IPlateformService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-19
 * Time: 下午6:10
 */
@Controller
public class HomeController {

	@Autowired
	private IPlateformService plateformService;

	@RequestMapping(value = {"/", "/index"})
	public String index()
	{
		Plateform web = plateformService.get("PLATEFORM00000001");

		if(StringUtils.isEmpty(web.getStartResource()))
		  	return "index";

		return web.getStartResource();
	}

}
