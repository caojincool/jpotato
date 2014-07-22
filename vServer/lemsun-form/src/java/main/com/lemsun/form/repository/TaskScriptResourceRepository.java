package com.lemsun.form.repository;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.repository.AbstractLocalRepository;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.form.TaskScriptResource;
import com.lemsun.form.WpfScriptResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WPF 平台资源, 数据操作层
 * User: Xudong
 * Date: 12-11-5
 * Time: 下午1:51
 */
@Repository
public class TaskScriptResourceRepository extends AbstractLocalRepository {

    private ResourceRepository resourceRepository;

    @Autowired
    public TaskScriptResourceRepository(ResourceRepository resourceRepository,
                                        MongoTemplate template) {
        super(template);
        this.resourceRepository = resourceRepository;
    }

    /**
     * 获取全部的网页函数脚本
     *
     * @return 脚本集合
     */
    public List<TaskScriptResource> getAll() {
        return resourceRepository.getAll(TaskScriptResource.class);
    }


    public List<TaskScriptResource> getAll(String parentPid) {
        return resourceRepository.getAll(TaskScriptResource.class, parentPid);
    }
    public void remove(String pid){
        resourceRepository.remove(pid);
    }
    public Page<TaskScriptResource> getPageing(AbstractPageRequest query){
        return resourceRepository.getPagging(query,TaskScriptResource.class);
    }
}
