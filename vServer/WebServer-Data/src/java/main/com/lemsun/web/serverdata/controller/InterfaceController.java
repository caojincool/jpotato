package com.lemsun.web.serverdata.controller;

import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.IAccount;
import com.lemsun.core.Plateform;
import com.lemsun.core.PlateformInstance;
import com.lemsun.core.service.IPlateformInstanceService;
import com.lemsun.core.service.IPlateformService;
import com.lemsun.web.model.ResponseEntity;
import com.lemsun.web.serverdata.controller.model.InterfaceAccountLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

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
     * 获取平台实例信息. 并更新信息内容
     * @param pf 平台模型
     * @param code 实例Code
     * @return 实例对象
     */
    @RequestMapping("{plateform}/{code}")
    public ResponseEntity<PlateformInstance> getPlateformInstance(
            @PathVariable("plateform") String pf,
            @PathVariable("code") String code) {

            PlateformInstance instance = context.getBean(IPlateformInstanceService.class).get(code);

        return new ResponseEntity<>(instance);
    }

    /**
     * 执行用户登录
     * @return
     */
    public ResponseEntity<IAccount> doLogin(InterfaceAccountLogin loginModel) {

        IAccountService accountService = context.getBean(IAccountService.class);



        return null;
    }

}
