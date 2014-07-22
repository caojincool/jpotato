package com.lemsun.form.repository;

import com.lemsun.core.formula.IFunctionStatement;

import com.lemsun.core.repository.AbstractLocalRepository;
import com.lemsun.form.InnerFunctionDefines;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * User: 刘晓宝
 * Date: 13-11-13
 * Time: 上午11:00
 */
@Repository
public class FunctionStatementRepository extends  AbstractLocalRepository{
    private static final Logger log = LoggerFactory.getLogger(FunctionStatementRepository.class);
    public static final String functionStatementCollectionName = "FunctionStatement";

    @Autowired
    public FunctionStatementRepository(MongoTemplate template) {
        super(template);
    }

    public void save(IFunctionStatement funtion){
        getTemplate().save(funtion, functionStatementCollectionName);
    }
    public void update(IFunctionStatement funtion){
        getTemplate().save(funtion,functionStatementCollectionName);
    }
    public void delete(String parentPid,String name){
        getTemplate().remove(query(Criteria.where("parentPid").is(parentPid).and("name").is(name)), functionStatementCollectionName);
    }
    public void remove(String parentPid){
        getTemplate().remove(query(Criteria.where("parentPid").is(parentPid)), functionStatementCollectionName);
    }

    public List<InnerFunctionDefines> queryForByPid(String parentpid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("parentPid").is(parentpid));
       // query.fields().exclude("context");
        return getTemplate().find(query,InnerFunctionDefines.class,functionStatementCollectionName);
    }


    public InnerFunctionDefines get(String parentPid,String name) {
        return getTemplate().findOne(query(Criteria.where("parentPid").is(parentPid).and("name").is(name)),InnerFunctionDefines.class,functionStatementCollectionName);
    }
}
