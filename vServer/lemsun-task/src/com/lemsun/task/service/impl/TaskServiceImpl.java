package com.lemsun.task.service.impl;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.Host;
import com.lemsun.task.*;
import com.lemsun.task.repository.TaskLogRepository;
import com.lemsun.task.repository.TaskRepository;
import com.lemsun.task.service.ITaskService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.NameMatcher;
import org.quartz.impl.matchers.StringMatcher;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: 宗旭东
 * Date: 14-3-11
 * Time: 上午10:23
 */
@Service()
public class TaskServiceImpl implements ITaskService ,InitializingBean ,DisposableBean {

    private TaskRepository taskRepository;
    private Scheduler scheduler;
    private TaskLogRepository taskLogRepository;
    private TaskTimeOutManager taskTimeOutManager;
    private Thread thread=null;
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository,TaskLogRepository taskLogRepository) {
        this.taskRepository = taskRepository;
        this.taskLogRepository=taskLogRepository;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
//        SchedulerFactory sf = new StdSchedulerFactory();
//        scheduler= sf.getScheduler();
//        List<TaskResource> tasks=taskRepository.getAllReadyTasks();
//        taskTimeOutManager=TaskTimeOutManager.singleton;
//        thread=new Thread(taskTimeOutManager,"线程管理");
//        thread.setDaemon(true);
//        thread.start();
//        for(TaskResource taskResource:tasks){
//            try{
//                addJob(taskResource);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            scheduler.start();

//        }
    }
    @Override
    public void destroy() throws Exception {
        scheduler.shutdown(true);
        taskTimeOutManager.setClose(true);
        taskTimeOutManager=null;
        scheduler=null;
    }
    @Override
    public void update(TaskResource taskResource) {
        taskRepository.update(taskResource);
        if(taskResource.getState() == TaskResource.ENABLED||taskResource.getState() == TaskResource.RESUME){
            addJob(taskResource);
        }else{
            delJob(taskResource);
       }
    }

    @Override
    public void delete(String pid) {
        TaskResource taskResource=get(pid);
        taskRepository.remove(pid);
        if(taskResource.getState() == 1){
            delJob(taskResource);
        }
    }
    @Override
    public TaskResource get(String pid) {
        return taskRepository.get(pid);
    }

    @Override
    public Page<TaskResource> getPageing(AbstractPageRequest query) {
        return taskRepository.getPagging(query);
    }

    @Override
    public void mangerTaskState(String pid, int state) {
        TaskResource taskResource=get(pid);
        taskResource.setState(state);
        taskRepository.update(taskResource);
        switch (state){
            case 1:
                delJob(taskResource);break;
            case 2:
            case 8:
                addJob(taskResource);break;
            case 4:
                delJob(taskResource);break;
        }
    }


    @Override
    public void immediateExecution(String pid2) {
        TaskResource taskResource=get(pid2);
        try {
            String pid=taskResource.getPid();
            String name=taskResource.getName();
            scheduler.deleteJob(getJobKey( pid));
            JobDetail job = JobBuilder.newJob(ScriptJob.class)
                    .withIdentity("job_" + pid)
                    .usingJobData("pid", pid)
                    .usingJobData("timeout", new Long(taskResource.getTimeOut()))
                    .requestRecovery().build();
            int count=1;
            Set<Trigger> set=new HashSet<>();

            ScriptJobListener triggerListener=new ScriptJobListener(taskTimeOutManager,taskResource.getName(),taskLogRepository);
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(pid+"=="+count, pid+"_"+name)
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.repeatMinutelyForever().withRepeatCount(1))
                    .build();
            set.add(trigger);
            scheduler.getListenerManager().addTriggerListener(triggerListener, new KeyMatcher(pid));
            scheduler.scheduleJob(job, set,true);
        }catch (Exception e){
            throw new TaskException(e.getMessage());
        }
    }

    /**
     * 更新任务
     * @param taskResource
     */
    private void addJob(TaskResource taskResource) {
        try {
            String pid=taskResource.getPid();
            String name=taskResource.getName();
            scheduler.deleteJob(getJobKey( pid));
            JobDataMap jobDataMap=new JobDataMap();
            jobDataMap.put("taskResource",taskResource);
            jobDataMap.put("timeout",new Long(taskResource.getTimeOut()));
            jobDataMap.put("taskResource",taskResource);
            JobDetail job = JobBuilder.newJob(ScriptJob.class)
                    .withIdentity("job_" + pid)
                    .usingJobData("pid", pid)
                    .usingJobData(jobDataMap)
                    .requestRecovery().build();
            int count=1;
            Set<Trigger> set=new HashSet<>();

            ScriptJobListener triggerListener=new ScriptJobListener(taskTimeOutManager,taskResource.getName(),taskLogRepository);
            for(CronTime cronExp:taskResource.getCronTimes()){

                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(pid+"=="+count, pid+"_"+name)
                        .startNow()
                        .withSchedule(CronScheduleBuilder.cronSchedule(cronExp.getCronExpression()))
                        .build();
                set.add(trigger);

            }
            scheduler.getListenerManager().addTriggerListener(triggerListener, new KeyMatcher(pid));
            scheduler.scheduleJob(job, set,true);
        }catch (Exception e){
            throw new TaskException(e.getMessage());
        }
    }

    /**
     * 删除任务
     * @param taskResource
     */
    private void delJob(TaskResource taskResource) {
        try {
            String pid=taskResource.getPid();
            scheduler.deleteJob(getJobKey( pid));
        }catch (SchedulerException e){
            throw new TaskException(e.getMessage());
        }
    }

    /**
     * 触发器可以匹配器
     */
    class KeyMatcher extends NameMatcher{
        KeyMatcher(String compareTo) {
            super(compareTo,  StringMatcher.StringOperatorName.STARTS_WITH);
        }
    }
    public JobKey getJobKey(String pid){
        return new JobKey("JOB_" + pid);
    }
}
