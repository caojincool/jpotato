package com.lemsun.web.manager.controller.component.editer;

import com.lemsun.auth.BaseAccount;
import com.lemsun.core.IResource;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.ResourceState;
import com.lemsun.reporter.ReporterScriptResource;
import com.lemsun.reporter.service.IReporterScriptResourceService;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.component.ScriptResourceModel;
import com.lemsun.web.manager.controller.model.query.ReportScriptQuery;
import com.lemsun.web.model.ResponseEntity;
import com.lemsun.web.model.view.ReporterScriptItemView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 填报脚本控制器
 * Created by dpyang on 2014/5/21.
 */
@Controller
@RequestMapping("component/rtscript")
public class ReporterScriptController extends BaseController{

    @Autowired
    IReporterScriptResourceService reporterScriptResourceService;


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView viewadd(HttpServletRequest request, String pid) throws Exception {
        LemsunResource resource = ((LemsunResource) request.getSession().
                getAttribute("resource"));

        ModelAndView view = new ModelAndView();

        if (resource == null)
            view.setViewName("redirect:" + PrepareModelInteceptor.getRootPath() + "component/main/create");
        else
            view.setViewName("component/add/reporterScript");

        ReporterScriptResource scriptResource=reporterScriptResourceService.get(resource.getPid());
        view.addObject("lemsunResource", mapper.writeValueAsString(scriptResource));

        return view;
    }

    @RequestMapping(value = "{pid}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String pid) throws Exception {
        ReporterScriptResource scriptResource=reporterScriptResourceService.get(pid);
        scriptResource.setContext(reporterScriptResourceService.getContent(pid));
        ModelAndView view=new ModelAndView("component/editer/rtscript");
        view.addObject("lemsunResource", mapper.writeValueAsString(scriptResource));
        return view;
    }


    @RequestMapping(value = "{pid}/edit", method = RequestMethod.POST)
    public ResponseEntity<String> doEdit(HttpServletRequest request)  {
        ResponseEntity<String> responseEntity= new ResponseEntity<>();
        try {
            ScriptResourceModel model = mapper.readValue(request.getReader(), ScriptResourceModel.class);
            LemsunResource lr = resourceService.getBaseResource(model.getPid());
            savaResource(lr, model);
            responseEntity.setSuccess(true);
        } catch (Exception e) {
            responseEntity.setSuccess(false);
            responseEntity.setMessage("更新脚本失败");
        }
        return responseEntity;
    }
    private void savaResource(LemsunResource lr, ScriptResourceModel model) throws Exception {
        ReporterScriptResource resource = new ReporterScriptResource(lr);
        model.encapseResource(resource);
        resource.setState(ResourceState.RELEASE);
        reporterScriptResourceService.update(resource);
        reporterScriptResourceService.updateContent(resource,model.getContext());
    }

    @RequestMapping("view")
    public ModelAndView view(ReportScriptQuery query) throws IOException {
        return super.view(query);
    }

    @Override

    void deleteResource(String pid) throws Exception {
        reporterScriptResourceService.remove(pid);
    }

    /**
     * 创建组件查询帮助内部类
     *
     * @return
     * @throws java.io.IOException
     */
    @Override
    protected ComponentQueryHandler createComponentQueryHandler() throws IOException {
        ComponentQueryHandler handler= new ComponentQueryHandler(){
            @Override
            protected Map<String, Integer> createMap() {
                Map<String,Integer> map=new HashMap<>();
                map.put("head",1);
                map.put("left",16);
                return map;
            }

            @Override
            protected Page<ReporterScriptResource> executeQueryComponent() {
                return reporterScriptResourceService.getPageing(query,ReporterScriptResource.class);
            }

            @Override
            protected  Page<ReporterScriptItemView> afterRelatedQueryHandler() {
                List<ReporterScriptItemView> reporterResources=new ArrayList<>(data.getSize());
                for(IResource reporter: data.getContent()){
                    BaseAccount account=null;
                    try{
                        account=accountService.getAccountByAccount(reporter.getCreateUser());
                    }catch (Exception e){
                        logger.info("异常数据",e.getMessage());
                    }
                    if(account==null){
                        account=new BaseAccount();
                    }

                    ReporterScriptItemView reporterItemView=new ReporterScriptItemView(reporter,account);
                    reporterResources.add(reporterItemView);
                }

                return  new PageImpl<>(reporterResources, query, data.getTotalElements());
            }
        };
        return handler;
    }
}
