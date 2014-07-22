package com.lemsun.web.serverdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-9-10
 * Time: 下午4:47
 */
@Controller
public class IndexController {

	@RequestMapping(value = {"/", "/index"})
	public String index() {
		return "index";
	}

}
