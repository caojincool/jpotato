package com.lemsun.web.manager.controller.iservice;

import com.lemsun.core.*;
import com.lemsun.core.formula.IFunctionStatement;
import com.lemsun.core.service.IPlateformInstanceService;
import com.lemsun.form.InnerFunctionDefines;
import com.lemsun.form.ScriptResource;
import com.lemsun.form.WebScriptResource;
import com.lemsun.form.WpfScriptResource;
import com.lemsun.form.service.*;
import com.lemsun.reporter.ReporterScriptResource;
import com.lemsun.reporter.service.IReporterScriptResourceService;
import com.lemsun.web.model.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 脚本组件的获取控制器
 * User: Xudong
 * Date: 13-1-29
 * Time: 下午2:32
 */
@Controller("interfaceScriptController")
@RequestMapping("/interface/{plateform}/script")
public class ScriptController {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private IPlateformInstanceService plateformInstanceService;

    /**
     * 获取脚本对象
     *
     * @param plateform 实例票据
     * @param type 请求类型, 在一个平台中的类型
     */
    @RequestMapping("get")
    public ResponseEntity<List<IFunctionStatement>> getGlobals(@PathVariable("plateform") String plateform, String type) throws Exception {

        PlateformInstance instance = plateformInstanceService.getPlatefrmByToken(plateform);

        if (instance == null)
            throw PlateformException.NotPlateformException;

        List<IFunctionStatement> resources = new ArrayList<>();

        //获取全局脚本
        IScriptResourceService scriptResourceService=context.getBean(IScriptResourceService.class);
       List<ScriptResource> rs = scriptResourceService.getAll();

        for (ScriptResource scriptResource:rs){
            scriptResource.setContext(scriptResourceService.getScriptContent(scriptResource));
        }

        if(rs != null) resources.addAll(rs);


        //获取类型的全局脚本
        if (StringUtils.equalsIgnoreCase(instance.getOwner().getCategory(), Plateform.WPF.getCategory())) {
            IReporterScriptResourceService reporterScriptResourceService=context.getBean(IReporterScriptResourceService.class);

            List<ReporterScriptResource>rsr=reporterScriptResourceService.getAll();

            for (ReporterScriptResource r : rsr) {
                r.setContext(reporterScriptResourceService.getContent(r));
                resources.add(r);
            }




           if(StringUtils.equalsIgnoreCase("wpf", type)) {
               IWpfScriptResourceService service = context.getBean(IWpfScriptResourceService.class);


               List<WpfScriptResource> res = service.getAll();

               for (WpfScriptResource r : res) {
                   r.setContext(service.getScriptContent(r));
                   resources.add(r);
               }

           }





        } else if (StringUtils.equalsIgnoreCase(instance.getOwner().getCategory(), Plateform.PLATEFORM.getCategory())) {
            IWebScriptResourceService service = context.getBean(IWebScriptResourceService.class);
            List<WebScriptResource> res = service.getAll();

            for (WebScriptResource r : res) {
                r.setContext(service.getScriptContent(r));
                resources.add(r);
            }
        }

        return new ResponseEntity<>(resources);
    }


    /**
     * 获取脚本内容
     *
     * @param plateform 实例对象
     * @param pid       脚本主键
     */
    @RequestMapping("content/get")
    public ResponseEntity<IFunctionStatement> getContent(@PathVariable("plateform") String plateform,
                                             String pid) {
        IWpfScriptResourceService service = context.getBean(IWpfScriptResourceService.class);
        IFunctionStatement scriptResource= service.get(pid);

        return new ResponseEntity<>(scriptResource);
    }


    /**
     * 获取组件的内部定义的函数
     * @param pid 组件PID
     * @return
     */
    @RequestMapping("inner/get")
    public ResponseEntity<List<InnerFunctionDefines>> getInnerFunctionStatement(String pid)
    {
        IFormResourceService service = context.getBean(IFormResourceService.class);
        List<InnerFunctionDefines> functionDefinesList=service.getStatementsByParentpid(pid);
        return new ResponseEntity<>(functionDefinesList);
    }
}
