package com.lemsun.web.manager.controller.component.editer;

import com.lemsun.auth.BaseAccount;
import com.lemsun.core.IResource;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.ResourceState;
import com.lemsun.form.ScriptResource;
import com.lemsun.form.service.IScriptResourceService;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.component.ScriptResourceModel;
import com.lemsun.web.manager.controller.model.query.ScriptQuery;
import com.lemsun.web.model.ResponseEntity;
import com.lemsun.web.model.view.ScriptItemView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
 */
@Controller
@RequestMapping("component/script")
public class ScriptController extends  BaseController{

    @Autowired
    @Qualifier("scriptResourceServiceImpl")
    IScriptResourceService scriptResourceService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request) throws Exception {
        LemsunResource resource = ((LemsunResource) request.getSession().
                getAttribute("resource"));

        ModelAndView view = new ModelAndView();

        if (resource == null){
            view.setViewName("redirect:" + PrepareModelInteceptor.getRootPath() + "component/main/create");
        }else{
            ScriptResource scriptResource=scriptResourceService.get(resource.getPid());
            view.addObject("lemsunResource", mapper.writeValueAsString(scriptResource));
            view.setViewName("component/add/Script");
        }
        return view;
    }

    @RequestMapping(value = "{pid}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String pid) throws Exception {
        ScriptResource scriptResource=scriptResourceService.get(pid);

        ModelAndView view=new ModelAndView("component/editer/Script");
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

    /**
     * 保存组件
     * @param lr
     * @param model
     * @throws Exception
     */
    private void savaResource(LemsunResource lr, ScriptResourceModel model) throws Exception {
        ScriptResource resource = new ScriptResource(lr);
        model.encapseResource(resource);
        resource.setState(ResourceState.RELEASE);
        scriptResourceService.update(resource);
        scriptResourceService.updateScriptContent(lr,model.getContext());
    }

    /**
     * 显示组件视图
     */
    @RequestMapping("view")
    public ModelAndView view(HttpServletRequest request, ScriptQuery query) throws IOException {
        return super.view(query);
    }

    @Override
    protected ComponentQueryHandler createComponentQueryHandler() throws IOException {
        ComponentQueryHandler handler= new ComponentQueryHandler(){

            @Override
            protected Map<String, Integer> createMap() {
                Map<String,Integer> map=new HashMap<>();
                map.put("head",1);
                map.put("left",11);
                return map;
            }
            @Override
            protected  Page<ScriptResource> executeQueryComponent() {
                return   scriptResourceService.getPageing(query);
            }

            @Override
            protected  Page<ScriptItemView> afterRelatedQueryHandler() {
                List<ScriptItemView> webskinItemViewList=new ArrayList<>(data.getSize());
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
                    ScriptItemView webskinItemView=new ScriptItemView(webskin,account);
                    webskinItemViewList.add(webskinItemView);

                }

                return  new PageImpl<>(webskinItemViewList, query, data.getTotalElements());
            }
        };
        return handler;
    }

    @Override
    protected IResource getIResource(String pid) throws Exception {
        return scriptResourceService.get(pid);
    }

    @Override
    void deleteResource(String pid) throws Exception {
        scriptResourceService.delete(pid);
    }
}
