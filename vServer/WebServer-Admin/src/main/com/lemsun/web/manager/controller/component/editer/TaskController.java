package com.lemsun.web.manager.controller.component.editer;

import com.lemsun.auth.BaseAccount;
import com.lemsun.core.IResource;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.ResourceState;
import com.lemsun.form.ScriptResource;
import com.lemsun.form.WpfScriptResource;
import com.lemsun.form.service.IScriptResourceService;
import com.lemsun.task.TaskResource;
import com.lemsun.task.service.ITaskService;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.component.ScriptResourceModel;
import com.lemsun.web.manager.controller.model.component.TaskResourceModel;
import com.lemsun.web.manager.controller.model.query.ScriptQuery;
import com.lemsun.web.manager.controller.model.query.TaskQuery;
import com.lemsun.web.model.ResponseEntity;
import com.lemsun.web.model.view.ScriptItemView;
import com.lemsun.web.model.view.TaskItemView;
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
 * User: 刘晓宝
 * Date: 14-3-11
 * Time: 上午10:21
 */
@Controller
@RequestMapping("component/task")
public class TaskController  extends BaseController{

    @Autowired
    private ITaskService taskService;
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request) throws Exception {
        LemsunResource resource = ((LemsunResource) request.getSession().
                getAttribute("resource"));
        ModelAndView view = new ModelAndView();
        if (resource == null){
            view.setViewName("redirect:" + PrepareModelInteceptor.getRootPath() + "component/main/create");
        }else{
            TaskResource taskResource=taskService.get(resource.getPid());
            view.addObject("lemsunResource", mapper.writeValueAsString(taskResource));
            view.setViewName("component/add/task");
        }
        return view;
    }
    @RequestMapping(value = "{pid}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String pid) throws Exception {
        TaskResource scriptResource=taskService.get(pid);
        ModelAndView view=new ModelAndView("component/editer/task");
        view.addObject("lemsunResource", mapper.writeValueAsString(scriptResource));
        return view;
    }
    @RequestMapping(value = "{pid}/edit", method = RequestMethod.POST)
    public ResponseEntity<String> doEdit(HttpServletRequest request)  {
        ResponseEntity<String> responseEntity= new ResponseEntity<>();
        try {
            request.getParameterValues("week");
            TaskResourceModel model = mapper.readValue(request.getReader(), TaskResourceModel.class);
            LemsunResource lr = resourceService.getBaseResource(model.getPid());
            savaResource(lr, model);
            responseEntity.setSuccess(true);
        } catch (Exception e) {
            responseEntity.setSuccess(false);
            responseEntity.setMessage("更新脚本失败");
        }
        return responseEntity;
    }

    @Override
    void deleteResource(String pid) throws Exception {
        taskService.delete(pid);
    }

    /**
     * 保存组件
     * @param lr
     * @param model
     * @throws Exception
     */
    private void savaResource(LemsunResource lr, TaskResourceModel model) throws Exception {
        TaskResource resource = new TaskResource(lr);
        model.encapseResource(resource);
        taskService.update(resource);
    }
    /**
     * 显示组件视图
     */
    @RequestMapping("view")
    public ModelAndView view(HttpServletRequest request, TaskQuery query) throws IOException {
        return super.view(query);
    }
    @Override
    protected ComponentQueryHandler createComponentQueryHandler() throws IOException {
        ComponentQueryHandler handler= new ComponentQueryHandler(){

            @Override
            protected Map<String, Integer> createMap() {
                Map<String,Integer> map=new HashMap<>();
                map.put("head",1);
                map.put("left",19);
                return map;
            }
            @Override
            protected Page<TaskResource> executeQueryComponent() {
                return   taskService.getPageing(query);
            }

            @Override
            protected  Page<TaskItemView> afterRelatedQueryHandler() {
                List<TaskItemView> webskinItemViewList=new ArrayList<>(data.getSize());
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
                    TaskItemView webskinItemView=new TaskItemView(webskin,account);
                    webskinItemViewList.add(webskinItemView);

                }

                return  new PageImpl<>(webskinItemViewList, query, data.getTotalElements());
            }
        };
        return handler;
    }
}
