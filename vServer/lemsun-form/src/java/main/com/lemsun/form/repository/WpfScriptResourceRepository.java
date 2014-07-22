package com.lemsun.form.repository;

import com.lemsun.core.repository.AbstractRepositorySupport;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.form.WpfScriptResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

/**
 * WPF 平台资源, 数据操作层
 * User: Xudong
 * Date: 12-11-5
 * Time: 下午1:51
 */
@Repository
public class WpfScriptResourceRepository extends AbstractRepositorySupport<WpfScriptResource> {


    @Autowired
    protected WpfScriptResourceRepository(MongoTemplate template, GridFsOperations gridFsOperations, ResourceRepository resourceRepository) {
        super(template, gridFsOperations, resourceRepository);
    }
}
