package com.lemsun.task.service;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.task.TaskLog;

import org.springframework.data.domain.Page;

/**
 * User: 刘晓宝
 * Date: 4/9/14
 * Time: 9:09 AM
 */
public interface ITaskLogService {
    /**
     * 查询获取分页的计划任务日志
     */
    Page<TaskLog> getPageing(AbstractPageRequest query);
}
