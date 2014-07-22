package com.lemsun.task;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * User: 刘晓宝
 * Date: 4/1/14
 * Time: 10:15 AM
 */

public class TaskTimeOutManager  implements   Runnable {
    private final Logger log = LoggerFactory.getLogger(TaskTimeOutManager.class);

    private ConcurrentLinkedQueue<JobDetail> tasks=new ConcurrentLinkedQueue<>() ;
    public static TaskTimeOutManager  singleton= new TaskTimeOutManager();

    private TaskTimeOutManager() {

    }

    protected volatile boolean paused = true;
    protected volatile boolean close = false;
    public ConcurrentLinkedQueue<JobDetail> getTasks() {
        return tasks;
    }
    public void setTasks(ConcurrentLinkedQueue<JobDetail> tasks) {
        this.tasks = tasks;
    }

    public boolean isPaused() {
        return paused;
    }
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    @Override
    public void run() {
        while(true){
            while(paused||tasks.size()==0){
                try {
                    if(close){
                        return;
                    }
                    Thread.currentThread().sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            for (JobDetail job:tasks){

                JobDataMap jobDataMap=job.getJobDataMap();
                long now=System.currentTimeMillis();
                Thread thread=(Thread)jobDataMap.get("currThread");
                long startTime=(Long)jobDataMap.get("startTime");
                long timeout=(Long)jobDataMap.get("timeout");
                long runningTime=now-startTime;
                if(runningTime>timeout){
                    if(log.isInfoEnabled()){
                        log.info("超时终止执行任务："+job.getKey());
                    }
                    if(thread.isAlive()){
                        thread.interrupt();
                    }
                    tasks.remove(job);
                }
            }

        }
    }
}
