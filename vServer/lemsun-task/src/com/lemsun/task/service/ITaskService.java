package com.lemsun.task.service;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.task.TaskResource;
import org.quartz.SchedulerException;
import org.springframework.data.domain.Page;

/**
 * 任务管理服务
 * User: 宗旭东
 * Date: 14-3-11
 * Time: 上午10:20
 */
public interface ITaskService {

    /**
     * 根据组件pid获取任务
     * @param pid
     * @return
     */
    TaskResource get(String pid);

    /**
     * 修改任务
     * @param taskResource
     */
    void update(TaskResource taskResource);

    /**
     * 删除任务
     * @param pid 任务pid
     */
    void delete(String pid);

    /**
     * 查询获取分页的组件模型
     */
   Page<TaskResource> getPageing(AbstractPageRequest query);

    /**
     * 任务状态更新
     * @param pid
     */
    void  mangerTaskState(String pid,int state);

    /**
     * 立即执行
     * @param pid
     */
    void immediateExecution(String pid);

}
