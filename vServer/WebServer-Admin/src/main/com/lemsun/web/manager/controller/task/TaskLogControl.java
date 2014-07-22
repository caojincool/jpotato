package com.lemsun.web.manager.controller.task;

import com.lemsun.task.TaskLog;
import com.lemsun.task.TaskResource;
import com.lemsun.task.service.ITaskLogService;
import com.lemsun.web.manager.controller.model.query.TaskLogQuery;
import com.lemsun.web.manager.controller.model.query.TaskMutiQuery;
import com.lemsun.web.model.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 任务日志
 * User: 刘晓宝
 * Date: 13-9-18
 * Time: 上午9:40
 */
@Controller
@RequestMapping("task/log")
public class TaskLogControl {
    @Autowired
    private ITaskLogService taskLogService;
    /**
     * 显示用户统计视图
     */
    @RequestMapping("view")
    public ModelAndView view(HttpServletRequest request) throws IOException {
        ModelAndView view = new ModelAndView("task/log/view");
        request.getSession().setAttribute("head",4);
        request.getSession().setAttribute("left",3);
        return view;
    }
    /**
     * 获取账号数据
     */
    @RequestMapping("list/get")
    public ResponseEntity getTaskLogList(TaskLogQuery query) {
        Page<TaskLog> data= taskLogService.getPageing(query);
        ResponseEntity<Page<TaskLog>> entity = new ResponseEntity<>(data);
        entity.setTotalCount(data.getTotalElements());
        return entity;
    }
}
