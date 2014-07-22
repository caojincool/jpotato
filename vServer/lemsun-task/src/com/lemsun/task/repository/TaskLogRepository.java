package com.lemsun.task.repository;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.repository.AbstractLocalRepository;
import com.lemsun.core.repository.ResourcePrimaryRepository;
import com.lemsun.task.TaskLog;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: 刘晓宝
 * Date: 4/9/14
 * Time: 8:50 AM
 */
@Repository
public class TaskLogRepository extends AbstractLocalRepository {
    public static final String TASKLOGCollectionName = "TaskLog";
    private ResourcePrimaryRepository primaryRepository;
    @Autowired
    public TaskLogRepository(MongoTemplate template,ResourcePrimaryRepository primaryRepository) {
        super(template);
        this.primaryRepository=primaryRepository;
    }

    /**
     * 保存日志
     * @param taskLog
     */
    public String save(TaskLog taskLog ){
        String lid=primaryRepository.generatorTaskLog();
        taskLog.setLid(lid);
        super.getTemplate().insert(taskLog, TASKLOGCollectionName);
        return lid;
    }

    /**
     * 获取日志对象
     * @param lid
     * @return
     */
    public TaskLog getByLid(String lid){
        Query query=new Query();
        query.addCriteria(Criteria.where("lid").is(lid));
       return super.getTemplate().findOne(query,TaskLog.class,TASKLOGCollectionName);
    }
    /**
     * 更新日志
     * @param taskLog
     */
    public void update(TaskLog taskLog ){
        super.getTemplate().save(taskLog, TASKLOGCollectionName);
    }

    /**
     * 任务日志分页查询
     * @param query
     * @return
     */
    public Page<TaskLog> getPagging(AbstractPageRequest query) {
        Query dbQuery = query.createQuery();
        long total = getTemplate().count(dbQuery, TASKLOGCollectionName);
        List<TaskLog> data = getTemplate().find(dbQuery, TaskLog.class, TASKLOGCollectionName);
        return new PageImpl<>(data, query, total);
    }
}
