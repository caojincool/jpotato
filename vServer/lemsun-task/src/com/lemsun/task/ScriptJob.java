package com.lemsun.task;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 执行脚本任务类
 * User: 刘晓宝
 * Date: 14-3-17
 * Time: 下午3:17
 */
public class ScriptJob extends AbstractTaskJob {
    private final Logger log = LoggerFactory.getLogger(ScriptJob.class);

    @Override
    public void executeJob(JobExecutionContext context) throws  JobExecutionException {
        Thread thread=Thread.currentThread();
        JobDataMap jobDataMap=context.getJobDetail().getJobDataMap();
        try{
               if(log.isInfoEnabled()){
                   log.info("进入执行任务方法");
               }
               thread.sleep(180000);
               jobDataMap.put("flag",TaskLog.SUCESS);
               jobDataMap.put("message","操作成功");

        }catch (InterruptedException e){

            jobDataMap.put("message","任务执行超过规定时间了！");
            jobDataMap.put("flag",TaskLog.TIMEOUT);
           throw new JobExecutionException(e);

        }
       if(log.isInfoEnabled()){
            log.info("结束执行任务方法");
        }
    }
}
