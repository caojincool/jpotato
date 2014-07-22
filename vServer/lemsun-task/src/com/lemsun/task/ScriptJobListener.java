package com.lemsun.task;

import com.lemsun.task.repository.TaskLogRepository;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * User: 刘晓宝
 * Date: 4/1/14
 * Time: 11:03 AM
 */
public class ScriptJobListener implements TriggerListener {

    private TaskTimeOutManager taskTimeOutManager;
    private TaskLogRepository taskLogRepository;
    private String name;
    private final Logger log = LoggerFactory.getLogger(ScriptJobListener.class);

    public ScriptJobListener(TaskTimeOutManager taskTimeOutManager,String name,TaskLogRepository taskLogRepository) {
        this.taskTimeOutManager = taskTimeOutManager;
        this.name=name;
        this.taskLogRepository=taskLogRepository;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * 任务开始执行调用
     * @param trigger
     * @param context
     */
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {        JobDetail job = context.getJobDetail();
        if(log.isInfoEnabled()){
            log.info("开始执行"+job.getKey());
        }
        JobDataMap jobDataMap = job.getJobDataMap();
        TaskLog taskLog=new TaskLog();
        TaskResource taskResource =(TaskResource)jobDataMap.get("taskResource");
        taskLog.setProps(taskResource);
        String lid=taskLogRepository.save(taskLog);
        jobDataMap.put("startTime",new Date().getTime());
        jobDataMap.put("currThread", Thread.currentThread());
        jobDataMap.put("lid",lid);
        taskTimeOutManager.getTasks().add(job);
        taskTimeOutManager.setPaused(false);
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    /**
     * 任务执行完成调用
     * @param trigger
     * @param context
     * @param triggerInstructionCode
     */
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        JobDetail job = context.getJobDetail();
        JobDataMap jobDataMap = job.getJobDataMap();
        String lid =(String)jobDataMap.get("lid");
        TaskLog taskLog=taskLogRepository.getByLid(lid);
        int flag=TaskLog.ERROR;
        String message="";
        if(jobDataMap.containsKey("flag")){
             flag=(Integer)jobDataMap.get("flag");
             message=(String)jobDataMap.get("message");
        }else{
            message="出错了！！";
        }
        taskLog.setRemark(message);
        taskLog.setState(flag);
        taskLog.setEndTime(new Date());
        taskLog.setRunTime(taskLog.getEndTime().getTime()-taskLog.getStartTime().getTime());
        taskLogRepository.update(taskLog);
        taskTimeOutManager.getTasks().remove(job);
        if(log.isInfoEnabled()){
            log.info("结束执行"+job.getKey());
        }
    }
}
