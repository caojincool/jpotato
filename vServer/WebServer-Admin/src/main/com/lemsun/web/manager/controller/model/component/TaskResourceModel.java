package com.lemsun.web.manager.controller.model.component;

import com.lemsun.task.CronTime;
import com.lemsun.task.StartParam;
import com.lemsun.task.TaskResource;
import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: 刘晓宝
 * Date: 13-11-22
 * Time: 下午1:53
 */
public class TaskResourceModel extends LemsunResourceModel{
    /**
     * 表达式
     */
    private List<CronTime> cronTimes=new ArrayList<>();
    /**
     * 启动参数
     */
    private List<StartParam> startParams=new ArrayList<>();

    /**
     * 脚本正文
     */
    private String context;

    private String executeDate;
    private String executeTime;
    private int state;
    /**
     * 执行条件
     */
    private String condition;
    public List<CronTime> getCronTimes() {
        return cronTimes;
    }

    public void setCronTimes(List<CronTime> cronTimes) {
        this.cronTimes = cronTimes;
    }

    public String getContext() {
        return context;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(String executeDate) {
        this.executeDate = executeDate;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public void setContext(String context) {
        this.context = context;
    }


    public List<StartParam> getStartParams() {
        return startParams;
    }

    public void setStartParams(List<StartParam> startParams) {
        this.startParams = startParams;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * 封装一个已经存在的脚本组件
     */
    public void encapseResource(TaskResource resource) {

        resource.setStartParams(getStartParams());
        resource.setCondition(getCondition());
        resource.setContext(getContext());
        resource.setCronTimes(getCronTimes());
        resource.setExecuteDate(getExecuteDate());
        resource.setExecuteTime(getExecuteTime());
        resource.setState(getState());
    }
}
