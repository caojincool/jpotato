package com.lemsun.task;

import com.lemsun.core.LemsunResource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 任务组件. 定义了用于服务器按照时间, 或者条件执行的函数
 * User: 宗旭东
 * Date: 14-3-11
 * Time: 上午10:01
 */
public class TaskResource extends LemsunResource {

    public static final int  CREATE=1;

    public static final int  ENABLED=2;

    public static final int  PAUSE=4;

    public static final int  RESUME=8;

    public static final int  RUNNING=16;
    /**
     * 表达式
     */
    private List<CronTime> cronTimes=new ArrayList<>();
    /**
     * 启动参数
     */
    private List<StartParam> startParams=new ArrayList<>();
    /**
     * 执行时间
     */
    private String executeTime;
    /**
     * 执行时间
     */
    private String executeDate;

    /**
     * 脚本正文
     */
    private String context;

    /**
     * 超时时间
     */
    private int timeOut;

    public TaskResource(){

    }

    public TaskResource(LemsunResource resource) {
        super(resource.getName(), resource.getCategory());
        setId(resource.getId());
        setPid(resource.getPid());
        setRemark(resource.getRemark());
        setStrParams(resource.getStrParams());
        setCreateUser(resource.getCreateUser());
        setUpdateTime(resource.getUpdateTime());
        setSystem(resource.isSystem());
        setBusinessCode(resource.getBusinessCode());
    }
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

    public List<StartParam> getStartParams() {
        return startParams;
    }

    public void setStartParams(List<StartParam> startParams) {
        this.startParams = startParams;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(String executeDate) {
        this.executeDate = executeDate;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
