package com.lemsun.form.repository;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.ResourceAttach;
import com.lemsun.core.repository.AbstractLocalFsRepository;
import com.lemsun.core.repository.ResourceRepository;

import com.lemsun.form.WpfPageResource;
import com.mongodb.gridfs.GridFSDBFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;


import java.util.List;



/**
 * wfp组件的数据库操作对象
 * User: Xudong
 * Date: 12-11-6
 * Time: 下午3:06
 */
@Repository
public class WpfPageResourceRepository extends AbstractLocalFsRepository {

    private static final Logger log = LoggerFactory.getLogger(FormResourceRepository.class);
    private GridFsOperations gridFsOperations;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    public WpfPageResourceRepository(MongoTemplate template,
                                     GridFsOperations gridFsOperations) {
        super(template, gridFsOperations);
        this.gridFsOperations = gridFsOperations;
    }
    /**
     * 根据组件编码获取该组件的所有附件信息
     * 例如:附件名称,类型,大小
     */
    public List<ResourceAttach> getResouceAttaches(String pid) {

        return getResourceAttaches(pid,super.ATTACH);
    }


    public void remove(String pid) {
        resourceRepository.remove(pid);
    }
    public Page<WpfPageResource> getPageing(AbstractPageRequest query){
        return resourceRepository.getPagging(query,WpfPageResource.class);
    }
}
