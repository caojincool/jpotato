package com.lemsun.web.manager.controller.task;

import com.lemsun.task.TaskResource;
import com.lemsun.task.service.ITaskService;
import com.lemsun.web.manager.controller.model.query.TaskMutiQuery;
import com.lemsun.web.model.ResponseEntity;
import com.lemsun.web.model.view.TaskItemView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 任务实例
 * User: 刘晓宝
 * Date: 13-9-10
 * Time: 下午5:22
 */
@Controller
@RequestMapping("task/instance")
public class TaskInstanceControl {
    @Autowired
    private ITaskService taskService;
    /**
     * 显示用户统计视图
     */
    @RequestMapping("view")
    public ModelAndView view(HttpServletRequest request) throws IOException {
        ModelAndView view = new ModelAndView("task/view");
        request.getSession().setAttribute("head",4);
        request.getSession().setAttribute("left",1);
        return view;
    }
    /**
     * 某一个任务统计
     */
    @RequestMapping(value = "{pid}/taskStatistics", method = RequestMethod.GET)
    public ModelAndView taskStatistics(HttpServletRequest request,@PathVariable String pid) throws IOException {
        ModelAndView view = new ModelAndView("task/taskStatistics");

        return view;
    }
    /**
     * 获取账号数据
     */
    @RequestMapping("list/get")
    public ResponseEntity getTaskList(TaskMutiQuery query) {
        Page<TaskResource> data= taskService.getPageing(query);
        ResponseEntity<Page<TaskResource>> entity = new ResponseEntity<>(data);
        entity.setTotalCount(data.getTotalElements());
        return entity;
    }
    /**
     * 立即启动任务
     */
    @RequestMapping(value = "{pid}/excute")
    public ResponseEntity<String> immediateExecution(@PathVariable String pid){
        ResponseEntity<String> responseEntity=new ResponseEntity<>("");
         try{
             taskService.immediateExecution(pid);
             responseEntity.setSuccess(true);
        }catch (Exception e){
             responseEntity.setSuccess(false);
             responseEntity.setMessage("启动失败");
         }
        return responseEntity;
    }
    /**
     * 立即启动任务
     */
    @RequestMapping(value = "{pid}/udateTaskState/{state}")
    public ResponseEntity<String> udateTaskState(@PathVariable String pid,@PathVariable int state){
        ResponseEntity<String> responseEntity=new ResponseEntity<>("");
        try{
            taskService.mangerTaskState(pid,state);
            responseEntity.setSuccess(true);
        }catch (Exception e){
            responseEntity.setSuccess(false);
            responseEntity.setMessage("任务状态更新失败");
        }
        return responseEntity;
    }
}
