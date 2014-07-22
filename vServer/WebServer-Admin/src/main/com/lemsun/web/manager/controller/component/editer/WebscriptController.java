package com.lemsun.web.manager.controller.component.editer;

import com.lemsun.auth.BaseAccount;
import com.lemsun.core.IResource;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.ResourceState;
import com.lemsun.form.WebScriptResource;
import com.lemsun.form.service.IWebScriptResourceService;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.component.ScriptResourceModel;
import com.lemsun.web.manager.controller.model.query.WebscriptQuery;
import com.lemsun.web.model.ResponseEntity;
import com.lemsun.web.model.view.WebScriptItemView;
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
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-23
 * Time: 上午11:40
 * 脚本组件的基本管理
 */
@Controller
@RequestMapping("component/webscript")
public class WebscriptController extends BaseController{

    @Autowired
    IWebScriptResourceService webScriptResourceService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request) throws Exception {
        LemsunResource resource = ((LemsunResource) request.getSession().
                getAttribute("resource"));

        ModelAndView view = new ModelAndView();

        if (resource == null){
            view.setViewName("redirect:" + PrepareModelInteceptor.getRootPath() + "component/main/create");
        }else{
            WebScriptResource scriptResource=webScriptResourceService.get(resource.getPid());
            view.addObject("lemsunResource", mapper.writeValueAsString(scriptResource));
            view.setViewName("component/add/Webscript");
        }
        return view;
    }

    @RequestMapping(value = "{pid}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String pid) throws Exception {
        WebScriptResource scriptResource=webScriptResourceService.get(pid);

        ModelAndView view=new ModelAndView("component/editer/Webscript");
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
        WebScriptResource resource = new WebScriptResource(lr);
        model.encapseResource(resource);
        resource.setState(ResourceState.RELEASE);
        webScriptResourceService.update(resource);
        webScriptResourceService.updateScriptContent(lr,model.getContext());
    }

    @RequestMapping("view")
    public ModelAndView view(HttpServletRequest request, WebscriptQuery query) throws IOException {
        return super.view(query);
    }

    @Override
    protected ComponentQueryHandler createComponentQueryHandler() throws IOException {
        ComponentQueryHandler handler= new ComponentQueryHandler(){

            @Override
            protected Map<String, Integer> createMap() {
                Map<String,Integer> map=new HashMap<>();
                map.put("head",1);
                map.put("left",13);
                return map;
            }
            @Override
            protected  Page<WebScriptResource> executeQueryComponent() {
                return   webScriptResourceService.getPageing(query);
            }

            @Override
            protected  Page<WebScriptItemView> afterRelatedQueryHandler() {
                List<WebScriptItemView> webskinItemViewList=new ArrayList<>(data.getSize());
                for(IResource webskin: data.getContent()){
                    BaseAccount account=null;
                    try{
                        account=accountService.getAccountByAccount(webskin.getCreateUser());
                    }catch (Exception e){
                        logger.info("异常数据",e.getMessage());
                    }
                    if(account==null){
                        account=new BaseAccount();
                    }
                    WebScriptItemView webskinItemView=new WebScriptItemView(webskin,account);
                    webskinItemViewList.add(webskinItemView);

                }

                return  new PageImpl<>(webskinItemViewList, query, data.getTotalElements());
            }
        };
        return handler;
    }
    @Override
    protected IResource getIResource(String pid) throws Exception {
        return webScriptResourceService.get(pid);
    }

    @Override
    void deleteResource(String pid) throws Exception {
        webScriptResourceService.delete(pid);
    }
}
