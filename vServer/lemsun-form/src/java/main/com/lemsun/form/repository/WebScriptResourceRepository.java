package com.lemsun.form.repository;

import com.lemsun.core.repository.AbstractRepositorySupport;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.form.WebScriptResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-29
 * Time: 下午1:40
 */
@Repository
public class WebScriptResourceRepository extends AbstractRepositorySupport<WebScriptResource> {

    @Autowired
    protected WebScriptResourceRepository(MongoTemplate template, GridFsOperations gridFsOperations, ResourceRepository resourceRepository) {
        super(template, gridFsOperations, resourceRepository);
    }
}
