package com.lemsun.reporter.repository;

import com.lemsun.core.LemsunResource;
import com.lemsun.core.repository.AbstractLocalFsRepository;
import com.lemsun.core.repository.AbstractRepositorySupport;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.reporter.ReporterResource;
import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

import java.io.InputStream;

import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;

/**
 * 填报组件的持久化对象
 * Created by dpyang on 2014/5/23.
 */
@Repository
public class ReporterResourceRepository extends AbstractRepositorySupport<ReporterResource> {



    @Autowired
    public ReporterResourceRepository(MongoTemplate template,
                                         GridFsOperations gridFsOperations,
                                         ResourceRepository resourceRepository) {
        super(template, gridFsOperations, resourceRepository);
    }


    public void uploadReporter(LemsunResource resource,InputStream stream, String filename){
        //删除原来文件
        getResourceRepository().getAttachFileRepository().updateContent(resource.getPid(), stream, filename);
    }

    public GridFSDBFile getReporterFile(ReporterResource resource){
        return getResourceRepository().getAttachFileRepository().getContentFile(resource.getPid());
    }
}
