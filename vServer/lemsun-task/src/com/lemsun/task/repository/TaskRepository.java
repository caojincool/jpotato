package com.lemsun.task.repository;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.IResource;
import com.lemsun.core.repository.AbstractLocalFsRepository;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.task.TaskResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: 宗旭东
 * Date: 14-3-11
 * Time: 上午10:22
 */
@Repository
public class TaskRepository extends AbstractLocalFsRepository {
    private ResourceRepository resourceRepository;
    @Autowired
    public TaskRepository(MongoTemplate template,
                          GridFsOperations gridFsOperations,
                          ResourceRepository resourceRepository) {
        super(template, gridFsOperations);
        this.resourceRepository=resourceRepository;
    }
    public void add(TaskResource taskResource) throws Exception {
        resourceRepository.create(taskResource);
    }
    public void update(TaskResource taskResource){
        resourceRepository.update(taskResource);
    }

    /**
     * 获取已启动任务
     * @return
     */
    public List<TaskResource> getAllReadyTasks(){
        Query query=new Query();
        query.addCriteria(Criteria.where("state").is(1));
        return getTemplate().find(query,TaskResource.class,ResourceRepository.ResourceCollectionName);
    }
    public TaskResource get(String pid){
        return (TaskResource)resourceRepository.get(pid);
    }
    public void remove(String pid) {
        resourceRepository.remove(pid);
    }
    public Page<TaskResource> getPagging(AbstractPageRequest query){
        return resourceRepository.getPagging(query,TaskResource.class);
    }
}
