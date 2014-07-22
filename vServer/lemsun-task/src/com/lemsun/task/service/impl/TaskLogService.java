package com.lemsun.task.service.impl;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.task.TaskLog;
import com.lemsun.task.repository.TaskLogRepository;
import com.lemsun.task.service.ITaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * User: 刘晓宝
 * Date: 4/9/14
 * Time: 9:10 AM
 */
@Service()
public class TaskLogService implements ITaskLogService {
    private TaskLogRepository taskLogRepository;
    @Autowired
    public TaskLogService(TaskLogRepository taskLogRepository) {
        this.taskLogRepository = taskLogRepository;
    }

    @Override
    public Page<TaskLog> getPageing(AbstractPageRequest query) {
        return taskLogRepository.getPagging(query);
    }
}
