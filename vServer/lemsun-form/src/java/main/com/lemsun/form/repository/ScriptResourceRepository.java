package com.lemsun.form.repository;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.formula.IFunctionStatement;
import com.lemsun.core.repository.AbstractLocalRepository;
import com.lemsun.core.repository.AbstractRepositorySupport;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.form.ScriptResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * 全局角本操作
 * User: Lucklim
 * Date: 13-1-28
 * Time: 上午11:03
 */
@Repository
public class ScriptResourceRepository extends AbstractRepositorySupport<ScriptResource> {

    @Autowired
    protected ScriptResourceRepository(MongoTemplate template, GridFsOperations gridFsOperations, ResourceRepository resourceRepository) {
        super(template, gridFsOperations, resourceRepository);
    }
}
