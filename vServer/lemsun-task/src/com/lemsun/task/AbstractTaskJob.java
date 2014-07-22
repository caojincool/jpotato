package com.lemsun.task;

import com.lemsun.core.Host;
import com.lemsun.core.service.IScriptEngine;
import com.lemsun.task.service.ITaskService;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.Timer;

/**
 * User: 宗旭东
 * Date: 14-4-1
 * Time: 上午8:56
 */
public abstract class AbstractTaskJob implements Job {

    private ITaskService taskService;

    private IScriptEngine engine;

    public ITaskService getTaskService() {
        return taskService;
    }

    public IScriptEngine getEngine() {
        return engine;
    }

    /**
     * 任务执行调用该方法
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDetail detail = context.getJobDetail();
        taskService =(ITaskService)detail.getJobDataMap().get("ITaskService");
        engine = (IScriptEngine)detail.getJobDataMap().get("ITaskService");

        executeJob( context);
    }

    public abstract void executeJob(JobExecutionContext context) throws JobExecutionException;
}
